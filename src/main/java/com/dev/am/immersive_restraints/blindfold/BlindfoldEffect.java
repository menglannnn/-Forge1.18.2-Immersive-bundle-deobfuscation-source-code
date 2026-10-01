/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 */
package com.dev.am.immersive_restraints.blindfold;

import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public interface BlindfoldEffect {
    @Nullable
    public Entity abduction$getLeashHolder();

    public void abduction$setLeashedTo(Entity var1, boolean var2);

    public boolean abduction$canBeLeashedInstantly(Player var1);

    public void abduction$dropLeash(boolean var1, boolean var2);

    public void abduction$setDelayedLeashHolderId(int var1);
}

