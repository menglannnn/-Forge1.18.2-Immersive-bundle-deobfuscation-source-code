/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.config;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

public class ServerConfig {
    private final UUID playerId;
    private final boolean untie;

    public ServerConfig(FriendlyByteBuf friendlyByteBuf) {
        this.playerId = friendlyByteBuf.readInt();
        this.untie = friendlyByteBuf.readBoolean();
    }

    public ServerConfig(UUID uUID, boolean bl2) {
        this.playerId = uUID;
        this.untie = bl2;
    }

    public static void encode(ServerConfig bt2, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(bt2.playerId);
        friendlyByteBuf.writeBoolean(bt2.untie);
    }

    public static void handle(ServerConfig bt2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer serverPlayer = ((NetworkEvent.Context)supplier.get()).getSender();
            if (serverPlayer == null) {
                return;
            }
            ServerPlayer serverPlayer2 = (ServerPlayer)serverPlayer.level.getPlayer(bt2.playerId);
            if (serverPlayer2 instanceof CurioSlotType) {
                CurioSlotType aG2 = (CurioSlotType)serverPlayer2;
                if (serverPlayer.distanceTo((Entity)serverPlayer2) > 10.0f) {
                    return;
                }
                aG2.abduction$setLegsUntied(bt2.untie);
                serverPlayer2.sendSystemMessage((Component)Component.translatable(bt2.untie ? "info.am.your_legs_are_untied" : "info.am.your_legs_are_tied"), true);
            }
        });
        context.setPacketHandled(true);
    }
}

