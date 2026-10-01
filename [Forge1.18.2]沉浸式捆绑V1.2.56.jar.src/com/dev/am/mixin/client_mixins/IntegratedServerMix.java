package com.dev.am.mixin.client_mixins;

import abductionmod.content.bm;
import net.minecraft.util.HttpUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({HttpUtil.class})
public abstract class IntegratedServerMix {
  @Inject(method = {"getAvailablePort"}, at = {@At("RETURN")}, cancellable = true)
  private static void injected(CallbackInfoReturnable<Integer> paramCallbackInfoReturnable) {
    if (bm.isDevEnvironment())
      paramCallbackInfoReturnable.setReturnValue(Integer.valueOf(6969)); 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\IntegratedServerMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */