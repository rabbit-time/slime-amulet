package click.rascal.slimeAmulet

import click.rascal.slimeAmulet.item.SlimeAmuletItem
import click.rascal.slimeAmulet.networking.NetworkingHandler

import net.fabricmc.api.ModInitializer

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val logger: Logger = SlimeAmulet.logger

class SlimeAmulet : ModInitializer {
    companion object {
        const val MOD_ID: String = "slime-amulet"
        const val NAMESPACE: String = "rascal"
        val logger: Logger = LoggerFactory.getLogger(MOD_ID)
    }
    override fun onInitialize() {
        SlimeAmuletItem.init()
        NetworkingHandler.init()
    }
}
