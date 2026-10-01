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
 *  net.minecraft.network.chat.TextComponent
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 */
package com.dev.am.immersive_restraints.command;

import com.dev.am.immersive_restraints.config.NiceNTightConfig;
import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.blindfold.BlindfoldType;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.network.StateSyncPacket;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.ArrayList;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SaveClothesCommand {
    public static final SuggestionProvider<CommandSourceStack> CLOTHES_SUGGESTION = (commandContext, suggestionsBuilder) -> {
        Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
        if (entity instanceof BlindfoldType) {
            BlindfoldType aD2 = (BlindfoldType)entity;
            return SharedSuggestionProvider.suggest((String[])aD2.abduction$getAllWardrobeKeys(), (SuggestionsBuilder)suggestionsBuilder);
        }
        return SharedSuggestionProvider.suggest(new ArrayList(), (SuggestionsBuilder)suggestionsBuilder);
    };

    static LiteralArgumentBuilder<CommandSourceStack> getBuilder(String string) {
        LiteralArgumentBuilder literalArgumentBuilder = (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)string).then(Commands.literal((String)"save").executes(commandContext -> {
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (entity instanceof Player) {
                Player player = (Player)entity;
                if (!((entity = player.getMainHandItem()).getItem() instanceof StateSyncPacket)) {
                    entity = EntityEventHandler.getMainClothes((LivingEntity)player);
                }
                if (entity == null) {
                    return NiceNTightSettings.inappropriateItemError((CommandSourceStack)commandContext.getSource());
                }
                if (!entity.hasCustomName()) {
                    ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.clothes_have_no_name").withStyle(ChatFormatting.RED));
                    return 1;
                }
                String string = entity.getCustomName().getString();
                BlindfoldType aD2 = (BlindfoldType)player;
                aD2.abduction$saveWardrobeItem(string, (ItemStack)entity);
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.clothes.save_as_name", new Object[]{string}).withStyle(ChatFormatting.GREEN), false);
            }
            return 0;
        }))).then(Commands.literal((String)"save").then(Commands.argument((String)"name", (ArgumentType)StringArgumentType.greedyString()).suggests(CLOTHES_SUGGESTION).executes(commandContext -> {
            Object object = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (object instanceof Player) {
                Player player = (Player)object;
                object = StringArgumentType.getString((CommandContext)commandContext, (String)"name");
                ItemStack itemStack = player.getMainHandItem();
                if (!(itemStack.getItem() instanceof StateSyncPacket)) {
                    itemStack = EntityEventHandler.getMainClothes((LivingEntity)player);
                }
                if (itemStack == null) {
                    return NiceNTightSettings.inappropriateItemError((CommandSourceStack)commandContext.getSource());
                }
                itemStack.setHoverName((Component)Component.literal((String)object).withStyle(ChatFormatting.LIGHT_PURPLE));
                BlindfoldType aD2 = (BlindfoldType)player;
                aD2.abduction$saveWardrobeItem((String)object, itemStack);
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.clothes.save_as_name", new Object[]{object}).withStyle(ChatFormatting.GREEN), false);
            }
            return 0;
        })))).then(Commands.literal((String)"wear").then(Commands.argument((String)"name", (ArgumentType)StringArgumentType.greedyString()).suggests(CLOTHES_SUGGESTION).executes(commandContext -> {
            Object object = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (object instanceof Player) {
                Player player = (Player)object;
                BlindfoldType aD2 = (BlindfoldType)player;
                ItemStack itemStack = aD2.abduction$getWardrobeItem((String)(object = StringArgumentType.getString((CommandContext)commandContext, (String)"name")));
                if (itemStack == null) {
                    ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.clothes_not_found").withStyle(ChatFormatting.RED));
                    return 1;
                }
                EntityEventHandler.setMainClothes((LivingEntity)player, itemStack);
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.clothes.load_name", new Object[]{object}).withStyle(ChatFormatting.GREEN), false);
            }
            return 0;
        })))).then(Commands.literal((String)"remove").then(Commands.argument((String)"name", (ArgumentType)StringArgumentType.greedyString()).suggests(CLOTHES_SUGGESTION).executes(commandContext -> {
            Object object = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (object instanceof Player) {
                Player player = (Player)object;
                object = StringArgumentType.getString((CommandContext)commandContext, (String)"name");
                BlindfoldType aD2 = (BlindfoldType)player;
                aD2.abduction$removeWardrobeItem((String)object);
                NiceNTightSettings.sendOkay((CommandContext<CommandSourceStack>)commandContext);
            }
            return 0;
        })))).then(Commands.literal((String)"unwear").executes(commandContext -> {
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (entity instanceof Player) {
                Player player = (Player)entity;
                EntityEventHandler.setMainClothes((LivingEntity)player, ((Item)ModItems.CLOTHES.get()).getDefaultInstance());
                NiceNTightSettings.sendOkay((CommandContext<CommandSourceStack>)commandContext);
            }
            return 0;
        }));
        return literalArgumentBuilder;
    }
}

