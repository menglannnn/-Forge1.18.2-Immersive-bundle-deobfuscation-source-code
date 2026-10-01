package com.dev.am.mixin.client_mixins.compat;

import abductionmod.content.aA;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import top.theillusivec4.curios.client.gui.RenderButton;
import top.theillusivec4.curios.common.inventory.CurioSlot;

@Mixin(value = {RenderButton.class}, remap = false)
public abstract class RenderButtonMix implements aA {
  @Shadow
  @Final
  private CurioSlot slot;
  
  public CurioSlot getSlot() {
    return this.slot;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\compat\RenderButtonMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */