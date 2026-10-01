package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.B;
import com.dev.am.immersive_restraints.N;
import com.dev.am.immersive_restraints.aT;
import com.dev.am.immersive_restraints.aW;
import com.dev.am.immersive_restraints.aZ;
import com.dev.am.immersive_restraints.ay;
import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.i;
import com.dev.am.immersive_restraints.config.GameruleRegistry;
import com.dev.am.immersive_restraints.config.TiedPeopleSink;
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
  private Options options;
  
  @Unique
  private static HashMap<i, Boolean> keyHandledMap = new HashMap<>();
  
  @Unique
  private static int changePosKeyCooldown;
  
  @Unique
  private static boolean checkedChangePosKey;
  
  @Inject(method = {"tick"}, at = {@At("TAIL")})
  private void tickMix(boolean paramBoolean, CallbackInfo paramCallbackInfo) {
    LocalPlayer localPlayer = (Minecraft.getInstance()).player;
    ay ay = (ay)localPlayer;
    if (localPlayer == null)
      return; 
    if (N.isIsRunning()) {
      abduction$handleKey(i.LEFT, this.options.keyLeft.isDown());
      abduction$handleKey(i.DOWN, this.options.keyDown.isDown());
      abduction$handleKey(i.UP, this.options.keyUp.isDown());
      abduction$handleKey(i.RIGHT, this.options.keyRight.isDown());
      this.jumping = false;
    } 
    if (B.changePosKey.isDown() && changePosKeyCooldown == 0) {
      if (!checkedChangePosKey) {
        checkedChangePosKey = true;
        ay.abduction$setHoldingChangePositionKey(true);
        aZ.sendToServer(new aW(true));
      } 
      i i = abduction$getDirection();
      if (i != null) {
        changePosKeyCooldown = 15;
        UUID uUID = localPlayer.getId();
        if (!bm.isTied((LivingEntity)localPlayer)) {
          Entity entity = (Minecraft.getInstance()).crosshairPickEntity;
          if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity;
            if (bm.isTied(livingEntity))
              uUID = livingEntity.getId(); 
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
    if (this.up) {
      i = i.LEFT;
    } else if (this.down) {
      i = i.RIGHT;
    } else if (this.jumping) {
      i = i.DOWN;
    } else if (this.left) {
      i = i.UP;
    } else if (this.options.keyDown.isDown()) {
      i = i.FORWARD;
    } else if (this.options.keyUp.isDown()) {
      i = i.BACKWARD;
    } 
    return i;
  }
  
  @Unique
  private void abduction$cancelOriginalMovements(LocalPlayer paramLocalPlayer) {
    if (B.changePosKey.isDown()) {
      this.left = false;
      this.jumping = false;
    } 
    if ((bm.areLegsTied((LivingEntity)paramLocalPlayer) && paramLocalPlayer.isOnGround()) || N.isIsRunning() || B.changePosKey.isDown()) {
      this.up = false;
      this.forwardImpulse = false;
      this.leftImpulse = false;
      this.down = false;
      this.moveForward = 0.0F;
      this.moveStrafe = 0.0F;
    } 
    if (bm.areHandsTied((LivingEntity)paramLocalPlayer))
      this.jumping = false; 
    if (bm.isFullyTied((LivingEntity)paramLocalPlayer) && paramLocalPlayer.isInWater() && GameruleRegistry.isGameruleEnabled(paramLocalPlayer.level, TiedPeopleSink.ID))
      this.left = false; 
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


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\KeyboardInputMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */