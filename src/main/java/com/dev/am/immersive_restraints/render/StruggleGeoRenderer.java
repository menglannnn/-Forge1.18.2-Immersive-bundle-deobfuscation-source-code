/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.Item
 *  software.bernie.geckolib.model.AnimatedGeoModel
 *  software.bernie.geckolib.renderers.geo.GeoItemRenderer
 */
package com.dev.am.immersive_restraints.render;

import com.dev.am.immersive_restraints.item.PartVisibilityEnum;
import com.dev.am.immersive_restraints.render.DynamicItemModel;
import com.dev.am.immersive_restraints.render.GeoArmorModelBase;
import com.dev.am.immersive_restraints.render.GeoArmorRenderer;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.data.SkinProvider;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.model.AnimatedGeoModel;
import software.bernie.geckolib.renderers.geo.GeoItemRenderer;

public class StruggleGeoRenderer
extends GeoItemRenderer<GeoArmorRenderer> {
    public StruggleGeoRenderer() {
        super((AnimatedGeoModel)new DynamicItemModel());
    }

    public ResourceLocation getTextureLocation(GeoArmorRenderer aX2) {
        ResourceLocation resourceLocation = null;
        if (PlayerEventHandler.hasCustomTag(this.currentItemStack, PlayerEventHandler.DYNAMIC_ITEM_TEXTURE_TAG)) {
            resourceLocation = SkinProvider.getResourceLocationFromTextureTag(this.currentItemStack, PlayerEventHandler.DYNAMIC_ITEM_TEXTURE_TAG, SkinProvider.NO_ITEM_SKIN);
        }
        if (resourceLocation != null) {
            return resourceLocation;
        }
        return super.getTextureLocation((Item)aX2);
    }
}

