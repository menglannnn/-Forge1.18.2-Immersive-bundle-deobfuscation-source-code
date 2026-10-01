/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.AnimatedGeoModel
 */
package com.dev.am.immersive_restraints.render;

import com.dev.am.immersive_restraints.render.GeoArmorModelBase;
import com.dev.am.immersive_restraints.item.GeoArmorBase;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class GeoArmorModelBase
extends AnimatedGeoModel<GeoArmorBase> {
    public ResourceLocation getModelLocation(GeoArmorBase aZ2) {
        return new ResourceLocation("immersive_restraints", "geo/" + aZ2.getResourceName() + ".geo.json");
    }

    public ResourceLocation getTextureLocation(GeoArmorBase aZ2) {
        return new ResourceLocation("immersive_restraints", "textures/armor/" + aZ2.getResourceName() + ".png");
    }

    public ResourceLocation getAnimationFileLocation(GeoArmorBase aZ2) {
        return new ResourceLocation("immersive_restraints", "animations/" + aZ2.getResourceName() + ".json");
    }
}

