/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.blockentity;

import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.mechanic.StruggleMinigame;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class StocksBlockEntity {
    public StocksBlockEntity(FriendlyByteBuf friendlyByteBuf) {
    }

    public StocksBlockEntity() {
    }

    public static void encode(StocksBlockEntity bn2, FriendlyByteBuf friendlyByteBuf) {
    }

    public static void handle(StocksBlockEntity bn2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(StruggleMinigame::stop);
        context.setPacketHandled(true);
    }
}

