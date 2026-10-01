package com.dev.am.mixin.client_mixins.compat;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.theillusivec4.curios.client.gui.CuriosScreen;
import top.theillusivec4.curios.common.inventory.container.CuriosContainer;

@Mixin({CuriosScreen.class})
public abstract class CuriosScreenRefmapMix<T extends AbstractContainerMenu> extends AbstractContainerScreen<CuriosContainer> {
  public CuriosScreenRefmapMix(CuriosContainer paramCuriosContainer, Inventory paramInventory, Component paramComponent) {
    super((AbstractContainerMenu)paramCuriosContainer, paramInventory, paramComponent);
  }
  
  @Redirect(method = {"render"}, at = @At(value = "INVOKE", target = "Ltop/theillusivec4/curios/client/gui/CuriosScreen;renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/network/chat/Component;II)V"))
  private void injected(CuriosScreen paramCuriosScreen, PoseStack paramPoseStack, Component paramComponent, int paramInt1, int paramInt2) {}
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\compat\CuriosScreenRefmapMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */