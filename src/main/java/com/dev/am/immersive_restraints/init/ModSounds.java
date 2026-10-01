/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package com.dev.am.immersive_restraints.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/*
 * Renamed from abductionmod.content.g
 */
public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, "immersive_restraints");
    public static final RegistryObject<SoundEvent> COLLAR_KEY_CLOSE = ModSounds.registerSoundEvent("collar_key_close");
    public static final RegistryObject<SoundEvent> COLLAR_KEY_OPEN = ModSounds.registerSoundEvent("collar_key_open");
    public static final RegistryObject<SoundEvent> COLLAR_PUT = ModSounds.registerSoundEvent("collar_put");
    public static final RegistryObject<SoundEvent> ELECTRIC_SHOCK = ModSounds.registerSoundEvent("electric_shock");
    public static final RegistryObject<SoundEvent> SHOCKER_ACTIVATED = ModSounds.registerSoundEvent("shocker_activated");
    public static final RegistryObject<SoundEvent> STICKY = ModSounds.registerSoundEvent("sticky");
    public static final RegistryObject<SoundEvent> DUCT_TAPE = ModSounds.registerSoundEvent("duct_tape");
    public static final RegistryObject<SoundEvent> SLAP = ModSounds.registerSoundEvent("slap");
    public static final RegistryObject<SoundEvent> WHIP = ModSounds.registerSoundEvent("whip");
    public static final RegistryObject<SoundEvent> CHAIN = ModSounds.registerSoundEvent("chain");
    public static final RegistryObject<SoundEvent> SLIME = ModSounds.registerSoundEvent("slime");
    public static final RegistryObject<SoundEvent> VINE = ModSounds.registerSoundEvent("vine");

    private static RegistryObject<SoundEvent> registerSoundEvent(String string) {
        return SOUND_EVENTS.register(string, () -> new SoundEvent(new ResourceLocation("immersive_restraints", string), 16.0f));
    }

    public static void register(IEventBus modBus) {
        SOUND_EVENTS.register(modBus);
    }
}