package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.bl;
import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.br;
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
  private static final ResourceLocation BLUR_LOCATION = new ResourceLocation("immersive_restraints", "textures/misc/blind_blur.png");
  
  @Inject(method = {"renderLevel"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GameRenderer;bobHurt(Lcom/mojang/blaze3d/vertex/PoseStack;F)V")})
  private void injectedBefore(CallbackInfo paramCallbackInfo, @Local(ordinal = 1) PoseStack paramPoseStack) {
    Minecraft minecraft = Minecraft.getInstance();
  }
  
  @Inject(method = {"render"}, at = {@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Lighting;setupFor3DItems()V", shift = At.Shift.AFTER)})
  private void renders(float paramFloat, long paramLong, boolean paramBoolean, CallbackInfo paramCallbackInfo) {
    LocalPlayer localPlayer = (Minecraft.getInstance()).player;
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
    LocalPlayer localPlayer = (Minecraft.getInstance()).player;
    if (localPlayer == null)
      return; 
    if (bm.areHandsTied((LivingEntity)localPlayer))
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false)); 
  }
  
  @Unique
  private void renderConfusionOverlay(ResourceLocation paramResourceLocation) {
    int i = Minecraft.getInstance().getWindow().getGuiScaledWidth();
    int j = Minecraft.getInstance().getWindow().getGuiScaledHeight();
    RenderSystem.disableDepthTest();
    RenderSystem.enableDepthTest(false);
    RenderSystem.enableCull();
    RenderSystem.enableBlend();
    RenderSystem.setShader(GameRenderer::getPositionColorShader);
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.setShaderTexture(0, paramResourceLocation);
    Tesselator tesselator = Tesselator.getInstance();
    BufferBuilder bufferBuilder = tesselator.begin();
    bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
    bufferBuilder.vertex(0.0D, j, -90.0D).uv(0.0F, 1.0F).endVertex();
    bufferBuilder.vertex(i, j, -90.0D).uv(1.0F, 1.0F).endVertex();
    bufferBuilder.vertex(i, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
    bufferBuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
    tesselator.end();
    RenderSystem.enableDepthTest(true);
    RenderSystem.enableDepthTest();
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\GameRendererMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */