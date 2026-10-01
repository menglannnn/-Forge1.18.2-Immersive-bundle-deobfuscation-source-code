/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  net.minecraft.client.Minecraft
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.resources.ResourceLocation
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.data.SkinProvider;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

/*
 * Renamed from abductionmod.content.ao
 */
public class DyeableProperty {
    private static final Pattern SHA1_PATTERN = Pattern.compile("^[a-fA-F0-9]{40}$");

    public static void register(CommandDispatcher<CommandSourceStack> commandDispatcher) {
        commandDispatcher.register(DyeableProperty.getBuilder());
    }

    public static LiteralArgumentBuilder<CommandSourceStack> getBuilder() {
        return (LiteralArgumentBuilder)Commands.literal((String)"clearTextureCache").executes(commandContext -> {
            File file = Minecraft.getInstance().getGameDirectory().gameDirectory;
            if (file.exists() && file.isDirectory()) {
                for (ResourceLocation resourceLocation : SkinProvider.textures.values()) {
                    try {
                        Minecraft.getInstance().textureManager.register(resourceLocation);
                    }
                    catch (Exception exception) {}
                }
                SkinProvider.textures.clear();
                try {
                    DyeableProperty.deleteSkins(file);
                }
                catch (IOException iOException) {
                    ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.clear_skin_cache.error"));
                }
                ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.clear_skin_cache.success"), false);
            } else {
                ((CommandSourceStack)commandContext.getSource()).sendFailure((Component)Component.translatable("command.clear_skin_cache.no_directory"));
            }
            return 1;
        });
    }

    public static void deleteSkins(File file) {
        File[] fileArray = file.listFiles();
        if (fileArray != null) {
            for (File file2 : fileArray) {
                File[] fileArray2;
                if (file2.isFile() && SHA1_PATTERN.matcher(file2.getName()).matches()) {
                    file2.delete();
                    continue;
                }
                if (!file2.isDirectory() || (fileArray2 = file2.listFiles()) == null) continue;
                for (File file3 : fileArray2) {
                    if (!file3.isFile() || !SHA1_PATTERN.matcher(file3.getName()).matches()) continue;
                    file3.delete();
                }
                if (file2.listFiles().length != 0) continue;
                file2.delete();
            }
        }
    }
}

