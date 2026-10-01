package abductionmod.content;

import com.dev.am.Abduction;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ac {
  public static final SuggestionProvider<CommandSourceStack> SUGGESTION;
  
  @NotNull
  private static List<String> getSuggestionList() {
    ArrayList<String> arrayList = new ArrayList(Abduction.slotNames.stream().toList());
    arrayList.add("all");
    return arrayList;
  }
  
  public static void register(CommandDispatcher<CommandSourceStack> paramCommandDispatcher) {
    paramCommandDispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("takeOff").requires(paramCommandSourceStack -> paramCommandSourceStack.m_6761_(2))).then(Commands.m_82129_("players", (ArgumentType)EntityArgument.m_91470_()).then(Commands.m_82129_("type", (ArgumentType)StringArgumentType.word()).suggests(SUGGESTION).executes(paramCommandContext -> {
                Collection collection = EntityArgument.m_91477_(paramCommandContext, "players");
                String str = StringArgumentType.getString(paramCommandContext, "type");
                for (ServerPlayer serverPlayer : collection) {
                  if (str.equals("all")) {
                    ((CommandSourceStack)paramCommandContext.getSource()).m_81354_((Component)(new TranslatableComponent("command.takeoff.all_success")).m_130940_(ChatFormatting.GREEN), false);
                    bm.takeOffAllBondage((Player)serverPlayer);
                    return 0;
                  } 
                  ItemStack itemStack = bm.getBondageInSlot((LivingEntity)serverPlayer, str);
                  if (itemStack != null) {
                    ((CommandSourceStack)paramCommandContext.getSource()).m_81354_((Component)(new TranslatableComponent("command.takeoff.success", new Object[] { itemStack.m_41611_() })).m_130940_(ChatFormatting.GREEN), false);
                    bm.takeOffBondage((LivingEntity)serverPlayer, itemStack, null, SoundEvents.f_12639_, ());
                    return 0;
                  } 
                } 
                return 0;
              }))));
  }
  
  static {
    SUGGESTION = ((paramCommandContext, paramSuggestionsBuilder) -> SharedSuggestionProvider.m_82970_(getSuggestionList(), paramSuggestionsBuilder));
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\ac.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */