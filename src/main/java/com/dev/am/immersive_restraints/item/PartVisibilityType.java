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
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  org.jetbrains.annotations.NotNull
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.ImmersiveRestraints;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/*
 * Renamed from abductionmod.content.ag
 */
public class PartVisibilityType {
    public static final SuggestionProvider<CommandSourceStack> SUGGESTION = (commandContext, suggestionsBuilder) -> SharedSuggestionProvider.suggest(PartVisibilityType.getSuggestionList(), (SuggestionsBuilder)suggestionsBuilder);

    @NotNull
    private static List<String> getSuggestionList() {
        ArrayList<String> arrayList = new ArrayList<String>(Abduction.slotNames.stream().toList());
        arrayList.add("all");
        return arrayList;
    }

    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)"takeOff").requires(commandSourceStack -> commandSourceStack.hasPermission(2))).then(Commands.argument((String)"players", (ArgumentType)EntityArgument.players()).then(Commands.argument((String)"type", (ArgumentType)StringArgumentType.word()).suggests(SUGGESTION).executes(commandContext -> {
            Collection collection = EntityArgument.getEntities((CommandContext)commandContext, (String)"players");
            String string = StringArgumentType.getString((CommandContext)commandContext, (String)"type");
            for (ServerPlayer serverPlayer : collection) {
                if (string.equals("all")) {
                    ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.takeoff.all_success").withStyle(ChatFormatting.GREEN), false);
                    EntityEventHandler.takeOffAllBondage((Player)serverPlayer);
                    return 0;
                }
                ItemStack itemStack2 = EntityEventHandler.getBondageInSlot((LivingEntity)serverPlayer, string);
                if (itemStack2 == null) continue;
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.takeoff.success", new Object[]{itemStack2.getHoverName()}).withStyle(ChatFormatting.GREEN), false);
                EntityEventHandler.tryTakeOffBondage(null, (LivingEntity)serverPlayer, itemStack2, null, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, itemStack -> serverPlayer.getInventory(itemStack, false));
                return 0;
            }
            return 0;
        })));
    }
}

