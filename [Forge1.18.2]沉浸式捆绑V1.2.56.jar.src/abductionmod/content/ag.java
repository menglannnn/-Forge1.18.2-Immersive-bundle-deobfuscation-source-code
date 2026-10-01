package abductionmod.content;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ag {
  public static void register(CommandDispatcher<CommandSourceStack> paramCommandDispatcher) {
    LiteralArgumentBuilder literalArgumentBuilder = (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_("textureOriginalSkinParts").then((ArgumentBuilder)limbVisibility("all", 0))).then((ArgumentBuilder)limbVisibility("head", 1))).then((ArgumentBuilder)limbVisibility("body", 2))).then((ArgumentBuilder)limbVisibility("leftArm", 3))).then((ArgumentBuilder)limbVisibility("rightArm", 4))).then((ArgumentBuilder)limbVisibility("leftLeg", 5))).then((ArgumentBuilder)limbVisibility("rightLeg", 6))).then(Commands.m_82127_("clearAll").executes(paramCommandContext -> {
            Entity entity = ((CommandSourceStack)paramCommandContext.getSource()).m_81373_();
            if (entity instanceof Player) {
              Player player = (Player)entity;
              ItemStack itemStack = player.m_21205_();
              boolean bool = false;
              if (itemStack.m_41619_()) {
                itemStack = bm.getMainClothes((LivingEntity)player);
                bool = true;
              } 
              if (itemStack.m_41720_() instanceof aC) {
                ((CommandSourceStack)paramCommandContext.getSource()).m_81354_((Component)(new TranslatableComponent("command.texture.original_skin_shown")).m_130940_(ChatFormatting.GREEN), false);
                bl.removeCustomTag(itemStack, bl.HIDE_ORIGINAL_SKIN_PARTS_TAG);
                if (bool)
                  ((av)player).abduction$setCurrentWardrobeItem(itemStack); 
              } else {
                return P.inappropriateItemError((CommandSourceStack)paramCommandContext.getSource());
              } 
            } 
            return 0;
          }));
    paramCommandDispatcher.register(literalArgumentBuilder);
    paramCommandDispatcher.register((LiteralArgumentBuilder)Commands.m_82127_("dc").then((ArgumentBuilder)literalArgumentBuilder));
  }
  
  private static LiteralArgumentBuilder<CommandSourceStack> limbVisibility(String paramString, int paramInt) {
    return (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.m_82127_(paramString).then(Commands.m_82127_("hidden").executes(paramCommandContext -> define(paramCommandContext, paramInt, '0')))).then(Commands.m_82127_("shown").executes(paramCommandContext -> define(paramCommandContext, paramInt, '1')));
  }
  
  private static int define(CommandContext<CommandSourceStack> paramCommandContext, int paramInt, char paramChar) {
    Entity entity = ((CommandSourceStack)paramCommandContext.getSource()).m_81373_();
    if (entity instanceof Player) {
      Player player = (Player)entity;
      ItemStack itemStack = player.m_21205_();
      boolean bool = false;
      if (itemStack.m_41619_()) {
        itemStack = bm.getMainClothes((LivingEntity)player);
        bool = true;
      } 
      Item item = itemStack.m_41720_();
      if (item instanceof aC) {
        aC aC = (aC)item;
        if (!(aC instanceof aG))
          return P.inappropriateItemError((CommandSourceStack)paramCommandContext.getSource()); 
        ((CommandSourceStack)paramCommandContext.getSource()).m_81354_((Component)(new TranslatableComponent("command.okay")).m_130940_(ChatFormatting.GREEN), false);
        String str = bl.getCustomTag(itemStack, bl.HIDE_ORIGINAL_SKIN_PARTS_TAG);
        if (str == null || str.length() != 7)
          str = "1111111"; 
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.setCharAt(paramInt, paramChar);
        str = stringBuilder.toString();
        bl.setCustomTag(itemStack, str, bl.HIDE_ORIGINAL_SKIN_PARTS_TAG);
        if (bool)
          ((av)player).abduction$setCurrentWardrobeItem(itemStack); 
      } else {
        return P.inappropriateItemError((CommandSourceStack)paramCommandContext.getSource());
      } 
    } 
    return 0;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\ag.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */