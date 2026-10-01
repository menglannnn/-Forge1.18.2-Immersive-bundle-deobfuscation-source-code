package abductionmod.content;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class af {
  public static void register(CommandDispatcher<CommandSourceStack> paramCommandDispatcher, String paramString1, String paramString2, boolean paramBoolean) {
    paramCommandDispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_(paramString1).then(Commands.m_82127_("setTexture").then(Commands.m_82129_("URL", (ArgumentType)StringArgumentType.greedyString()).executes(paramCommandContext -> {
                String str = StringArgumentType.getString(paramCommandContext, "URL");
                if (!bu.isValidImageUrl(str)) {
                  ((CommandSourceStack)paramCommandContext.getSource()).m_81352_((Component)(new TranslatableComponent("command.texture.url_malformed")).m_130940_(ChatFormatting.RED));
                  return 1;
                } 
                if (!bu.isWhitelistedHost(str)) {
                  ((CommandSourceStack)paramCommandContext.getSource()).m_81352_((Component)(new TranslatableComponent("command.texture.url_host_not_allowed")).m_130940_(ChatFormatting.RED));
                  return 1;
                } 
                return execute("command.texture.set", paramBoolean, (CommandSourceStack)paramCommandContext.getSource(), ());
              })))).then(Commands.m_82127_("removeTexture").executes(paramCommandContext -> execute("command.texture.removed", paramBoolean, (CommandSourceStack)paramCommandContext.getSource(), ())))).then(Commands.m_82127_("showUrl").executes(paramCommandContext -> execute(null, paramBoolean, (CommandSourceStack)paramCommandContext.getSource(), ()))));
  }
  
  private static int execute(@Nullable String paramString, boolean paramBoolean, CommandSourceStack paramCommandSourceStack, Consumer<ItemStack> paramConsumer) {
    Entity entity = paramCommandSourceStack.m_81373_();
    if (entity instanceof Player) {
      Player player = (Player)entity;
      ItemStack itemStack = player.m_21205_();
      boolean bool = false;
      if (itemStack.m_41619_() && paramBoolean) {
        itemStack = bm.getMainClothes((LivingEntity)player);
        bool = true;
      } 
      if (itemStack != null) {
        Item item = itemStack.m_41720_();
        if (item instanceof aC) {
          aC aC = (aC)item;
          if (paramBoolean && !(aC instanceof aG))
            return P.inappropriateItemError(paramCommandSourceStack); 
          if (paramString != null)
            paramCommandSourceStack.m_81354_((Component)(new TranslatableComponent(paramString)).m_130940_(ChatFormatting.GREEN), false); 
          paramConsumer.accept(itemStack);
          if (bool)
            ((av)player).abduction$setCurrentWardrobeItem(itemStack); 
        } else {
          return P.inappropriateItemError(paramCommandSourceStack);
        } 
      } else {
        return P.inappropriateItemError(paramCommandSourceStack);
      } 
    } 
    return 0;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\af.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */