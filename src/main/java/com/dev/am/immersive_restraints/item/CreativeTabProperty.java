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
 *  net.minecraft.world.entity.player.Player
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.config.GameruleRegistry;
import com.dev.am.immersive_restraints.config.UtmForEverybody;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

/*
 * Renamed from abductionmod.content.am
 */
public class CreativeTabProperty {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder(String string) {
        return (LiteralArgumentBuilder)Commands.literal((String)string).executes(commandContext -> {
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (entity instanceof Player) {
                Player player = (Player)entity;
                if (player.hasPermission(2) || GameruleRegistry.isGameruleEnabled(player.level, UtmForEverybody.ID)) {
                    EntityEventHandler.takeOffAllBondage(player);
                } else {
                    ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.utm.cant_use").withStyle(ChatFormatting.RED));
                }
            }
            return 0;
        });
    }
}

