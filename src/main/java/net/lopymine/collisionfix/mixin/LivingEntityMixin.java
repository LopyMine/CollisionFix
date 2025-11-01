package net.lopymine.collisionfix.mixin;

import net.minecraft.entity.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "isInsideWall", at = @At("HEAD"), cancellable = true)
    private void isInsideWall(CallbackInfoReturnable<Boolean> cir) {
	    if (this.getCurrentWorld().isClient()) {
            cir.setReturnValue(false);
        }
    }

	@Inject(method = "pushAway", at = @At("HEAD"), cancellable = true)
    private void pushAway(CallbackInfo ci) {
        if (this.getCurrentWorld().isClient()) {
            ci.cancel();
        }
    }

    @Inject(method = "pushAwayFrom", at = @At("HEAD"), cancellable = true)
    private void pushAwayFrom(CallbackInfo ci) {
        if (this.getCurrentWorld().isClient()) {
            ci.cancel();
        }
    }

	@Unique
	private World getCurrentWorld() {
		//? if >=1.21.9 {
		return this.getEntityWorld();
		//?} else {
		/*return this.getWorld();
		 *///?}
	}
}
