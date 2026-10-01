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
 *  org.jetbrains.annotations.Nullable
 */
package com.dev.am.immersive_restraints.command;

import com.dev.am.immersive_restraints.config.NiceNTightConfig;
import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.item.BondageItem;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.animation.StruggleAnimations;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
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
import org.jetbrains.annotations.Nullable;

public class ArmBindStyleCommand {
    public static final SuggestionProvider<CommandSourceStack> ARM_BIND_TYPES_SUGGESTION = (commandContext, suggestionsBuilder) -> SharedSuggestionProvider.suggest(StruggleAnimations.armBindStyles.values(), (SuggestionsBuilder)suggestionsBuilder);

    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)"armBindStyle").then(Commands.literal((String)"set").then(Commands.argument((String)"type", (ArgumentType)StringArgumentType.greedyString()).suggests(ARM_BIND_TYPES_SUGGESTION).executes(commandContext -> {
            block5: {
                Object object;
                Entity entity;
                block7: {
                    block6: {
                        entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
                        if (!(entity instanceof Player)) break block5;
                        Player player = (Player)entity;
                        object = (entity = player.getMainHandItem()).getItem();
                        if (!(object instanceof BondageItem)) break block6;
                        BondageItem aK2 = (BondageItem)object;
                        if (aK2.properties.isBindHands((ItemStack)entity)) break block7;
                    }
                    return NiceNTightSettings.inappropriateItemError((CommandSourceStack)commandContext.getSource());
                }
                object = StringArgumentType.getString((CommandContext)commandContext, (String)"type");
                if (!StruggleAnimations.armBindStyles.containsValue(object)) {
                    ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.clothes.arm_bind_type_not_found", new Object[]{object}).withStyle(ChatFormatting.RED));
                    return 0;
                }
                for (String string : StruggleAnimations.armBindStyles.keySet()) {
                    if (!StruggleAnimations.armBindStyles.get(string).equals(object)) continue;
                    PlayerEventHandler.setCustomTag((ItemStack)entity, string, PlayerEventHandler.ARM_BIND_TYPE_TAG);
                    ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.clothes.arm_bind_type", new Object[]{object}).withStyle(ChatFormatting.GREEN), false);
                    break;
                }
            }
            return 0;
        })))).then(Commands.literal((String)"reset").executes(commandContext -> ArmBindStyleCommand.execute("command.okay", (CommandSourceStack)commandContext.getSource(), (itemStack, player) -> PlayerEventHandler.removeCustomTag(itemStack, PlayerEventHandler.ARM_BIND_TYPE_TAG))));
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

