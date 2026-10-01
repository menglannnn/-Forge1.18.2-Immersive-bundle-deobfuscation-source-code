package com.dev.am.immersive_restraints.mixin.client_mixins.compat;

import com.dev.am.immersive_restraints.aA;
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


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\compat\RenderButtonMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */