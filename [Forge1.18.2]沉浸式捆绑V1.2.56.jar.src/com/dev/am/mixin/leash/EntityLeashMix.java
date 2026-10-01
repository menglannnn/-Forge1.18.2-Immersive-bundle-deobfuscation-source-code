package com.dev.am.mixin.leash;

import abductionmod.content.aw;
import abductionmod.content.ay;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Entity.class})
public abstract class EntityLeashMix {
  @Inject(method = {"interact"}, at = {@At("RETURN")}, cancellable = true)
  private void interactMix(Player paramPlayer, InteractionHand paramInteractionHand, CallbackInfoReturnable<InteractionResult> paramCallbackInfoReturnable) {
    Entity entity = (Entity)this;
    if (paramPlayer instanceof ay) {
      ay ay = (ay)paramPlayer;
      if (ay.am$getTyingTimeout() > 0) {
        paramCallbackInfoReturnable.setReturnValue(InteractionResult.PASS);
        return;
      } 
    } 
    ItemStack itemStack = paramPlayer.m_21120_(paramInteractionHand);
    if (entity instanceof aw) {
      aw aw = (aw)entity;
      if (!entity.m_6084_()) {
        paramCallbackInfoReturnable.setReturnValue(InteractionResult.PASS);
      } else {
        if (itemStack.m_41619_() && aw.abduction$getLeashHolder() != null && aw.abduction$getLeashHolder() != paramPlayer) {
          if (!paramPlayer.f_19853_.f_46443_) {
            Entity entity1 = aw.abduction$getLeashHolder();
            aw.abduction$setLeashedTo((Entity)paramPlayer, true);
            if (entity1 instanceof net.minecraft.world.entity.decoration.LeashFenceKnotEntity)
              entity1.m_146870_(); 
          } 
          paramCallbackInfoReturnable.setReturnValue(InteractionResult.SUCCESS);
          return;
        } 
        if (aw.abduction$getLeashHolder() == paramPlayer) {
          aw.abduction$dropLeash(true, !(paramPlayer.m_150110_()).f_35937_);
          paramCallbackInfoReturnable.setReturnValue(InteractionResult.m_19078_(entity.f_19853_.f_46443_));
        } else {
          InteractionResult interactionResult = abduction$checkAndHandleImportantInteractions(paramPlayer, paramInteractionHand);
          if (interactionResult.m_19077_()) {
            paramCallbackInfoReturnable.setReturnValue(interactionResult);
          } else {
            paramCallbackInfoReturnable.setReturnValue(InteractionResult.PASS);
          } 
        } 
      } 
    } 
  }
  
  @Unique
  private InteractionResult abduction$checkAndHandleImportantInteractions(Player paramPlayer, InteractionHand paramInteractionHand) {
    Entity entity = (Entity)this;
    if (entity instanceof aw) {
      aw aw = (aw)entity;
      ItemStack itemStack = paramPlayer.m_21120_(paramInteractionHand);
      if (itemStack.m_150930_(Items.f_42655_) && aw.abduction$canBeLeashedInstantly(paramPlayer)) {
        aw.abduction$setLeashedTo((Entity)paramPlayer, true);
        itemStack.m_41774_(1);
        return InteractionResult.m_19078_(entity.f_19853_.f_46443_);
      } 
    } 
    return InteractionResult.PASS;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\leash\EntityLeashMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */