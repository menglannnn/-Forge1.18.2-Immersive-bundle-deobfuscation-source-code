package com.dev.am.immersive_restraints.mixin;

import com.dev.am.immersive_restraints.bm;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.commands.GameRuleCommand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({GameRuleCommand.class})
public abstract class GameruleCommandMix {
  @Inject(method = {"setRule"}, at = {@At("RETURN")})
  private static void setRuleMix(CommandContext<CommandSourceStack> paramCommandContext, GameRules.Key paramKey, CallbackInfoReturnable<Integer> paramCallbackInfoReturnable) {
    bm.syncGamerules(((CommandSourceStack)paramCommandContext.getSource()).getLevel(), paramPlayer -> true);
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\GameruleCommandMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */