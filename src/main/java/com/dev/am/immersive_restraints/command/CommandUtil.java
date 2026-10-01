/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  abductionmod.content.n
 *  net.minecraft.client.Minecraft
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.client.event.ClientChatReceivedEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package com.dev.am.immersive_restraints.command;

import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.core.ModCore;
import com.dev.am.immersive_restraints.animation.AnimationParser;
import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.mechanic.MinigameCamera;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid="immersive_restraints", bus=Mod.EventBusSubscriber.Bus.FORGE, value={Dist.CLIENT})
public class CommandUtil {
    @SubscribeEvent
    public static void onChatReceived(ClientChatReceivedEvent clientChatReceivedEvent) {
        if (EntityEventHandler.isDevEnvironment() && clientChatReceivedEvent.getMessage().getString().contains("?play ")) {
            String[] stringArray = clientChatReceivedEvent.getMessage().getString().split(" ");
            if (stringArray.length != 3) {
                return;
            }
            Player player = Minecraft.getInstance().level.getPlayer(clientChatReceivedEvent.getSenderUUID());
            if (player != null) {
                AnimationParser.playAnimation((Player)Minecraft.getInstance().player, (MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.a(stringArray[2])}), false, true);
            }
        }
    }
}

