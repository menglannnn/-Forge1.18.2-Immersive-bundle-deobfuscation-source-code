package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.br;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ItemInHandRenderer.class})
public class ItemInHandRendererMixin {
  @Inject(method = {"renderHandsWithItems"}, at = {@At("HEAD")}, cancellable = true)
  private void disableDefaultItemIfNeeded(float paramFloat, PoseStack paramPoseStack, MultiBufferSource.BufferSource paramBufferSource, LocalPlayer paramLocalPlayer, int paramInt, CallbackInfo paramCallbackInfo) {
    if (bm.areHandsTied((LivingEntity)paramLocalPlayer))
      paramCallbackInfo.cancel(); 
  }
  
  @ModifyVariable(method = {"renderArmWithItem"}, at = @At("HEAD"), argsOnly = true, ordinal = 0)
  private ItemStack injected(ItemStack paramItemStack) {
    ItemStack itemStack = br.getDynamicIconStack(paramItemStack);
    return (itemStack != null) ? itemStack : paramItemStack;
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\ItemInHandRendererMixin.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */