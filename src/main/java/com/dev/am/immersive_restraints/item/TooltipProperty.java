/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.player.Player
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

/*
 * Renamed from abductionmod.content.al
 */
public class TooltipProperty {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)"untie").requires(commandSourceStack -> commandSourceStack.hasPermission(2))).then(Commands.argument((String)"player", (ArgumentType)EntityArgument.players()).executes(commandContext -> {
            Collection collection = EntityArgument.getEntities((CommandContext)commandContext, (String)"player");
            for (ServerPlayer serverPlayer : collection) {
                EntityEventHandler.takeOffAllBondage((Player)serverPlayer);
            }
            return 0;
        }));
    }
}

