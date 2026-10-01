/*
 * Decompiled with CFR 0.152.
 */
package com.dev.am.immersive_restraints.config;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.mechanic.MinigameDirection;
import java.util.ArrayList;

public class TextureConfig
extends ConfigBase {
    @Override
    public ArrayList<MinigameDirection> generateSequence() {
        for (int i = 0; i < 100; ++i) {
            this.list.add(MinigameDirection.randomKeypadDirection());
            this.addPause(7);
        }
        return this.list;
    }
}

