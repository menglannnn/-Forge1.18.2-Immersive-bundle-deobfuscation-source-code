package com.dev.am.mixin.tools;

import abductionmod.content.aQ;
import abductionmod.content.aR;
import net.minecraft.world.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({SwordItem.class})
public class SwordMix implements aQ {
  public aR getToolMaterial() {
    return aR.create().with(aR.a.CUTTING, 3.0F).with(aR.a.SCISSORING, 2.0F);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\tools\SwordMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */