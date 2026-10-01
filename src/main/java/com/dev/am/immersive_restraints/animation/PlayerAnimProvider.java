/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Registry
 *  net.minecraft.data.PackOutput
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.CropBlock
 *  net.minecraft.world.level.block.SlabBlock
 *  net.minecraft.world.level.block.StairBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraftforge.client.model.generators.BlockStateProvider
 *  net.minecraftforge.client.model.generators.ConfiguredModel
 *  net.minecraftforge.client.model.generators.ModelFile
 *  net.minecraftforge.common.data.ExistingFileHelper
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
import java.util.function.Function;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

/*
 * Renamed from abductionmod.content.av
 */
public class PlayerAnimProvider
extends BlockStateProvider {
    public PlayerAnimProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, "immersive_restraints", existingFileHelper);
    }

    protected void registerStatesAndModels() {
        for (RegistryObject<Block> registryObject : BlockVariantNames.blocks.values()) {
            if (registryObject.get() instanceof ModBlock) {
                this.simpleBlockWithItem(registryObject);
            }
            if (registryObject.get() instanceof BondageStairBlock) {
                this.stairsBlockWithItem(registryObject);
            }
            if (registryObject.get() instanceof BondageSlabBlock) {
                this.slabBlockWithItem(registryObject);
            }
            if (!(registryObject.get() instanceof LockableDoorBlock)) continue;
            this.doorBlockWithItem(registryObject);
        }
    }

    private void simpleBlockWithItem(RegistryObject<Block> registryObject) {
        String string = this.getBlockName(registryObject);
        this.simpleBlock((Block)registryObject.get());
        this.itemModels().withExistingParent(string, new ResourceLocation("immersive_restraints", "block/" + string));
    }

    private void stairsBlockWithItem(RegistryObject<Block> registryObject) {
        String string = this.getBlockName(registryObject);
        StairBlock stairBlock = (StairBlock)registryObject.get();
        this.stairsBlock(stairBlock, this.blockTexture((Block)BuiltInRegistries.BLOCK.get(new ResourceLocation("immersive_restraints", string.replace("_stairs", "")))));
        this.itemModels().withExistingParent(string, new ResourceLocation("immersive_restraints", "block/" + string));
    }

    private void doorBlockWithItem(RegistryObject<Block> registryObject) {
        String string = this.getBlockName(registryObject);
        LockableDoorBlock r_02 = (LockableDoorBlock)registryObject.get();
        ResourceLocation resourceLocation = this.blockTexture((Block)BuiltInRegistries.BLOCK.get(new ResourceLocation("immersive_restraints", string.replace("_door", ""))));
        this.doorBlock(r_02, resourceLocation, resourceLocation);
    }

    private void slabBlockWithItem(RegistryObject<Block> registryObject) {
        String string = this.getBlockName(registryObject);
        SlabBlock slabBlock = (SlabBlock)registryObject.get();
        ResourceLocation resourceLocation = this.blockTexture((Block)BuiltInRegistries.BLOCK.get(new ResourceLocation("immersive_restraints", string.replace("_slab", ""))));
        this.slabBlock(slabBlock, resourceLocation, resourceLocation);
        this.itemModels().withExistingParent(string, new ResourceLocation("immersive_restraints", "block/" + string));
    }

    private void horizontalBlockWithItem(RegistryObject<Block> registryObject) {
        String string = this.getBlockName(registryObject);
        this.horizontalBlock((Block)registryObject.get(), (ModelFile)this.models().getExistingFile(new ResourceLocation("immersive_restraints", "block/" + string)));
        this.itemModels().withExistingParent(string, new ResourceLocation("immersive_restraints", "block/" + string));
    }

    private String getBlockName(RegistryObject<Block> registryObject) {
        return registryObject.getId().getPath();
    }

    public ModelFile flowerPotCross(String string) {
        return this.models().withExistingParent(string, "flower_pot_cross");
    }

    public void makeCrop(CropBlock cropBlock, String string, String string2) {
        Function<BlockState, ConfiguredModel[]> function = blockState -> this.states((BlockState)blockState, cropBlock, string, string2);
        this.getVariantBuilder((Block)cropBlock).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState blockState, CropBlock cropBlock, String string, String string2) {
        ConfiguredModel[] configuredModelArray = new ConfiguredModel[]{new ConfiguredModel((ModelFile)this.models().crop(string + blockState.getValue((Property)cropBlock.getAgeProperty()), new ResourceLocation("immersive_restraints", "block/" + string2 + blockState.getValue((Property)cropBlock.getAgeProperty()))))};
        return configuredModelArray;
    }
}

