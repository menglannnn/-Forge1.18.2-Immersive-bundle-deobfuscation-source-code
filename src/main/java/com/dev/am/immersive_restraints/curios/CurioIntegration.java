/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.Util
 *  net.minecraft.network.chat.ChatType
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 */
package com.dev.am.immersive_restraints.curios;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.config.GameruleRegistry;
import com.dev.am.mod.gamerule.TyingTime;
import com.dev.am.mod.gamerule.TyingTimeWhenTied;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface CurioIntegration {
    default public int am$getTyingTimeSeconds(LivingEntity livingEntity, ItemStack itemStack) {
        Player player;
        int n = GameruleRegistry.getGameruleValue(livingEntity.level, TyingTime.ID);
        if (livingEntity instanceof Player && EntityEventHandler.isTied((LivingEntity)(player = (Player)livingEntity))) {
            n = GameruleRegistry.getGameruleValue(livingEntity.level, TyingTimeWhenTied.ID);
        }
        return n;
    }

    default public boolean am$goesIntoSlot() {
        return true;
    }

    default public InteractionResult am$interactWithBondage(ItemStack itemStack, Player player, LivingEntity livingEntity, CurioSlotType aG2, int n, CurioIntegration aH2) {
        EntityEventHandler.a a2 = EntityEventHandler.canPutBondage(livingEntity, itemStack);
        if (!aG2.abduction$isBeingTied() && aH2.am$goesIntoSlot() && a2 == EntityEventHandler.a.FAIL) {
            return InteractionResult.FAIL;
        }
        if (aG2.abduction$isFinishedTying() || a2 == EntityEventHandler.a.SWAP) {
            this.am$onFinishedTying(itemStack, player, livingEntity);
            aG2.abduction$stopTyingProcess();
            return InteractionResult.SUCCESS;
        }
        aG2.abduction$setPlayerPressing(itemStack.getOrCreateTagElement());
        aG2.abduction$setTyerHash(n);
        aG2.abduction$setTyingEntity((LivingEntity)player);
        aG2.abduction$setUntying(false);
        return InteractionResult.PASS;
    }

    default public void am$onFinishedTying(ItemStack itemStack, Player player, LivingEntity livingEntity) {
        if (player instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer)player;
            CurioSlotType aG2 = (CurioSlotType)livingEntity;
            aG2.am$setTyingTimeout(10);
            aG2.abduction$setUntyingCoolDown(20);
        }
        EntityEventHandler.putBondageItemInto(livingEntity, (LivingEntity)player, itemStack.copyWithCount(1), null, false);
    }

    default public InteractionResult am$interactWithTyingItem(ItemStack itemStack, Player player, LivingEntity livingEntity) {
        if (livingEntity.level.isClientSide) {
            return InteractionResult.FAIL;
        }
        if (livingEntity instanceof CurioSlotType) {
            CurioSlotType aG2 = (CurioSlotType)livingEntity;
            if (aG2.abduction$isUntying()) {
                if (player instanceof ServerPlayer) {
                    ServerPlayer serverPlayer = (ServerPlayer)player;
                    serverPlayer.sendSystemMessage((Component)Component.translatable("error.player_is_untying").withStyle(ChatFormatting.RED), ChatType.GAME_INFO, Util.NIL_UUID);
                }
                return InteractionResult.FAIL;
            }
            int n = EntityEventHandler.calculateTyerHash((LivingEntity)player, livingEntity, itemStack);
            if (aG2.abduction$isBeingTied() && n != aG2.abduction$getTyerHash()) {
                if (player instanceof ServerPlayer) {
                    ServerPlayer serverPlayer = (ServerPlayer)player;
                    serverPlayer.sendSystemMessage((Component)Component.translatable("error.already_being_tied").withStyle(ChatFormatting.RED), ChatType.GAME_INFO, Util.NIL_UUID);
                }
                return InteractionResult.FAIL;
            }
            Item item = itemStack.getItem();
            if (item instanceof CurioIntegration) {
                CurioIntegration aH2 = (CurioIntegration)item;
                return this.am$interactWithBondage(itemStack, player, livingEntity, aG2, n, aH2);
            }
        }
        return InteractionResult.PASS;
    }
}

