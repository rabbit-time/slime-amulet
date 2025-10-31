package click.rascal.slimeAmulet.client.model

import click.rascal.slimeAmulet.client.networking.ClientNetworkingHandler
import click.rascal.slimeAmulet.component.Components
import click.rascal.slimeAmulet.item.SlimeAmuletItem
import click.rascal.slimeAmulet.item.SlimeAmuletItem.Companion.activateSoundID
import click.rascal.slimeAmulet.logger

import net.minecraft.client.item.ClampedModelPredicateProvider
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundEvent

object SlimeAmuletPredicateProvider {
    private fun playSound(stack: ItemStack, player: PlayerEntity, activeState: Boolean) {
        if (stack !== player.mainHandStack) {
            return
        }
        var pitch: Float = 1.0f
        var volume: Float = 0.5f
        if (!activeState) {
            pitch = 0.5f
            volume = 0.3f
        }
        player.playSound(SoundEvent.of(activateSoundID), volume, pitch)
    }
    private fun stateUpdate(stack: ItemStack, entity: LivingEntity?, activeState: Boolean) {
        val prevActiveState: Boolean = stack.get(Components.ACTIVE_COMPONENT)!!
        if (prevActiveState == activeState) {
            return
        }
        if (entity is PlayerEntity) {
            playSound(stack, entity, activeState)
        }
        stack.set(Components.ACTIVE_COMPONENT, activeState)
    }
    fun register() {
        ModelPredicateProviderRegistry.register(
            SlimeAmuletItem.SLIME_AMULET,
            Components.activeComponentID,
            ClampedModelPredicateProvider { stack: ItemStack, _, entity: LivingEntity?, _ ->
                if (entity == null) {
                    stateUpdate(stack, entity,false)
                    return@ClampedModelPredicateProvider 0.0f
                }
                val seed: Long = ClientNetworkingHandler.seed ?: run {
                    logger.info("Could not retrieve world seed.")
                    return@ClampedModelPredicateProvider 0.0f
                }
                val item = stack.item as SlimeAmuletItem
                val inSlimeChunk: Boolean = item.inSlimeChunk(entity.chunkPos, seed)
                stateUpdate(stack, entity,inSlimeChunk)

                return@ClampedModelPredicateProvider if (inSlimeChunk) 1.0f else 0.0f
            }
        )
    }
}