package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.w;
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
    Minecraft minecraft = Minecraft.getInstance();
    LocalPlayer localPlayer = minecraft.player;
    long l = minecraft.getWindow().getWindow();
    boolean bool = (InputConstants.isKeyDown(l, 340) || InputConstants.isKeyDown(l, 344)) ? true : false;
    if (localPlayer != null && bool) {
      w.hoveredStack = paramItemStack.getOrCreateTagElement();
    } else {
      w.hoveredStack = null;
    } 
  }
  
  @Inject(method = {"onClose"}, at = {@At("HEAD")})
  private void onCloseMix(CallbackInfo paramCallbackInfo) {
    w.hoveredStack = null;
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\ScreenMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */