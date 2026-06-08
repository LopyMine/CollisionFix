package net.lopymine.collisionfix.yacl;

import dev.isxander.yacl3.api.*;
import lombok.experimental.ExtensionMethod;

import java.util.*;
import net.lopymine.collisionfix.CollisionFix;
import net.lopymine.collisionfix.config.CollisionFixConfig;
import net.lopymine.collisionfix.utils.EntityId;
import net.lopymine.mossylib.yacl.api.*;
import net.lopymine.mossylib.yacl.extension.SimpleOptionExtension;
import net.minecraft.core.registries.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.*;
import net.minecraft.world.entity.EntityType;

@ExtensionMethod(SimpleOptionExtension.class)
public class EntitiesCategory {

	public static OptionGroup get(CollisionFixConfig defConfig, CollisionFixConfig config) {
		HashSet<Identifier> defCollideEntities = defConfig.getEntityList();
		HashSet<Identifier> collideEntities = config.getEntityList();

		var list = new ArrayList<>(BuiltInRegistries.ENTITY_TYPE.entrySet());
		list.sort((a, b) -> {
			Identifier idA = a.getKey().identifier();
			Identifier idB = b.getKey().identifier();
			return idA.compareTo(idB);
		});

		SimpleGroup simpleCategory = SimpleGroup.startBuilder("entity_list");

		for (var entry : list) {
			Identifier id = entry.getKey().identifier();
			EntityType<?> entityType = entry.getValue();

			addEntityOption(defCollideEntities, collideEntities, id, entityType.getDescription(), simpleCategory);
		}

		return simpleCategory.build(CollisionFix.MOD_ID);
	}

	private static void addEntityOption(HashSet<Identifier> defCollideEntities, HashSet<Identifier> collideEntities, Identifier id, Component name, SimpleGroup simpleCategory) {
		addEntityOption(defCollideEntities, collideEntities, EntityId.of(id, name), simpleCategory);
	}

	private static void addEntityOption(HashSet<Identifier> defCollideEntities, HashSet<Identifier> collideEntities, EntityId entityId, SimpleGroup simpleGroup) {
		Identifier identifier = entityId.id();

		Option<Boolean> option = SimpleOption.<Boolean>startBuilder("entity")
				.withBinding(!defCollideEntities.contains(identifier), () -> !collideEntities.contains(identifier), (bl) -> {
					if (!bl) {
						collideEntities.add(identifier);
					} else {
						collideEntities.remove(identifier);
					}
				}, true)
				.withController()
				.getOptionBuilder()
				.name(CollisionFix.text("modmenu.option.entity.name", entityId.name()))
				.description(
						OptionDescription.createBuilder()
							.text(CollisionFix.text("modmenu.option.entity.description", entityId.name()))
							.build()
				)
				.build();

		simpleGroup.options(option);
	}

}
