/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  software.bernie.geckolib.model.AnimatedGeoModel
 */
package com.dev.am.immersive_restraints.render;

import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.render.BondageRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class ColorMarkModel
extends AnimatedGeoModel<BondageRenderer> {
    public ResourceLocation getModelLocation(BondageRenderer aV2) {
        return new ResourceLocation("immersive_restraints", "geo/color_mark.geo.json");
    }

    public ResourceLocation getTextureLocation(BondageRenderer aV2) {
        return new ResourceLocation("immersive_restraints", "textures/item/color_mark.png");
    }

    public ResourceLocation getAnimationFileLocation(BondageRenderer aV2) {
        return new ResourceLocation("immersive_restraints", "animations/color_mark.animation.json");
    }
}

