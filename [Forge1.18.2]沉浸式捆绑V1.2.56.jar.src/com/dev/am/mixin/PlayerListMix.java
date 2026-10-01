package com.dev.am.mixin;

import abductionmod.content.ax;
import abductionmod.content.bm;
import com.dev.am.mod.gamerule.DefaultTalkArea;
import com.dev.am.mod.gamerule.EarplugsMuteChat;
import com.dev.am.mod.gamerule.GameruleRegistry;
import java.util.UUID;
import java.util.function.Function;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerList.class})
public abstract class PlayerListMix {
  @Unique
  private boolean am$someoneReceivedMessage;
  
  @Shadow
  public abstract MinecraftServer m_7873_();
  
  @Inject(method = {"broadcastMessage(Lnet/minecraft/network/chat/Component;Ljava/util/function/Function;Lnet/minecraft/network/chat/ChatType;Ljava/util/UUID;)V"}, at = {@At("HEAD")})
  private void broadcastMessageHead(Component paramComponent, Function<ServerPlayer, Component> paramFunction, ChatType paramChatType, UUID paramUUID, CallbackInfo paramCallbackInfo) {
    ServerPlayer serverPlayer = m_7873_().m_6846_().m_11259_(paramUUID);
    this.am$someoneReceivedMessage = false;
  }
  
  @Inject(method = {"broadcastMessage(Lnet/minecraft/network/chat/Component;Ljava/util/function/Function;Lnet/minecraft/network/chat/ChatType;Ljava/util/UUID;)V"}, at = {@At("TAIL")})
  private void broadcastMessageTail(Component paramComponent, Function<ServerPlayer, Component> paramFunction, ChatType paramChatType, UUID paramUUID, CallbackInfo paramCallbackInfo) {
    ServerPlayer serverPlayer = m_7873_().m_6846_().m_11259_(paramUUID);
    if (!this.am$someoneReceivedMessage && serverPlayer instanceof ax) {
      ax ax = (ax)serverPlayer;
      if (ax.abduction$getTalkArea() != -1)
        serverPlayer.m_9146_((Component)(new TranslatableComponent("chat.nobody_heard_you")).m_130940_(ChatFormatting.RED), ChatType.GAME_INFO, paramUUID); 
    } 
  }
  
  @Redirect(method = {"broadcastMessage(Lnet/minecraft/network/chat/Component;Ljava/util/function/Function;Lnet/minecraft/network/chat/ChatType;Ljava/util/UUID;)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;sendMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/ChatType;Ljava/util/UUID;)V"))
  private void msgMix(ServerPlayer paramServerPlayer, Component paramComponent, ChatType paramChatType, UUID paramUUID) {
    MutableComponent mutableComponent;
    if (bm.isDeafened((LivingEntity)paramServerPlayer) && GameruleRegistry.isGameruleEnabled(paramServerPlayer.f_19853_, EarplugsMuteChat.ID))
      return; 
    boolean bool = false;
    ServerPlayer serverPlayer = paramServerPlayer.f_8924_.m_6846_().m_11259_(paramUUID);
    if (serverPlayer != null) {
      float f = paramServerPlayer.m_20270_((Entity)serverPlayer);
      int i = getEffectiveTalkArea(paramServerPlayer);
      int j = getEffectiveTalkArea(serverPlayer);
      if ((i > 0 || j > 0) && paramServerPlayer.f_19853_ != serverPlayer.f_19853_)
        return; 
      if (i > 0 && j > 0) {
        bool = true;
        if (f > i || f > j)
          return; 
      } else if (i > 0) {
        if (f > i)
          return; 
      } else if (j > 0) {
        bool = true;
        if (f > j)
          return; 
      } 
    } 
    if (serverPlayer != paramServerPlayer)
      this.am$someoneReceivedMessage = true; 
    if (bool)
      mutableComponent = (new TextComponent("§c*§r")).m_7220_(paramComponent); 
    paramServerPlayer.m_9146_((Component)mutableComponent, paramChatType, paramUUID);
  }
  
  private static int getEffectiveTalkArea(ServerPlayer paramServerPlayer) {
    ax ax = (ax)paramServerPlayer;
    return Math.max(ax.abduction$getTalkArea(), GameruleRegistry.getGameruleValue(paramServerPlayer.f_19853_, DefaultTalkArea.ID).intValue());
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\PlayerListMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */