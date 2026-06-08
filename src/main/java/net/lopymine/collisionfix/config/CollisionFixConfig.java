package net.lopymine.collisionfix.config;

import com.mojang.serialization.Codec;
import java.io.File;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import lombok.*;
import net.lopymine.collisionfix.CollisionFix;
import net.lopymine.mossylib.loader.MossyLoader;
import net.lopymine.mossylib.utils.*;
import net.minecraft.resources.Identifier;
import org.slf4j.*;
import static com.mojang.serialization.codecs.RecordCodecBuilder.create;
import static net.lopymine.mossylib.utils.CodecUtils.option;

@Getter
@Setter
@AllArgsConstructor
public class CollisionFixConfig {

	public static final Codec<CollisionFixConfig> CODEC = create((instance) -> instance.group(
			option("mod_enabled", true, Codec.BOOL, CollisionFixConfig::isModEnabled),
			option("entity_list", new HashSet<>(), Identifier.CODEC, CollisionFixConfig::getEntityList),
			option("server_list", new ArrayList<>(List.of("hypixel.net", "server_ip_here")), Codec.STRING.listOf(), CollisionFixConfig::getServersList),
			option("server_list_mode", ListMode.BLACKLIST, ListMode.CODEC, CollisionFixConfig::getServerListMode)
	).apply(instance, CollisionFixConfig::new));

	private static final File CONFIG_FILE = MossyLoader.getConfigDir().resolve(CollisionFix.MOD_ID + ".json5").toFile();
	private static final Logger LOGGER = LoggerFactory.getLogger(CollisionFix.MOD_NAME + "/Config");
	private static CollisionFixConfig INSTANCE;

	private boolean modEnabled;
	private HashSet<Identifier> entityList;
	private List<String> serversList;
	private ListMode serverListMode;

	private CollisionFixConfig() {
		throw new IllegalArgumentException();
	}

	public static CollisionFixConfig getInstance() {
		return INSTANCE == null ? reload() : INSTANCE;
	}

	public static CollisionFixConfig reload() {
		return INSTANCE = read();
	}

	public static CollisionFixConfig getNewInstance() {
		return CodecUtils.parseNewInstanceHacky(CODEC);
	}

	private static CollisionFixConfig read() {
		return ConfigUtils.readConfig(CODEC, CONFIG_FILE, LOGGER);
	}

	public void saveAsync() {
		CompletableFuture.runAsync(this::save);
	}

	public void save() {
		ConfigUtils.saveConfig(this, CODEC, CONFIG_FILE, LOGGER);
	}
}
