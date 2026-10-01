/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.data.PackOutput
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.client.model.generators.ItemModelBuilder
 *  net.minecraftforge.client.model.generators.ItemModelProvider
 *  net.minecraftforge.common.data.ExistingFileHelper
 *  net.minecraftforge.registries.RegistryObject
 */
package com.dev.am.immersive_restraints.animation;

import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.block.BlockVariantNames;
import com.dev.am.immersive_restraints.render.DynamicItemModel;
import com.dev.am.immersive_restraints.block.LockableDoorBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

/*
 * Renamed from abductionmod.content.aw
 */
public class BendyLibIntegration
extends ItemModelProvider {
    public BendyLibIntegration(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, "immersive_restraints", existingFileHelper);
    }

    protected void registerModels() {
        for (RegistryObject<Block> registryObject : BlockVariantNames.blocks.values()) {
            if (!(registryObject.get() instanceof LockableDoorBlock)) continue;
            this.simpleItem(((Block)registryObject.get()).asItem());
        }
    }

    private ItemModelBuilder simpleItem(Item item) {
        return (ItemModelBuilder)((ItemModelBuilder)this.withExistingParent(item.getRegistryName().getPath(), new ResourceLocation("item/generated"))).texture("layer0", new ResourceLocation("immersive_restraints", "item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return (ItemModelBuilder)((ItemModelBuilder)this.withExistingParent(item.getRegistryName().getPath(), new ResourceLocation("item/handheld"))).texture("layer0", new ResourceLocation("immersive_restraints", "item/" + item.getRegistryName().getPath()));
    }
}

