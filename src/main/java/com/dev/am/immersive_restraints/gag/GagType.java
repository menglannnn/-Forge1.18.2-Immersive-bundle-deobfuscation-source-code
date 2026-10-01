/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.data.loot.BlockLoot
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.registries.RegistryObject
 */
package com.dev.am.immersive_restraints.gag;

import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.block.BlockVariantNames;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.block.ModBlock;
import com.dev.am.immersive_restraints.render.DynamicItemModel;
import com.dev.am.immersive_restraints.block.LockableDoorBlock;
import com.dev.am.immersive_restraints.item.PartVisibilityEnum;
import com.dev.am.immersive_restraints.block.DirectionalBondageBlock;
import com.dev.am.immersive_restraints.block.BondageSlabBlock;
import com.dev.am.immersive_restraints.block.BondageStairBlock;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class GagType
extends BlockLoot {
    protected void addTables() {
        for (RegistryObject<Block> registryObject : BlockVariantNames.blocks.values()) {
            if (!(registryObject.get() instanceof ModBlock) && !(registryObject.get() instanceof BondageStairBlock) && !(registryObject.get() instanceof BondageSlabBlock) && !(registryObject.get() instanceof LockableDoorBlock) && !(registryObject.get() instanceof DirectionalBondageBlock)) continue;
            this.registerDefaultEffect((Block)registryObject.get());
        }
    }

    protected Iterable<Block> getKnownBlocks() {
        return BlockVariantNames.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}

