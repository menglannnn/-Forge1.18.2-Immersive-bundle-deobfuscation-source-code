/*
 * Decompiled with CFR 0.152.
 */
package com.dev.am.immersive_restraints.blindfold;

public interface BlindfoldOverlay {
    public a abduction$getRpStatus();

    public void abduction$setRpStatus(a var1);

    public long abduction$getNoRpCommandTime();

    public void abduction$setNoRpCommandTime(long var1);

    public long abduction$getNoRpImmunityUntilTicks();

    public boolean abduction$isImmuneToBondage();

    public void abduction$setNoRpImmunityUntilTicks(long var1);

    public void abduction$setTalkArea(int var1);

    public int abduction$getTalkArea();

    public void abduction$setTalkAreaPreference(int var1);

    public int abduction$getTalkAreaPreference();

    public static enum a {
        NORMAL,
        SOFT_NO_RP,
        NO_RP;

    }
}

