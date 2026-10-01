package com.dev.am.mixin.client_mixins;

import abductionmod.content.aw;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ClientPacketListener.class})
public abstract class ClientPacketListenerMix {
  @Shadow
  private ClientLevel f_104889_;
  
  @Inject(method = {"handleEntityLinkPacket"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;getEntity(I)Lnet/minecraft/world/entity/Entity;", shift = At.Shift.AFTER)})
  private void handle(ClientboundSetEntityLinkPacket paramClientboundSetEntityLinkPacket, CallbackInfo paramCallbackInfo) {
    Entity entity = this.f_104889_.m_6815_(paramClientboundSetEntityLinkPacket.m_133172_());
    if (entity instanceof aw) {
      aw aw = (aw)entity;
      aw.abduction$setDelayedLeashHolderId(paramClientboundSetEntityLinkPacket.m_133175_());
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\ClientPacketListenerMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */