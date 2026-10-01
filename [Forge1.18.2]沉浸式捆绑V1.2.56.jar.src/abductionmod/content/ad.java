package abductionmod.content;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;

public class ad {
  public static void register(CommandDispatcher<CommandSourceStack> paramCommandDispatcher) {
    paramCommandDispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("talkArea").then(Commands.m_82129_("value", (ArgumentType)IntegerArgumentType.integer(0)).executes(paramCommandContext -> {
              Entity entity = ((CommandSourceStack)paramCommandContext.getSource()).m_81373_();
              if (entity instanceof ax) {
                ax ax = (ax)entity;
                int i = IntegerArgumentType.getInteger(paramCommandContext, "value");
                ax.abduction$setTalkArea(i);
                ((CommandSourceStack)paramCommandContext.getSource()).m_81354_((Component)(new TranslatableComponent("command.talkarea.set", new Object[] { Integer.valueOf(i) })).m_130940_(ChatFormatting.GREEN), false);
              } 
              return 0;
            }))).then(Commands.m_82127_("reset").executes(paramCommandContext -> {
              Entity entity = ((CommandSourceStack)paramCommandContext.getSource()).m_81373_();
              if (entity instanceof ax) {
                ax ax = (ax)entity;
                ax.abduction$setTalkArea(-1);
                ((CommandSourceStack)paramCommandContext.getSource()).m_81354_((Component)(new TranslatableComponent("command.talkarea.reset")).m_130940_(ChatFormatting.GREEN), false);
              } 
              return 0;
            }))).then(Commands.m_82127_("setPreference").then(Commands.m_82129_("value", (ArgumentType)IntegerArgumentType.integer(0)).executes(paramCommandContext -> {
                int i = IntegerArgumentType.getInteger(paramCommandContext, "value");
                Entity entity = ((CommandSourceStack)paramCommandContext.getSource()).m_81373_();
                if (entity instanceof ax) {
                  ax ax = (ax)entity;
                  ax.abduction$setTalkAreaPreference(i);
                  ((CommandSourceStack)paramCommandContext.getSource()).m_81354_((Component)(new TranslatableComponent("command.talkarea.preference_set", new Object[] { Integer.valueOf(i) })).m_130940_(ChatFormatting.GREEN), false);
                } 
                return 0;
              })))).executes(paramCommandContext -> {
            Entity entity = ((CommandSourceStack)paramCommandContext.getSource()).m_81373_();
            if (entity instanceof ax) {
              ax ax = (ax)entity;
              int i = ax.abduction$getTalkArea();
              TranslatableComponent translatableComponent = new TranslatableComponent("command.talkarea.your_value", new Object[] { Integer.valueOf(i) });
              if (i < 0)
                translatableComponent = new TranslatableComponent("command.talkarea.unlimited"); 
              ((CommandSourceStack)paramCommandContext.getSource()).m_81354_((Component)translatableComponent.m_130944_(new ChatFormatting[] { ChatFormatting.LIGHT_PURPLE, ChatFormatting.ITALIC }, ), false);
            } 
            return 0;
          }));
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\ad.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */