package click.rascal.slimeAmulet.networking

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler

object NetworkingHandler {
    fun init() {
        PayloadTypeRegistry.playS2C().register(SeedPayload.ID, SeedPayload.CODEC)
        ServerPlayConnectionEvents.JOIN.register { handler: ServerPlayNetworkHandler, _, server: MinecraftServer ->
            ServerPlayNetworking.send(handler.player, SeedPayload(server.overworld.seed))
        }
    }
}