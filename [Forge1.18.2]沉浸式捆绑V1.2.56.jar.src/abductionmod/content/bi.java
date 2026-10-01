package abductionmod.content;

import java.nio.charset.Charset;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class bi {
  private final a type;
  
  private final float value;
  
  private final int barId;
  
  private final String tiedEntityName;
  
  public bi(FriendlyByteBuf paramFriendlyByteBuf) {
    this.type = a.valueOf(paramFriendlyByteBuf.readCharSequence(paramFriendlyByteBuf.readInt(), Charset.defaultCharset()).toString());
    this.value = paramFriendlyByteBuf.readFloat();
    this.barId = paramFriendlyByteBuf.readInt();
    this.tiedEntityName = paramFriendlyByteBuf.readCharSequence(paramFriendlyByteBuf.readInt(), Charset.defaultCharset()).toString();
  }
  
  public bi(a parama, float paramFloat, int paramInt, String paramString) {
    this.type = parama;
    this.value = paramFloat;
    this.barId = paramInt;
    this.tiedEntityName = paramString;
  }
  
  public static void encode(bi parambi, FriendlyByteBuf paramFriendlyByteBuf) {
    paramFriendlyByteBuf.writeInt(parambi.type.toString().length());
    paramFriendlyByteBuf.writeCharSequence(parambi.type.toString(), Charset.defaultCharset());
    paramFriendlyByteBuf.writeFloat(parambi.value);
    paramFriendlyByteBuf.writeInt(parambi.barId);
    paramFriendlyByteBuf.writeInt(parambi.tiedEntityName.length());
    paramFriendlyByteBuf.writeCharSequence(parambi.tiedEntityName, Charset.defaultCharset());
  }
  
  public static void handle(bi parambi, Supplier<NetworkEvent.Context> paramSupplier) {
    NetworkEvent.Context context = paramSupplier.get();
    context.enqueueWork(() -> w.tyingBars.put(Integer.valueOf(parambi.barId), new w.a(80, parambi.value, parambi.type, parambi.tiedEntityName)));
    context.setPacketHandled(true);
  }
  
  public enum a {
    TYING(1),
    BEING_TIED(2),
    UNTYING(3),
    STRUGGLING(4),
    STRUGGLING_OVERCHARGE(5),
    NONE(6);
    
    private final int offset;
    
    a(int param1Int1) {
      this.offset = param1Int1;
    }
    
    public int getOffset() {
      return this.offset;
    }
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bi.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */