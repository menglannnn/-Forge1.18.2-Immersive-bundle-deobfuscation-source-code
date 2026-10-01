package com.dev.am.mixin.tools;

import abductionmod.content.aQ;
import abductionmod.content.aR;
import net.minecraft.world.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ShearsItem.class})
public class ShearsMix implements aQ {
  public aR getToolMaterial() {
    return aR.create().with(aR.a.CUTTING, 4.0F).with(aR.a.SCISSORING, 12.0F);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\tools\ShearsMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */