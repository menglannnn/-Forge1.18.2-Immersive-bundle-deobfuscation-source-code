/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.StairBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 */
package com.dev.am.immersive_restraints.block;

import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.block.ModBlockProperties;
import java.util.function.Supplier;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

/*
 * Renamed from abductionmod.content.v
 */
public class BondageStairBlock
extends StairBlock
implements ModBlockProperties {
    public BondageStairBlock(Supplier<BlockState> supplier, BlockBehaviour.Properties properties) {
        super(supplier, properties);
    }
}

