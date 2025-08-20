package net.cobra.moreores.block.data;

import net.cobra.moreores.MoreOresModInitializer;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record PolishingStateData(BlockPos pos, String action) implements CustomPayload {
    public static final Id<PolishingStateData> ID = new Id<>(MoreOresModInitializer.byId("polishing_state"));

    public static final PacketCodec<RegistryByteBuf, PolishingStateData> PACKET_CODEC = PacketCodec.of((value, buf) -> {
        buf.writeBlockPos(value.pos);
        buf.writeString(value.action);
    }, buf -> new PolishingStateData(buf.readBlockPos(), buf.readString()));

    @Override
    public Id<? extends CustomPayload> getId() {
        return null;
    }
}
