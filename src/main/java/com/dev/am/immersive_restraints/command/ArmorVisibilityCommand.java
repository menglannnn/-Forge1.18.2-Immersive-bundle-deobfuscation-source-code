/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 */
package com.dev.am.immersive_restraints.command;

import com.dev.am.immersive_restraints.config.NiceNTightConfig;
import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.function.Consumer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ArmorVisibilityCommand {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)"tiedArmorVisibility").then(Commands.literal((String)"hidden").executes(commandContext -> ArmorVisibilityCommand.define((CommandContext<CommandSourceStack>)commandContext, itemStack -> PlayerEventHandler.setCustomTag(itemStack, false, "armorVisibility"))))).then(Commands.literal((String)"shown").executes(commandContext -> ArmorVisibilityCommand.define((CommandContext<CommandSourceStack>)commandContext, itemStack -> PlayerEventHandler.setCustomTag(itemStack, true, "armorVisibility"))))).then(Commands.literal((String)"reset").executes(commandContext -> ArmorVisibilityCommand.define((CommandContext<CommandSourceStack>)commandContext, itemStack -> PlayerEventHandler.removeCustomTag(itemStack, "armorVisibility"))));
    }

    private static int define(CommandContext<CommandSourceStack> commandContext, Consumer<ItemStack> consumer) {
        Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (!(entity = player.getMainHandItem()).isEmpty()) {
                NiceNTightSettings.sendOkay(commandContext);
                consumer.accept((ItemStack)entity);
            } else {
                return NiceNTightSettings.inappropriateItemError((CommandSourceStack)commandContext.getSource());
            }
        }
        return 0;
    }
}

