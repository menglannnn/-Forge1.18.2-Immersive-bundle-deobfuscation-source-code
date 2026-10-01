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
 *  net.minecraft.network.chat.ClickEvent
 *  net.minecraft.network.chat.ClickEvent$Action
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.HoverEvent
 *  net.minecraft.network.chat.HoverEvent$Action
 *  net.minecraft.network.chat.TextComponent
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  org.jetbrains.annotations.Nullable
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.config.NiceNTightConfig;
import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.blindfold.BlindfoldType;
import com.dev.am.immersive_restraints.item.BondageItem;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.network.StateSyncPacket;
import com.dev.am.immersive_restraints.command.ClothesProtectionCommand;
import com.dev.am.immersive_restraints.data.TagProvider;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/*
 * Renamed from abductionmod.content.aj
 */
public class ResistanceProperty {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder(String string, String string2, boolean bl2) {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)string).then(Commands.literal((String)"setTexture").then(Commands.argument((String)"URL", (ArgumentType)StringArgumentType.greedyString()).executes(commandContext -> {
            String string2 = StringArgumentType.getString((CommandContext)commandContext, (String)"URL");
            if (!TagProvider.isValidImageUrl(string2)) {
                ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.texture.url_malformed").withStyle(ChatFormatting.RED));
                return 1;
            }
            if (!TagProvider.isWhitelistedHost(string2)) {
                ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.texture.url_host_not_allowed").withStyle(ChatFormatting.RED));
                return 1;
            }
            return ResistanceProperty.execute("command.texture.set", bl2, (CommandSourceStack)commandContext.getSource(), itemStack -> PlayerEventHandler.setCustomTag(itemStack, string2, string2));
        })))).then(Commands.literal((String)"removeTexture").executes(commandContext -> ResistanceProperty.execute("command.texture.removed", bl2, (CommandSourceStack)commandContext.getSource(), itemStack -> PlayerEventHandler.removeCustomTag(itemStack, string2))))).then(Commands.literal((String)"showUrl").executes(commandContext -> ResistanceProperty.execute(null, bl2, (CommandSourceStack)commandContext.getSource(), itemStack -> {
            String string2 = PlayerEventHandler.getCustomTag(itemStack, string2);
            if (string2 != null) {
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.literal(string2).withStyle(style -> style.withBold(Boolean.valueOf(true)).withItalic(Boolean.valueOf(true)).withColor(ChatFormatting.BLUE).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, string2)).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (Object)Component.translatable("url.open")))), false);
            } else {
                ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.texture.url_not_set").withStyle(ChatFormatting.RED));
            }
        })));
    }

    private static int execute(@Nullable String string, boolean bl2, CommandSourceStack commandSourceStack, Consumer<ItemStack> consumer) {
        Entity entity = commandSourceStack.getEntityOrException();
        if (entity instanceof Player) {
            Item item;
            Player player = (Player)entity;
            entity = player.getMainHandItem();
            boolean bl3 = false;
            if (entity.isEmpty() && bl2) {
                entity = EntityEventHandler.getMainClothes((LivingEntity)player);
                bl3 = true;
            }
            if (entity != null && (item = entity.getItem()) instanceof BondageItem) {
                BondageItem aK2 = (BondageItem)item;
                if (bl2 && !(aK2 instanceof StateSyncPacket)) {
                    return NiceNTightSettings.inappropriateItemError(commandSourceStack);
                }
                if (string != null) {
                    commandSourceStack.sendSuccess((Component)Component.translatable(string).withStyle(ChatFormatting.GREEN), false);
                }
                consumer.accept((ItemStack)entity);
                if (bl3) {
                    ((BlindfoldType)player).abduction$setCurrentWardrobeItem((ItemStack)entity);
                }
            } else {
                return NiceNTightSettings.inappropriateItemError(commandSourceStack);
            }
        }
        return 0;
    }
}

