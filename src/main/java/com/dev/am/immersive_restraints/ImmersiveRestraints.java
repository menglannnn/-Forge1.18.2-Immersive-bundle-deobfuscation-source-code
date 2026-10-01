package com.dev.am.immersive_restraints;

import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.init.ModSounds;
import com.dev.am.immersive_restraints.core.CreativeTab;
import com.dev.am.immersive_restraints.block.BlockVariantNames;
import com.dev.am.immersive_restraints.item.TextureProperty;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ImmersiveRestraints.MOD_ID)
public class ImmersiveRestraints {
    public static final String MOD_ID = "immersive_restraints";
    public static final String MOD_NAME = "Immersive Restraints";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public ImmersiveRestraints() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modBus);
        ModSounds.register(modBus);
        CreativeTab.register(modBus);
        BlockVariantNames.BLOCKS.register(modBus);
        TextureProperty.register(modBus);

        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("{} v1.2.56 initialized for Forge 1.20.1", MOD_NAME);
    }
}