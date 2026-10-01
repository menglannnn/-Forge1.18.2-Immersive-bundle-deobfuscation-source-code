/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.data;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

/*
 * Renamed from abductionmod.content.bg
 */
public class ModItemTags {
    private final boolean isHolding;

    public ModItemTags(FriendlyByteBuf friendlyByteBuf) {
        this.isHolding = friendlyByteBuf.readBoolean();
    }

    public ModItemTags(boolean bl2) {
        this.isHolding = bl2;
    }

    public static void encode(ModItemTags bg_02, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeBoolean(bg_02.isHolding);
    }

    public static void handle(ModItemTags bg_02, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer serverPlayer = ((NetworkEvent.Context)supplier.get()).getSender();
            if (serverPlayer instanceof CurioSlotType) {
                CurioSlotType aG2 = (CurioSlotType)serverPlayer;
                aG2.abduction$setHoldingChangePositionKey(bg_02.isHolding);
            }
        });
        context.setPacketHandled(true);
    }
}

