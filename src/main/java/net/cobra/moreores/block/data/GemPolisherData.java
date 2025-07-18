package net.cobra.moreores.block.data;

import net.cobra.moreores.MoreOresModInitializer;
import net.cobra.moreores.block.entity.GemPolisherBlockEntity;
import net.cobra.moreores.screen.GemPolisherScreenHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record GemPolisherData(long energy, BlockPos blockPos) implements CustomPayload {
    public static final Id<GemPolisherData> ID = new Id<>(MoreOresModInitializer.getId("pos_energy"));

    public void handlePacket(ClientPlayNetworking.Context context) {
        ClientWorld world = context.client().world;
        if (world == null) return;

        if (world.getBlockEntity(this.blockPos) instanceof GemPolisherBlockEntity blockEntity) {
            blockEntity.setEnergyLevel(this.energy);

            if (context.player().currentScreenHandler instanceof GemPolisherScreenHandler screenHandler && screenHandler.blockEntity.getPos().equals(this.blockPos)) {
                blockEntity.setEnergyLevel(this.energy);
            }
        }
    }

    public static final PacketCodec<RegistryByteBuf, GemPolisherData> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.LONG, GemPolisherData::energy,
                    BlockPos.PACKET_CODEC, GemPolisherData::blockPos,
                    GemPolisherData::new
            );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
