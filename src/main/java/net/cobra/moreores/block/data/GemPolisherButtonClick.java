package net.cobra.moreores.block.data;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.entity.GemPolisherBlockEntity;
import net.cobra.moreores.screen.GemPolisherScreenHandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public record GemPolisherButtonClick(int buttonID, BlockPos pos) implements CustomPayload {
    public static final Id<GemPolisherButtonClick> ID = new Id<>(MoreOresModInitializer.getId("button_click"));

    public static final PacketCodec<RegistryByteBuf, GemPolisherButtonClick> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.VAR_INT, GemPolisherButtonClick::buttonID,
                    BlockPos.PACKET_CODEC, GemPolisherButtonClick::pos,
                    GemPolisherButtonClick::new
            );

    public void handle(ServerPlayNetworking.Context context) {
        ServerWorld world = context.server().getOverworld();

        if(world.getBlockEntity(pos) instanceof GemPolisherBlockEntity gemPolisherBlockEntity) {
            switch (buttonID) {
                case 0 -> gemPolisherBlockEntity.startPolish();
                case 1 -> gemPolisherBlockEntity.pausePolish();
                case 2 -> gemPolisherBlockEntity.resumePolish();
                case 3 -> gemPolisherBlockEntity.stopPolish();
                }

                if(context.player().currentScreenHandler instanceof GemPolisherScreenHandler screenHandler && screenHandler.blockEntity.getPos().equals(pos)) {
                    switch (buttonID) {
                        case 0 -> gemPolisherBlockEntity.startPolish();
                        case 1 -> gemPolisherBlockEntity.pausePolish();
                        case 2 -> gemPolisherBlockEntity.resumePolish();
                        case 3 -> gemPolisherBlockEntity.stopPolish();
                    }
                }
            }

        MoreOresModInitializer.LOGGER.info("Received button click with ID: {} at {}", buttonID, pos);

        }


    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
