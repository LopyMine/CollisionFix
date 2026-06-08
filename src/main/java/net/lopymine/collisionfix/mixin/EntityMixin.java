package net.lopymine.collisionfix.mixin;

import net.lopymine.collisionfix.manager.EntityCollisionManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Shadow
	public abstract Level level();

	@Inject(method = "isInWall", at = @At("HEAD"), cancellable = true)
    private void isInsideWall(CallbackInfoReturnable<Boolean> cir) {
		Entity entity = (Entity) (Object) this;
		if (this.collisionFix$getCurrentWorld().isClientSide() && (EntityCollisionManager.shouldCollide(entity))) {
            cir.setReturnValue(false);
        }
    }

	@Unique
	private Level collisionFix$getCurrentWorld() {
		return this.level();
	}
}
