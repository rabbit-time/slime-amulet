package click.rascal.slimeAmulet.component

import click.rascal.slimeAmulet.SlimeAmulet
import click.rascal.slimeAmulet.logger
import com.mojang.serialization.Codec
import net.minecraft.component.DataComponentType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object Components {
    val activeComponentID: Identifier = Identifier(SlimeAmulet.NAMESPACE,"active")
    val ACTIVE_COMPONENT: DataComponentType<Boolean> = Registry.register(
        Registries.DATA_COMPONENT_TYPE,
        activeComponentID,
        DataComponentType.builder<Boolean>().codec(Codec.BOOL).build()
        )
    fun init() {
        logger.info("Registering data components...")
    }
}