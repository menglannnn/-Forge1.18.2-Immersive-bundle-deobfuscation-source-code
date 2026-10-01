package abductionmod.content;

import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class bd {
  public bd(FriendlyByteBuf paramFriendlyByteBuf) {}
  
  public bd() {}
  
  public static void encode(bd parambd, FriendlyByteBuf paramFriendlyByteBuf) {}
  
  public static void handle(bd parambd, Supplier<NetworkEvent.Context> paramSupplier) {
    NetworkEvent.Context context = paramSupplier.get();
    context.enqueueWork(N::stop);
    context.setPacketHandled(true);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bd.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */