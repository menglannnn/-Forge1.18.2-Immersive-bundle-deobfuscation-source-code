package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.N;
import com.dev.am.immersive_restraints.bi;
import com.dev.am.immersive_restraints.w;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {ForgeIngameGui.class}, remap = false)
public abstract class InGameUiMix extends Gui {
  @Unique
  private static final ResourceLocation AM_BAR_LOCATION = new ResourceLocation("immersive_restraints", "textures/gui/tying_bar.png");
  
  @Unique
  public int abduction$barsRendered;
  
  @Unique
  final int abduction$bindBarWidthPx = 102;
  
  @Unique
  final int abduction$bindBarHeightPx = 8;
  
  public InGameUiMix(Minecraft paramMinecraft) {
    super(paramMinecraft);
  }
  
  @Shadow
  public abstract void setupOverlayRenderState(boolean paramBoolean1, boolean paramBoolean2, @Nullable ResourceLocation paramResourceLocation);
  
  @Inject(method = {"renderHelmet"}, at = {@At("HEAD")})
  private void renders(float paramFloat, PoseStack paramPoseStack, CallbackInfo paramCallbackInfo) {
    abduction$renderTyingBars(paramPoseStack);
  }
  
  @Unique
  private void abduction$renderTyingBars(PoseStack paramPoseStack) {
    setupOverlayRenderState(true, false, AM_BAR_LOCATION);
    this.abduction$barsRendered = 0;
    for (w.a a : w.tyingBars.values()) {
      if (a.tyingType != bi.a.NONE && a.tyingBarDisappearTime > 0 && a.tyingBarProgress > 0.0F)
        abduction$renderTyingBar(paramPoseStack, this.width / 2 - 51, a); 
    } 
    if (N.isIsRunning())
      N.render(paramPoseStack, this); 
  }
  
  @Unique
  public void abduction$renderTyingBar(PoseStack paramPoseStack, int paramInt, w.a parama) {
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.setShaderTexture(0, AM_BAR_LOCATION);
    float f1 = parama.tyingBarProgress;
    float f2 = 0.0F;
    if (f1 > 1.0F) {
      f2 = f1 - 1.0F;
      f1 = 1.0F;
    } 
    int i = (int)(f1 * 102.0F);
    int j = (int)(f2 * 102.0F);
    int k = this.height - 58 + -this.abduction$barsRendered * 9;
    blit(paramPoseStack, paramInt, k, 0, 0, 102, 8);
    if (i > 0) {
      int m = parama.tyingType.getOffset();
      blit(paramPoseStack, paramInt, k, 0, 8 * m, i, 8);
      if (j > 0)
        switch (parama.tyingType) {
          case STRUGGLING:
            blit(paramPoseStack, paramInt, k, 0, 8 * bi.a.STRUGGLING_OVERCHARGE.getOffset(), j, 8);
            break;
        }  
    } 
    this.abduction$barsRendered++;
    if (w.tyingBars.keySet().size() > 1)
      drawString(paramPoseStack, this.minecraft.font, parama.entityName, paramInt + 51, k, 16777215); 
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\InGameUiMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */