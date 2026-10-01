/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ArmorItem
 *  software.bernie.geckolib.model.AnimatedGeoModel
 *  software.bernie.geckolib.renderers.geo.GeoArmorRenderer
 */
package com.dev.am.immersive_restraints.render;

import com.dev.am.immersive_restraints.render.GeoArmorModelBase;
import com.dev.am.immersive_restraints.item.GeoArmorBase;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.data.SkinProvider;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import software.bernie.geckolib.model.AnimatedGeoModel;
import software.bernie.geckolib.renderers.geo.GeoArmorRenderer;

public class GeoArmorRendererBase
extends GeoArmorRenderer<GeoArmorBase> {
    public GeoArmorRendererBase() {
        super((AnimatedGeoModel)new GeoArmorModelBase());
        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }

    public ResourceLocation getTextureLocation(GeoArmorBase aZ2) {
        ResourceLocation resourceLocation = SkinProvider.getResourceLocationFromTextureTag(this.itemStack, PlayerEventHandler.DYNAMIC_TEXTURE_TAG, SkinProvider.NO_SKIN_OTHER);
        if (resourceLocation != null) {
            return resourceLocation;
        }
        return super.getTextureLocation((ArmorItem)aZ2);
    }
}

