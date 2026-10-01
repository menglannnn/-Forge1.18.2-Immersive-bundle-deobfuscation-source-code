/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.config.NiceNTightConfig;
import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import com.dev.am.immersive_restraints.render.DynamicItemModel;
import com.dev.am.immersive_restraints.data.LanguageProvider;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

/*
 * Renamed from abductionmod.content.ad
 */
public class AutoLockProperty {
    static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)"reloadGagScripts").requires(commandSourceStack -> commandSourceStack.hasPermission(4))).executes(commandContext -> {
            NiceNTightSettings.sendOkay((CommandContext<CommandSourceStack>)commandContext);
            LanguageProvider.initGagFiles(((CommandSourceStack)commandContext.getSource()).getLevel().getServerLevel());
            return 0;
        });
    }
}

