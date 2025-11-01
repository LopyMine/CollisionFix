package net.lopymine.collisionfix.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

	//? if >=1.21.9 {
	@Shadow
	public abstract World getEntityWorld();
	//?} else {
	/*@Shadow
	public abstract World getWorld();
	*///?}

    @Inject(method = "isInsideWall", at = @At("HEAD"), cancellable = true)
    private void isInsideWall(CallbackInfoReturnable<Boolean> cir) {
        if (this.getCurrentWorld().isClient()) {
            cir.setReturnValue(false);
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
