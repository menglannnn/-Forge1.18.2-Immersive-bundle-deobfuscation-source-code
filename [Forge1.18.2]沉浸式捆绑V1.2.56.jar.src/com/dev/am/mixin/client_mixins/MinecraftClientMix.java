package com.dev.am.mixin.client_mixins;

import abductionmod.content.B;
import abductionmod.content.N;
import abductionmod.content.aZ;
import abductionmod.content.bb;
import abductionmod.content.be;
import abductionmod.content.bh;
import abductionmod.content.bk;
import abductionmod.content.bm;
import abductionmod.content.i;
import abductionmod.content.m;
import abductionmod.content.w;
import com.dev.am.mod.gamerule.Gamerules;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Minecraft.class})
public abstract class MinecraftClientMix {
  @Unique
  private static boolean handledMinigamePress;
  
  @Shadow
  @Nullable
  public LocalPlayer f_91074_;
  
  @Shadow
  @Nullable
  public HitResult f_91077_;
  
  @Unique
  private static void abduction$handleKeyGame() {
    LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
    boolean bool = ((m.getAnimationState((Player)localPlayer)).struggle != null) ? true : false;
    if (!N.isIsRunning() && B.untyingKey.m_90857_() && !handledMinigamePress && bool) {
      handledMinigamePress = true;
      N.start(parami -> aZ.sendToServer(new be(false, parami)), parami -> aZ.sendToServer(new be(true, parami)), () -> (paramLocalPlayer.m_6084_() && bm.getFirstBondageWithBaseResistance((LivingEntity)paramLocalPlayer, bm.struggleStackPredicate) != null));
    } 
    if (N.isIsRunning() && ((B.untyingKey.m_90857_() && !handledMinigamePress) || !bool)) {
      handledMinigamePress = true;
      N.stop();
    } 
    if (!B.untyingKey.m_90857_())
      handledMinigamePress = false; 
    N.tick();
  }
  
  @Inject(method = {"tick"}, at = {@At("HEAD")})
  private void tickMix(CallbackInfo paramCallbackInfo) {
    LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
    if (localPlayer == null) {
      m.playingAnimations.clear();
      Gamerules.gameruleValuesClient.clear();
      w.reset();
    } 
    if (localPlayer != null) {
      for (w.a a : w.tyingBars.values()) {
        if (a.tyingBarDisappearTime > 0)
          a.tyingBarDisappearTime--; 
      } 
      w.tyingBars.entrySet().removeIf(paramEntry -> (((w.a)paramEntry.getValue()).tyingBarDisappearTime == 0));
      abduction$handleKeyGame();
      if (B.tightenKey.m_90857_() && this.f_91077_ != null && !bm.areHandsTied((LivingEntity)localPlayer)) {
        HitResult hitResult = this.f_91077_;
        if (hitResult instanceof EntityHitResult) {
          EntityHitResult entityHitResult = (EntityHitResult)hitResult;
          aZ.sendToServer(new bb(entityHitResult.m_82443_().m_142081_()));
        } 
      } 
      if (bm.isDevEnvironment() && B.debugKey.m_90857_())
        Minecraft.m_91087_().m_91152_((Screen)new bk((LivingEntity)this.f_91074_)); 
      if (B.talkAreaPrefKey.m_90859_())
        aZ.sendToServer(new bh()); 
    } 
  }
  
  @Inject(method = {"runTick"}, at = {@At("HEAD")})
  private void runTickMix(CallbackInfo paramCallbackInfo) {
    N.tickFrame();
  }
  
  @Inject(method = {"continueAttack"}, at = {@At("HEAD")}, cancellable = true)
  private void continueAttackMix(CallbackInfo paramCallbackInfo) {
    LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
    if (bm.areHandsTied((LivingEntity)localPlayer)) {
      paramCallbackInfo.cancel();
      return;
    } 
    if (bm.hasMittens((LivingEntity)localPlayer)) {
      HitResult hitResult = this.f_91077_;
      if (hitResult instanceof EntityHitResult) {
        EntityHitResult entityHitResult = (EntityHitResult)hitResult;
      } else {
        paramCallbackInfo.cancel();
        return;
      } 
    } 
  }
  
  @Inject(method = {"startAttack"}, at = {@At("HEAD")}, cancellable = true)
  private void startAttackMix(CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
    if (bm.areHandsTied((LivingEntity)localPlayer)) {
      localPlayer.m_6674_(InteractionHand.MAIN_HAND);
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false));
      return;
    } 
    if (bm.hasMittens((LivingEntity)localPlayer)) {
      HitResult hitResult = this.f_91077_;
      if (hitResult instanceof EntityHitResult) {
        EntityHitResult entityHitResult = (EntityHitResult)hitResult;
      } else {
        localPlayer.m_6674_(InteractionHand.MAIN_HAND);
        paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false));
        return;
      } 
    } 
  }
  
  @Inject(method = {"startUseItem"}, at = {@At("HEAD")}, cancellable = true)
  private void startUseItem(CallbackInfo paramCallbackInfo) {
    LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
    if (bm.areHandsTied((LivingEntity)localPlayer)) {
      if (!(localPlayer.m_21205_().m_41720_() instanceof abductionmod.content.aF)) {
        paramCallbackInfo.cancel();
        return;
      } 
      this.f_91077_ = (HitResult)BlockHitResult.m_82426_(localPlayer.m_20182_(), localPlayer.m_6350_(), localPlayer.m_142538_());
    } 
    if (bm.hasMittens((LivingEntity)localPlayer)) {
      boolean bool = false;
      HitResult hitResult = this.f_91077_;
      if (hitResult instanceof BlockHitResult) {
        BlockHitResult blockHitResult = (BlockHitResult)hitResult;
        Block block = localPlayer.f_19853_.m_8055_(blockHitResult.m_82425_()).m_60734_();
        bool = (block instanceof net.minecraft.world.level.block.ButtonBlock || block instanceof net.minecraft.world.level.block.LeverBlock) ? true : false;
      } 
      if (!bool) {
        paramCallbackInfo.cancel();
        return;
      } 
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\MinecraftClientMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */