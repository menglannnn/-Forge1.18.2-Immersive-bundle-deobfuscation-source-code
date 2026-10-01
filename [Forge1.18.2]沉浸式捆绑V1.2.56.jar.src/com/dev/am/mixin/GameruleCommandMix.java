package com.dev.am.mixin;

import abductionmod.content.bm;
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
    bm.syncGamerules(((CommandSourceStack)paramCommandContext.getSource()).m_81377_(), paramPlayer -> true);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\GameruleCommandMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */