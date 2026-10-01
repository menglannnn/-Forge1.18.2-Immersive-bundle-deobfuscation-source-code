/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.FloatArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.ChatFormatting
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 */
package com.dev.am.immersive_restraints.command;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class AdjustCommand {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)"adjust").then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)"all").then(Commands.argument((String)"number", (ArgumentType)FloatArgumentType.floatArg((float)-4.0f, (float)4.0f)).executes(commandContext -> AdjustCommand.adjustAll((CommandContext<CommandSourceStack>)commandContext, "blindfold", "gag")))).then(Commands.literal((String)"blindfolds").then(Commands.argument((String)"number", (ArgumentType)FloatArgumentType.floatArg((float)-4.0f, (float)4.0f)).executes(commandContext -> AdjustCommand.adjustAll((CommandContext<CommandSourceStack>)commandContext, "blindfold"))))).then(Commands.literal((String)"gags").then(Commands.argument((String)"number", (ArgumentType)FloatArgumentType.floatArg((float)-4.0f, (float)4.0f)).executes(commandContext -> AdjustCommand.adjustAll((CommandContext<CommandSourceStack>)commandContext, "gag")))))).then(((LiteralArgumentBuilder)Commands.literal((String)"current").then(Commands.literal((String)"blindfold").then(Commands.argument((String)"number", (ArgumentType)FloatArgumentType.floatArg((float)-4.0f, (float)4.0f)).executes(commandContext -> AdjustCommand.adjustCurrent((CommandContext<CommandSourceStack>)commandContext, "blindfold"))))).then(Commands.literal((String)"gag").then(Commands.argument((String)"number", (ArgumentType)FloatArgumentType.floatArg((float)-4.0f, (float)4.0f)).executes(commandContext -> AdjustCommand.adjustCurrent((CommandContext<CommandSourceStack>)commandContext, "gag")))));
    }

    private static int adjustCurrent(CommandContext<CommandSourceStack> commandContext, String string) {
        Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if ((entity = EntityEventHandler.getBondageInSlot((LivingEntity)player, string)) == null || entity.isEmpty()) {
                ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.adjust.item_missing").withStyle(ChatFormatting.RED));
                return 1;
            }
            float f = FloatArgumentType.getFloat(commandContext, (String)"number");
            PlayerEventHandler.setCustomTag((ItemStack)entity, Float.valueOf(f), PlayerEventHandler.DATA_AM_ADJUSTMENT_MAP_TAG);
        }
        return 0;
    }

    private static int adjustAll(CommandContext<CommandSourceStack> commandContext, String ... stringArray) {
        Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
        if (entity instanceof Player) {
            Player player = (Player)entity;
            float f = FloatArgumentType.getFloat(commandContext, (String)"number");
            CurioSlotType aG2 = (CurioSlotType)player;
            CompoundTag compoundTag = aG2.abduction$getAdjustmentOffsetsMap().copy();
            for (String string : stringArray) {
                compoundTag.putFloat(string, f);
            }
            aG2.abduction$setAdjustmentOffsetsMap(compoundTag);
        }
        return 0;
    }
}

