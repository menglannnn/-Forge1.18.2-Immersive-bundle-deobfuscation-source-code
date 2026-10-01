package abductionmod.content;

import java.util.List;
import net.minecraft.Util;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class bt {
  public static List<? extends Player> getOtherPlayersInRadius(@NotNull ServerPlayer paramServerPlayer, int paramInt) {
    return paramServerPlayer.f_19853_.m_6907_().stream().filter(paramPlayer -> (paramPlayer != paramServerPlayer && paramPlayer.m_20270_((Entity)paramServerPlayer) < paramInt)).toList();
  }
  
  public static void sendMessageToOtherPlayers(ServerPlayer paramServerPlayer, int paramInt, Component paramComponent) {
    for (Player player : getOtherPlayersInRadius(paramServerPlayer, paramInt))
      ((ServerPlayer)player).m_9146_(paramComponent, ChatType.CHAT, Util.f_137441_); 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bt.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */