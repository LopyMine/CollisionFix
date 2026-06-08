package net.lopymine.collisionfix.config;

import com.mojang.serialization.Codec;
import java.util.Locale;
import net.lopymine.collisionfix.CollisionFix;
import net.lopymine.mossylib.yacl.utils.EnumWithText;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

public enum ListMode implements StringRepresentable, EnumWithText {

	WHITELIST,
	BLACKLIST;

	public static final Codec<ListMode> CODEC = StringRepresentable.fromEnum(ListMode::values);

	@Override
	public Component getText() {
		return CollisionFix.text("list_mode." + this.getSerializedName());
	}

	@Override
	public String getSerializedName() {
		return this.name().toLowerCase(Locale.ROOT);
	}

	public boolean isBlacklist() {
		return this == BLACKLIST;
	}
}
