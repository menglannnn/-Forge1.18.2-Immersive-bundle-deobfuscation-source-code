/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 */
package com.dev.am.immersive_restraints.network;

import com.dev.am.immersive_restraints.core.ModCore;
import com.dev.am.immersive_restraints.network.AnimationSyncPacket;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PacketHandler
extends AnimationSyncPacket {
    public PacketHandler() {
        super(new Item.Properties().tab(ModItems.ABDUCTION_ITEM_GROUP).stacksTo(1));
    }

    public PacketHandler(Item.Properties properties) {
        super(properties);
    }

    public boolean hasContainerItem(ItemStack itemStack) {
        return true;
    }

    public ItemStack getContainerItem(ItemStack itemStack) {
        itemStack = itemStack.getOrCreateTagElement();
        return itemStack;
    }
}

