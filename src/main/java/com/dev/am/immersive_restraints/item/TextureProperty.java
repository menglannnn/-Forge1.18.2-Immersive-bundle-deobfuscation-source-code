/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.command.SaveClothesCommand;
import com.dev.am.immersive_restraints.item.UnequipSoundProperty;
import java.awt.Color;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/*
 * Renamed from abductionmod.content.as
 */
public class TextureProperty {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, "immersive_restraints");
    public static final RegistryObject<MobEffect> CHLOROFORM = MOB_EFFECTS.register("chloroform", () -> new UnequipSoundProperty(MobEffectCategory.HARMFUL, new Color(228, 242, 236).getRGB()));

    public static void register(IEventBus iEventBus) {
        MOB_EFFECTS.register(iEventBus);
    }
}