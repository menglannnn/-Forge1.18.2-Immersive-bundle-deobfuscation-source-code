/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.item.alchemy.PotionUtils
 *  net.minecraftforge.common.brewing.IBrewingRecipe
 */
package com.dev.am.immersive_restraints.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

/*
 * Renamed from abductionmod.content.ar
 */
public class ModelProperty
implements IBrewingRecipe {
    private final Potion input;
    private final Item ingredient;
    private final Item output;

    public ModelProperty(Potion potion, Item item, Item item2) {
        this.input = potion;
        this.ingredient = item;
        this.output = item2;
    }

    public boolean isInput(ItemStack itemStack) {
        return PotionUtils.getPotion((ItemStack)itemStack) == this.input;
    }

    public boolean isIngredient(ItemStack itemStack) {
        return itemStack.getItem() == this.ingredient;
    }

    public ItemStack getOutput(ItemStack itemStack, ItemStack itemStack2) {
        if (!this.isInput(itemStack) || !this.isIngredient(itemStack2)) {
            return ItemStack.EMPTY;
        }
        return this.output.getDefaultInstance();
    }
}

