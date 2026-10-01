package com.dev.am.mixin.client_mixins;

import abductionmod.content.B;
import abductionmod.content.N;
import abductionmod.content.aT;
import abductionmod.content.aW;
import abductionmod.content.aZ;
import abductionmod.content.ay;
import abductionmod.content.bm;
import abductionmod.content.i;
import com.dev.am.mod.gamerule.GameruleRegistry;
import com.dev.am.mod.gamerule.TiedPeopleSink;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({KeyboardInput.class})
public abstract class KeyboardInputMix extends Input {
  @Shadow
  @Final
  private Options f_108578_;
  
  @Unique
  private static HashMap<i, Boolean> keyHandledMap = new HashMap<>();
  
  @Unique
  private static int changePosKeyCooldown;
  
  @Unique
  private static boolean checkedChangePosKey;
  
  @Inject(method = {"tick"}, at = {@At("TAIL")})
  private void tickMix(boolean paramBoolean, CallbackInfo paramCallbackInfo) {
    LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
    ay ay = (ay)localPlayer;
    if (localPlayer == null)
      return; 
    if (N.isIsRunning()) {
      abduction$handleKey(i.LEFT, this.f_108578_.f_92086_.m_90857_());
      abduction$handleKey(i.DOWN, this.f_108578_.f_92085_.m_90857_());
      abduction$handleKey(i.UP, this.f_108578_.f_92087_.m_90857_());
      abduction$handleKey(i.RIGHT, this.f_108578_.f_92088_.m_90857_());
      this.f_108573_ = false;
    } 
    if (B.changePosKey.m_90857_() && changePosKeyCooldown == 0) {
      if (!checkedChangePosKey) {
        checkedChangePosKey = true;
        ay.abduction$setHoldingChangePositionKey(true);
        aZ.sendToServer(new aW(true));
      } 
      i i = abduction$getDirection();
      if (i != null) {
        changePosKeyCooldown = 15;
        UUID uUID = localPlayer.m_142081_();
        if (!bm.isTied((LivingEntity)localPlayer)) {
          Entity entity = (Minecraft.m_91087_()).f_91076_;
          if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity;
            if (bm.isTied(livingEntity))
              uUID = livingEntity.m_142081_(); 
          } 
        } 
        aZ.sendToServer(new aT(uUID, i));
      } 
    } else {
      checkedChangePosKey = false;
      ay.abduction$setHoldingChangePositionKey(false);
      aZ.sendToServer(new aW(false));
    } 
    if (changePosKeyCooldown > 0)
      changePosKeyCooldown--; 
    abduction$cancelOriginalMovements(localPlayer);
  }
  
  @Unique
  @Nullable
  private i abduction$getDirection() {
    i i = null;
    if (this.f_108570_) {
      i = i.LEFT;
    } else if (this.f_108571_) {
      i = i.RIGHT;
    } else if (this.f_108573_) {
      i = i.DOWN;
    } else if (this.f_108572_) {
      i = i.UP;
    } else if (this.f_108578_.f_92085_.m_90857_()) {
      i = i.FORWARD;
    } else if (this.f_108578_.f_92087_.m_90857_()) {
      i = i.BACKWARD;
    } 
    return i;
  }
  
  @Unique
  private void abduction$cancelOriginalMovements(LocalPlayer paramLocalPlayer) {
    if (B.changePosKey.m_90857_()) {
      this.f_108572_ = false;
      this.f_108573_ = false;
    } 
    if ((bm.areLegsTied((LivingEntity)paramLocalPlayer) && paramLocalPlayer.m_20096_()) || N.isIsRunning() || B.changePosKey.m_90857_()) {
      this.f_108570_ = false;
      this.f_108568_ = false;
      this.f_108569_ = false;
      this.f_108571_ = false;
      this.f_108567_ = 0.0F;
      this.f_108566_ = 0.0F;
    } 
    if (bm.areHandsTied((LivingEntity)paramLocalPlayer))
      this.f_108573_ = false; 
    if (bm.isFullyTied((LivingEntity)paramLocalPlayer) && paramLocalPlayer.m_5842_() && GameruleRegistry.isGameruleEnabled(paramLocalPlayer.f_19853_, TiedPeopleSink.ID))
      this.f_108572_ = false; 
  }
  
  @Unique
  private void abduction$handleKey(i parami, boolean paramBoolean) {
    if (!keyHandledMap.containsKey(parami))
      keyHandledMap.put(parami, Boolean.valueOf(false)); 
    if (paramBoolean && !((Boolean)keyHandledMap.get(parami)).booleanValue()) {
      keyHandledMap.put(parami, Boolean.valueOf(true));
      N.keyPress(parami);
    } 
    if (!paramBoolean)
      keyHandledMap.put(parami, Boolean.valueOf(false)); 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\KeyboardInputMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */