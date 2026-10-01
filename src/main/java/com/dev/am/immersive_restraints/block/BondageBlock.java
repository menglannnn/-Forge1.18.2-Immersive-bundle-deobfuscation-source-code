/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.block;

import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.core.ModCore;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

public class BondageBlock {
    private final UUID playerId;

    public BondageBlock(FriendlyByteBuf friendlyByteBuf) {
        this.playerId = friendlyByteBuf.readInt();
    }

    public BondageBlock(UUID uUID) {
        this.playerId = uUID;
    }

    public static void encode(BondageBlock bi2, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(bi2.playerId);
    }

    public static void handle(BondageBlock bi2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().level.getPlayer(bi2.playerId);
            ModCore.openSlaveScreen(player);
        });
        context.setPacketHandled(true);
    }
}

