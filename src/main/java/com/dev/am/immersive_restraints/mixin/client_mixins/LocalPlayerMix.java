package com.dev.am.immersive_restraints.mixin.client_mixins;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LocalPlayer.class})
public abstract class LocalPlayerMix {
  @Inject(method = {"tick"}, at = {@At("HEAD")})
  private void tickMix(CallbackInfo paramCallbackInfo) {
    LocalPlayer localPlayer = (LocalPlayer)this;
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\LocalPlayerMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */