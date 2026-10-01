/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.Util
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.chat.ChatType
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraftforge.network.NetworkEvent$Context
 *  top.theillusivec4.curios.api.SlotResult
 */
package com.dev.am.immersive_restraints.block;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.SlotResult;

public class StAndrewsCrossBlock {
    private final UUID playerId;

    public StAndrewsCrossBlock(FriendlyByteBuf friendlyByteBuf) {
        this.playerId = friendlyByteBuf.readInt();
    }

    public StAndrewsCrossBlock(UUID uUID) {
        this.playerId = uUID;
    }

    public static void encode(StAndrewsCrossBlock bl2, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(bl2.playerId);
    }

    public static void handle(StAndrewsCrossBlock bl2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer serverPlayer = ((NetworkEvent.Context)supplier.get()).getSender();
            if (serverPlayer == null || EntityEventHandler.hasMittens((LivingEntity)serverPlayer)) {
                return;
            }
            ServerPlayer serverPlayer2 = (ServerPlayer)serverPlayer.level.getPlayer(bl2.playerId);
            if (serverPlayer2 instanceof CurioSlotType) {
                CurioSlotType aG2 = (CurioSlotType)serverPlayer2;
                List<SlotResult> list = EntityEventHandler.getBondages((LivingEntity)serverPlayer2);
                for (SlotResult slotResult : list) {
                    if (!PlayerEventHandler.hasCustomTag(slotResult.stack(), "struggleAddedResistance")) continue;
                    context.getSender().sendSystemMessage((Component)Component.translatable("command.you_have_tightened", new Object[]{serverPlayer2.getName()}).withStyle(ChatFormatting.WHITE), ChatType.GAME_INFO, Util.NIL_UUID);
                    serverPlayer2.sendSystemMessage((Component)Component.translatable("command.someone_tightened_you").withStyle(ChatFormatting.WHITE), ChatType.GAME_INFO, Util.NIL_UUID);
                    PlayerEventHandler.removeCustomTag(slotResult.stack(), "struggleAddedResistance");
                    EntityEventHandler.playSound((LivingEntity)serverPlayer2, SoundEvents.ITEM_PICKUP);
                    break;
                }
            }
        });
        context.setPacketHandled(true);
    }
}

