package com.dev.am.immersive_restraints.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "immersive_restraints");

    public static void register(net.minecraftforge.eventbus.api.IEventBus bus) {
        CREATIVE_TABS.register(bus);
    }
}