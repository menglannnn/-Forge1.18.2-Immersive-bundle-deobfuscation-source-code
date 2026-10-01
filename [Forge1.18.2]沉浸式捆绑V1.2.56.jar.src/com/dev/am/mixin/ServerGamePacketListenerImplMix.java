package com.dev.am.mixin;

import abductionmod.content.W;
import abductionmod.content.bm;
import abductionmod.content.c;
import java.util.Arrays;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ServerGamePacketListenerImpl.class})
public abstract class ServerGamePacketListenerImplMix {
  @Shadow
  public ServerPlayer f_9743_;
  
  @Unique
  @NotNull
  private static List<String> abduction$getList(ForgeConfigSpec.ConfigValue<String> paramConfigValue) {
    return Arrays.<String>stream(((String)paramConfigValue.get()).replace(" ", "").split(",")).toList();
  }
  
  @Inject(method = {"handleCommand"}, at = {@At("HEAD")}, cancellable = true)
  private void handleCommand(String paramString, CallbackInfo paramCallbackInfo) {
    String str = paramString.replace("/", "").split(" ")[0];
    if (this.f_9743_.m_19880_().contains(W.helplessTag) && bm.isTied((LivingEntity)this.f_9743_) && !abduction$getList(c.helplessAllowedCommands).contains(str)) {
      this.f_9743_.m_6352_((Component)(new TranslatableComponent("command.youre_helpless")).m_130940_(ChatFormatting.RED), Util.f_137441_);
      paramCallbackInfo.cancel();
    } 
    if (!bm.isTied((LivingEntity)this.f_9743_))
      return; 
    if (!abduction$getList(c.tiedAllowedCommands).contains(str)) {
      this.f_9743_.m_6352_((Component)(new TranslatableComponent("command.youre_tied_and_cant_execute")).m_130940_(ChatFormatting.RED), Util.f_137441_);
      paramCallbackInfo.cancel();
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\ServerGamePacketListenerImplMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */