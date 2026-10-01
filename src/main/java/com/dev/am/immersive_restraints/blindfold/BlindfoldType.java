/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 */
package com.dev.am.immersive_restraints.blindfold;

import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface BlindfoldType {
    @Nullable
    public ItemStack abduction$getWardrobeItem(String var1);

    @Nonnull
    public ItemStack abduction$getCurrentWardrobeItem();

    public void abduction$saveWardrobeItem(String var1, @Nonnull ItemStack var2);

    public void abduction$setCurrentWardrobeItem(@Nonnull ItemStack var1);

    public void abduction$removeWardrobeItem(String var1);

    public String[] abduction$getAllWardrobeKeys();

    public void abduction$copyWardrobe(Player var1);

    public HashMap<String, ItemStack> abduction$getMap();
}

