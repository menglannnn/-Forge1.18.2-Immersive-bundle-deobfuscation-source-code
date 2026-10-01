/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.BufferBuilder
 *  com.mojang.blaze3d.vertex.DefaultVertexFormat
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.PoseStack$Pose
 *  com.mojang.blaze3d.vertex.Tesselator
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  com.mojang.blaze3d.vertex.VertexFormat$Mode
 *  com.mojang.math.Matrix4f
 *  com.mojang.math.Vector3f
 *  io.github.kosmx.bendylib.impl.BendableCuboid$Quad
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.PlayerModel
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.client.renderer.GameRenderer
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TextComponent
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  top.theillusivec4.curios.api.SlotResult
 */
package com.dev.am.immersive_restraints.event;

import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.item.PartVisibilityEnum;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.item.BondageItem;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.item.PartVisibility;
import com.dev.am.immersive_restraints.core.ModCore;
import com.dev.am.immersive_restraints.animation.AnimationParser;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import io.github.kosmx.bendylib.impl.BendableCuboid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.SlotResult;

public class InteractEventHandler {
    public static Map<String, EntityRenderer<? extends Player>> abduction$playerRenderersSlim = new HashMap<String, EntityRenderer<? extends Player>>();
    public static Map<String, EntityRenderer<? extends Player>> abduction$playerRenderersFat = new HashMap<String, EntityRenderer<? extends Player>>();

    public static void appendArmorVisibilityTooltip(ItemStack itemStack, List<Component> list) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        if (localPlayer != null && EntityEventHandler.isTied((LivingEntity)localPlayer)) {
            boolean bl2 = false;
            for (ItemStack itemStack2 : localPlayer.getArmorSlots()) {
                if (itemStack2 != itemStack) continue;
                bl2 = true;
                break;
            }
            if (bl2) {
                list.add((Component)Component.literal(ChatFormatting.GRAY + ChatFormatting.ITALIC.toString() + Component.translatable(EntityEventHandler.shouldDrawArmorStack((LivingEntity)localPlayer, itemStack) ? "item.armor.drawn" : "item.armor.not_drawn").getString()));
            }
        }
    }

    @NotNull
    public static PartVisibilityEnum hidePartsInModel(Player player, PlayerModel<?> playerModel, @Nullable ItemStack itemStack) {
        PartVisibilityEnum PartVisibilityEnum = new PartVisibilityEnum(playerModel);
        List<SlotResult> list = EntityEventHandler.getBondagesForRendering((LivingEntity)player);
        for (SlotResult slotResult : list) {
            Object object;
            Object object2;
            ItemStack itemStack2 = slotResult.stack();
            if (itemStack2 != itemStack && (object2 = PlayerEventHandler.getCustomTag(itemStack2, PlayerEventHandler.HIDE_ORIGINAL_SKIN_PARTS_TAG)) != null && ((String)object2).length() == 7) {
                if (((String)object2).charAt(0) == '0') {
                    playerModel.setAllVisible(false);
                } else {
                    block9: for (int i = 1; i < ((String)object2).length(); ++i) {
                        boolean bl2 = ((String)object2).charAt(i) != '0';
                        switch (i) {
                            case 1: {
                                playerModel.body.visible = playerModel.hat.visible = bl2;
                                continue block9;
                            }
                            case 2: {
                                playerModel.body.visible = playerModel.jacket.visible = bl2;
                                continue block9;
                            }
                            case 3: {
                                playerModel.leftArm.visible = playerModel.leftSleeve.visible = bl2;
                                continue block9;
                            }
                            case 4: {
                                playerModel.rightArm.visible = playerModel.rightSleeve.visible = bl2;
                                continue block9;
                            }
                            case 5: {
                                playerModel.leftArm.visible = playerModel.leftPants.visible = bl2;
                                continue block9;
                            }
                            case 6: {
                                playerModel.rightArm.visible = playerModel.rightPants.visible = bl2;
                            }
                        }
                    }
                }
            }
            if (!((object = itemStack2.getItem()) instanceof BondageItem)) continue;
            object2 = (BondageItem)object;
            object = new PartVisibilityEnum(playerModel);
            ((BondageItem)object2).hideBodyParts.accept((PartVisibility)object);
            ((PartVisibilityEnum)object).unloadInto(playerModel);
        }
        return PartVisibilityEnum;
    }

    public static void updatePose() {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer localPlayer = minecraft.player;
        if (localPlayer != null) {
            boolean bl2 = localPlayer.getRandom().nextFloat() < 0.7f;
            for (ItemStack itemStack : localPlayer.getArmorSlots()) {
                Item item = itemStack.getItem();
                if (!itemStack.isEmpty() && !(item instanceof BondageItem)) continue;
                itemStack.serializeNBT();
            }
        }
        AnimationParser.levelLoaded = true;
    }

    public static void renderPanorama(ResourceLocation[] resourceLocationArray, Minecraft minecraft, float f, float f2, float f3, CallbackInfo callbackInfo) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.begin();
        Matrix4f matrix4f = Matrix4f.orthographic((double)85.0, (float)((float)minecraft.getWindow().getWidth() / (float)minecraft.getWindow().getHeight()), (float)0.05f, (float)10.0f);
        RenderSystem.setShaderTexture0();
        RenderSystem.setProjectionMatrix((Matrix4f)matrix4f);
        PoseStack poseStack = RenderSystem.getModelViewStack();
        poseStack.pushPose();
        poseStack.translate();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        RenderSystem.applyModelViewMatrix();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest((boolean)false);
        RenderSystem.enableCull();
        for (int i = 0; i < 4; ++i) {
            poseStack.pushPose();
            float f4 = ((float)(i % 2) / 2.0f - 0.5f) / 256.0f;
            float f5 = ((float)(i / 2) / 2.0f - 0.5f) / 256.0f;
            poseStack.translate((double)f4, (double)f5, 0.0);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(f));
            poseStack.mulPose(Vector3f.XP.rotationDegrees(f2));
            RenderSystem.applyModelViewMatrix();
            for (int j = 0; j < 6; ++j) {
                RenderSystem.setShaderTexture((int)0, (ResourceLocation)resourceLocationArray[j]);
                bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP_NORMAL);
                int n2 = 255;
                if (j == 0) {
                    bufferBuilder.vertex(-1.0, -1.0, 1.0).uv(0.0f, 0.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(-1.0, 1.0, 1.0).uv(0.0f, 1.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(1.0, 1.0, 1.0).uv(1.0f, 1.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(1.0, -1.0, 1.0).uv(1.0f, 0.0f).color(255, 255, 255, n2).endVertex();
                }
                if (j == 1) {
                    bufferBuilder.vertex(1.0, -1.0, 1.0).uv(0.0f, 0.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(1.0, 1.0, 1.0).uv(0.0f, 1.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(1.0, 1.0, -1.0).uv(1.0f, 1.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(1.0, -1.0, -1.0).uv(1.0f, 0.0f).color(255, 255, 255, n2).endVertex();
                }
                if (j == 2) {
                    bufferBuilder.vertex(1.0, -1.0, -1.0).uv(0.0f, 0.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(1.0, 1.0, -1.0).uv(0.0f, 1.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(-1.0, 1.0, -1.0).uv(1.0f, 1.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(-1.0, -1.0, -1.0).uv(1.0f, 0.0f).color(255, 255, 255, n2).endVertex();
                }
                if (j == 3) {
                    bufferBuilder.vertex(-1.0, -1.0, -1.0).uv(0.0f, 0.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(-1.0, 1.0, -1.0).uv(0.0f, 1.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(-1.0, 1.0, 1.0).uv(1.0f, 1.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(-1.0, -1.0, 1.0).uv(1.0f, 0.0f).color(255, 255, 255, n2).endVertex();
                }
                if (j == 4) {
                    bufferBuilder.vertex(-1.0, -1.0, -1.0).uv(0.0f, 0.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(-1.0, -1.0, 1.0).uv(0.0f, 1.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(1.0, -1.0, 1.0).uv(1.0f, 1.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(1.0, -1.0, -1.0).uv(1.0f, 0.0f).color(255, 255, 255, n2).endVertex();
                }
                if (j == 5) {
                    bufferBuilder.vertex(-1.0, 1.0, 1.0).uv(0.0f, 0.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(-1.0, 1.0, -1.0).uv(0.0f, 1.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(1.0, 1.0, -1.0).uv(1.0f, 1.0f).color(255, 255, 255, n2).endVertex();
                    bufferBuilder.vertex(1.0, 1.0, 1.0).uv(1.0f, 0.0f).color(255, 255, 255, n2).endVertex();
                }
                tesselator.end();
            }
            poseStack.popPose();
            RenderSystem.applyModelViewMatrix();
            RenderSystem.enableColorWrite((boolean)true, (boolean)true, (boolean)true, (boolean)false);
        }
        RenderSystem.enableColorWrite((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        RenderSystem.resetTextureMatrix();
        poseStack.popPose();
        RenderSystem.applyModelViewMatrix();
        RenderSystem.enableDepthTest((boolean)true);
        RenderSystem.disableCull();
        RenderSystem.enableDepthTest();
        callbackInfo.cancel();
    }

    public static void renderCustom(BendableCuboid.Quad[] quadArray, PoseStack.Pose pose, VertexConsumer vertexConsumer, float f, float f2, float f3, float f4, int n2, int n3) {
        for (int i = 0; i < quadArray.length; ++i) {
            BendableCuboid.Quad quad = quadArray[i];
            quad.render(pose, vertexConsumer, n2, n3, i % 2 == 0 ? f * 2.0f : f, i % 2 == 0 ? 0.0f : f2, i % 2 == 0 ? 0.0f : f3, f4);
        }
    }

    public static void updateLevel(CallbackInfo callbackInfo, PoseStack poseStack) {
        poseStack.pushPose();
        poseStack.translate();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        RenderSystem.applyModelViewMatrix();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableColorWrite((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        RenderSystem.resetTextureMatrix();
        callbackInfo.cancel();
        poseStack.popPose();
    }
}

