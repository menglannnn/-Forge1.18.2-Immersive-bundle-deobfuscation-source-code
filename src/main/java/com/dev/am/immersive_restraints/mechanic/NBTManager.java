/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.world.item.ItemStack
 *  org.jetbrains.annotations.Nullable
 */
package com.dev.am.immersive_restraints.mechanic;

import java.util.ArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class bw {
    public static final ArrayList<String> allModifiedTags = new ArrayList();
    public static final String COLLAR_DISPLAY_NAME_TAG = bw.registerModifiedTag("collarDisplayName");
    public static final String KEY_VALUE_TAG = "keyValue";
    public static final String IS_LOCKED_TAG = "isLocked";
    public static final String NO_DROP_ON_DEATH_TAG = "noDropOnDeath";
    public static final String ARMOR_VISIBILITY_TAG = "armorVisibility";
    public static final String GAG_TYPE_TAG = bw.registerModifiedTag("gagType");
    public static final String ARM_BIND_TYPE_TAG = bw.registerModifiedTag("armBindType");
    public static final String DYNAMIC_TEXTURE_TAG = bw.registerModifiedTag("dynamicTexture");
    public static final String DYNAMIC_BLINDFOLD_TEXTURE_TAG = bw.registerModifiedTag("blindfoldDynamicTexture");
    public static final String DYNAMIC_CLOTHES_TEXTURE_TAG = bw.registerModifiedTag("clothesDynamicTexture");
    public static final String DYNAMIC_ITEM_TEXTURE_TAG = bw.registerModifiedTag("itemDynamicTexture");
    public static final String ARM_SHAPE_TAG = bw.registerModifiedTag("armShape");
    public static final String HIDE_ORIGINAL_SKIN_PARTS_TAG = bw.registerModifiedTag("hideOriginalSkin");
    public static final String STRUGGLE_ADDED_RESISTANCE_TAG = "struggleAddedResistance";
    public static final String DATA_AM_ADJUSTMENT_MAP_TAG = bw.registerModifiedTag("abduction$AdjustmentData");

    public static void setCustomTag(ItemStack itemStack, String string, String string2) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        compoundTag.putString(string2, string);
    }

    public static void setCustomTag(ItemStack itemStack, Integer n2, String string) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        compoundTag.putInt(string, n2.intValue());
    }

    public static void setCustomTag(ItemStack itemStack, CompoundTag compoundTag, String string) {
        CompoundTag compoundTag2 = itemStack.getOrCreateTag();
        compoundTag2.put(string, (Tag)compoundTag);
    }

    public static void setCustomTag(ItemStack itemStack, Float f, String string) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        compoundTag.putFloat(string, f.floatValue());
    }

    public static void setCustomTag(ItemStack itemStack, Boolean bl2, String string) {
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        compoundTag.putBoolean(string, bl2.booleanValue());
    }

    public static boolean removeCustomTag(ItemStack itemStack, String string) {
        CompoundTag compoundTag = itemStack.getTag();
        if (compoundTag != null && compoundTag.contains(string)) {
            compoundTag.remove(string);
            return true;
        }
        return false;
    }

    public static boolean hasCustomTag(ItemStack itemStack, String string) {
        if (itemStack.getTag() == null) {
            return false;
        }
        CompoundTag compoundTag = itemStack.getTag();
        return compoundTag.contains(string);
    }

    @Nullable
    public static String getCustomTag(ItemStack itemStack, String string) {
        CompoundTag compoundTag = itemStack.getTag();
        if (compoundTag != null && compoundTag.contains(string)) {
            return compoundTag.getString(string);
        }
        return null;
    }

    @Nullable
    public static CompoundTag getCustomTagCompound(ItemStack itemStack, String string) {
        CompoundTag compoundTag = itemStack.getTag();
        if (compoundTag != null && compoundTag.contains(string)) {
            return compoundTag.getCompound(string);
        }
        return null;
    }

    @Nullable
    public static Integer getCustomTagInt(ItemStack itemStack, String string) {
        CompoundTag compoundTag = itemStack.getTag();
        if (compoundTag != null && compoundTag.contains(string)) {
            return compoundTag.getInt(string);
        }
        return null;
    }

    @Nullable
    public static Float getCustomTagFloat(ItemStack itemStack, String string) {
        CompoundTag compoundTag = itemStack.getTag();
        if (compoundTag != null && compoundTag.contains(string)) {
            return Float.valueOf(compoundTag.getFloat(string));
        }
        return null;
    }

    @Nullable
    public static Boolean getCustomTagBool(ItemStack itemStack, String string) {
        CompoundTag compoundTag = itemStack.getTag();
        if (compoundTag != null && compoundTag.contains(string)) {
            return compoundTag.getBoolean(string);
        }
        return null;
    }

    public static String registerModifiedTag(String string) {
        allModifiedTags.add(string);
        return string;
    }
}

