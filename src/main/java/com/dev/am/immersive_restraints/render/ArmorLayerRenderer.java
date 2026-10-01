/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TextComponent
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.level.Level
 *  org.jetbrains.annotations.Nullable
 */
package com.dev.am.immersive_restraints.render;

import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.network.StrugglePacket;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ArmorLayerRenderer
extends Item {
    public ArmorLayerRenderer() {
        super(new Item.Properties().tab(ModItems.ABDUCTION_ITEM_GROUP).stacksTo(1));
    }

    public static boolean isWet(ItemStack itemStack) {
        CompoundTag compoundTag = itemStack.getTag();
        if (compoundTag == null) {
            return false;
        }
        int n2 = compoundTag.getInt("WetTicks");
        return n2 > 0;
    }

    public static void makeWet(ItemStack itemStack) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        compoundTag.putInt("WetTicks", 1200);
    }

    public boolean shouldCauseReequipAnimation(ItemStack itemStack, ItemStack itemStack2, boolean bl2) {
        return false;
    }

    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int n2, boolean bl2) {
        int n3;
        CompoundTag compoundTag;
        if (!level.isClientSide && (compoundTag = itemStack.getTag()) != null && (n3 = compoundTag.getInt("WetTicks")) > 0) {
            compoundTag.putInt("WetTicks", --n3);
        }
        super.inventoryTick(itemStack, level, entity, n2, bl2);
    }

    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        if (ArmorLayerRenderer.isWet(itemStack)) {
            Level level = player.level();
            if (!level.isClientSide) {
                itemStack.getTag().remove("WetTicks");
                StrugglePacket.applyChloroform(livingEntity);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    public boolean isFoil(ItemStack itemStack) {
        return super.isFoil(itemStack) || ArmorLayerRenderer.isWet(itemStack);
    }

    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);
        if (ArmorLayerRenderer.isWet(itemStack)) {
            list.add((Component)Component.literal(ChatFormatting.GOLD + Component.translatable("item.desc.wet").getString()));
        }
    }
}

