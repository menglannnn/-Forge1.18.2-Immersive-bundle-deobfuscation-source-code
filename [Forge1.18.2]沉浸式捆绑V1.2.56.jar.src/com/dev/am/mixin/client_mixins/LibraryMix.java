package com.dev.am.mixin.client_mixins;

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


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\LibraryMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */