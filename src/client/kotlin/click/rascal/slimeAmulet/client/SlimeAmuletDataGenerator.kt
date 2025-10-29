package click.rascal.slimeAmulet.client

import click.rascal.slimeAmulet.client.datagen.LanguageProviderEnUS
import click.rascal.slimeAmulet.client.datagen.RecipeProvider
import click.rascal.slimeAmulet.logger as logger

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator


class SlimeAmuletDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
        logger.info("Initializing data generators...")
        val pack: FabricDataGenerator.Pack = fabricDataGenerator.createPack()
        pack.addProvider(::LanguageProviderEnUS)
        pack.addProvider(::RecipeProvider)
    }
}

