package com.dev.am.mixin.client_mixins;

import abductionmod.content.bl;
import abductionmod.content.bm;
import abductionmod.content.br;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({GameRenderer.class})
public abstract class GameRendererMix {
  @Unique
  private static final ResourceLocation BLUR_LOCATION = new ResourceLocation("am", "textures/misc/blind_blur.png");
  
  @Inject(method = {"renderLevel"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;bobHurt(Lcom/mojang/blaze3d/vertex/PoseStack;F)V")})
  private void injectedBefore(CallbackInfo paramCallbackInfo, @Local(ordinal = 1) PoseStack paramPoseStack) {
    Minecraft minecraft = Minecraft.m_91087_();
  }
  
  @Inject(method = {"render"}, at = {@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Lighting;setupFor3DItems()V", shift = At.Shift.AFTER)})
  private void renders(float paramFloat, long paramLong, boolean paramBoolean, CallbackInfo paramCallbackInfo) {
    LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
    if (localPlayer == null)
      return; 
    if (bm.isBlindfolded((LivingEntity)localPlayer)) {
      ResourceLocation resourceLocation = BLUR_LOCATION;
      if (bm.isBlindfolded((LivingEntity)localPlayer)) {
        ItemStack itemStack = bm.getBlindfold((LivingEntity)localPlayer);
        ResourceLocation resourceLocation1 = br.getResourceLocationFromTextureTag(itemStack, bl.DYNAMIC_BLINDFOLD_TEXTURE_TAG);
        if (resourceLocation1 != null)
          resourceLocation = resourceLocation1; 
      } 
      renderConfusionOverlay(resourceLocation);
    } 
  }
  
  @Inject(method = {"shouldRenderBlockOutline"}, at = {@At("HEAD")}, cancellable = true)
  private void shouldRenderBlockOutline(CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
    if (localPlayer == null)
      return; 
    if (bm.areHandsTied((LivingEntity)localPlayer))
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false)); 
  }
  
  @Unique
  private void renderConfusionOverlay(ResourceLocation paramResourceLocation) {
    int i = Minecraft.m_91087_().m_91268_().m_85445_();
    int j = Minecraft.m_91087_().m_91268_().m_85446_();
    RenderSystem.m_69465_();
    RenderSystem.m_69458_(false);
    RenderSystem.m_69453_();
    RenderSystem.m_69478_();
    RenderSystem.m_157427_(GameRenderer::m_172817_);
    RenderSystem.m_157429_(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.m_157456_(0, paramResourceLocation);
    Tesselator tesselator = Tesselator.m_85913_();
    BufferBuilder bufferBuilder = tesselator.m_85915_();
    bufferBuilder.m_166779_(VertexFormat.Mode.QUADS, DefaultVertexFormat.f_85817_);
    bufferBuilder.m_5483_(0.0D, j, -90.0D).m_7421_(0.0F, 1.0F).m_5752_();
    bufferBuilder.m_5483_(i, j, -90.0D).m_7421_(1.0F, 1.0F).m_5752_();
    bufferBuilder.m_5483_(i, 0.0D, -90.0D).m_7421_(1.0F, 0.0F).m_5752_();
    bufferBuilder.m_5483_(0.0D, 0.0D, -90.0D).m_7421_(0.0F, 0.0F).m_5752_();
    tesselator.m_85914_();
    RenderSystem.m_69458_(true);
    RenderSystem.m_69482_();
    RenderSystem.m_157429_(1.0F, 1.0F, 1.0F, 1.0F);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\GameRendererMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */