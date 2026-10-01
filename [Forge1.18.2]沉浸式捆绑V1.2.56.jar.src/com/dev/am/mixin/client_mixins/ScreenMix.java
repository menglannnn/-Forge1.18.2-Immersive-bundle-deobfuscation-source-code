package com.dev.am.mixin.client_mixins;

import abductionmod.content.w;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Screen.class})
public abstract class ScreenMix {
  @Inject(method = {"renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/item/ItemStack;II)V"}, at = {@At("HEAD")})
  private void renderTooltipMix(PoseStack paramPoseStack, ItemStack paramItemStack, int paramInt1, int paramInt2, CallbackInfo paramCallbackInfo) {
    Minecraft minecraft = Minecraft.m_91087_();
    LocalPlayer localPlayer = minecraft.f_91074_;
    long l = minecraft.m_91268_().m_85439_();
    boolean bool = (InputConstants.m_84830_(l, 340) || InputConstants.m_84830_(l, 344)) ? true : false;
    if (localPlayer != null && bool) {
      w.hoveredStack = paramItemStack.m_41777_();
    } else {
      w.hoveredStack = null;
    } 
  }
  
  @Inject(method = {"onClose"}, at = {@At("HEAD")})
  private void onCloseMix(CallbackInfo paramCallbackInfo) {
    w.hoveredStack = null;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\ScreenMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */