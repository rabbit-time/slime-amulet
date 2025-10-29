package click.rascal.slimeAmulet.client.datagen

import click.rascal.slimeAmulet.item.SlimeAmuletItem
import click.rascal.slimeAmulet.logger

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder
import net.minecraft.item.Items
import net.minecraft.recipe.book.RecipeCategory

class RecipeProvider(output: FabricDataOutput): FabricRecipeProvider(output) {
    override fun generate(exporter: RecipeExporter) {
        logger.info("Generating recipes...")
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, SlimeAmuletItem.SLIME_AMULET, 1)
            .pattern(" i ")
            .pattern("isi")
            .pattern("ri ")
            .input('i', Items.IRON_INGOT)
            .input('r', Items.REDSTONE)
            .input('s', Items.SLIME_BALL)
            .criterion(hasItem(Items.SLIME_BALL), conditionsFromItem(Items.SLIME_BALL))
            .offerTo(exporter)
    }
}