package net.lopymine.collisionfix.mixin;

import net.lopymine.collisionfix.utils.MarkedEntityType;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.*;

@Mixin(EntityType.class)
public class EntityTypeMixin implements MarkedEntityType {

	@Unique
	private boolean bl = false;

	@Override
	public void colisionFix$mark(boolean bl) {
		this.bl = bl;
	}

	@Override
	public boolean colisionFix$isMarked() {
		return this.bl;
	}
}
