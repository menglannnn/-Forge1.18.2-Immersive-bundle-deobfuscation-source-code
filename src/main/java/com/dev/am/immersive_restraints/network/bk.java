/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.network;

import com.dev.am.immersive_restraints.animation.AnimationParser;
import com.dev.am.immersive_restraints.mechanic.MinigameCamera;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

public class bk {
    private final CompoundTag animationDefinitionTag;
    private final UUID playerId;

    public bk(FriendlyByteBuf friendlyByteBuf) {
        this.animationDefinitionTag = friendlyByteBuf.readNbt();
        this.playerId = friendlyByteBuf.readUUID();
    }

    public bk(CompoundTag compoundTag, UUID uUID) {
        this.animationDefinitionTag = compoundTag;
        this.playerId = uUID;
    }

    public static void encode(bk bk2, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeNbt(bk2.animationDefinitionTag);
        friendlyByteBuf.writeUUID(bk2.playerId);
    }

    public static void handle(bk bk2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().level.getPlayer(bk2.playerId);
            if (player != null) {
                m_0.playAnimation(player, new n_0(bk2.animationDefinitionTag), false, true);
            }
        });
        context.setPacketHandled(true);
    }
}

