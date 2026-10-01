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
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.config.NiceNTightConfig;
import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.blindfold.BlindfoldType;
import com.dev.am.immersive_restraints.item.BondageItem;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/*
 * Renamed from abductionmod.content.ai
 */
public class BindLegsProperty {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder(String string) {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)string).then(BindLegsProperty.armSize("slimArms", "command.texture.slim_arms", itemStack -> PlayerEventHandler.setCustomTag(itemStack, "slim", PlayerEventHandler.ARM_SHAPE_TAG)))).then(BindLegsProperty.armSize("fullArms", "command.texture.full_arms", itemStack -> PlayerEventHandler.setCustomTag(itemStack, "full", PlayerEventHandler.ARM_SHAPE_TAG)))).then(BindLegsProperty.armSize("resetArms", "command.texture.arm_tag_removed", itemStack -> PlayerEventHandler.removeCustomTag(itemStack, PlayerEventHandler.ARM_SHAPE_TAG)));
    }

    private static LiteralArgumentBuilder<CommandSourceStack> armSize(String string, String string2, Consumer<ItemStack> consumer) {
        return (LiteralArgumentBuilder)Commands.literal((String)string).executes(commandContext -> {
            Entity entity = ((CommandSourceStack)commandContext.getSource()).getEntityOrException();
            if (entity instanceof Player) {
                Player player = (Player)entity;
                boolean bl2 = false;
                ItemStack itemStack = player.getMainHandItem();
                if (itemStack.isEmpty()) {
                    itemStack = EntityEventHandler.getMainClothes((LivingEntity)player);
                    bl2 = true;
                }
                if (itemStack != null && itemStack.getItem() instanceof BondageItem) {
                    ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable(string2).withStyle(ChatFormatting.GREEN), false);
                    consumer.accept(itemStack);
                    if (bl2) {
                        ((BlindfoldType)player).abduction$setCurrentWardrobeItem(itemStack);
                    }
                } else {
                    return NiceNTightSettings.inappropriateItemError((CommandSourceStack)commandContext.getSource());
                }
            }
            return 0;
        });
    }
}

