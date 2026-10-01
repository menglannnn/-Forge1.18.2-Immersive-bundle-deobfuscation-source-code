package abductionmod.content;

import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class bc {
  private final int id;
  
  public bc(FriendlyByteBuf paramFriendlyByteBuf) {
    this.id = paramFriendlyByteBuf.readInt();
  }
  
  public bc(int paramInt) {
    this.id = paramInt;
  }
  
  public static void encode(bc parambc, FriendlyByteBuf paramFriendlyByteBuf) {
    paramFriendlyByteBuf.writeInt(parambc.id);
  }
  
  public static void handle(bc parambc, Supplier<NetworkEvent.Context> paramSupplier) {
    NetworkEvent.Context context = paramSupplier.get();
    context.enqueueWork(() -> {
          ServerPlayer serverPlayer = ((NetworkEvent.Context)paramSupplier.get()).getSender();
          if (serverPlayer instanceof ay) {
            ay ay = (ay)serverPlayer;
            ay.abduction$setAnimationId(parambc.id);
          } 
        });
    context.setPacketHandled(true);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bc.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */