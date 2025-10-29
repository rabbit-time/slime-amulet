package click.rascal.slimeAmulet.client.datagen

import click.rascal.slimeAmulet.SlimeAmulet
import click.rascal.slimeAmulet.logger

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider

class LanguageProviderEnUS(dataOutput: FabricDataOutput): FabricLanguageProvider(dataOutput, "en_us") {
    override fun generateTranslations(builder: TranslationBuilder) {
        logger.info("Generating translations for en_us.json...")
        builder.add("item.${SlimeAmulet.NAMESPACE}.slime_amulet", "Slime Amulet")
        builder.add("tooltip.${SlimeAmulet.NAMESPACE}.slime_amulet.tooltip", "Locates slime chunks")
    }
}