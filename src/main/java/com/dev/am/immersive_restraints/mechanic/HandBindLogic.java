/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.mechanic;

import com.dev.am.immersive_restraints.mechanic.StruggleMinigame;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class bn {
    public bn(FriendlyByteBuf friendlyByteBuf) {
    }

    public bn() {
    }

    public static void encode(bn bn2, FriendlyByteBuf friendlyByteBuf) {
    }

    public static void handle(bn bn2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(O::stop);
        context.setPacketHandled(true);
    }
}

