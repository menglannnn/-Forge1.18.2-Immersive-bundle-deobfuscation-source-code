/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TextComponent
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  org.jetbrains.annotations.Nullable
 */
package com.dev.am.immersive_restraints.render;

import com.dev.am.immersive_restraints.render.DynamicItemModel;
import com.dev.am.immersive_restraints.block.LockableDoorBlock;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.jetbrains.annotations.Nullable;

public class PlayerModelModifier
extends BlockItem {
    public PlayerModelModifier(Block block, Item.Properties properties) {
        super(block, properties);
    }

    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (itemStack.getOrCreateTagElement().getOrCreateTag().getBoolean("reversed")) {
            list.add((Component)Component.literal(ChatFormatting.GOLD + Component.translatable("item.desc.door_reversed").getString()));
        } else {
            list.add((Component)Component.literal(ChatFormatting.GRAY + Component.translatable("item.desc.can_door_reversed").getString()));
        }
        list.add((Component)Component.literal(ChatFormatting.GRAY + Component.translatable("item.desc.key_padded_door").getString()));
        super.appendHoverText(itemStack, level, list, tooltipFlag);
    }

    @Nullable
    protected BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState blockState = super.getStateForPlacement(blockPlaceContext);
        ItemStack itemStack = blockPlaceContext.getItem();
        if (blockState != null && blockState.getBlock() instanceof LockableDoorBlock && itemStack.getOrCreateTagElement().getOrCreateTag().getBoolean("reversed")) {
            blockState = (BlockState)blockState.setValue((Property)LockableDoorBlock.REVERSED, (Comparable)Boolean.TRUE);
        }
        return blockState;
    }
}

