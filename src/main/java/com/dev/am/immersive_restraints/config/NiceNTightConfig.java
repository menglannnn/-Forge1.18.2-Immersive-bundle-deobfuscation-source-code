/*
 * Decompiled with CFR 0.152.
 */
package com.dev.am.immersive_restraints.config;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.mechanic.MinigameDirection;
import java.util.ArrayList;

public class NiceNTightConfig
extends ConfigBase {
    @Override
    public float getSpeedModifier() {
        return super.getSpeedModifier() * 1.3f;
    }

    @Override
    public ArrayList<MinigameDirection> generateSequence() {
        int n2 = 3;
        for (int i = 0; i < 100; ++i) {
            for (float f = 3.0f; this.random.nextFloat() < f; f *= 0.3f) {
                this.addPause(n2);
                this.list.add(MinigameDirection.randomKeypadDirection());
            }
            this.addPause(this.random.nextInt(4, 8));
        }
        return this.list;
    }
}

