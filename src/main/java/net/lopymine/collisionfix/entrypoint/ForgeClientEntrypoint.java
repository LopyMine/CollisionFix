package net.lopymine.collisionfix.entrypoint;

//? if forge {

/*import net.lopymine.collisionfix.CollisionFix;
import net.lopymine.collisionfix.modmenu.ModMenuIntegration;
import net.minecraftforge.fml.ModLoadingContext;

public class ForgeClientEntrypoint {

	public static void onInitializeClient() {
		CollisionFix.onInitializeClient();
		ModMenuIntegration integration = new ModMenuIntegration();
		integration.register(ModLoadingContext.get().getActiveContainer());
	}

}

*///?}

