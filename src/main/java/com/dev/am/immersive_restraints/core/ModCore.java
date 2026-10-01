/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 */
package com.dev.am.immersive_restraints.core;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.event.ClientEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ModCore {
    public static void openSlaveScreen(Player player) {
        if (player != null) {
            Minecraft.getInstance().setScreen((Screen)new ClientEventHandler((LivingEntity)player));
        }
    }
}

