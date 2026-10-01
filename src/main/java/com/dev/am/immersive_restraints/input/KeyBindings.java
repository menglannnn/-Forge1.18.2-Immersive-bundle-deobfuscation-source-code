/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.KeyMapping
 *  net.minecraftforge.client.ClientRegistry
 */
package com.dev.am.immersive_restraints.input;

import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public final class KeyBindings {
    public static KeyMapping untyingKey;
    public static KeyMapping changePosKey;
    public static KeyMapping tightenKey;
    public static KeyMapping debugKey;
    public static KeyMapping minigameUpKey;
    public static KeyMapping minigameDownKey;
    public static KeyMapping minigameLeftKey;
    public static KeyMapping minigameRightKey;
    public static KeyMapping talkAreaPrefKey;

    private KeyBindings() {
    }

    public static void init() {
        untyingKey = KeyBindings.registerKey("start_untying", "key.categories.am", 71);
        changePosKey = KeyBindings.registerKey("change_position", "key.categories.am", 89);
        tightenKey = KeyBindings.registerKey("tighten_binds", "key.categories.am", 75);
        talkAreaPrefKey = KeyBindings.registerKey("talk_area_pref", "key.categories.am", 59);
        minigameUpKey = KeyBindings.registerKey("minigame_up", "key.categories.am", 265);
        minigameDownKey = KeyBindings.registerKey("minigame_down", "key.categories.am", 264);
        minigameLeftKey = KeyBindings.registerKey("minigame_left", "key.categories.am", 263);
        minigameRightKey = KeyBindings.registerKey("minigame_right", "key.categories.am", 262);
        if (EntityEventHandler.isDevEnvironment()) {
            debugKey = KeyBindings.registerKey("debug_key", "key.categories.am", 72);
        }
    }

    private static KeyMapping registerKey(String string, String string2, int n2) {
        KeyMapping keyMapping = new KeyMapping("key.am." + string, n2, string2);
        ClientRegistry.registerKeyBinding((KeyMapping)keyMapping);
        return keyMapping;
    }
}

