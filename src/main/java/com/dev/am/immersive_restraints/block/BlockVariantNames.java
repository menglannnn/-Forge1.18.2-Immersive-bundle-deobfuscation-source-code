/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockBehaviour
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.dev.am.immersive_restraints.block;

import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.render.PlayerModelModifier;
import com.dev.am.immersive_restraints.block.ModBlock;
import com.dev.am.immersive_restraints.render.DynamicItemModel;
import com.dev.am.immersive_restraints.block.LockableDoorBlock;
import com.dev.am.immersive_restraints.item.PartVisibilityEnum;
import com.dev.am.immersive_restraints.block.DirectionalBondageBlock;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.block.BondageSlabBlock;
import com.dev.am.immersive_restraints.block.BondageStairBlock;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/*
 * Renamed from abductionmod.content.o
 */
public class BlockVariantNames {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, "immersive_restraints");
    public static HashMap<String, RegistryObject<Block>> blocks = new LinkedHashMap<String, RegistryObject<Block>>();

    private static <ClothesProtectionCommand extends Block> RegistryObject<ClothesProtectionCommand> registerBlock(String string, Supplier<ClothesProtectionCommand> supplier) {
        return BlockVariantNames.registerBlock(string, supplier, block -> new BlockItem(block, new Item.Properties().tab(ModItems.ABDUCTION_ITEM_GROUP)));
    }

    private static <ClothesProtectionCommand extends Block> RegistryObject<ClothesProtectionCommand> registerBlock(String string, Supplier<ClothesProtectionCommand> supplier, Function<Block, BlockItem> function) {
        RegistryObject registryObject = BLOCKS.register(string, supplier);
        blocks.put(string, (RegistryObject<Block>)registryObject);
        BlockVariantNames.registerBlockItem(string, registryObject, function);
        return registryObject;
    }

    private static <ClothesProtectionCommand extends Block> RegistryObject<Item> registerBlockItem(String string, RegistryObject<ClothesProtectionCommand> registryObject, Function<Block, BlockItem> function) {
        return ModItems.ITEMS.register(string, () -> (Item)function.apply((Block)registryObject.get()));
    }

    static {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of((BlockBehaviour)Blocks.OAK_PLANKS).strength(1.5f);
        for (DyeColor dyeColor : DyeColor.values()) {
            BlockVariantNames.registerBlock(dyeColor + "_padded_block", () -> new ModBlock(properties));
        }
        for (DyeColor dyeColor : DyeColor.values()) {
            String string = dyeColor + "_padded_block";
            BlockVariantNames.registerBlock(string + "_stairs", () -> new BondageStairBlock(() -> ((Block)blocks.get(string).get()).defaultBlockState(), properties));
        }
        for (DyeColor dyeColor : DyeColor.values()) {
            BlockVariantNames.registerBlock(dyeColor + "_padded_block_slab", () -> new BondageSlabBlock(properties));
        }
        for (DyeColor dyeColor : DyeColor.values()) {
            BlockVariantNames.registerBlock(dyeColor + "_padded_block_door", () -> new LockableDoorBlock(properties.strength(3.0f)), block -> new PlayerModelModifier((Block)block, new Item.Properties().tab(ModItems.ABDUCTION_ITEM_GROUP)));
        }
        for (DyeColor dyeColor : DyeColor.values()) {
            BlockVariantNames.registerBlock(dyeColor + "_padded_pane", () -> new DirectionalBondageBlock(properties.noOcclusion()));
        }
    }
}