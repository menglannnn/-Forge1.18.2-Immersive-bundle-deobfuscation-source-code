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
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 */
package com.dev.am.immersive_restraints.command;

import com.dev.am.immersive_restraints.config.NiceNTightConfig;
import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class SetCollarNameCommand {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)"collar").then(Commands.literal((String)"setDisplayName").then(Commands.argument((String)"name", (ArgumentType)StringArgumentType.greedyString()).executes(commandContext -> {
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (entity instanceof Player) {
                Player player = (Player)entity;
                if ((entity = player.getMainHandItem()).getItem() != ModItems.COLLAR.get()) {
                    return SetCollarNameCommand.sendWrongItems((CommandContext<CommandSourceStack>)commandContext);
                }
                if (!SetCollarNameCommand.checkKey((CommandContext<CommandSourceStack>)commandContext, player, (ItemStack)entity)) {
                    return 0;
                }
                String string = "\u00a76" + StringArgumentType.getString((CommandContext)commandContext, (String)"name").replace("&", "\u00a7");
                String string2 = string.replaceAll("\u00a7.", "");
                if (string2.isEmpty()) {
                    return 0;
                }
                if (string2.length() > SetCollarNameCommand.getMaxNameLength() || string.length() > SetCollarNameCommand.getMaxNameLength() * 2) {
                    ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.collar_set_display_name.too_long", new Object[]{string}).withStyle(ChatFormatting.RED));
                    return 0;
                }
                PlayerEventHandler.setCustomTag((ItemStack)entity, string + "\u00a7r", PlayerEventHandler.COLLAR_DISPLAY_NAME_TAG);
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.collar_set_display_name.success", new Object[]{string}).withStyle(ChatFormatting.GREEN), false);
            }
            return 0;
        })))).then(Commands.literal((String)"resetDisplayName").executes(commandContext -> {
            CommandSourceStack commandSourceStack = (CommandSourceStack)commandContext.getSource();
            Entity entity = commandSourceStack.getEntityOrException();
            if (entity instanceof Player) {
                Player player = (Player)entity;
                if ((entity = player.getMainHandItem()).getItem() != ModItems.COLLAR.get()) {
                    return SetCollarNameCommand.sendWrongItems((CommandContext<CommandSourceStack>)commandContext);
                }
                if (!SetCollarNameCommand.checkKey((CommandContext<CommandSourceStack>)commandContext, player, (ItemStack)entity)) {
                    return 0;
                }
                NiceNTightSettings.sendOkay((CommandContext<CommandSourceStack>)commandContext);
                PlayerEventHandler.removeCustomTag((ItemStack)entity, PlayerEventHandler.COLLAR_DISPLAY_NAME_TAG);
            }
            return 0;
        }));
    }

    private static int getMaxNameLength() {
        return 26;
    }

    private static boolean checkKey(CommandContext<CommandSourceStack> commandContext, Player player, ItemStack itemStack) {
        if (PlayerEventHandler.hasCustomTag(itemStack, "keyValue")) {
            ItemStack itemStack2 = player.getOffhandItem();
            String string = "";
            if (PlayerEventHandler.hasCustomTag(itemStack2, "keyValue")) {
                string = PlayerEventHandler.getCustomTag(itemStack2, "keyValue");
            }
            if (!string.equals(PlayerEventHandler.getCustomTag(itemStack, "keyValue"))) {
                SetCollarNameCommand.sendNoKey(commandContext);
                return false;
            }
            return true;
        }
        return true;
    }

    private static int sendWrongItems(CommandContext<CommandSourceStack> commandContext) {
        ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.collar_set_display_name.wrong_items").withStyle(ChatFormatting.RED));
        return 1;
    }

    private static int sendNoKey(CommandContext<CommandSourceStack> commandContext) {
        ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.collar_set_display_name.no_key").withStyle(ChatFormatting.RED));
        return 1;
    }
}

