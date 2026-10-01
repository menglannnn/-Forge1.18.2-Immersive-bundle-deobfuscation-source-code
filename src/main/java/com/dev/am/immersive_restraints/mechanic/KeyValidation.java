/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dev.am.immersive_restraints.animation.StruggleAnimations
 *  javax.annotation.Nullable
 */
package com.dev.am.immersive_restraints.mechanic;

import com.dev.am.immersive_restraints.animation.StruggleAnimations;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.mechanic.MinigameCamera;
import javax.annotation.Nullable;

/*
 * Renamed from abductionmod.content.l
 */
public class KeyValidation {
    public MinigameCamera definition;
    @Nullable
    public Integer left;
    @Nullable
    public Integer right;
    @Nullable
    public Integer up;
    @Nullable
    public Integer down;
    @Nullable
    public Integer forward;
    @Nullable
    public Integer backward;
    @Nullable
    public Integer parent;
    @Nullable
    public StruggleAnimations.a struggle;

    public KeyValidation(MinigameCamera n_02) {
        this.definition = n_02;
    }

    public KeyValidation withStruggle(StruggleAnimations.a a2) {
        this.struggle = a2;
        return this;
    }

    public KeyValidation withLeftRight(Integer n2) {
        this.left = n2;
        this.right = n2;
        return this;
    }

    public KeyValidation withLeft(Integer n2) {
        this.left = n2;
        return this;
    }

    public KeyValidation withForward(Integer n2) {
        this.forward = n2;
        return this;
    }

    public KeyValidation withBackward(Integer n2) {
        this.backward = n2;
        return this;
    }

    public KeyValidation withRight(Integer n2) {
        this.right = n2;
        return this;
    }

    public KeyValidation withUp(Integer n2) {
        this.up = n2;
        return this;
    }

    public KeyValidation withDown(Integer n2) {
        this.down = n2;
        return this;
    }

    public KeyValidation returnTo(Integer n2) {
        this.parent = n2;
        return this;
    }

    public KeyValidation copy(Integer n2) {
        KeyValidation l_02 = k.getById((int)n2);
        this.definition.copy(l_02.definition);
        return this.withUp(l_02.up).withDown(l_02.down).withRight(l_02.right).withLeft(l_02.left).withForward(l_02.forward).withBackward(l_02.backward).withStruggle(l_02.struggle);
    }
}

