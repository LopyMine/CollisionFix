package net.lopymine.collisionfix.entrypoint;

//? if forge {
/*import net.lopymine.collisionfix.CollisionFix;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(CollisionFix.MOD_ID)
public class ForgeEntrypoint {

	public ForgeEntrypoint() {
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ForgeClientEntrypoint::onInitializeClient);
	}

}

*///?}
