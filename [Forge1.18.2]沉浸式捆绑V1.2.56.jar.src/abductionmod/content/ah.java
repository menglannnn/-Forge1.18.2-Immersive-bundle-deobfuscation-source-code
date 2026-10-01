package abductionmod.content;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class ah {
  public static void register(CommandDispatcher<CommandSourceStack> paramCommandDispatcher) {
    paramCommandDispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("untie").requires(paramCommandSourceStack -> paramCommandSourceStack.m_6761_(2))).then(Commands.m_82129_("player", (ArgumentType)EntityArgument.m_91470_()).executes(paramCommandContext -> {
              Collection collection = EntityArgument.m_91477_(paramCommandContext, "player");
              for (ServerPlayer serverPlayer : collection)
                bm.takeOffAllBondage((Player)serverPlayer); 
              return 0;
            })));
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\ah.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */