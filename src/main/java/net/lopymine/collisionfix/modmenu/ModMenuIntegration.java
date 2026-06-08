package net.lopymine.collisionfix.modmenu;

import net.lopymine.collisionfix.CollisionFix;
import net.lopymine.collisionfix.yacl.YACLConfigurationScreen;
import net.lopymine.mossylib.modmenu.AbstractModMenuIntegration;
import net.minecraft.client.gui.screens.Screen;

public class ModMenuIntegration extends AbstractModMenuIntegration {

	@Override
	protected String getModId() {
		return CollisionFix.MOD_ID;
	}

	@Override
	protected Screen createConfigScreen(Screen screen) {
		return YACLConfigurationScreen.createScreen(screen);
	}
}
