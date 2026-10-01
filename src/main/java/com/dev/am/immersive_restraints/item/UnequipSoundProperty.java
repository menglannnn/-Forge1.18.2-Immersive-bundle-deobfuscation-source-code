/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 */
package com.dev.am.immersive_restraints.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

/*
 * Renamed from abductionmod.content.aq
 */
public class UnequipSoundProperty
extends MobEffect {
    public UnequipSoundProperty(MobEffectCategory mobEffectCategory, int n2) {
        super(mobEffectCategory, n2);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, "0cf3abd2-712c-4ce3-8624-0d1e2c1f9bad", -10.0, AttributeModifier.Operation.ADDITION);
    }

    public static int getDuration() {
        return 400;
    }

    public void applyEffectTick(LivingEntity livingEntity, int n2) {
        super.applyEffectTick(livingEntity, n2);
        MobEffectInstance mobEffectInstance = livingEntity.getEffect((MobEffect)this);
        if (mobEffectInstance == null) {
            return;
        }
        livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, mobEffectInstance.getDuration(), n2, false, false, false));
        livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, mobEffectInstance.getDuration(), n2, false, false, false));
    }

    public boolean isDurationEffectTick(int n2, int n3) {
        return true;
    }
}

