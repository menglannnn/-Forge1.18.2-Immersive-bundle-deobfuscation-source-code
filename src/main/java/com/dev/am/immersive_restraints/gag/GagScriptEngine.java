/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.ChatFormatting
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TextComponent
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 */
package com.dev.am.immersive_restraints.gag;

import com.dev.am.immersive_restraints.command.ArmBindStyleCommand;
import com.dev.am.immersive_restraints.data.RecipeProvider;
import com.dev.am.immersive_restraints.config.GameruleRegistry;
import com.dev.am.mod.gamerule.MeRadius;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

/*
 * Renamed from abductionmod.content.aa
 */
public class GagScriptEngine {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)Commands.literal((String)"me").then(Commands.argument((String)"text", (ArgumentType)StringArgumentType.greedyString()).executes(commandContext -> {
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (entity instanceof ServerPlayer) {
                ServerPlayer serverPlayer = (ServerPlayer)entity;
                entity = Component.literal(serverPlayer.getName() + " " + StringArgumentType.getString((CommandContext)commandContext, (String)"text")).withStyle(new ChatFormatting[]{ChatFormatting.LIGHT_PURPLE, ChatFormatting.ITALIC});
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)entity, false);
                ((CommandSourceStack)commandContext.getSource()).getLevel().sendSystemMessage((Component)entity, serverPlayer.getId());
                RecipeProvider.sendMessageToOtherPlayers(serverPlayer, GameruleRegistry.getGameruleValue(serverPlayer.level, MeRadius.ID), (Component)entity);
            }
            return 0;
        }));
    }
}

