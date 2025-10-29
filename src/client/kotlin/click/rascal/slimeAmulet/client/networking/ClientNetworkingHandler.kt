package click.rascal.slimeAmulet.client.networking

import click.rascal.slimeAmulet.networking.NetworkingHandler

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.network.PacketByteBuf

object ClientNetworkingHandler {
    var seed: Long? = null
    fun init() {
        ClientPlayNetworking.registerGlobalReceiver(
            NetworkingHandler.SEED_PACKET_ID
        ) { _, _, buf: PacketByteBuf, _ ->
            val worldSeed: Long = buf.readLong()
            seed = worldSeed
        }
    }
}