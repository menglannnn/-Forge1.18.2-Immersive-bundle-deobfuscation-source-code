/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.model.PlayerModel
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.item.PartVisibility;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;

public class PartVisibilityEnum
extends PartVisibility {
    public PartVisibilityEnum(PlayerModel<?> playerModel) {
        this((HumanoidModel<?>)playerModel);
        this.showJacket = playerModel.jacket.visible;
        this.showLeftSleeve = playerModel.leftSleeve.visible;
        this.showRightSleeve = playerModel.rightSleeve.visible;
        this.showLeftPants = playerModel.leftPants.visible;
        this.showRightPants = playerModel.rightPants.visible;
    }

    public PartVisibilityEnum(HumanoidModel<?> humanoidModel) {
        this.showHead = humanoidModel.body.visible;
        this.showBody = humanoidModel.body.visible;
        this.showLeftArm = humanoidModel.leftArm.visible;
        this.showRightArm = humanoidModel.rightArm.visible;
        this.showLeftLeg = humanoidModel.leftArm.visible;
        this.showRightLeg = humanoidModel.rightArm.visible;
        this.showHat = humanoidModel.hat.visible;
    }

    public void unloadInto(PlayerModel<?> playerModel) {
        this.unloadInto((HumanoidModel<?>)playerModel);
        playerModel.jacket.visible = this.showJacket;
        playerModel.leftSleeve.visible = this.showLeftSleeve;
        playerModel.rightSleeve.visible = this.showRightSleeve;
        playerModel.leftPants.visible = this.showLeftPants;
        playerModel.rightPants.visible = this.showRightPants;
    }

    public void unloadInto(HumanoidModel<?> humanoidModel) {
        humanoidModel.body.visible = this.showHead;
        humanoidModel.body.visible = this.showBody;
        humanoidModel.leftArm.visible = this.showLeftArm;
        humanoidModel.rightArm.visible = this.showRightArm;
        humanoidModel.leftArm.visible = this.showLeftLeg;
        humanoidModel.rightArm.visible = this.showRightLeg;
        humanoidModel.hat.visible = this.showHat;
    }
}

