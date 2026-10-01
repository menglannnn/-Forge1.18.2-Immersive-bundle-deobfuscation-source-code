/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 */
package com.dev.am.immersive_restraints.curios;

import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface CurioSlotType {
    public int am$getTyingTimeout();

    public void am$setTyingTimeout(int var1);

    public int abduction$getAnimationId();

    public void abduction$setAnimationId(int var1);

    public int abduction$getTyerHash();

    public void abduction$setTyerHash(int var1);

    public int abduction$getTyingTime();

    public int abduction$getMaxTyingTime();

    public int abduction$getUntyingPowerSpent();

    public float abduction$getUntyingProgressPercent();

    public void abduction$addUntyingPower(int var1);

    public float abduction$getTyingProgressPercent();

    public int abduction$getUntyingCoolDown();

    public void abduction$setUntyingCoolDown(int var1);

    @Nullable
    public LivingEntity abduction$getTyingEntity();

    public void abduction$setTyingEntity(LivingEntity var1);

    public void abduction$addTyingTime(int var1);

    public void abduction$setPlayerPressing(ItemStack var1);

    public boolean abduction$isFinishedTying();

    public boolean abduction$isFinishedUnTying();

    public boolean abduction$isBeingTied();

    public boolean abduction$isUntying();

    public void abduction$setUntying(boolean var1);

    public boolean abduction$isHoldingChangePositionKey();

    public void abduction$setHoldingChangePositionKey(boolean var1);

    public void abduction$stopTyingProcess();

    public CompoundTag abduction$getAdjustmentOffsetsMap();

    public void abduction$setAdjustmentOffsetsMap(CompoundTag var1);

    public float abduction$getLeashLength();

    public void abduction$setLeashLength(float var1);

    public void abduction$setUntieClicks(int var1);

    public int abduction$getUntieClicks();

    public boolean abduction$getProtectedClothes();

    public void abduction$setProtectedClothes(boolean var1);

    public boolean abduction$getLegsUntied();

    public void abduction$setLegsUntied(boolean var1);
}

