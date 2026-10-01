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
 *  net.minecraft.world.entity.player.Player
 */
package com.dev.am.immersive_restraints.command;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ClothesProtectionCommand {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)Commands.literal((String)"clothesprotection").executes(commandContext -> {
            Object object = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (object instanceof Player) {
                Player player = (Player)object;
                boolean bl2 = !(object = (CurioSlotType)player).abduction$getProtectedClothes();
                object.abduction$setProtectedClothes(bl2);
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable(bl2 ? "command.clothesprotection.enabled" : "command.clothesprotection.disabled").withStyle(ChatFormatting.GREEN), false);
            }
            return 0;
        });
    }
}

