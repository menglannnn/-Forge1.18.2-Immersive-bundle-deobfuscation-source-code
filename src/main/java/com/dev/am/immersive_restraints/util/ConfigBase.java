/*
 * Decompiled with CFR 0.152.
 */
package com.dev.am.immersive_restraints.util;

import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.mechanic.MinigameDirection;
import java.util.ArrayList;
import java.util.Random;

public abstract class ConfigBase {
    ArrayList<MinigameDirection> list = new ArrayList();
    Random random = new Random();

    public float getSpeedModifier() {
        return 1.0f;
    }

    public void addPause(int n2) {
        for (int i = 0; i < n2; ++i) {
            this.list.add(null);
        }
    }

    abstract ArrayList<MinigameDirection> generateSequence();
}

