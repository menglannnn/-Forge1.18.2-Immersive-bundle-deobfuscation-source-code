package abductionmod.content;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ae {
  public static void register(CommandDispatcher<CommandSourceStack> paramCommandDispatcher, String paramString) {
    paramCommandDispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_(paramString).then((ArgumentBuilder)armSize("slimArms", "command.texture.slim_arms", paramItemStack -> bl.setCustomTag(paramItemStack, "slim", bl.ARM_SHAPE_TAG)))).then((ArgumentBuilder)armSize("fullArms", "command.texture.full_arms", paramItemStack -> bl.setCustomTag(paramItemStack, "full", bl.ARM_SHAPE_TAG)))).then((ArgumentBuilder)armSize("resetArms", "command.texture.arm_tag_removed", paramItemStack -> bl.removeCustomTag(paramItemStack, bl.ARM_SHAPE_TAG))));
  }
  
  private static LiteralArgumentBuilder<CommandSourceStack> armSize(String paramString1, String paramString2, Consumer<ItemStack> paramConsumer) {
    return (LiteralArgumentBuilder<CommandSourceStack>)Commands.m_82127_(paramString1).executes(paramCommandContext -> {
          Entity entity = ((CommandSourceStack)paramCommandContext.getSource()).m_81373_();
          if (entity instanceof Player) {
            Player player = (Player)entity;
            boolean bool = false;
            ItemStack itemStack = player.m_21205_();
            if (itemStack.m_41619_()) {
              itemStack = bm.getMainClothes((LivingEntity)player);
              bool = true;
            } 
            if (itemStack != null && itemStack.m_41720_() instanceof aC) {
              ((CommandSourceStack)paramCommandContext.getSource()).m_81354_((Component)(new TranslatableComponent(paramString)).m_130940_(ChatFormatting.GREEN), false);
              paramConsumer.accept(itemStack);
              if (bool)
                ((av)player).abduction$setCurrentWardrobeItem(itemStack); 
            } else {
              return P.inappropriateItemError((CommandSourceStack)paramCommandContext.getSource());
            } 
          } 
          return 0;
        });
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\ae.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */