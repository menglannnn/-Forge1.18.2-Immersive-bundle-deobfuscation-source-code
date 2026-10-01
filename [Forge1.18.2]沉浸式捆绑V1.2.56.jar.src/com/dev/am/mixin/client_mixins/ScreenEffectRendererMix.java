package com.dev.am.mixin.client_mixins;

import abductionmod.content.l;
import abductionmod.content.m;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {ScreenEffectRenderer.class}, remap = false)
public abstract class ScreenEffectRendererMix {
  @Inject(method = {"getOverlayBlock"}, at = {@At("HEAD")}, cancellable = true)
  private static void getOverlayBlock(Player paramPlayer, CallbackInfoReturnable<Pair<BlockState, BlockPos>> paramCallbackInfoReturnable) {
    l l = m.getAnimationState(paramPlayer);
    if (l != null && l.definition.camY != 0.0F)
      paramCallbackInfoReturnable.setReturnValue(null); 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\ScreenEffectRendererMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */