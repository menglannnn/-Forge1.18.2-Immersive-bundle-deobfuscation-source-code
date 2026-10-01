/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.BoolArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.server.level.ServerPlayer
 */
package com.dev.am.immersive_restraints.command;

import com.dev.am.immersive_restraints.config.NiceNTightConfig;
import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class LegsTyingCommand {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)"legsTying").requires(commandSourceStack -> commandSourceStack.hasPermission(2))).then(Commands.argument((String)"players", (ArgumentType)EntityArgument.players()).then(Commands.argument((String)"tie", (ArgumentType)BoolArgumentType.bool()).executes(commandContext -> {
            boolean bl2 = BoolArgumentType.getBool((CommandContext)commandContext, (String)"tie");
            Collection collection = EntityArgument.getEntities((CommandContext)commandContext, (String)"players");
            for (ServerPlayer serverPlayer : collection) {
                CurioSlotType aG2 = (CurioSlotType)serverPlayer;
                boolean bl3 = !bl2;
                aG2.abduction$setLegsUntied(bl3);
                serverPlayer.sendSystemMessage((Component)Component.translatable(bl3 ? "info.am.your_legs_are_untied" : "info.am.your_legs_are_tied"), true);
            }
            NiceNTightSettings.sendOkay((CommandContext<CommandSourceStack>)commandContext);
            return 0;
        })));
    }
}

