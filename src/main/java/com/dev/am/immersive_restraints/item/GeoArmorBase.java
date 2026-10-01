/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.item.ArmorMaterial
 *  net.minecraft.world.item.ArmorMaterials
 *  net.minecraft.world.item.Item$Properties
 *  software.bernie.geckolib.core.Animatable
 *  software.bernie.geckolib.core.manager.AnimationData
 *  software.bernie.geckolib.core.manager.AnimationFactory
 *  software.bernie.geckolib.item.GeoArmorItem
 *  software.bernie.geckolib.util.GeckoLibUtil
 */
package com.dev.am.immersive_restraints.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.core.Animatable;
import software.bernie.geckolib.core.manager.AnimationData;
import software.bernie.geckolib.core.manager.AnimationFactory;
import software.bernie.geckolib.item.GeoArmorItem;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GeoArmorBase
extends GeoArmorItem
implements Animatable {
    public AnimationFactory factory = GeckoLibUtil.createFactory((Animatable)this);
    private final String resourceName;

    public GeoArmorBase(Item.Properties properties, EquipmentSlot equipmentSlot, String string) {
        super((ArmorMaterial)ArmorMaterials.GOLD, equipmentSlot, properties);
        this.resourceName = string;
    }

    public void registerControllers(AnimationData animationData) {
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public AnimationFactory getFactory() {
        return this.factory;
    }
}

