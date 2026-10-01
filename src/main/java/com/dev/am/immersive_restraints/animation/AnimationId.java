/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dev.kosmx.playerAnim.api.layered.IAnimation
 *  dev.kosmx.playerAnim.api.layered.ModifierLayer
 *  dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory
 *  net.minecraft.client.player.AbstractClientPlayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
 */
package com.dev.am.immersive_restraints.animation;

import com.dev.am.immersive_restraints.core.ModCore;
import com.dev.am.immersive_restraints.animation.AnimationParser;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid="immersive_restraints", bus=Mod.EventBusSubscriber.Bus.MOD)
public class AnimationId {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent fMLClientSetupEvent) {
        AnimationParser.animationLayers.clear();
        for (int i = 0; i < 1; ++i) {
            String string = "am_animation" + i;
            AnimationParser.animationLayers.add(string);
            PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(new ResourceLocation("immersive_restraints", string), 42 + i, AnimationId::registerPlayerAnimation);
        }
    }

    private static IAnimation registerPlayerAnimation(AbstractClientPlayer abstractClientPlayer) {
        return new ModifierLayer();
    }
}

