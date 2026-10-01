/*
 * Decompiled with CFR 0.152.
 */
package com.dev.am.immersive_restraints.mechanic;

/*
 * Renamed from abductionmod.content.j
 */
public final class MinigameArrowData {
    public String name;
    public long unlockAt;
    boolean needsUnlocking;

    public MinigameArrowData(String string, boolean bl2, long l) {
        this.name = string;
        this.needsUnlocking = bl2;
        this.unlockAt = l;
    }
}

