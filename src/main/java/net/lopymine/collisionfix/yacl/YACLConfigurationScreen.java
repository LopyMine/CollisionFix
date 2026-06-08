package net.lopymine.collisionfix.yacl;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import lombok.experimental.ExtensionMethod;
import net.lopymine.collisionfix.CollisionFix;
import net.lopymine.collisionfix.config.*;
import net.lopymine.collisionfix.manager.EntityCollisionManager;
import net.lopymine.mossylib.yacl.api.*;
import net.lopymine.mossylib.yacl.extension.SimpleOptionExtension;
import net.minecraft.client.gui.screens.Screen;

@ExtensionMethod(SimpleOptionExtension.class)
public class YACLConfigurationScreen {

	private YACLConfigurationScreen() {
		throw new IllegalStateException("Screen class");
	}

	public static Screen createScreen(Screen parent) {
		CollisionFixConfig defConfig = CollisionFixConfig.getNewInstance();
		CollisionFixConfig config = CollisionFixConfig.getInstance();

		Runnable runnable = () -> {
			EntityCollisionManager.bumpTypes();
			EntityCollisionManager.bumpServer();
			config.saveAsync();
		};

		return SimpleYACLScreen.startBuilder(CollisionFix.MOD_ID, parent, runnable)
				.categories(getGeneralCategory(defConfig, config))
				.build();
	}

	public static SimpleCategory getGeneralCategory(CollisionFixConfig defConfig, CollisionFixConfig config) {
		return SimpleCategory.startBuilder("general")
				.groups(getMainGroup(defConfig, config))
				.groups(getServersListGroup(defConfig, config))
				.groups(EntitiesCategory.get(defConfig, config));
	}

	private static SimpleGroup getMainGroup(CollisionFixConfig defConfig, CollisionFixConfig config) {
		return SimpleGroup.startBuilder("main").options(
				SimpleOption.<Boolean>startBuilder("mod_enabled")
						.withBinding(defConfig.isModEnabled(), config::isModEnabled, config::setModEnabled, true)
						.withController()
						.withDescription(SimpleContent.NONE),
				SimpleOption.<ListMode>startBuilder("server_list_mode")
						.withBinding(defConfig.getServerListMode(), config::getServerListMode, (mode) -> {
							config.setServerListMode(mode);
							EntityCollisionManager.bumpServer();
						}, true)
						.withController(ListMode.class)
						.withDescription(SimpleContent.NONE)
		);
	}

	private static OptionGroup getServersListGroup(CollisionFixConfig defConfig, CollisionFixConfig config) {
		return (OptionGroup) SimpleOption.<String>startListBuilder("server_list")
					.withBinding(defConfig.getServersList(), config::getServersList, config::setServersList, true)
					.withDescription(SimpleContent.NONE)
					.custom((builder) -> {
						builder.initial("");
						builder.controller(StringControllerBuilder::create);
					}).build(CollisionFix.MOD_ID);
	}

}
