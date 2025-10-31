package click.rascal.slimeAmulet.item

import click.rascal.slimeAmulet.SlimeAmulet
import click.rascal.slimeAmulet.component.Components
import click.rascal.slimeAmulet.logger

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.math.random.ChunkRandom

class SlimeAmuletItem(settings: Settings) : Item(settings) {
    companion object {
        val activateSoundID: Identifier = Identifier.of("minecraft", "item.lodestone_compass.lock")
        val ID: Identifier = Identifier.of(SlimeAmulet.NAMESPACE, "slime_amulet")
        val settings: Settings = Settings()
            .maxCount(1)
            .component(Components.ACTIVE_COMPONENT, false)
        val SLIME_AMULET: SlimeAmuletItem = register(ID, settings).also {
            registerToGroup(it, ItemGroups.TOOLS, Items.RECOVERY_COMPASS)
        } as SlimeAmuletItem
        fun init() = Unit
        private fun register(id: Identifier, settings: Settings): Item {
            logger.info("Registered item as $id")
            return Registry.register(Registries.ITEM, id, SlimeAmuletItem(settings))
        }
        private fun registerToGroup(item: Item, group: RegistryKey<ItemGroup>, addAfterItem: Item) {
            ItemGroupEvents.modifyEntriesEvent(group).register {
                content -> content.addAfter(addAfterItem, item)
            }
            logger.info("Registered ${item.name} to group $group")
        }
    }
    fun inSlimeChunk(chunkPos: ChunkPos, seed: Long): Boolean {
        return ChunkRandom.getSlimeRandom(
            chunkPos.x,
            chunkPos.z,
            seed,
            0x3ad8025fL
        ).nextInt(10) == 0
    }
    override fun getName(stack: ItemStack): Text {
        return (super.getName(stack) as MutableText).styled {
            val color = if (stack.get(Components.ACTIVE_COMPONENT)!!) {
                Formatting.GREEN
            } else {
                Formatting.DARK_GREEN
            }
            it.withColor(color)
        }
    }
    override fun appendTooltip(
        stack: ItemStack,
        context: TooltipContext,
        tooltip: MutableList<Text>,
        type: TooltipType
    ) {
        tooltip.add(Text.translatable("tooltip.rascal.slime_amulet.tooltip").styled {
            it.withColor(Formatting.GRAY)
        })
        super.appendTooltip(stack, context, tooltip, type)
    }
}