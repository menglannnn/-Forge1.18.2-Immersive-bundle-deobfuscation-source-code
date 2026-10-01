/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.ChatFormatting
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 */
package com.dev.am.immersive_restraints.command;

import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class HelplessCommand {
    public static String helplessTag = "am_helpless";

    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)Commands.literal((String)"helpless").executes(commandContext -> {
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (entity instanceof Player) {
                Player player = (Player)entity;
                if (player.getTags().contains(helplessTag)) {
                    if (EntityEventHandler.isTied((LivingEntity)player)) {
                        ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.helpless_tied").withStyle(ChatFormatting.RED));
                        return 0;
                    }
                    player.addTag(helplessTag);
                    ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.helpless_removed").withStyle(ChatFormatting.GREEN));
                } else {
                    player.removeTag(helplessTag);
                    ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.helpless_added").withStyle(ChatFormatting.YELLOW));
                }
            }
            return 0;
        });
    }
}

