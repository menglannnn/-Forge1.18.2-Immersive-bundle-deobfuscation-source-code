/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.Style
 *  net.minecraft.network.chat.TextComponent
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.state.BlockState
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.dev.am.immersive_restraints.network;

import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.init.ModSounds;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AnimationSyncPacket
extends Item {
    public AnimationSyncPacket() {
        super(new Item.Properties().tab(ModItems.ABDUCTION_ITEM_GROUP).stacksTo(1));
    }

    public AnimationSyncPacket(Item.Properties properties) {
        super(properties);
    }

    @NotNull
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        EntityEventHandler.useKeyOnPlayer(null, player, itemStack);
        return InteractionResultHolder.success((Object)itemStack, (boolean)level.isClientSide());
    }

    public boolean doesSneakBypassUse(ItemStack itemStack, LevelReader levelReader, BlockPos blockPos, Player player) {
        return true;
    }

    @NotNull
    public InteractionResult useOn(UseOnContext useOnContext) {
        if (useOnContext.getPlayer() != null) {
            Player player = useOnContext.getPlayer();
            if (player.level.isClientSide) {
                return InteractionResult.SUCCESS;
            }
            EntityEventHandler.useKeyOnPlayer(null, useOnContext.getPlayer(), useOnContext.getItem());
        }
        return super.useOn(useOnContext);
    }

    @NotNull
    public InteractionResult interactLivingEntity(ItemStack itemStack2, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        if (livingEntity instanceof Player) {
            Player player2 = (Player)livingEntity;
            if (livingEntity.level.isClientSide) {
                return InteractionResult.SUCCESS;
            }
            EntityEventHandler.tryTakeOffBondage((LivingEntity)player, (LivingEntity)player2, itemStack2, null, (SoundEvent)ModSounds.COLLAR_KEY_OPEN.get(), itemStack -> player2.getInventory(itemStack, false));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);
        if (PlayerEventHandler.hasCustomTag(itemStack, "keyValue")) {
            String string = PlayerEventHandler.getCustomTag(itemStack, "keyValue");
            list.add((Component)Component.literal(ChatFormatting.GRAY + Component.translatable("item.desc.key_provided").getString()).append((Component)Component.literal(string).withStyle(Style.EMPTY.withColor(EntityEventHandler.getColorFromString(string)))));
        }
        list.add((Component)Component.literal(ChatFormatting.GRAY + Component.translatable(itemStack.is((Item)ModItems.STEEL_KEY.get()) ? "item.desc.escape_doors" : "item.desc.escape").getString()));
    }

    public boolean canAttackBlock(ItemStack itemStack, LivingEntity livingEntity2, LivingEntity livingEntity3) {
        if (itemStack.isDamageable()) {
            itemStack.hurtAndBreak(1, livingEntity3, livingEntity -> livingEntity.getItemBySlot(EquipmentSlot.MAINHAND));
        }
        return true;
    }

    public boolean mineBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity2) {
        if (itemStack.isDamageable() && blockState.getDestroySpeed((BlockGetter)level, blockPos) != 0.0f) {
            itemStack.hurtAndBreak(2, livingEntity2, livingEntity -> livingEntity.getItemBySlot(EquipmentSlot.MAINHAND));
        }
        return true;
    }
}

