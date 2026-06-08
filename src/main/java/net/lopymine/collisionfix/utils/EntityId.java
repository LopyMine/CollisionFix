package net.lopymine.collisionfix.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public interface EntityId {

	Identifier id();

	Component name();

	static EntityId of(Identifier id, Component name) {
		return new EntityId() {
			@Override
			public Identifier id() {
				return id;
			}

			@Override
			public Component name() {
				return name;
			}
		};
	}

}
