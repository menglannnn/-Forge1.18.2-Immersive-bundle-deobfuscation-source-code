/*
 * Decompiled with CFR 0.152.
 */
package com.dev.am.immersive_restraints.mechanic;

import java.util.Random;

/*
 * Renamed from abductionmod.content.i
 */
public enum MinigameDirection {
    LEFT(0),
    UP(1),
    DOWN(2),
    RIGHT(3),
    FORWARD(4),
    BACKWARD(5);

    public final int id;

    private MinigameDirection(int n3) {
        this.id = n3;
    }

    public static MinigameDirection random() {
        return MinigameDirection.values()[new Random().nextInt(MinigameDirection.values().length)];
    }

    public static MinigameDirection randomKeypadDirection() {
        MinigameDirection i_02 = MinigameDirection.values()[new Random().nextInt(MinigameDirection.values().length)];
        if (i_02 == FORWARD || i_02 == BACKWARD) {
            return MinigameDirection.randomKeypadDirection();
        }
        return i_02;
    }

    public static MinigameDirection getById(int n2) {
        for (MinigameDirection i_02 : MinigameDirection.values()) {
            if (i_02.id != n2) continue;
            return i_02;
        }
        return null;
    }
}

