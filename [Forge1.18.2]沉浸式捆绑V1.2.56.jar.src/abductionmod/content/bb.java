package abductionmod.content;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.SlotResult;

public class bb {
  private final UUID playerId;
  
  public bb(FriendlyByteBuf paramFriendlyByteBuf) {
    this.playerId = paramFriendlyByteBuf.m_130259_();
  }
  
  public bb(UUID paramUUID) {
    this.playerId = paramUUID;
  }
  
  public static void encode(bb parambb, FriendlyByteBuf paramFriendlyByteBuf) {
    paramFriendlyByteBuf.m_130077_(parambb.playerId);
  }
  
  public static void handle(bb parambb, Supplier<NetworkEvent.Context> paramSupplier) {
    NetworkEvent.Context context = paramSupplier.get();
    context.enqueueWork(() -> {
          ServerPlayer serverPlayer = (ServerPlayer)(((NetworkEvent.Context)paramSupplier.get()).getSender()).f_19853_.m_46003_(parambb.playerId);
          if (serverPlayer instanceof ay) {
            ay ay = (ay)serverPlayer;
            List<SlotResult> list = bm.getBondages((LivingEntity)serverPlayer);
            for (SlotResult slotResult : list) {
              if (bl.hasCustomTag(slotResult.stack(), "struggleAddedResistance")) {
                paramContext.getSender().m_9146_((Component)(new TranslatableComponent("command.you_have_tightened", new Object[] { serverPlayer.m_6302_() })).m_130940_(ChatFormatting.WHITE), ChatType.GAME_INFO, Util.f_137441_);
                serverPlayer.m_9146_((Component)(new TranslatableComponent("command.someone_tightened_you")).m_130940_(ChatFormatting.WHITE), ChatType.GAME_INFO, Util.f_137441_);
                bl.removeCustomTag(slotResult.stack(), "struggleAddedResistance");
                bm.playSound((LivingEntity)serverPlayer, SoundEvents.f_12591_);
                break;
              } 
            } 
          } 
        });
    context.setPacketHandled(true);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bb.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */