package com.dev.am.immersive_restraints.mixin;

import com.dev.am.immersive_restraints.W;
import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.c;
import java.util.Arrays;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
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
  public ServerPlayer player;
  
  @Unique
  @NotNull
  private static List<String> abduction$getList(ForgeConfigSpec.ConfigValue<String> paramConfigValue) {
    return Arrays.<String>stream(((String)paramConfigValue.get()).replace(" ", "").split(",")).toList();
  }
  
  @Inject(method = {"handleCommand"}, at = {@At("HEAD")}, cancellable = true)
  private void handleCommand(String paramString, CallbackInfo paramCallbackInfo) {
    String str = paramString.replace("/", "").split(" ")[0];
    if (this.player.getTags().contains(W.helplessTag) && bm.isTied((LivingEntity)this.player) && !abduction$getList(c.helplessAllowedCommands).contains(str)) {
      this.player.sendSystemMessage((Component)(Component.translatable("command.youre_helpless")).withStyle(ChatFormatting.RED), Util.NIL_UUID);
      paramCallbackInfo.cancel();
    } 
    if (!bm.isTied((LivingEntity)this.player))
      return; 
    if (!abduction$getList(c.tiedAllowedCommands).contains(str)) {
      this.player.sendSystemMessage((Component)(Component.translatable("command.youre_tied_and_cant_execute")).withStyle(ChatFormatting.RED), Util.NIL_UUID);
      paramCallbackInfo.cancel();
    } 
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\ServerGamePacketListenerImplMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */