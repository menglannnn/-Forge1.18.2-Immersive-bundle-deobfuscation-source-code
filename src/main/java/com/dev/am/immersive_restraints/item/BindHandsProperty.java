/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.ChatFormatting
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.world.entity.Entity
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.blindfold.BlindfoldOverlay;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;

/*
 * Renamed from abductionmod.content.ah
 */
public class BindHandsProperty {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)"talkArea").then(Commands.argument((String)"value", (ArgumentType)IntegerArgumentType.integer((int)0)).executes(commandContext -> {
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (entity instanceof BlindfoldOverlay) {
                BlindfoldOverlay aF2 = (BlindfoldOverlay)entity;
                int n = IntegerArgumentType.getInteger((CommandContext)commandContext, (String)"value");
                aF2.abduction$setTalkArea(n);
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.talkarea.set", new Object[]{n}).withStyle(ChatFormatting.GREEN), false);
            }
            return 0;
        }))).then(Commands.literal((String)"reset").executes(commandContext -> {
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (entity instanceof BlindfoldOverlay) {
                BlindfoldOverlay aF2 = (BlindfoldOverlay)entity;
                aF2.abduction$setTalkArea(-1);
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.talkarea.reset").withStyle(ChatFormatting.GREEN), false);
            }
            return 0;
        }))).then(Commands.literal((String)"setPreference").then(Commands.argument((String)"value", (ArgumentType)IntegerArgumentType.integer((int)0)).executes(commandContext -> {
            int n = IntegerArgumentType.getInteger((CommandContext)commandContext, (String)"value");
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (entity instanceof BlindfoldOverlay) {
                BlindfoldOverlay aF2 = (BlindfoldOverlay)entity;
                aF2.abduction$setTalkAreaPreference(n);
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.talkarea.preference_set", new Object[]{n}).withStyle(ChatFormatting.GREEN), false);
            }
            return 0;
        })))).executes(commandContext -> {
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (entity instanceof BlindfoldOverlay) {
                BlindfoldOverlay aF2 = (BlindfoldOverlay)entity;
                int n = aF2.abduction$getTalkArea();
                Component translatableComponent = Component.translatable("command.talkarea.your_value", new Object[]{n});
                if (n < 0) {
                    translatableComponent = Component.translatable("command.talkarea.unlimited");
                }
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)translatableComponent.withStyle(new ChatFormatting[]{ChatFormatting.LIGHT_PURPLE, ChatFormatting.ITALIC}), false);
            }
            return 0;
        });
    }
}