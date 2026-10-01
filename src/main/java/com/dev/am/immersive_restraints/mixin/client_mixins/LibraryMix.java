package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.mojang.blaze3d.audio.Library;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Library.class})
public class LibraryMix {
  @Inject(at = {@At("TAIL")}, method = {"init"})
  private void run(CallbackInfo paramCallbackInfo) {}
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\LibraryMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */