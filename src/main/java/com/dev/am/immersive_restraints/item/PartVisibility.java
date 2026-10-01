/*
 * Decompiled with CFR 0.152.
 */
package com.dev.am.immersive_restraints.item;

import java.util.function.Consumer;

/*
 * Renamed from abductionmod.content.h
 */
public class PartVisibility {
    public boolean showHead;
    public boolean showBody;
    public boolean showLeftArm;
    public boolean showRightArm;
    public boolean showLeftLeg;
    public boolean showRightLeg;
    public boolean showHat;
    public boolean showJacket;
    public boolean showLeftSleeve;
    public boolean showRightSleeve;
    public boolean showLeftPants;
    public boolean showRightPants;
    public static final Consumer<PartVisibility> modelHeadOnly = h_02 -> {
        h_02.showBody = false;
        h_02.showLeftArm = false;
        h_02.showRightArm = false;
        h_02.showLeftLeg = false;
        h_02.showRightLeg = false;
    };
    public static final Consumer<PartVisibility> modelArmsOnly = h_02 -> {
        h_02.showHead = false;
        h_02.showHat = false;
        h_02.showBody = false;
        h_02.showLeftLeg = false;
        h_02.showRightLeg = false;
    };
    public static final Consumer<PartVisibility> torsoOnly = h_02 -> {
        h_02.showHead = false;
        h_02.showHat = false;
        h_02.showLeftArm = false;
        h_02.showRightArm = false;
        h_02.showLeftLeg = false;
        h_02.showRightLeg = false;
    };
    public static final Consumer<PartVisibility> bodyOnly = h_02 -> {
        h_02.showHead = false;
        h_02.showHat = false;
    };
    public static final Consumer<PartVisibility> hideArms = h_02 -> {
        h_02.showLeftArm = false;
        h_02.showRightArm = false;
        h_02.showLeftSleeve = false;
        h_02.showRightSleeve = false;
    };

    public PartVisibility copy() {
        PartVisibility h_02 = new PartVisibility();
        h_02.showHead = this.showHead;
        h_02.showBody = this.showBody;
        h_02.showLeftArm = this.showLeftArm;
        h_02.showRightArm = this.showRightArm;
        h_02.showLeftLeg = this.showLeftLeg;
        h_02.showRightLeg = this.showRightLeg;
        h_02.showHat = this.showHat;
        h_02.showJacket = this.showJacket;
        h_02.showLeftSleeve = this.showLeftSleeve;
        h_02.showRightSleeve = this.showRightSleeve;
        h_02.showLeftPants = this.showLeftPants;
        h_02.showRightPants = this.showRightPants;
        return h_02;
    }
}

