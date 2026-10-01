/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.ChatFormatting
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.blindfold.BlindfoldOverlay;
import com.dev.am.immersive_restraints.data.BlockModelProvider;
import com.dev.am.immersive_restraints.command.ArmBindStyleCommand;
import com.dev.am.immersive_restraints.data.RecipeProvider;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.config.GameruleRegistry;
import com.dev.am.mod.gamerule.NoRpImmunityTimeSeconds;
import com.dev.am.mod.gamerule.NoRpResetTimeSeconds;
import com.dev.am.mod.gamerule.NoRpThinkingTimeSeconds;
import com.dev.am.mod.gamerule.NorpUntiesPlayersAfter2ndRun;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.text.DecimalFormat;
import java.util.Date;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

/*
 * Renamed from abductionmod.content.ab
 */
public class KeyPredicate {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder(String string) {
        return (LiteralArgumentBuilder)Commands.literal((String)string).executes(commandContext -> {
            Object object = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (object instanceof ServerPlayer) {
                ServerPlayer serverPlayer = (ServerPlayer)object;
                object = (BlindfoldOverlay)serverPlayer;
                int n = serverPlayer.serverLevel.getGameTime();
                long l = ((long)n - object.abduction$getNoRpCommandTime()) / 20L;
                if (object.abduction$getNoRpImmunityUntilTicks() > (long)n) {
                    ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.no_rp.already_immune").withStyle(ChatFormatting.RED));
                    return 0;
                }
                if ((long)n - object.abduction$getNoRpCommandTime() > 20L * (long)GameruleRegistry.getGameruleValue(serverPlayer.level, NoRpResetTimeSeconds.ID).intValue()) {
                    object.abduction$setRpStatus(BlindfoldOverlay.a.NORMAL);
                }
                switch (object.abduction$getRpStatus()) {
                    case NORMAL: {
                        object.abduction$setRpStatus(BlindfoldOverlay.a.SOFT_NO_RP);
                        object.abduction$setNoRpCommandTime(n);
                        KeyPredicate.warnOtherPlayers((CommandContext<CommandSourceStack>)commandContext, serverPlayer, string);
                        break;
                    }
                    case SOFT_NO_RP: {
                        long l2 = (long)GameruleRegistry.getGameruleValue(serverPlayer.level, NoRpThinkingTimeSeconds.ID).intValue() - l;
                        if (l2 > 0L) {
                            ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.no_rp.please_wait", new Object[]{l2}).withStyle(ChatFormatting.YELLOW), false);
                            return 0;
                        }
                        if (GameruleRegistry.isGameruleEnabled(serverPlayer.level, NorpUntiesPlayersAfter2ndRun.ID)) {
                            ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.no_rp.freed").withStyle(ChatFormatting.YELLOW), false);
                            EntityEventHandler.takeOffAllBondage((Player)serverPlayer);
                            object.abduction$setNoRpImmunityUntilTicks((long)n + 20L * (long)GameruleRegistry.getGameruleValue(serverPlayer.level, NoRpImmunityTimeSeconds.ID).intValue());
                            object.abduction$setRpStatus(BlindfoldOverlay.a.NO_RP);
                        } else {
                            object.abduction$setNoRpCommandTime(n);
                            KeyPredicate.warnOtherPlayers((CommandContext<CommandSourceStack>)commandContext, serverPlayer, string);
                        }
                        KeyPredicate.writeLog(serverPlayer);
                    }
                }
            }
            return 0;
        });
    }

    private static void warnOtherPlayers(CommandContext<CommandSourceStack> commandContext, ServerPlayer serverPlayer, String string) {
        RecipeProvider.sendMessageToOtherPlayers(serverPlayer, 150, (Component)Component.translatable("command.no_rp.message", new Object[]{serverPlayer.getName()}).withStyle(ChatFormatting.RED));
        ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.no_rp.sent_warning").withStyle(ChatFormatting.RED), false);
        if (GameruleRegistry.isGameruleEnabled(serverPlayer.level, NorpUntiesPlayersAfter2ndRun.ID)) {
            ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.no_rp.sent_warning_will_freed", new Object[]{string}).withStyle(ChatFormatting.YELLOW), false);
        }
    }

    private static void writeLog(ServerPlayer serverPlayer) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Player player : RecipeProvider.getOtherPlayersInRadius(serverPlayer, 100)) {
            float f = player.distanceTo((Entity)serverPlayer);
            stringBuilder.append(player.getName()).append("(distance=").append(new DecimalFormat("#.##").format(f)).append(EntityEventHandler.isTied((LivingEntity)player) ? ", tied" : "").append("),");
        }
        Object object = "[" + new Date() + "]\n";
        object = (String)object + "Triggered by : " + serverPlayer.getName() + "\n";
        if (!stringBuilder.isEmpty()) {
            object = (String)object + "Players around : [" + stringBuilder.substring(0, stringBuilder.length() - 1) + "]\n";
        }
        object = (String)object + "\n";
        BlockModelProvider.writeToFile((String)object, "norp.log");
    }
}

