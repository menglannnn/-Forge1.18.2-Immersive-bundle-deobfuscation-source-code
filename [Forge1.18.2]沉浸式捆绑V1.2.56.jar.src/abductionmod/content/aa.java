package abductionmod.content;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class aa {
  public static void register(CommandDispatcher<CommandSourceStack> paramCommandDispatcher) {
    paramCommandDispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("reloadGagScripts").requires(paramCommandSourceStack -> paramCommandSourceStack.m_6761_(4))).executes(paramCommandContext -> {
            ((CommandSourceStack)paramCommandContext.getSource()).m_81354_((Component)(new TranslatableComponent("command.okay")).m_130940_(ChatFormatting.GREEN), false);
            bs.initGagFiles(((CommandSourceStack)paramCommandContext.getSource()).m_81377_().m_177941_());
            return 0;
          }));
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\aa.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */