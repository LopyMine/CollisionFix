package net.lopymine.collisionfix.manager;

import java.util.Objects;
import net.lopymine.collisionfix.config.CollisionFixConfig;
import net.lopymine.collisionfix.utils.MarkedEntityType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.*;
import org.jetbrains.annotations.Nullable;

public class EntityCollisionManager {

	private static boolean WAS_FIRST_BUMP = false;
	@Nullable
	private static ServerData LAST_DATA = null;
	@Nullable
	private static String LAST_IP = null;
	private static boolean LAST_SERVER_RESULT = false;

	public static boolean shouldCollide(Entity entity) {
		if (Minecraft.getInstance().player != null && entity == Minecraft.getInstance().player) {
			return false;
		}

		if (!CollisionFixConfig.getInstance().isModEnabled()) {
			return false;
		}

		if (!worksOnThisServer(false)) {
			return false;
		}

		if (!WAS_FIRST_BUMP) {
			bumpTypes();
			WAS_FIRST_BUMP = true;
		}

		return ((MarkedEntityType) entity.getType()).colisionFix$isMarked();
	}

	public static void bumpServer() {
		worksOnThisServer(true);
	}

	private static boolean worksOnThisServer(boolean bump) {
		CollisionFixConfig config = CollisionFixConfig.getInstance();

		ServerData currentServer = Minecraft.getInstance().getCurrentServer();
		if (currentServer == null) {
			LAST_DATA = null;
			LAST_IP = null;
			LAST_SERVER_RESULT = config.getServerListMode().isBlacklist() != config.getServersList().contains("localworld");
			return LAST_SERVER_RESULT;
		}

		String ip = currentServer.ip;
		if (!bump) {
			if (currentServer == LAST_DATA && Objects.equals(ip, LAST_IP)) {
				return LAST_SERVER_RESULT;
			}
		}

		boolean contains = config.getServersList().contains(ip);
		boolean blacklist = config.getServerListMode().isBlacklist();

		LAST_DATA = currentServer;
		LAST_IP = ip;
		LAST_SERVER_RESULT = blacklist != contains;

		return LAST_SERVER_RESULT;
	}

	public static void bumpTypes() {
		CollisionFixConfig config = CollisionFixConfig.getInstance();

		for (var entry : BuiltInRegistries.ENTITY_TYPE.entrySet()) {
			Identifier id = entry.getKey().identifier();
			EntityType<?> value = entry.getValue();
			boolean bl = config.getEntityList().contains(id);
			((MarkedEntityType) value).colisionFix$mark(bl);
		}
	}
}
