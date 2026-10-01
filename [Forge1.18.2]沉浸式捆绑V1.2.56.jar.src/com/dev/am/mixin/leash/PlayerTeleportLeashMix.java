package com.dev.am.mixin.leash;

import abductionmod.content.aw;
import abductionmod.content.bm;
import com.dev.am.mod.gamerule.GameruleRegistry;
import com.dev.am.mod.gamerule.TeleportWithLeashedPlayers;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ServerGamePacketListenerImpl.class})
public abstract class PlayerTeleportLeashMix {
  @Shadow
  public ServerPlayer f_9743_;
  
  @Unique
  private List<Entity> abduction$slaveList = new ArrayList<>();
  
  @Inject(method = {"teleport(DDDFFLjava/util/Set;Z)V"}, at = {@At("HEAD")})
  private void teleportH(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2, Set<ClientboundPlayerPositionPacket.RelativeArgument> paramSet, boolean paramBoolean, CallbackInfo paramCallbackInfo) {
    this.abduction$slaveList = bm.getLeashedList(this.f_9743_);
  }
  
  @Inject(method = {"teleport(DDDFFLjava/util/Set;Z)V"}, at = {@At("TAIL")})
  private void teleportT(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2, Set<ClientboundPlayerPositionPacket.RelativeArgument> paramSet, boolean paramBoolean, CallbackInfo paramCallbackInfo) {
    if (GameruleRegistry.isGameruleEnabled(this.f_9743_.f_19853_, TeleportWithLeashedPlayers.ID))
      for (Entity entity : this.abduction$slaveList) {
        if (entity instanceof ServerPlayer) {
          ServerPlayer serverPlayer = (ServerPlayer)entity;
          if (serverPlayer instanceof aw) {
            aw aw = (aw)serverPlayer;
            aw.abduction$dropLeash(false, false);
            if (entity.f_19853_ == this.f_9743_.f_19853_) {
              serverPlayer.f_8906_.m_143617_(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2, paramSet, paramBoolean);
            } else {
              serverPlayer.m_8999_((ServerLevel)this.f_9743_.f_19853_, paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
              serverPlayer.m_20091_();
            } 
            aw.abduction$setLeashedTo((Entity)this.f_9743_, true);
          } 
        } 
      }  
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\leash\PlayerTeleportLeashMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */