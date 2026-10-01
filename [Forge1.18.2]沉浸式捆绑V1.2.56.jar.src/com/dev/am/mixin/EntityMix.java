package com.dev.am.mixin;

import abductionmod.content.aw;
import abductionmod.content.ay;
import abductionmod.content.bm;
import abductionmod.content.k;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Entity.class})
public abstract class EntityMix {
  @Inject(method = {"getPose"}, at = {@At("TAIL")}, cancellable = true)
  private void getPose(CallbackInfoReturnable<Pose> paramCallbackInfoReturnable) {
    Entity entity = (Entity)this;
    if (entity instanceof LivingEntity) {
      LivingEntity livingEntity = (LivingEntity)entity;
      if (bm.areHandsTied(livingEntity)) {
        paramCallbackInfoReturnable.setReturnValue(Pose.STANDING);
        return;
      } 
    } 
  }
  
  @Inject(method = {"getEyeHeight()F"}, at = {@At("TAIL")}, cancellable = true)
  private void getEyePosition(CallbackInfoReturnable<Float> paramCallbackInfoReturnable) {
    Entity entity = (Entity)this;
    if (entity instanceof ay) {
      ay ay = (ay)entity;
      if (ay.abduction$getAnimationId() != 0) {
        float f = (k.getById(ay.abduction$getAnimationId())).definition.camY;
        paramCallbackInfoReturnable.setReturnValue(Float.valueOf(((Float)paramCallbackInfoReturnable.getReturnValue()).floatValue() + f));
      } 
    } 
  }
  
  @Inject(method = {"isSprinting"}, at = {@At("HEAD")}, cancellable = true)
  private void isSprintingMix(CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    Entity entity = (Entity)this;
    if (entity instanceof Player) {
      Player player = (Player)entity;
      if (bm.areLegsTied((LivingEntity)player) || (bm.isTied((LivingEntity)player) && player.m_20069_()))
        paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false)); 
    } 
  }
  
  @Inject(method = {"startRiding(Lnet/minecraft/world/entity/Entity;Z)Z"}, at = {@At("HEAD")}, cancellable = true)
  private void startRiding(Entity paramEntity, boolean paramBoolean, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    Entity entity = (Entity)this;
    if (entity instanceof Player) {
      Player player = (Player)entity;
      ay ay = (ay)player;
      if (!ay.abduction$isHoldingChangePositionKey())
        return; 
      if (bm.isTied((LivingEntity)player))
        return; 
      AABB aABB = new AABB(player.m_20185_() - 7.0D, player.m_20186_() - 7.0D, player.m_20189_() - 7.0D, player.m_20185_() + 7.0D, player.m_20186_() + 7.0D, player.m_20189_() + 7.0D);
      for (Player player1 : player.f_19853_.m_45976_(Player.class, aABB)) {
        if (player1 instanceof aw) {
          aw aw = (aw)player1;
          if (aw.abduction$getLeashHolder() == entity) {
            paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(player1.m_7998_(paramEntity, true)));
            return;
          } 
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\EntityMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */