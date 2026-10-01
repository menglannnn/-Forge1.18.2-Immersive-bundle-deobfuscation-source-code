/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TextComponent
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.util;

import com.dev.am.immersive_restraints.mechanic.StruggleMinigame;
import java.util.function.Supplier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraftforge.network.NetworkEvent;

public class bh {
    private final int value;

    public bh(FriendlyByteBuf friendlyByteBuf) {
        this.value = friendlyByteBuf.readInt();
    }

    public bh(int n2) {
        this.value = n2;
    }

    public static void encode(bh bh2, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(bh2.value);
    }

    public static void handle(bh bh2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            O.floatingTexts.clear();
            O.floatingTexts.add(new O.b((Component)Component.literal((bh2.value > 0 ? "+" : "") + bh2.value).withStyle(bh2.value > 0 ? ChatFormatting.GREEN : ChatFormatting.RED)));
        });
        context.setPacketHandled(true);
    }
}

