package com.dev.am.immersive_restraints.mixin.leash;

import com.dev.am.immersive_restraints.aw;
import com.dev.am.immersive_restraints.ay;
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
    ItemStack itemStack = paramPlayer.getItemInHand(paramInteractionHand);
    if (entity instanceof aw) {
      aw aw = (aw)entity;
      if (!entity.isAlive()) {
        paramCallbackInfoReturnable.setReturnValue(InteractionResult.PASS);
      } else {
        if (itemStack.isEmpty() && aw.abduction$getLeashHolder() != null && aw.abduction$getLeashHolder() != paramPlayer) {
          if (!paramPlayer.level.isClientSide) {
            Entity entity1 = aw.abduction$getLeashHolder();
            aw.abduction$setLeashedTo((Entity)paramPlayer, true);
            if (entity1 instanceof net.minecraft.world.entity.decoration.LeashFenceKnotEntity)
              entity1.discard(); 
          } 
          paramCallbackInfoReturnable.setReturnValue(InteractionResult.SUCCESS);
          return;
        } 
        if (aw.abduction$getLeashHolder() == paramPlayer) {
          aw.abduction$dropLeash(true, !(paramPlayer.getAbilities()).instabuild);
          paramCallbackInfoReturnable.setReturnValue(InteractionResult.sidedSuccess(entity.level.isClientSide));
        } else {
          InteractionResult interactionResult = abduction$checkAndHandleImportantInteractions(paramPlayer, paramInteractionHand);
          if (interactionResult.shouldSwing()) {
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
      ItemStack itemStack = paramPlayer.getItemInHand(paramInteractionHand);
      if (itemStack.is(Items.LEAD) && aw.abduction$canBeLeashedInstantly(paramPlayer)) {
        aw.abduction$setLeashedTo((Entity)paramPlayer, true);
        itemStack.shrink(1);
        return InteractionResult.sidedSuccess(entity.level.isClientSide);
      } 
    } 
    return InteractionResult.PASS;
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\leash\EntityLeashMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */