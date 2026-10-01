/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.data.PackOutput
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.common.data.LanguageProvider
 *  net.minecraftforge.registries.RegistryObject
 *  org.apache.commons.lang3.text.WordUtils
 */
package com.dev.am.immersive_restraints.animation;

import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.block.BlockVariantNames;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.block.ModBlock;
import com.dev.am.immersive_restraints.render.DynamicItemModel;
import com.dev.am.immersive_restraints.block.LockableDoorBlock;
import com.dev.am.immersive_restraints.block.BondageSlabBlock;
import com.dev.am.immersive_restraints.block.BondageStairBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.text.WordUtils;

/*
 * Renamed from abductionmod.content.ax
 */
public class PlayerAnimLibIntegration
extends LanguageProvider {
    public PlayerAnimLibIntegration(PackOutput output) {
        super(output, "immersive_restraints_gen", "en_us");
    }

    protected void addTranslations() {
        for (RegistryObject<Block> registryObject : BlockVariantNames.blocks.values()) {
            if (!(registryObject.get() instanceof ModBlock) && !(registryObject.get() instanceof BondageStairBlock) && !(registryObject.get() instanceof BondageSlabBlock) && !(registryObject.get() instanceof LockableDoorBlock)) continue;
            String string = WordUtils.capitalize((String)registryObject.getId().getPath().replace("_", " ")).replace("Block ", "");
            this.add((Block)registryObject.get(), string);
        }
    }
}

