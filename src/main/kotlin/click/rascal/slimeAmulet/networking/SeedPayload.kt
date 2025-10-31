package click.rascal.slimeAmulet.networking

import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.Identifier

data class SeedPayload(val seed: Long) : CustomPayload {
    companion object {
        val SEED_PACKET_ID: Identifier = Identifier("rascal", "seed")
        val ID: CustomPayload.Id<SeedPayload> = CustomPayload.Id(SEED_PACKET_ID)
        val CODEC: PacketCodec<RegistryByteBuf, SeedPayload> = PacketCodec.tuple(
            PacketCodecs.VAR_LONG,
            SeedPayload::seed,
            ::SeedPayload
        )
    }
    override fun getId(): CustomPayload.Id<out CustomPayload> = ID
}