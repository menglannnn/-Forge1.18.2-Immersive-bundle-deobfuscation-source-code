/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.data.loot.ChestLoot
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.storage.loot.LootTable$Builder
 */
package com.dev.am.immersive_restraints.gag;

import java.util.function.BiConsumer;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class GagLevel
extends ChestLoot {
    private static final ResourceLocation CUSTOM_CHEST_LOOT = new ResourceLocation("immersive_restraints", "chests/custom_chest_loot");

    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
    }
}

