/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.mechanic;

import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class bq {
    private final UUID playerId;
    private final String slotName;

    public bq(FriendlyByteBuf friendlyByteBuf) {
        this.playerId = friendlyByteBuf.readInt();
        this.slotName = friendlyByteBuf.readUtf(friendlyByteBuf.readInt());
    }

    public bq(UUID uUID, String string) {
        this.playerId = uUID;
        this.slotName = string;
    }

    public static void encode(bq bq2, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(bq2.playerId);
        friendlyByteBuf.writeInt(bq2.slotName.length());
        friendlyByteBuf.writeUtf(bq2.slotName, bq2.slotName.length());
    }

    public static void handle(bq bq2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer serverPlayer = ((NetworkEvent.Context)supplier.get()).getSender();
            if (serverPlayer == null) {
                return;
            }
            ServerPlayer serverPlayer2 = (ServerPlayer)serverPlayer.level.getPlayer(bq2.playerId);
            if (serverPlayer2 instanceof aG) {
                aG aG2 = (aG)serverPlayer2;
                if (serverPlayer.distanceTo((Entity)serverPlayer2) > 10.0f) {
                    return;
                }
                ItemStack itemStack2 = bx.getBondageInSlot((LivingEntity)serverPlayer2, bq2.slotName);
                if (itemStack2 != null && !itemStack2.isEmpty()) {
                    bx.tryTakeOffBondage((LivingEntity)serverPlayer, (LivingEntity)serverPlayer2, itemStack2, null, SoundEvents.ITEM_PICKUP, itemStack -> serverPlayer2.getInventory(itemStack, false));
                }
            }
        });
        context.setPacketHandled(true);
    }
}

