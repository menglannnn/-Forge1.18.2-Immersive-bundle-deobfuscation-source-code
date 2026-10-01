package com.dev.am.immersive_restraints.core;

import com.dev.am.immersive_restraints.init.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeTab {
    public static final CreativeModeTab ABDUCTION_ITEM_GROUP = CreativeModeTab.builder()
            .title(net.minecraft.network.chat.Component.translatable("itemGroup.immersive_restraints"))
            .icon(() -> new ItemStack(ModItems.HANDCUFFS.get()))
            .displayItems((parameters, output) -> {
                for (var item : ModItems.wearableItems) {
                    output.accept(item.get());
                }
            })
            .build();

    public static void register(net.minecraftforge.eventbus.api.IEventBus modBus) {
    }
}