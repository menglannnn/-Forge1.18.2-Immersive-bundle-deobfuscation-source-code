package com.dev.am.immersive_restraints.mixin.tools;

import com.dev.am.immersive_restraints.aQ;
import com.dev.am.immersive_restraints.aR;
import net.minecraft.world.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({SwordItem.class})
public class SwordMix implements aQ {
  public aR getToolMaterial() {
    return aR.create().with(aR.a.CUTTING, 3.0F).with(aR.a.SCISSORING, 2.0F);
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\tools\SwordMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */