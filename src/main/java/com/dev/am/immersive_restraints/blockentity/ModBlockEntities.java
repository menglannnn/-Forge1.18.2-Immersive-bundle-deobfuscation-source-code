/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.blockentity;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class ModBlockEntities {
    private final int id;

    public ModBlockEntities(FriendlyByteBuf friendlyByteBuf) {
        this.id = friendlyByteBuf.readInt();
    }

    public ModBlockEntities(int n2) {
        this.id = n2;
    }

    public static void encode(ModBlockEntities bm2, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(bm2.id);
    }

    public static void handle(ModBlockEntities bm2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer serverPlayer = ((NetworkEvent.Context)supplier.get()).getSender();
            if (serverPlayer instanceof CurioSlotType) {
                CurioSlotType aG2 = (CurioSlotType)serverPlayer;
                aG2.abduction$setAnimationId(bm2.id);
            }
        });
        context.setPacketHandled(true);
    }
}

