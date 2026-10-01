/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.suggestion.SuggestionProvider
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 *  net.minecraft.ChatFormatting
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.SharedSuggestionProvider
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package com.dev.am.immersive_restraints.command;

import com.dev.am.immersive_restraints.config.NiceNTightConfig;
import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.item.BondageItem;
import com.dev.am.immersive_restraints.render.DynamicItemModel;
import com.dev.am.immersive_restraints.data.LanguageProvider;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.List;
import java.util.function.BiFunction;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GagTextStyleCommand {
    public static final SuggestionProvider<CommandSourceStack> GAG_TYPES_SUGGESTION = (commandContext, suggestionsBuilder) -> SharedSuggestionProvider.suggest(GagTextStyleCommand.getSuggestionList(), (SuggestionsBuilder)suggestionsBuilder);

    @NotNull
    private static List<String> getSuggestionList() {
        return LanguageProvider.gagScripts.keySet().stream().filter(string -> !LanguageProvider.gagScripts.get(string).startsWith("//!hidden")).toList();
    }

    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)"gagTextStyle").then(Commands.literal((String)"set").then(Commands.argument((String)"type", (ArgumentType)StringArgumentType.word()).suggests(GAG_TYPES_SUGGESTION).executes(commandContext -> {
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (entity instanceof Player) {
                Player player = (Player)entity;
                if (!((entity = player.getMainHandItem()).getItem() instanceof BondageItem)) {
                    return NiceNTightSettings.inappropriateItemError((CommandSourceStack)commandContext.getSource());
                }
                String string = StringArgumentType.getString((CommandContext)commandContext, (String)"type");
                if (!GagTextStyleCommand.getSuggestionList().contains(string)) {
                    ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.clothes.gag_type_not_found", new Object[]{string}).withStyle(ChatFormatting.RED));
                    return 0;
                }
                PlayerEventHandler.setCustomTag((ItemStack)entity, string, PlayerEventHandler.GAG_TYPE_TAG);
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.clothes.set_gag_type", new Object[]{string}).withStyle(ChatFormatting.GREEN), false);
            }
            return 0;
        })))).then(Commands.literal((String)"reset").executes(commandContext -> GagTextStyleCommand.execute("command.okay", (CommandSourceStack)commandContext.getSource(), (itemStack, player) -> PlayerEventHandler.removeCustomTag(itemStack, PlayerEventHandler.GAG_TYPE_TAG))));
    }

    private static int execute(@Nullable String string, CommandSourceStack commandSourceStack, BiFunction<ItemStack, Player, Boolean> biFunction) {
        Entity entity = commandSourceStack.getEntityOrException();
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if ((entity = player.getMainHandItem()).getItem() instanceof BondageItem) {
                if (string != null) {
                    commandSourceStack.sendSuccess((Component)Component.translatable(string).withStyle(ChatFormatting.GREEN), false);
                }
                biFunction.apply((ItemStack)entity, player);
            } else {
                return NiceNTightSettings.inappropriateItemError(commandSourceStack);
            }
        }
        return 0;
    }
}

