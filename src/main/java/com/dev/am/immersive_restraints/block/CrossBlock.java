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
package com.dev.am.immersive_restraints.block;

import com.dev.am.immersive_restraints.core.ModCore;
import com.dev.am.immersive_restraints.animation.AnimationParser;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.mechanic.MinigameCamera;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

public class CrossBlock {
    private final CompoundTag animationDefinitionTag;
    private final UUID playerId;

    public CrossBlock(FriendlyByteBuf friendlyByteBuf) {
        this.animationDefinitionTag = friendlyByteBuf.readCompoundTag();
        this.playerId = friendlyByteBuf.readInt();
    }

    public CrossBlock(CompoundTag compoundTag, UUID uUID) {
        this.animationDefinitionTag = compoundTag;
        this.playerId = uUID;
    }

    public static void encode(CrossBlock bk2, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeCompoundTag(bk2.animationDefinitionTag);
        friendlyByteBuf.writeInt(bk2.playerId);
    }

    public static void handle(CrossBlock bk2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Player player = Minecraft.getInstance().level.getPlayer(bk2.playerId);
            if (player != null) {
                AnimationParser.playAnimation(player, new MinigameCamera(bk2.animationDefinitionTag), false, true);
            }
        });
        context.setPacketHandled(true);
    }
}

