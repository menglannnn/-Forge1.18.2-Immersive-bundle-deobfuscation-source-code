package abductionmod.content;

import com.dev.am.mod.gamerule.GameruleRegistry;
import com.dev.am.mod.gamerule.UtmForEverybody;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class ai {
  public static void register(CommandDispatcher<CommandSourceStack> paramCommandDispatcher, String paramString) {
    paramCommandDispatcher.register((LiteralArgumentBuilder)Commands.m_82127_(paramString).executes(paramCommandContext -> {
            Entity entity = ((CommandSourceStack)paramCommandContext.getSource()).m_81373_();
            if (entity instanceof Player) {
              Player player = (Player)entity;
              if (player.m_20310_(2) || GameruleRegistry.isGameruleEnabled(player.f_19853_, UtmForEverybody.ID)) {
                bm.takeOffAllBondage(player);
              } else {
                ((CommandSourceStack)paramCommandContext.getSource()).m_81352_((Component)(new TranslatableComponent("command.utm.cant_use")).m_130940_(ChatFormatting.RED));
              } 
            } 
            return 0;
          }));
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\ai.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */