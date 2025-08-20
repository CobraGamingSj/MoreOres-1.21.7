package net.cobra.moreores.networking;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.data.GemPolisherButtonClick;
import net.cobra.moreores.block.data.PolishingStateData;
import net.cobra.moreores.block.entity.GemPolisherBlockEntity;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.math.BlockPos;

import static net.cobra.moreores.MoreOresModInitializer.LOGGER;

public class ModC2SNetworks {

    public static void registerServerC2S(){
        ServerPlayNetworking.registerGlobalReceiver(GemPolisherButtonClick.ID, GemPolisherButtonClick::handle);
        ServerPlayNetworking.registerGlobalReceiver(PolishingStateData.ID, (payload, context) -> {
            BlockPos pos = payload.pos();
            String action = payload.action();

            context.server().execute(() -> {
                if(context.player().getWorld().getBlockEntity(pos) instanceof GemPolisherBlockEntity be) {
                    switch(action) {
                        case "start" -> be.startPolish();
                        case "pause" -> be.pausePolish();
                        case "resume" -> be.resumePolish();
                        case "stop" -> be.stopPolish();
                    }
                }
            });
        });
    }

    public static void register() {
        LOGGER.info("Loading ModC2SNetworks for" + MoreOresModInitializer.MOD_ID + " mod.");
    }
}
