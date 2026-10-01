/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.inventory.CraftingContainer
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Unique
 */
package com.dev.am.immersive_restraints.data;

import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.network.PacketHandler;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.render.PlayerModelModifier;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.render.DynamicItemModel;
import com.dev.am.immersive_restraints.block.LockableDoorBlock;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Unique;

public class DataProvider {
    @Unique
    public static void handleCustomCrafts(ItemStack itemStack, CraftingContainer craftingContainer) {
        DataProvider.assignKeysToBondage(itemStack, (Item)ModItems.ARMBINDER.get(), (Item)ModItems.STEEL_KEY.get(), craftingContainer);
        DataProvider.assignKeysToBondage(itemStack, (Item)ModItems.STRAITJACKET.get(), (Item)ModItems.STEEL_KEY.get(), craftingContainer);
        DataProvider.assignKeysToBondage(itemStack, (Item)ModItems.LATEX_SACK.get(), (Item)ModItems.STEEL_KEY.get(), craftingContainer);
        DataProvider.assignKeysToBondage(itemStack, (Item)ModItems.CHAIN.get(), (Item)ModItems.STEEL_KEY.get(), craftingContainer);
        DataProvider.assignKeysToBondage(itemStack, (Item)ModItems.HANDCUFFS.get(), (Item)ModItems.STEEL_KEY.get(), craftingContainer);
        DataProvider.assignKeysToBondage(itemStack, (Item)ModItems.MITTENS.get(), (Item)ModItems.STEEL_KEY.get(), craftingContainer);
        DataProvider.assignKeysToBondage(itemStack, (Item)ModItems.COLLAR.get(), (Item)ModItems.COLLAR_KEY.get(), craftingContainer);
        DataProvider.reverseDoor(itemStack, craftingContainer);
        DataProvider.cloneKey(itemStack, craftingContainer);
    }

    @Unique
    private static void reverseDoor(ItemStack itemStack2, CraftingContainer craftingContainer) {
        if (itemStack2.getItem() instanceof PlayerModelModifier && craftingContainer.items.stream().anyMatch(itemStack -> itemStack.getItem() instanceof PlayerModelModifier)) {
            ItemStack itemStack3 = itemStack2;
            for (ItemStack itemStack4 : craftingContainer.items) {
                if (!(itemStack4.getItem() instanceof PlayerModelModifier)) continue;
                itemStack3 = itemStack4;
                break;
            }
            itemStack2.deserializeNBT(LockableDoorBlock.getReversedStack(itemStack3).serializeNBT());
        }
    }

    @Unique
    private static void cloneKey(ItemStack itemStack, CraftingContainer craftingContainer) {
        if (itemStack.getItem() instanceof PacketHandler) {
            ItemStack itemStack2 = itemStack;
            for (ItemStack itemStack3 : craftingContainer.items) {
                String string;
                if (!(itemStack3.getItem() instanceof PacketHandler) || (string = PlayerEventHandler.getCustomTag(itemStack3, "keyValue")) == null || string.isEmpty()) continue;
                itemStack2 = itemStack3;
                break;
            }
            craftingContainer.clearContent();
            itemStack.deserializeNBT(itemStack2.serializeNBT());
        }
    }

    @Unique
    private static void assignKeysToBondage(ItemStack itemStack, Item item, Item item2, CraftingContainer craftingContainer) {
        if (itemStack.is(item)) {
            ItemStack itemStack2 = null;
            ItemStack itemStack3 = null;
            for (ItemStack itemStack4 : craftingContainer.items) {
                if (itemStack4.getItem() == item2) {
                    itemStack2 = itemStack4;
                    continue;
                }
                if (!itemStack4.is(itemStack.getItem())) continue;
                itemStack3 = itemStack4;
            }
            if (itemStack3 != null && itemStack2 != null) {
                Object object = PlayerEventHandler.getCustomTag(itemStack2, "keyValue");
                if (object == null || ((String)object).isEmpty()) {
                    object = EntityEventHandler.generateRandomKey();
                }
                PlayerEventHandler.setCustomTag(itemStack3, (String)object, "keyValue");
                PlayerEventHandler.setCustomTag(itemStack, (String)object, "keyValue");
                PlayerEventHandler.setCustomTag(itemStack2, (String)object, "keyValue");
                itemStack.deserializeNBT(itemStack3.serializeNBT());
            }
        }
    }
}

