package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.bm;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.layers.PlayerItemInHandLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerItemInHandLayer.class})
public abstract class PlayerItemInHandLayerMix {
  @Inject(method = {"renderArmWithItem"}, at = {@At("HEAD")}, cancellable = true)
  private void initMix(LivingEntity paramLivingEntity, ItemStack paramItemStack, ItemTransforms.TransformType paramTransformType, HumanoidArm paramHumanoidArm, PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, int paramInt, CallbackInfo paramCallbackInfo) {
    if (paramLivingEntity instanceof Player) {
      Player player = (Player)paramLivingEntity;
      if (bm.areHandsTied((LivingEntity)player))
        paramCallbackInfo.cancel(); 
    } 
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\PlayerItemInHandLayerMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */