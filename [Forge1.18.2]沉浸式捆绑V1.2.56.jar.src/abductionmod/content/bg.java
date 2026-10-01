package abductionmod.content;

import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class bg {
  private final UUID playerId;
  
  private final String slotName;
  
  public bg(FriendlyByteBuf paramFriendlyByteBuf) {
    this.playerId = paramFriendlyByteBuf.m_130259_();
    this.slotName = paramFriendlyByteBuf.m_130136_(paramFriendlyByteBuf.readInt());
  }
  
  public bg(UUID paramUUID, String paramString) {
    this.playerId = paramUUID;
    this.slotName = paramString;
  }
  
  public static void encode(bg parambg, FriendlyByteBuf paramFriendlyByteBuf) {
    paramFriendlyByteBuf.m_130077_(parambg.playerId);
    paramFriendlyByteBuf.writeInt(parambg.slotName.length());
    paramFriendlyByteBuf.m_130072_(parambg.slotName, parambg.slotName.length());
  }
  
  public static void handle(bg parambg, Supplier<NetworkEvent.Context> paramSupplier) {
    NetworkEvent.Context context = paramSupplier.get();
    context.enqueueWork(() -> {
          ServerPlayer serverPlayer1 = ((NetworkEvent.Context)paramSupplier.get()).getSender();
          if (serverPlayer1 == null)
            return; 
          ServerPlayer serverPlayer2 = (ServerPlayer)serverPlayer1.f_19853_.m_46003_(parambg.playerId);
          if (serverPlayer2 instanceof ay) {
            ay ay = (ay)serverPlayer2;
            if (serverPlayer1.m_20270_((Entity)serverPlayer2) > 10.0F)
              return; 
            ItemStack itemStack = bm.getBondageInSlot((LivingEntity)serverPlayer2, parambg.slotName);
            if (itemStack != null && !itemStack.m_41619_())
              bm.takeOffBondage((LivingEntity)serverPlayer2, itemStack, null, SoundEvents.f_12591_, ()); 
          } 
        });
    context.setPacketHandled(true);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bg.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */