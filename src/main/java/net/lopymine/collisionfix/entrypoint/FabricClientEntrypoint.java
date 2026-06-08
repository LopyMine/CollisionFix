package net.lopymine.collisionfix.entrypoint;

//? if fabric {
import net.fabricmc.api.ClientModInitializer;
import net.lopymine.collisionfix.CollisionFix;

public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		CollisionFix.onInitializeClient();
	}
}

//?}
