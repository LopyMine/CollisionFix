package net.lopymine.collisionfix;

import net.lopymine.mossylib.logger.MossyLogger;
import net.minecraft.network.chat.*;

public class CollisionFix {

	public static final String MOD_NAME = /*$ mod_name*/ "Collision Fix";
	public static final String MOD_ID = /*$ mod_id*/ "collisionfix";
	public static final MossyLogger LOGGER = new MossyLogger(MOD_NAME);

	public static MutableComponent text(String path, Object... args) {
		return Component.translatable(String.format("%s.%s", MOD_ID, path), args);
	}

	public static void onInitializeClient() {
		LOGGER.info("{} Initialized", MOD_NAME);
	}
}
