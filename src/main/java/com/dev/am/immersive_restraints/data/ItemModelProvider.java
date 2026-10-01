/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.level.ItemLike
 */
package com.dev.am.immersive_restraints.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;

public class ItemModelProvider {
    public static ItemStack icon(int n2) {
        ItemStack itemStack = new ItemStack((ItemLike)Items.BARRIER);
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt("CustomModelData", n2);
        itemStack.setTag(compoundTag);
        return itemStack;
    }

    public static ItemStack icon(Item item) {
        return ItemModelProvider.icon(item, 0, null);
    }

    public static ItemStack icon(Item item, Enchantment enchantment) {
        return ItemModelProvider.icon(item, 0, enchantment);
    }

    public static ItemStack icon(Item item, int n2) {
        return ItemModelProvider.icon(item, n2, null);
    }

    public static ItemStack icon(Item item, int n2, Enchantment enchantment) {
        ItemStack itemStack = new ItemStack((ItemLike)item);
        if (enchantment != null) {
            itemStack.enchant(enchantment, 5);
        }
        return itemStack;
    }
}

