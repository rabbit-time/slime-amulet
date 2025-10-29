package click.rascal.slimeAmulet.client

import click.rascal.slimeAmulet.client.model.SlimeAmuletPredicateProvider
import click.rascal.slimeAmulet.client.networking.ClientNetworkingHandler

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

@Environment(EnvType.CLIENT)
class SlimeAmuletClient : ClientModInitializer {
    override fun onInitializeClient() {
        SlimeAmuletPredicateProvider.register()
        ClientNetworkingHandler.init()
    }
}