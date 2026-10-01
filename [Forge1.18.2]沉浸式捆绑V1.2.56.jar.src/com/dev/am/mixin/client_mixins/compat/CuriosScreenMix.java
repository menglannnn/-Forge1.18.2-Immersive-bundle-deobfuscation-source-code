package com.dev.am.mixin.client_mixins.compat;

import abductionmod.content.aA;
import com.dev.am.Abduction;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.client.gui.CuriosScreen;
import top.theillusivec4.curios.common.inventory.container.CuriosContainer;

@Mixin(value = {CuriosScreen.class}, remap = false)
public abstract class CuriosScreenMix<T extends AbstractContainerMenu> extends AbstractContainerScreen<CuriosContainer> {
  public CuriosScreenMix(CuriosContainer paramCuriosContainer, Inventory paramInventory, Component paramComponent) {
    super((AbstractContainerMenu)paramCuriosContainer, paramInventory, paramComponent);
  }
  
  @Inject(at = {@At("TAIL")}, method = {"updateRenderButtons"})
  private void updateRenderButtons(CallbackInfo paramCallbackInfo) {
    this.f_169369_.removeIf(paramWidget -> {
          if (paramWidget instanceof aA) {
            aA aA = (aA)paramWidget;
            if (Abduction.slotNames.contains(aA.getSlot().getIdentifier()));
          } 
          return false;
        });
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\compat\CuriosScreenMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */