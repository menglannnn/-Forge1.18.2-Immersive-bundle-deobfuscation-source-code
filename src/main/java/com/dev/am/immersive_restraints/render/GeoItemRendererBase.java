/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  software.bernie.geckolib.core.util.Color
 *  software.bernie.geckolib.model.AnimatedGeoModel
 *  software.bernie.geckolib.renderers.geo.GeoItemRenderer
 */
package com.dev.am.immersive_restraints.render;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.render.ColorMarkModel;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.render.BondageRenderer;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.util.Color;
import software.bernie.geckolib.model.AnimatedGeoModel;
import software.bernie.geckolib.renderers.geo.GeoItemRenderer;

public class GeoItemRendererBase
extends GeoItemRenderer<BondageRenderer> {
    public RenderType getRenderType(BondageRenderer aV2, float f, PoseStack poseStack, @Nullable MultiBufferSource multiBufferSource, @Nullable VertexConsumer vertexConsumer, int n2, ResourceLocation resourceLocation) {
        return super.getRenderType((Object)aV2, f, poseStack, multiBufferSource, vertexConsumer, n2, resourceLocation);
    }

    public GeoItemRendererBase() {
        super((AnimatedGeoModel)new ColorMarkModel());
    }

    public ResourceLocation getTextureLocation(BondageRenderer aV2) {
        return super.getTextureLocation((Item)aV2);
    }

    public Color getRenderColor(BondageRenderer aV2, float f, PoseStack poseStack, @Nullable MultiBufferSource multiBufferSource, @Nullable VertexConsumer vertexConsumer, int n2) {
        String[] stringArray;
        for (String string : stringArray = new String[]{PlayerEventHandler.DYNAMIC_BLINDFOLD_TEXTURE_TAG, PlayerEventHandler.DYNAMIC_TEXTURE_TAG, PlayerEventHandler.DYNAMIC_TEXTURE_TAG, PlayerEventHandler.DYNAMIC_CLOTHES_TEXTURE_TAG}) {
            String string2 = PlayerEventHandler.getCustomTag(this.currentItemStack, string);
            if (string2 == null) continue;
            return GeoItemRendererBase.colorFromString(string2);
        }
        if (this.currentItemStack.getTag() != null) {
            String string = this.currentItemStack.getTag().toString();
            return GeoItemRendererBase.colorFromString(string);
        }
        return GeoItemRendererBase.colorFromString("");
    }

    @NotNull
    private static Color colorFromString(String string) {
        return Color.ofHSB((float)(Math.abs((float)string.hashCode() % 360.0f) / 360.0f), (float)0.5254902f, (float)2.0784314f);
    }

    public void render(BondageRenderer aV2, PoseStack poseStack, MultiBufferSource multiBufferSource, int n2, ItemStack itemStack) {
        super.render((Item)aV2, poseStack, multiBufferSource, n2, itemStack);
    }
}

