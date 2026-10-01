package abductionmod.content;

import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

public class ba {
  private final CompoundTag animationDefinitionTag;
  
  private final UUID playerId;
  
  public ba(FriendlyByteBuf paramFriendlyByteBuf) {
    this.animationDefinitionTag = paramFriendlyByteBuf.m_130260_();
    this.playerId = paramFriendlyByteBuf.m_130259_();
  }
  
  public ba(CompoundTag paramCompoundTag, UUID paramUUID) {
    this.animationDefinitionTag = paramCompoundTag;
    this.playerId = paramUUID;
  }
  
  public static void encode(ba paramba, FriendlyByteBuf paramFriendlyByteBuf) {
    paramFriendlyByteBuf.m_130079_(paramba.animationDefinitionTag);
    paramFriendlyByteBuf.m_130077_(paramba.playerId);
  }
  
  public static void handle(ba paramba, Supplier<NetworkEvent.Context> paramSupplier) {
    NetworkEvent.Context context = paramSupplier.get();
    context.enqueueWork(() -> {
          Player player = (Minecraft.m_91087_()).f_91073_.m_46003_(paramba.playerId);
          if (player != null)
            m.playAnimation(player, new n(paramba.animationDefinitionTag), false, true); 
        });
    context.setPacketHandled(true);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\ba.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */