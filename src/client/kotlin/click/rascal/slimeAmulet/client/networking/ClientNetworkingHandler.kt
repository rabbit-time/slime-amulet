package click.rascal.slimeAmulet.client.networking

import click.rascal.slimeAmulet.networking.SeedPayload
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.network.packet.CustomPayload

object ClientNetworkingHandler {
    var seed: Long? = null
    fun init() {
        ClientPlayNetworking.registerGlobalReceiver(
            SeedPayload.ID
        ) { payload: CustomPayload, _ ->
            seed = (payload as SeedPayload).seed
        }
    }
}