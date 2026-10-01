package com.dev.am.immersive_restraints.mixin;

import com.dev.am.immersive_restraints.aC;
import com.dev.am.immersive_restraints.bl;
import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.bs;
import java.util.function.Consumer;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.network.TextFilter;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.theillusivec4.curios.api.SlotResult;

@Mixin({ServerGamePacketListenerImpl.class})
public abstract class ServerChatPacketMix {
  @Shadow
  public ServerPlayer player;
  
  @Shadow
  protected abstract void handleChat(TextFilter.FilteredText paramFilteredText);
  
  @Shadow
  protected abstract void filterText(String paramString, Consumer<TextFilter.FilteredText> paramConsumer);
  
  @Redirect(method = {"handleChat(Lnet/minecraft/network/protocol/game/ServerboundChatPacket;)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;filterTextPacket(Ljava/lang/String;Ljava/util/function/Consumer;)V"))
  private void injected(ServerGamePacketListenerImpl paramServerGamePacketListenerImpl, String paramString, Consumer<TextFilter.FilteredText> paramConsumer, ServerboundChatPacket paramServerboundChatPacket) {
    for (SlotResult slotResult : bm.getBondages((LivingEntity)this.player)) {
      ItemStack itemStack = slotResult.stack();
      String str = bl.getCustomTag(itemStack, bl.GAG_TYPE_TAG);
      if (str != null) {
        paramString = bs.transformWithGagStyle(str, paramString);
        continue;
      } 
      Item item = itemStack.getItem();
      if (item instanceof aC) {
        aC aC = (aC)item;
        if (aC.properties.isGag(itemStack))
          paramString = bs.transformWithGagStyle("default", paramString); 
      } 
    } 
    filterText(paramString, this::handleChat);
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\ServerChatPacketMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */