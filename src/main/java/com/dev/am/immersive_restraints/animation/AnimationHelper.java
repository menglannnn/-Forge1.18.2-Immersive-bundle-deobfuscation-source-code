/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.CriterionTriggerInstance
 *  net.minecraft.core.Registry
 *  net.minecraft.data.PackOutput
 *  net.minecraft.data.recipes.RecipeOutput
 *  net.minecraft.data.recipes.RecipeProvider
 *  net.minecraft.data.recipes.SimpleCookingRecipeBuilder
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.common.crafting.conditions.IConditionBuilder
 *  net.minecraftforge.registries.RegistryObject
 */
package com.dev.am.immersive_restraints.animation;

import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.block.BlockVariantNames;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.block.ModBlock;
import com.dev.am.immersive_restraints.render.DynamicItemModel;
import com.dev.am.immersive_restraints.block.LockableDoorBlock;
import com.dev.am.immersive_restraints.block.BondageSlabBlock;
import com.dev.am.immersive_restraints.block.BondageStairBlock;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

/*
 * Renamed from abductionmod.content.az
 */
public class AnimationHelper
extends RecipeProvider
implements IConditionBuilder {
    public AnimationHelper(PackOutput output) {
        super(output);
    }

    protected static void door(RecipeOutput output, ItemLike itemLike, ItemLike itemLike2) {
        AnimationHelper.shaped((ItemLike)itemLike, (Ingredient)Ingredient.of((ItemLike[])new ItemLike[]{itemLike2})).unlockedBy(AnimationHelper.hasItem((ItemLike)itemLike2), (CriterionTriggerInstance)AnimationHelper.has((ItemLike)itemLike2)).save(output);
    }

    protected void buildRecipes(RecipeOutput output) {
        for (RegistryObject<Block> registryObject : BlockVariantNames.blocks.values()) {
            BlockItem blockItem;
            String string;
            String string2;
            Item item;
            if (registryObject.get() instanceof ModBlock && (item = (Item)BuiltInRegistries.ITEM.get(new ResourceLocation(string2 = (string = registryObject.getId().getPath()).replace("padded_block", "wool")))) instanceof BlockItem) {
                blockItem = (BlockItem)item;
                SimpleCookingRecipeBuilder.smelting((Ingredient)Ingredient.of((ItemLike[])new ItemLike[]{item}), (ItemLike)((ItemLike)registryObject.get()), (float)0.35f, (int)200).unlockedBy("has_" + string2, (CriterionTriggerInstance)AnimationHelper.has((ItemLike)blockItem.getBlock())).save(output);
            }
            if (registryObject.get() instanceof BondageStairBlock && (item = ((Block)BlockVariantNames.blocks.get(string2 = (string = registryObject.getId().getPath()).replace("_stairs", "")).get()).asItem()) instanceof BlockItem) {
                blockItem = (BlockItem)item;
                AnimationHelper.shapeless((ItemLike)((Block)registryObject.get()).asItem(), (Ingredient)Ingredient.of((ItemLike[])new ItemLike[]{item})).unlockedBy("has_" + string2, (CriterionTriggerInstance)AnimationHelper.has((ItemLike)item)).save(output);
            }
            if (registryObject.get() instanceof BondageSlabBlock && (item = ((Block)BlockVariantNames.blocks.get(string2 = (string = registryObject.getId().getPath()).replace("_slab", "")).get()).asItem()) instanceof BlockItem) {
                blockItem = (BlockItem)item;
                AnimationHelper.slab(output, (ItemLike)((ItemLike)registryObject.get()), (ItemLike)blockItem.getBlock());
            }
            if (!(registryObject.get() instanceof LockableDoorBlock) || !((item = ((Block)BlockVariantNames.blocks.get(string2 = (string = registryObject.getId().getPath()).replace("_door", "")).get()).asItem()) instanceof BlockItem)) continue;
            blockItem = (BlockItem)item;
            AnimationHelper.door(output, (ItemLike)registryObject.get(), (ItemLike)blockItem.getBlock());
        }
    }
}

