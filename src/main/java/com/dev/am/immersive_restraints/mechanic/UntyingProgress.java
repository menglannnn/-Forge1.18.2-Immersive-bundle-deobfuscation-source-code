/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.ItemBlockRenderTypes
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.client.event.EntityRenderersEvent$AddLayers
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
 *  software.bernie.geckolib.renderers.geo.GeoArmorRenderer
 */
package com.dev.am.immersive_restraints.mechanic;

import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.render.StruggleGeoRenderer;
import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.render.GeoArmorModelBase;
import com.dev.am.immersive_restraints.render.GeoArmorRendererBase;
import com.dev.am.immersive_restraints.item.GeoArmorBase;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.block.BlockVariantNames;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib.renderers.geo.GeoArmorRenderer;

/*
 * Renamed from abductionmod.content.z
 */
@Mod.EventBusSubscriber(modid="immersive_restraints", bus=Mod.EventBusSubscriber.Bus.MOD, value={Dist.CLIENT})
public class UntyingProgress {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent fMLClientSetupEvent) {
        if (!EntityEventHandler.isDevEnvironment()) {
            ModUtil.discordStartGameCallback();
        }
        BlockVariantNames.blocks.values().forEach(registryObject -> ItemBlockRenderTypes.setRenderLayer((Block)((Block)registryObject.get()), (RenderType)RenderType.translucent()));
        KeyBindings.init();
    }

    @SubscribeEvent
    public static void registerArmorRenderers(EntityRenderersEvent.AddLayers addLayers) {
        GeoArmorRenderer.registerArmorRenderer(GeoArmorBase.class, GeoArmorRendererBase::new);
    }
}

