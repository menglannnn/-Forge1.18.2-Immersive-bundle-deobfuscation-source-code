package abductionmod.content;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

public class aj {
  public static void register(CommandDispatcher<CommandSourceStack> paramCommandDispatcher, String paramString) {
    paramCommandDispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.m_82127_(paramString).then(Commands.m_82129_("player", (ArgumentType)EntityArgument.m_91466_()).executes(paramCommandContext -> {
              LivingEntity livingEntity;
              ServerPlayer serverPlayer = EntityArgument.m_91474_(paramCommandContext, "player");
              Entity entity = ((CommandSourceStack)paramCommandContext.getSource()).m_81373_();
              if (entity instanceof LivingEntity) {
                livingEntity = (LivingEntity)entity;
              } else {
                return 0;
              } 
              runCommand(paramCommandContext, livingEntity, serverPlayer);
              return 0;
            }))).executes(paramCommandContext -> {
            LivingEntity livingEntity;
            Entity entity = ((CommandSourceStack)paramCommandContext.getSource()).m_81373_();
            if (entity instanceof LivingEntity) {
              livingEntity = (LivingEntity)entity;
            } else {
              return 0;
            } 
            TargetingConditions targetingConditions = TargetingConditions.m_148353_().m_26883_(5.0D).m_26888_(());
            List<Player> list = entity.f_19853_.m_45955_(targetingConditions, livingEntity, (new AABB(entity.m_142538_())).m_82400_(10.0D));
            if (list.isEmpty())
              return 0; 
            Player player = list.get(0);
            for (Player player1 : list) {
              if (livingEntity.m_20270_((Entity)player1) < livingEntity.m_20270_((Entity)player))
                player = player1; 
            } 
            runCommand(paramCommandContext, livingEntity, (ServerPlayer)player);
            return 0;
          }));
  }
  
  private static void runCommand(CommandContext<CommandSourceStack> paramCommandContext, LivingEntity paramLivingEntity, ServerPlayer paramServerPlayer) {
    if (paramLivingEntity == paramServerPlayer)
      return; 
    if (paramLivingEntity.m_20270_((Entity)paramServerPlayer) > 5.0F) {
      ((CommandSourceStack)paramCommandContext.getSource()).m_81352_((Component)(new TranslatableComponent("command.yoink.too_far")).m_130940_(ChatFormatting.RED));
      return;
    } 
    if (!bm.isTied((LivingEntity)paramServerPlayer)) {
      ((CommandSourceStack)paramCommandContext.getSource()).m_81352_((Component)(new TranslatableComponent("command.yoink.not_tied")).m_130940_(ChatFormatting.RED));
      return;
    } 
    if (bm.isTied(paramLivingEntity)) {
      ((CommandSourceStack)paramCommandContext.getSource()).m_81352_((Component)(new TranslatableComponent("command.yoink.youre_tied")).m_130940_(ChatFormatting.RED));
      return;
    } 
    paramServerPlayer.m_8999_((ServerLevel)paramLivingEntity.f_19853_, paramLivingEntity.m_20185_(), paramLivingEntity.m_20186_(), paramLivingEntity.m_20189_(), paramServerPlayer.m_146908_(), paramServerPlayer.m_146909_());
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\aj.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */