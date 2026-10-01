package abductionmod.content;

import java.util.function.Supplier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class bh {
  public bh(FriendlyByteBuf paramFriendlyByteBuf) {}
  
  public bh() {}
  
  public static void encode(bh parambh, FriendlyByteBuf paramFriendlyByteBuf) {}
  
  public static void handle(bh parambh, Supplier<NetworkEvent.Context> paramSupplier) {
    NetworkEvent.Context context = paramSupplier.get();
    context.enqueueWork(() -> {
          ServerPlayer serverPlayer = ((NetworkEvent.Context)paramSupplier.get()).getSender();
          if (serverPlayer instanceof ax) {
            ax ax = (ax)serverPlayer;
            int i = ax.abduction$getTalkAreaPreference();
            if (i <= 0) {
              serverPlayer.m_5661_((Component)(new TranslatableComponent("command.talkarea.preference_not_set")).m_130940_(ChatFormatting.RED), false);
              return;
            } 
            ax.abduction$setTalkArea(i);
            serverPlayer.m_5661_((Component)(new TranslatableComponent("command.talkarea.set", new Object[] { Integer.valueOf(i) })).m_130940_(ChatFormatting.GREEN), false);
          } 
        });
    context.setPacketHandled(true);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bh.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */