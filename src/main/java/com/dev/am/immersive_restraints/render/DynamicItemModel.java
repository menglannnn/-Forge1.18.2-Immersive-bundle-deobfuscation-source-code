/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.AnimatedGeoModel
 */
package com.dev.am.immersive_restraints.render;

import com.dev.am.immersive_restraints.render.GeoArmorModelBase;
import com.dev.am.immersive_restraints.render.GeoArmorRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class DynamicItemModel
extends AnimatedGeoModel<GeoArmorRenderer> {
    public ResourceLocation getModelLocation(GeoArmorRenderer aX2) {
        return new ResourceLocation("immersive_restraints", "geo/dynamic_item_model.geo.json");
    }

    public ResourceLocation getTextureLocation(GeoArmorRenderer aX2) {
        return new ResourceLocation("immersive_restraints", "textures/misc/hourglass.png");
    }

    public ResourceLocation getAnimationFileLocation(GeoArmorRenderer aX2) {
        return new ResourceLocation("immersive_restraints", "animations/dynamic_item_model.animation.json");
    }
}

