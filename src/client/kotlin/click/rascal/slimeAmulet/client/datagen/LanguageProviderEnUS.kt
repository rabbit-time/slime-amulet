package click.rascal.slimeAmulet.client.datagen

import click.rascal.slimeAmulet.SlimeAmulet
import click.rascal.slimeAmulet.logger

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper.WrapperLookup

import java.util.concurrent.CompletableFuture

class LanguageProviderEnUS(
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<WrapperLookup>
): FabricLanguageProvider(dataOutput, "en_us", registryLookup) {
    override fun generateTranslations(wrapperLookup: WrapperLookup, builder: TranslationBuilder) {
        logger.info("Generating translations for en_us.json...")
        builder.add("item.${SlimeAmulet.NAMESPACE}.slime_amulet", "Slime Amulet")
        builder.add("tooltip.${SlimeAmulet.NAMESPACE}.slime_amulet.tooltip", "Locates slime chunks")
    }
}