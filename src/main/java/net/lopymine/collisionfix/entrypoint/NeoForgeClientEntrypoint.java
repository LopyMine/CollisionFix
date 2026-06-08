package net.lopymine.collisionfix.entrypoint;

//? if neoforge {

/*import net.lopymine.collisionfix.CollisionFix;
import net.lopymine.collisionfix.modmenu.ModMenuIntegration;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = CollisionFix.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeClientEntrypoint {

	public NeoForgeClientEntrypoint(ModContainer container) {
		CollisionFix.onInitializeClient();
		ModMenuIntegration integration = new ModMenuIntegration();
		integration.register(container);
	}

}

*///?}

