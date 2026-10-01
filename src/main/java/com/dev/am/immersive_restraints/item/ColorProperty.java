/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.ChatFormatting
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.targeting.TargetingConditions
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.phys.AABB
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

/*
 * Renamed from abductionmod.content.an
 */
public class ColorProperty {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder(String string) {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)string).then(Commands.argument((String)"player", (ArgumentType)EntityArgument.player()).executes(commandContext -> {
            ServerPlayer serverPlayer = EntityArgument.getPlayer((CommandContext)commandContext, (String)"player");
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (!(entity instanceof LivingEntity)) {
                return 0;
            }
            LivingEntity livingEntity = (LivingEntity)entity;
            ColorProperty.runCommand((CommandContext<CommandSourceStack>)commandContext, livingEntity, serverPlayer);
            return 0;
        }))).executes(commandContext -> {
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (!(entity instanceof LivingEntity)) {
                return 0;
            }
            LivingEntity livingEntity2 = (LivingEntity)entity;
            TargetingConditions targetingConditions = TargetingConditions.forCombat().range(5.0).selector(livingEntity -> {
                ServerPlayer serverPlayer;
                return livingEntity instanceof ServerPlayer && EntityEventHandler.isTied((LivingEntity)(serverPlayer = (ServerPlayer)livingEntity));
            });
            List list = entity.level.getNearbyEntities(targetingConditions, livingEntity2, new AABB(entity.getBoundingBox()).inflate(10.0));
            if (list.isEmpty()) {
                return 0;
            }
            Player player = (Player)list.get(0);
            for (Player player2 : list) {
                if (!(livingEntity2.distanceTo((Entity)player2) < livingEntity2.distanceTo((Entity)player))) continue;
                player = player2;
            }
            ColorProperty.runCommand((CommandContext<CommandSourceStack>)commandContext, livingEntity2, (ServerPlayer)player);
            return 0;
        });
    }

    private static void runCommand(CommandContext<CommandSourceStack> commandContext, LivingEntity livingEntity, ServerPlayer serverPlayer) {
        if (livingEntity == serverPlayer) {
            return;
        }
        if (livingEntity.distanceTo((Entity)serverPlayer) > 5.0f) {
            ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.yoink.too_far").withStyle(ChatFormatting.RED));
            return;
        }
        if (!EntityEventHandler.isTied((LivingEntity)serverPlayer)) {
            ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.yoink.not_tied").withStyle(ChatFormatting.RED));
            return;
        }
        if (EntityEventHandler.isTied(livingEntity)) {
            ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.yoink.youre_tied").withStyle(ChatFormatting.RED));
            return;
        }
        serverPlayer.teleportTo((ServerLevel)livingEntity.level, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), serverPlayer.getYRot(), serverPlayer.getXRot());
    }
}

