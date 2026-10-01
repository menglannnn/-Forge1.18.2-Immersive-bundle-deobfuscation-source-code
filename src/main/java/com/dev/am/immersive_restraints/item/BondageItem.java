/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Multimap
 *  javax.annotation.Nonnull
 *  net.minecraft.ChatFormatting
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.Style
 *  net.minecraft.network.chat.TextComponent
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.common.capabilities.ICapabilityProvider
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioIntegration;
import com.dev.am.immersive_restraints.command.ClothesProtectionCommand;
import com.dev.am.immersive_restraints.network.TyingPacket;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.network.StateSyncPacket;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.item.BondageProperties;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.item.PartVisibility;
import com.dev.am.immersive_restraints.animation.StruggleAnimations;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BondageItem
extends Item
implements CurioIntegration {
    public String name;
    public BondageProperties properties;
    public BiPredicate<ItemStack, ItemStack> takeOffItemPredicate;
    @Nullable
    public Supplier<Item[]> arrayOf3dItems;
    @Nonnull
    public Consumer<PartVisibility> hideBodyParts;

    public BondageItem(String string, BondageProperties b_02) {
        this(string, b_02, null, 16, null, null);
    }

    public BondageItem(String string, BondageProperties b_02, BiPredicate<ItemStack, ItemStack> biPredicate, int n, @Nullable Supplier<Item[]> supplier, @Nullable Consumer<PartVisibility> consumer) {
        super(new Item.Properties().tab(ModItems.ABDUCTION_ITEM_GROUP).stacksTo(n));
        this.name = string;
        this.properties = b_02;
        this.arrayOf3dItems = supplier;
        this.hideBodyParts = Objects.requireNonNullElseGet(consumer, () -> h_02 -> {});
        this.takeOffItemPredicate = biPredicate == null ? (itemStack, itemStack2) -> false : biPredicate;
    }

    public static boolean isModified(ItemStack itemStack) {
        if (itemStack.getTag() == null) {
            return false;
        }
        for (String string : PlayerEventHandler.allModifiedTags) {
            if (!itemStack.getOrCreateTagElement().getOrCreateTag().contains(string)) continue;
            return true;
        }
        return false;
    }

    private static void addTextureTooltipText(ItemStack itemStack, String string, List<Component> list, ChatFormatting chatFormatting) {
        String string2 = PlayerEventHandler.getCustomTag(itemStack, string);
        if (string2 != null) {
            list.add((Component)Component.literal(chatFormatting + Component.translatable("texture.custom_texture").getString()));
        }
    }

    @NotNull
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand interactionHand) {
        return this.am$interactWithTyingItem(itemStack, player, livingEntity);
    }

    public void tickCurio(String string, int n, LivingEntity livingEntity) {
    }

    public void onEquippedCurio(String string, LivingEntity livingEntity) {
    }

    public void onUnequippedCurio(String string, LivingEntity livingEntity) {
    }

    public Multimap<Attribute, AttributeModifier> curioModifiers(ItemStack itemStack, String string) {
        HashMultimap hashMultimap = HashMultimap.create();
        return hashMultimap;
    }



    public boolean canApplyAtEnchantingTable(ItemStack itemStack, Enchantment enchantment) {
        Item item;
        if (enchantment.isCurse() && (item = itemStack.getItem()) instanceof BondageItem) {
            BondageItem aK2 = (BondageItem)item;
            BondageProperties b_02 = aK2.properties;
            if (aK2 instanceof StateSyncPacket || b_02.isGag(itemStack) || itemStack.is((Item)ModItems.COLLAR.get()) || b_02.isBlind(itemStack) || b_02.isDeafen(itemStack)) {
                return !b_02.isBindLegs(itemStack) && !b_02.isBindHands(itemStack);
            }
        }
        return super.canApplyAtEnchantingTable(itemStack, enchantment);
    }

    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        String string;
        String string2;
        Float f;
        boolean bl2;
        boolean bl3;
        String string3;
        super.appendHoverText(itemStack, level, list, tooltipFlag);
        if (this.properties.getResistance(itemStack) > 0) {
            list.add((Component)Component.literal(ChatFormatting.GREEN + Component.translatable("item.desc.resistance", new Object[]{this.properties.getResistance(itemStack)}).getString()));
        }
        if ((string3 = PlayerEventHandler.getCustomTag(itemStack, PlayerEventHandler.ARM_SHAPE_TAG)) != null) {
            switch (string3) {
                case "full": {
                    list.add((Component)Component.literal(ChatFormatting.GOLD + Component.translatable("arms.size.full").getString()));
                    break;
                }
                case "slim": {
                    list.add((Component)Component.literal(ChatFormatting.GOLD + Component.translatable("arms.size.slim").getString()));
                }
            }
        }
        if (bl3 = PlayerEventHandler.hasCustomTag(itemStack, "keyValue")) {
            String string4 = PlayerEventHandler.getCustomTag(itemStack, "keyValue");
            list.add((Component)Component.literal(ChatFormatting.GRAY + Component.translatable("item.desc.key_needed").getString()).append((Component)Component.literal(string4).withStyle(Style.EMPTY.withColor(EntityEventHandler.getColorFromString(string4)))));
        }
        if ((bl2 = PlayerEventHandler.hasCustomTag(itemStack, "isLocked")) || bl3) {
            boolean bl4 = bl2 ? PlayerEventHandler.getCustomTagBool(itemStack, "isLocked") : false;
            list.add((Component)Component.literal(ChatFormatting.GOLD + Component.translatable(bl4 ? "item.desc.locked" : "item.desc.unlocked").getString()));
        }
        if (PlayerEventHandler.hasCustomTag(itemStack, PlayerEventHandler.HIDE_ORIGINAL_SKIN_PARTS_TAG)) {
            list.add((Component)Component.literal(ChatFormatting.BLUE + Component.translatable("skin.mod.hides_original").getString()));
        }
        if (PlayerEventHandler.hasCustomTag(itemStack, "struggleAddedResistance")) {
            list.add((Component)Component.literal(ChatFormatting.LIGHT_PURPLE + Component.translatable("skin.mod.has_added_resistance").getString()));
        }
        if (PlayerEventHandler.hasCustomTag(itemStack, PlayerEventHandler.COLLAR_DISPLAY_NAME_TAG)) {
            list.add((Component)Component.literal(ChatFormatting.AQUA + Component.translatable("command.collar_set_display_name.desc", new Object[]{PlayerEventHandler.getCustomTag(itemStack, PlayerEventHandler.COLLAR_DISPLAY_NAME_TAG)}).getString()));
        }
        if (PlayerEventHandler.hasCustomTag(itemStack, PlayerEventHandler.DATA_AM_ADJUSTMENT_MAP_TAG) && (f = PlayerEventHandler.getCustomTagFloat(itemStack, PlayerEventHandler.DATA_AM_ADJUSTMENT_MAP_TAG)) != null && f.floatValue() != 0.0f) {
            list.add((Component)Component.literal(ChatFormatting.AQUA + Component.translatable("skin.mod.adjusted").getString()));
        }
        if ((string2 = PlayerEventHandler.getCustomTag(itemStack, PlayerEventHandler.GAG_TYPE_TAG)) != null) {
            list.add((Component)Component.literal(ChatFormatting.GRAY + Component.translatable("item.desc.has_custom_gag_type").getString()).append((Component)Component.literal(string2).withStyle(Style.EMPTY.withColor(ChatFormatting.GOLD))));
        }
        if ((string = PlayerEventHandler.getCustomTag(itemStack, PlayerEventHandler.ARM_BIND_TYPE_TAG)) != null && StruggleAnimations.armBindStyles.containsKey(string)) {
            list.add((Component)Component.literal(ChatFormatting.GRAY + Component.translatable("item.desc.has_custom_arm_bind_type").getString()).append((Component)Component.literal(StruggleAnimations.armBindStyles.get(string)).withStyle(Style.EMPTY.withColor(ChatFormatting.GOLD))));
        }
        BondageItem.addTextureTooltipText(itemStack, PlayerEventHandler.DYNAMIC_TEXTURE_TAG, list, ChatFormatting.AQUA);
        BondageItem.addTextureTooltipText(itemStack, PlayerEventHandler.DYNAMIC_BLINDFOLD_TEXTURE_TAG, list, ChatFormatting.LIGHT_PURPLE);
        BondageItem.addTextureTooltipText(itemStack, PlayerEventHandler.DYNAMIC_CLOTHES_TEXTURE_TAG, list, ChatFormatting.DARK_PURPLE);
        if (this.properties.isBindHands(itemStack) || this.properties.isBindLegs(itemStack)) {
            list.add((Component)Component.literal(ChatFormatting.GRAY + Component.translatable("item.desc.restrain").getString()));
        }
        if (this.properties.isGag(itemStack)) {
            list.add((Component)Component.literal(ChatFormatting.GRAY + Component.translatable("item.desc.gag").getString()));
        }
        if (this.properties.isDeafen(itemStack)) {
            list.add((Component)Component.literal(ChatFormatting.GRAY + Component.translatable("item.desc.deafen").getString()));
        }
        if (this.properties.isBlind(itemStack)) {
            list.add((Component)Component.literal(ChatFormatting.GRAY + Component.translatable("item.desc.blind").getString()));
        }
        if (EnchantmentHelper.hasBindingCurse((ItemStack)itemStack)) {
            list.add((Component)Component.literal(ChatFormatting.RED + Component.translatable("item.desc.binding_curse").getString()));
        }
    }
}