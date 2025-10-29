package click.rascal.slimeAmulet.networking

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.util.Identifier

object NetworkingHandler {
    val SEED_PACKET_ID: Identifier = Identifier("rascal", "seed")
    fun init() {
        ServerPlayConnectionEvents.JOIN.register { handler: ServerPlayNetworkHandler, _, server: MinecraftServer ->
            val player = handler.player
            val buf: PacketByteBuf = buildPacket(server)
            ServerPlayNetworking.send(player, SEED_PACKET_ID, buf)
        }
    }
    fun buildPacket(server: MinecraftServer): PacketByteBuf {
        val buf: PacketByteBuf = PacketByteBufs.create().also {
            it.writeLong(server.overworld.seed)
        }
        return buf
    }
}