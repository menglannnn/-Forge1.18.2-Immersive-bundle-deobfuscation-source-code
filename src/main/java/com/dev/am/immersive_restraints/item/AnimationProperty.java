/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.data.DataGenerator
 *  net.minecraft.data.PackOutput
 *  net.minecraft.data.DataProvider
 *  net.minecraftforge.common.data.ExistingFileHelper
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  net.minecraftforge.forge.event.lifecycle.GatherDataEvent
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.animation.PlayerAnimProvider;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.animation.BendyLibIntegration;
import com.dev.am.immersive_restraints.animation.PlayerAnimLibIntegration;
import com.dev.am.immersive_restraints.core.ModCore;
import com.dev.am.immersive_restraints.animation.AnimLibCompat;
import com.dev.am.immersive_restraints.animation.AnimationHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.DataProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

/*
 * Renamed from abductionmod.content.at
 */
@Mod.EventBusSubscriber(modid="immersive_restraints", bus=Mod.EventBusSubscriber.Bus.MOD)
public class AnimationProperty {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent gatherDataEvent) {
        DataGenerator dataGenerator = gatherDataEvent.getGenerator();
        PackOutput output = dataGenerator.getPackOutput();
        ExistingFileHelper existingFileHelper = gatherDataEvent.getExistingFileHelper();
        dataGenerator.addProvider(gatherDataEvent.includeServer(), (DataProvider)new AnimLibCompat(output));
        dataGenerator.addProvider(gatherDataEvent.includeClient(), (DataProvider)new PlayerAnimProvider(output, existingFileHelper));
        dataGenerator.addProvider(gatherDataEvent.includeClient(), (DataProvider)new BendyLibIntegration(output, existingFileHelper));
        dataGenerator.addProvider(gatherDataEvent.includeClient(), (DataProvider)new PlayerAnimLibIntegration(output));
        dataGenerator.addProvider(gatherDataEvent.includeServer(), (DataProvider)new AnimationHelper(output));
        dataGenerator.run();
    }
}

