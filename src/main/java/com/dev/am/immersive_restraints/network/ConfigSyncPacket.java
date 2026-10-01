/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dev.am.immersive_restraints.item.VulnerabilityType
 *  net.minecraft.world.item.Item$Properties
 */
package com.dev.am.immersive_restraints.network;

import com.dev.am.immersive_restraints.core.ModCore;
import com.dev.am.immersive_restraints.network.AnimationSyncPacket;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.animation.AnimationType;
import com.dev.am.immersive_restraints.item.VulnerabilityType;
import com.dev.am.immersive_restraints.command.SetCollarNameCommand;
import net.minecraft.world.item.Item;

public class ConfigSyncPacket
extends AnimationSyncPacket
implements AnimationType {
    public ConfigSyncPacket() {
        super(new Item.Properties().tab(ModItems.ABDUCTION_ITEM_GROUP).durability(150));
    }

    @Override
    public VulnerabilityType getToolMaterial() {
        return bb.create().with(VulnerabilityType.Kind.METAL_FILING, 15.0f);
    }
}

