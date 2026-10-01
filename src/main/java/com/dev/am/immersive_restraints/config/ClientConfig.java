/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.config;

import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.blindfold.BlindfoldOverlay;
import java.util.function.Supplier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class ClientConfig {
    public ClientConfig(FriendlyByteBuf friendlyByteBuf) {
    }

    public ClientConfig() {
    }

    public static void encode(ClientConfig br2, FriendlyByteBuf friendlyByteBuf) {
    }

    public static void handle(ClientConfig br2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer serverPlayer = ((NetworkEvent.Context)supplier.get()).getSender();
            if (serverPlayer instanceof BlindfoldOverlay) {
                BlindfoldOverlay aF2 = (BlindfoldOverlay)serverPlayer;
                int n2 = aF2.abduction$getTalkAreaPreference();
                if (n2 <= 0) {
                    serverPlayer.sendSystemMessage((Component)Component.translatable("command.talkarea.preference_not_set").withStyle(ChatFormatting.RED), false);
                    return;
                }
                aF2.abduction$setTalkArea(n2);
                serverPlayer.sendSystemMessage((Component)Component.translatable("command.talkarea.set", new Object[]{n2}).withStyle(ChatFormatting.GREEN), false);
            }
        });
        context.setPacketHandled(true);
    }
}

