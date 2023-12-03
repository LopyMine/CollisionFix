package net.lopymine.collisionfix;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;

public class CollisionFix implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        LOGGER.info("Collision Fix Initialized");
    }
}
