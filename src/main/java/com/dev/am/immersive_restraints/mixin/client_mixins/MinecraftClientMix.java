package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.B;
import com.dev.am.immersive_restraints.N;
import com.dev.am.immersive_restraints.aZ;
import com.dev.am.immersive_restraints.bb;
import com.dev.am.immersive_restraints.be;
import com.dev.am.immersive_restraints.bh;
import com.dev.am.immersive_restraints.bk;
import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.i;
import com.dev.am.immersive_restraints.m;
import com.dev.am.immersive_restraints.w;
import com.dev.am.immersive_restraints.config.Gamerules;
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
  public LocalPlayer player;
  
  @Shadow
  @Nullable
  public HitResult hitResult;
  
  @Unique
  private static void abduction$handleKeyGame() {
    LocalPlayer localPlayer = (Minecraft.getInstance()).player;
    boolean bool = ((m.getAnimationState((Player)localPlayer)).struggle != null) ? true : false;
    if (!N.isIsRunning() && B.untyingKey.isDown() && !handledMinigamePress && bool) {
      handledMinigamePress = true;
      N.start(parami -> aZ.sendToServer(new be(false, parami)), parami -> aZ.sendToServer(new be(true, parami)), () -> (paramLocalPlayer.isAlive() && bm.getFirstBondageWithBaseResistance((LivingEntity)paramLocalPlayer, bm.struggleStackPredicate) != null));
    } 
    if (N.isIsRunning() && ((B.untyingKey.isDown() && !handledMinigamePress) || !bool)) {
      handledMinigamePress = true;
      N.stop();
    } 
    if (!B.untyingKey.isDown())
      handledMinigamePress = false; 
    N.tick();
  }
  
  @Inject(method = {"tick"}, at = {@At("HEAD")})
  private void tickMix(CallbackInfo paramCallbackInfo) {
    LocalPlayer localPlayer = (Minecraft.getInstance()).player;
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
      if (B.tightenKey.isDown() && this.hitResult != null && !bm.areHandsTied((LivingEntity)localPlayer)) {
        HitResult hitResult = this.hitResult;
        if (hitResult instanceof EntityHitResult) {
          EntityHitResult entityHitResult = (EntityHitResult)hitResult;
          aZ.sendToServer(new bb(entityHitResult.getEntity().getId()));
        } 
      } 
      if (bm.isDevEnvironment() && B.debugKey.isDown())
        Minecraft.getInstance().setScreen((Screen)new bk((LivingEntity)this.player)); 
      if (B.talkAreaPrefKey.wasPressed())
        aZ.sendToServer(new bh()); 
    } 
  }
  
  @Inject(method = {"runTick"}, at = {@At("HEAD")})
  private void runTickMix(CallbackInfo paramCallbackInfo) {
    N.tickFrame();
  }
  
  @Inject(method = {"continueAttack"}, at = {@At("HEAD")}, cancellable = true)
  private void continueAttackMix(CallbackInfo paramCallbackInfo) {
    LocalPlayer localPlayer = (Minecraft.getInstance()).player;
    if (bm.areHandsTied((LivingEntity)localPlayer)) {
      paramCallbackInfo.cancel();
      return;
    } 
    if (bm.hasMittens((LivingEntity)localPlayer)) {
      HitResult hitResult = this.hitResult;
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
    LocalPlayer localPlayer = (Minecraft.getInstance()).player;
    if (bm.areHandsTied((LivingEntity)localPlayer)) {
      localPlayer.swing(InteractionHand.MAIN_HAND);
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false));
      return;
    } 
    if (bm.hasMittens((LivingEntity)localPlayer)) {
      HitResult hitResult = this.hitResult;
      if (hitResult instanceof EntityHitResult) {
        EntityHitResult entityHitResult = (EntityHitResult)hitResult;
      } else {
        localPlayer.swing(InteractionHand.MAIN_HAND);
        paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false));
        return;
      } 
    } 
  }
  
  @Inject(method = {"startUseItem"}, at = {@At("HEAD")}, cancellable = true)
  private void startUseItem(CallbackInfo paramCallbackInfo) {
    LocalPlayer localPlayer = (Minecraft.getInstance()).player;
    if (bm.areHandsTied((LivingEntity)localPlayer)) {
      if (!(localPlayer.getMainHandItem().getItem() instanceof abductionmod.content.aF)) {
        paramCallbackInfo.cancel();
        return;
      } 
      this.hitResult = (HitResult)BlockHitResult.miss(localPlayer.getEyePosition(), localPlayer.getLookAngle(), localPlayer.getBoundingBox());
    } 
    if (bm.hasMittens((LivingEntity)localPlayer)) {
      boolean bool = false;
      HitResult hitResult = this.hitResult;
      if (hitResult instanceof BlockHitResult) {
        BlockHitResult blockHitResult = (BlockHitResult)hitResult;
        Block block = localPlayer.level.getBlockState(blockHitResult.getBlockPos()).getBlock();
        bool = (block instanceof net.minecraft.world.level.block.ButtonBlock || block instanceof net.minecraft.world.level.block.LeverBlock) ? true : false;
      } 
      if (!bool) {
        paramCallbackInfo.cancel();
        return;
      } 
    } 
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\MinecraftClientMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */