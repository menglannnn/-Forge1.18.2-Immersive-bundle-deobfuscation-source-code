/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.mechanic;

import com.dev.am.immersive_restraints.curios.CurioSlotType;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class bm {
    private final int id;

    public bm(FriendlyByteBuf friendlyByteBuf) {
        this.id = friendlyByteBuf.readInt();
    }

    public bm(int n2) {
        this.id = n2;
    }

    public static void encode(bm bm2, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(bm2.id);
    }

    public static void handle(bm bm2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer serverPlayer = ((NetworkEvent.Context)supplier.get()).getSender();
            if (serverPlayer instanceof aG) {
                aG aG2 = (aG)serverPlayer;
                aG2.abduction$setAnimationId(bm2.id);
            }
        });
        context.setPacketHandled(true);
    }
}

