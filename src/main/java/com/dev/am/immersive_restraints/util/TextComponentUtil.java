/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.util;

import com.dev.am.immersive_restraints.core.ModCore;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

public class bi {
    private final UUID playerId;

    public bi(FriendlyByteBuf friendlyByteBuf) {
        this.playerId = friendlyByteBuf.readInt();
    }

    public bi(UUID uUID) {
        this.playerId = uUID;
    }

    public static void encode(bi bi2, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(bi2.playerId);
    }

    public static void handle(bi bi2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().level.getPlayer(bi2.playerId);
            A.openSlaveScreen(player);
        });
        context.setPacketHandled(true);
    }
}

