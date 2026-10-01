/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dev.am.immersive_restraints.item.VulnerabilityType
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Multimap
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.item.Item$Properties
 */
package com.dev.am.immersive_restraints.network;

import com.dev.am.immersive_restraints.core.ModCore;
import com.dev.am.immersive_restraints.network.AnimationSyncPacket;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.animation.AnimationType;
import com.dev.am.immersive_restraints.item.VulnerabilityType;
import com.dev.am.immersive_restraints.command.SetCollarNameCommand;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;

public class SkinSyncPacket
extends AnimationSyncPacket
implements AnimationType {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public SkinSyncPacket() {
        super(new Item.Properties().tab(ModItems.ABDUCTION_ITEM_GROUP).durability(150));
        ImmutableMultimap.Builder builder = ImmutableMultimap.builder();
        builder.put((Object)Attributes.ATTACK_DAMAGE, (Object)new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", 1.0, AttributeModifier.Operation.ADDITION));
        builder.put((Object)Attributes.ATTACK_SPEED, (Object)new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", 0.0, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        return equipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
    }

    @Override
    public VulnerabilityType getToolMaterial() {
        return bb.create().with(VulnerabilityType.Kind.CUTTING, 10.0f).with(VulnerabilityType.Kind.SCISSORING, 5.0f);
    }
}

