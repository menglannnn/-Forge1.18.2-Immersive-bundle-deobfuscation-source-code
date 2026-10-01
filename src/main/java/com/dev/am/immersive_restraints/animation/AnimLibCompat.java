/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.datafixers.util.Pair
 *  net.minecraft.data.PackOutput
 *  net.minecraft.data.loot.LootTableProvider
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.LootTable$Builder
 *  net.minecraft.world.level.storage.loot.LootTables
 *  net.minecraft.world.level.storage.loot.ValidationContext
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParamSet
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
 */
package com.dev.am.immersive_restraints.animation;

import com.dev.am.immersive_restraints.render.GeoArmorModelBase;
import com.dev.am.immersive_restraints.gag.GagType;
import com.dev.am.immersive_restraints.gag.GagLevel;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

/*
 * Renamed from abductionmod.content.ay
 */
public class AnimLibCompat
extends LootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> loot_tables = ImmutableList.of((Object)Pair.of(GagType::new, (Object)LootContextParamSets.EMPTY), (Object)Pair.of(GagLevel::new, (Object)LootContextParamSets.CHEST));

    public AnimLibCompat(PackOutput output) {
        super(output);
    }

    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return this.loot_tables;
    }

    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
        map.forEach((resourceLocation, lootTable) -> LootTables.validate((ValidationContext)validationContext, (ResourceLocation)resourceLocation, (LootTable)lootTable));
    }
}

