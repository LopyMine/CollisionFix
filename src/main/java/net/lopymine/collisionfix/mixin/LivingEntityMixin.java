package net.lopymine.collisionfix.mixin;

import net.lopymine.collisionfix.manager.EntityCollisionManager;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = "isInWall", at = @At("HEAD"), cancellable = true)
    private void isInsideWall(CallbackInfoReturnable<Boolean> cir) {
	    if (this.collisionFix$getCurrentWorld().isClientSide() && (EntityCollisionManager.shouldCollide(this))) {
            cir.setReturnValue(false);
        }
    }

	@Inject(method = "doPush", at = @At("HEAD"), cancellable = true)
    private void pushAway(CallbackInfo ci) {
		if (this.collisionFix$getCurrentWorld().isClientSide() && (EntityCollisionManager.shouldCollide(this))) {
            ci.cancel();
        }
    }

    @Inject(method = "push", at = @At("HEAD"), cancellable = true)
    private void pushAwayFrom(CallbackInfo ci) {
	    if (this.collisionFix$getCurrentWorld().isClientSide() && (EntityCollisionManager.shouldCollide(this))) {
            ci.cancel();
        }
    }

	@Unique
	private Level collisionFix$getCurrentWorld() {
		return this.level();
	}
}
