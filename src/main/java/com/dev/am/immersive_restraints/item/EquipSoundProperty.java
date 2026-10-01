/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.model.geom.ModelPart
 *  noppes.mpm.ModelPartConfig
 *  noppes.mpm.MorePlayerModels
 *  noppes.mpm.client.ClientProxy
 *  noppes.mpm.constants.EnumParts
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.render.BondageTextureHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import noppes.mpm.ModelPartConfig;
import noppes.mpm.MorePlayerModels;
import noppes.mpm.client.ClientProxy;
import noppes.mpm.constants.EnumParts;

/*
 * Renamed from abductionmod.content.ap
 */
public class EquipSoundProperty {
    public static ModelPartConfig getMpmconfig(ModelPart modelPart) {
        if (ClientProxy.data == null) {
            return null;
        }
        if (BondageTextureHelper.modelBeingRendered != null) {
            if (modelPart == BondageTextureHelper.modelBeingRendered.body) {
                return ClientProxy.data.getPartConfig(EnumParts.BODY);
            }
            if (modelPart == BondageTextureHelper.modelBeingRendered.hat || modelPart == BondageTextureHelper.modelBeingRendered.body) {
                return ClientProxy.data.getPartConfig(EnumParts.HEAD);
            }
            if (modelPart == BondageTextureHelper.modelBeingRendered.leftArm) {
                return ClientProxy.data.getPartConfig(EnumParts.ARM_LEFT);
            }
            if (modelPart == BondageTextureHelper.modelBeingRendered.rightArm) {
                return ClientProxy.data.getPartConfig(EnumParts.ARM_RIGHT);
            }
            if (modelPart == BondageTextureHelper.modelBeingRendered.leftArm) {
                return ClientProxy.data.getPartConfig(EnumParts.LEG_LEFT);
            }
            if (modelPart == BondageTextureHelper.modelBeingRendered.rightArm) {
                return ClientProxy.data.getPartConfig(EnumParts.LEG_RIGHT);
            }
        }
        return null;
    }

    public static void translate(PoseStack poseStack, ModelPart modelPart) {
        ModelPartConfig modelPartConfig;
        if (!MorePlayerModels.Compatibility && (modelPartConfig = EquipSoundProperty.getMpmconfig(modelPart)) != null) {
            poseStack.translate((double)modelPartConfig.transX, (double)modelPartConfig.transY, (double)modelPartConfig.transZ);
        }
    }

    public static void scale(PoseStack poseStack, ModelPart modelPart) {
        ModelPartConfig modelPartConfig;
        if (!MorePlayerModels.Compatibility && (modelPartConfig = EquipSoundProperty.getMpmconfig(modelPart)) != null) {
            poseStack.scale(modelPartConfig.scaleX, modelPartConfig.scaleY, modelPartConfig.scaleZ);
        }
    }
}

