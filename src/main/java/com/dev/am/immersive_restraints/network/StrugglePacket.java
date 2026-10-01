/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.ItemUtils
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.UseAnim
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.level.Level
 */
package com.dev.am.immersive_restraints.network;

import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.core.ModCore;
import com.dev.am.immersive_restraints.render.ArmorLayerRenderer;
import com.dev.am.immersive_restraints.command.SaveClothesCommand;
import com.dev.am.immersive_restraints.item.UnequipSoundProperty;
import com.dev.am.immersive_restraints.command.ClothesProtectionCommand;
import com.dev.am.immersive_restraints.item.TextureProperty;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

public class StrugglePacket
extends Item {
    public int getUseDuration(ItemStack itemStack) {
        return 32;
    }

    public StrugglePacket() {
        super(new Item.Properties().tab(ModItems.ABDUCTION_ITEM_GROUP).stacksTo(1).durability(9));
    }

    public boolean isFoil(ItemStack itemStack) {
        return true;
    }

    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }

    public boolean isFoil(ItemStack itemStack) {
        return false;
    }

    public boolean isBookEnchantable(ItemStack itemStack, ItemStack itemStack2) {
        return false;
    }

    public int getItemEnchantability(ItemStack itemStack) {
        return 0;
    }

    public boolean canApplyAtEnchantingTable(ItemStack itemStack, Enchantment enchantment) {
        return false;
    }

    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide) {
            StrugglePacket.applyChloroform(livingEntity);
            return StrugglePacket.decrementStack(livingEntity, livingEntity.getUseItem(), itemStack);
        }
        return itemStack;
    }

    public static void applyChloroform(LivingEntity livingEntity) {
        livingEntity.hurt(DamageSource.OUT_OF_WORLD, 1.0f);
        livingEntity.addEffect(new MobEffectInstance((MobEffect)TextureProperty.CHLOROFORM.get(), UnequipSoundProperty.getDuration()));
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getMainHandItem();
        ItemStack itemStack2 = player.getOffhandItem();
        if (itemStack2.is((Item)ModItems.RAG.get())) {
            player.getCooldowns().addCooldown((Item)this, 20);
            if (!level.isClientSide) {
                ArmorLayerRenderer.makeWet(itemStack2);
                StrugglePacket.decrementStack((LivingEntity)player, interactionHand, itemStack);
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1.0f, 1.0f);
                return InteractionResultHolder.success((Object)player.getItemInHand(interactionHand));
            }
        } else {
            ItemUtils.useItem((Level)level, (Player)player, (InteractionHand)interactionHand);
        }
        return super.use(level, player, interactionHand);
    }

    private static ItemStack decrementStack(LivingEntity livingEntity2, InteractionHand interactionHand, ItemStack itemStack) {
        itemStack.hurtAndBreak(1, livingEntity2, livingEntity -> {});
        if (itemStack.isEmpty()) {
            livingEntity2.setItemInHand(interactionHand, Items.AIR.getDefaultInstance());
            return Items.AIR.getDefaultInstance();
        }
        return itemStack;
    }
}

