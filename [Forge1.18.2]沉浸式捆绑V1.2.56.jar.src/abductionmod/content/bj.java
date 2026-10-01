package abductionmod.content;

import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

public class bj {
  private final UUID playerId;
  
  private final boolean untie;
  
  public bj(FriendlyByteBuf paramFriendlyByteBuf) {
    this.playerId = paramFriendlyByteBuf.m_130259_();
    this.untie = paramFriendlyByteBuf.readBoolean();
  }
  
  public bj(UUID paramUUID, boolean paramBoolean) {
    this.playerId = paramUUID;
    this.untie = paramBoolean;
  }
  
  public static void encode(bj parambj, FriendlyByteBuf paramFriendlyByteBuf) {
    paramFriendlyByteBuf.m_130077_(parambj.playerId);
    paramFriendlyByteBuf.writeBoolean(parambj.untie);
  }
  
  public static void handle(bj parambj, Supplier<NetworkEvent.Context> paramSupplier) {
    NetworkEvent.Context context = paramSupplier.get();
    context.enqueueWork(() -> {
          ServerPlayer serverPlayer1 = ((NetworkEvent.Context)paramSupplier.get()).getSender();
          if (serverPlayer1 == null)
            return; 
          ServerPlayer serverPlayer2 = (ServerPlayer)serverPlayer1.f_19853_.m_46003_(parambj.playerId);
          if (serverPlayer2 instanceof ay) {
            ay ay = (ay)serverPlayer2;
            if (serverPlayer1.m_20270_((Entity)serverPlayer2) > 10.0F)
              return; 
            ay.abduction$setLegsUntied(parambj.untie);
            serverPlayer2.m_5661_((Component)new TranslatableComponent(parambj.untie ? "info.am.your_legs_are_untied" : "info.am.your_legs_are_tied"), true);
          } 
        });
    context.setPacketHandled(true);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bj.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */