package com.dev.am.mixin;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.commands.EmoteCommands;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EmoteCommands.class})
public abstract class EmoteCommandsMix {
  @Inject(method = {"register"}, at = {@At("HEAD")}, cancellable = true)
  private static void register(CommandDispatcher<CommandSourceStack> paramCommandDispatcher, CallbackInfo paramCallbackInfo) {
    paramCallbackInfo.cancel();
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\EmoteCommandsMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */