package net.cobra.moreores.networking;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.data.GemPolisherButtonClick;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import static net.cobra.moreores.MoreOresModInitializer.LOGGER;

public class ModC2SNetworks {

    public static void registerServerC2S(){
        ServerPlayNetworking.registerGlobalReceiver(GemPolisherButtonClick.ID, GemPolisherButtonClick::handle);
    }

    public static void register() {
        LOGGER.info("Loading ModC2SNetworks for" + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
