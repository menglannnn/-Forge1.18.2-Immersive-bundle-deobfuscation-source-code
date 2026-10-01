package com.dev.am.immersive_restraints.mixin.leash;

import com.dev.am.immersive_restraints.aw;
import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.config.GameruleRegistry;
import com.dev.am.immersive_restraints.config.TeleportWithLeashedPlayers;
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
  public ServerPlayer player;
  
  @Unique
  private List<Entity> abduction$slaveList = new ArrayList<>();
  
  @Inject(method = {"teleport(DDDFFLjava/util/Set;Z)V"}, at = {@At("HEAD")})
  private void teleportH(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2, Set<ClientboundPlayerPositionPacket.RelativeArgument> paramSet, boolean paramBoolean, CallbackInfo paramCallbackInfo) {
    this.abduction$slaveList = bm.getLeashedList(this.player);
  }
  
  @Inject(method = {"teleport(DDDFFLjava/util/Set;Z)V"}, at = {@At("TAIL")})
  private void teleportT(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2, Set<ClientboundPlayerPositionPacket.RelativeArgument> paramSet, boolean paramBoolean, CallbackInfo paramCallbackInfo) {
    if (GameruleRegistry.isGameruleEnabled(this.player.level, TeleportWithLeashedPlayers.ID))
      for (Entity entity : this.abduction$slaveList) {
        if (entity instanceof ServerPlayer) {
          ServerPlayer serverPlayer = (ServerPlayer)entity;
          if (serverPlayer instanceof aw) {
            aw aw = (aw)serverPlayer;
            aw.abduction$dropLeash(false, false);
            if (entity.level == this.player.level) {
              serverPlayer.connection.teleportTo(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2, paramSet, paramBoolean);
            } else {
              serverPlayer.teleportTo((ServerLevel)this.player.level, paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
              serverPlayer.teleportTo();
            } 
            aw.abduction$setLeashedTo((Entity)this.player, true);
          } 
        } 
      }  
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\leash\PlayerTeleportLeashMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */