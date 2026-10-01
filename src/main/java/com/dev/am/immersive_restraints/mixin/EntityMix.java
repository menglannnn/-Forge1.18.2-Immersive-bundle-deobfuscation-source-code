package com.dev.am.immersive_restraints.mixin;

import com.dev.am.immersive_restraints.aw;
import com.dev.am.immersive_restraints.ay;
import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.k;
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
      if (bm.areLegsTied((LivingEntity)player) || (bm.isTied((LivingEntity)player) && player.onGround()))
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
      AABB aABB = new AABB(player.getX() - 7.0D, player.getY() - 7.0D, player.getZ() - 7.0D, player.getX() + 7.0D, player.getY() + 7.0D, player.getZ() + 7.0D);
      for (Player player1 : player.level.getEntitiesOfClass(Player.class, aABB)) {
        if (player1 instanceof aw) {
          aw aw = (aw)player1;
          if (aw.abduction$getLeashHolder() == entity) {
            paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(player1.canSee(paramEntity, true)));
            return;
          } 
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\EntityMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */