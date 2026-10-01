/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.arguments.EntityArgument
 *  net.minecraft.commands.arguments.item.ItemArgument
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  top.theillusivec4.curios.api.CuriosApi
 *  top.theillusivec4.curios.api.SlotContext
 *  top.theillusivec4.curios.api.type.ISlotType
 *  top.theillusivec4.curios.api.type.util.ICuriosHelper
 *  top.theillusivec4.curios.common.slottype.SlotType
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import java.util.HashSet;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.ISlotType;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;
import top.theillusivec4.curios.common.slottype.SlotType;

/*
 * Renamed from abductionmod.content.af
 */
public class SoundProperty {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)"restrain").requires(commandSourceStack -> commandSourceStack.hasPermission(2))).then(Commands.argument((String)"players", (ArgumentType)EntityArgument.players()).then(((RequiredArgumentBuilder)Commands.argument((String)"item1", (ArgumentType)ItemArgument.item()).executes(commandContext -> SoundProperty.execute((CommandContext<CommandSourceStack>)commandContext, 1))).then(((RequiredArgumentBuilder)Commands.argument((String)"item2", (ArgumentType)ItemArgument.item()).executes(commandContext -> SoundProperty.execute((CommandContext<CommandSourceStack>)commandContext, 2))).then(((RequiredArgumentBuilder)Commands.argument((String)"item3", (ArgumentType)ItemArgument.item()).executes(commandContext -> SoundProperty.execute((CommandContext<CommandSourceStack>)commandContext, 3))).then(((RequiredArgumentBuilder)Commands.argument((String)"item4", (ArgumentType)ItemArgument.item()).executes(commandContext -> SoundProperty.execute((CommandContext<CommandSourceStack>)commandContext, 4))).then(((RequiredArgumentBuilder)Commands.argument((String)"item5", (ArgumentType)ItemArgument.item()).executes(commandContext -> SoundProperty.execute((CommandContext<CommandSourceStack>)commandContext, 5))).then(Commands.argument((String)"item6", (ArgumentType)ItemArgument.item()).executes(commandContext -> SoundProperty.execute((CommandContext<CommandSourceStack>)commandContext, 6)))))))));
    }

    private static int execute(CommandContext<CommandSourceStack> commandContext, int n) {
        Collection collection = EntityArgument.getEntities(commandContext, (String)"players");
        ICuriosHelper iCuriosHelper = CuriosApi.getCuriosHelper();
        for (ServerPlayer serverPlayer : collection) {
            HashSet<String> hashSet = new HashSet<String>();
            block1: for (int i = 1; i <= n; ++i) {
                ItemStack itemStack2 = ItemArgument.getItem(commandContext, (String)("item" + i)).resolve().getDefaultInstance();
                Collection collection2 = CuriosApi.getSlotHelper().getSlotTypes();
                for (ISlotType iSlotType : collection2) {
                    SlotType slotType;
                    String string;
                    if (!(iSlotType instanceof SlotType) || hashSet.contains(string = (slotType = (SlotType)iSlotType).getIdentifier()) || !iCuriosHelper.isStackValid(new SlotContext(string, (LivingEntity)serverPlayer, i, false, true), itemStack2)) continue;
                    ItemStack itemStack3 = EntityEventHandler.getBondageInSlot((LivingEntity)serverPlayer, string);
                    if (itemStack3 != null && !itemStack3.isEmpty()) {
                        if (itemStack3.is(itemStack2.getItem())) continue;
                        EntityEventHandler.tryTakeOffBondage(null, (LivingEntity)serverPlayer, itemStack3, null, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, itemStack -> serverPlayer.getInventory(itemStack, false));
                    }
                    EntityEventHandler.putBondageItemInto((LivingEntity)serverPlayer, null, itemStack2, string, true);
                    hashSet.add(string);
                    continue block1;
                }
            }
        }
        return 0;
    }
}