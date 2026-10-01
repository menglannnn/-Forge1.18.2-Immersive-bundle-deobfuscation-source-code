/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.Util
 *  net.minecraft.network.chat.ChatType
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  org.jetbrains.annotations.NotNull
 */
package com.dev.am.immersive_restraints.data;

import java.util.List;
import net.minecraft.Util;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class RecipeProvider {
    public static List<? extends Player> getOtherPlayersInRadius(@NotNull ServerPlayer serverPlayer, int n2) {
        return serverPlayer.level.players().stream().filter(player -> player != serverPlayer && player.distanceTo((Entity)serverPlayer) < (float)n2).toList();
    }

    public static void sendMessageToOtherPlayers(ServerPlayer serverPlayer, int n2, Component component) {
        for (Player player : RecipeProvider.getOtherPlayersInRadius(serverPlayer, n2)) {
            ((ServerPlayer)player).sendSystemMessage(component, ChatType.CHAT, Util.NIL_UUID);
        }
    }
}

