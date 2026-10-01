/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  net.minecraft.ChatFormatting
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraftforge.event.RegisterCommandsEvent
 */
package com.dev.am.immersive_restraints.config;

import com.dev.am.immersive_restraints.core.ModCore;
import com.dev.am.immersive_restraints.command.AdjustCommand;
import com.dev.am.immersive_restraints.command.ArmBindStyleCommand;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.command.ClothesProtectionCommand;
import com.dev.am.immersive_restraints.command.SaveClothesCommand;
import com.dev.am.immersive_restraints.command.SetCollarNameCommand;
import com.dev.am.immersive_restraints.render.GeoArmorModelBase;
import com.dev.am.immersive_restraints.command.GagTextStyleCommand;
import com.dev.am.immersive_restraints.render.GeoArmorRendererBase;
import com.dev.am.immersive_restraints.command.HelplessCommand;
import com.dev.am.immersive_restraints.render.DynamicItemModel;
import com.dev.am.immersive_restraints.command.LegsTyingCommand;
import com.dev.am.immersive_restraints.gag.GagScriptEngine;
import com.dev.am.immersive_restraints.render.StruggleGeoRenderer;
import com.dev.am.immersive_restraints.item.KeyPredicate;
import com.dev.am.immersive_restraints.item.AutoLockProperty;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.item.PopOffProperty;
import com.dev.am.immersive_restraints.item.SoundProperty;
import com.dev.am.immersive_restraints.item.PartVisibilityType;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.item.BindHandsProperty;
import com.dev.am.immersive_restraints.item.BindLegsProperty;
import com.dev.am.immersive_restraints.item.ResistanceProperty;
import com.dev.am.immersive_restraints.item.VulnerabilityProperty;
import com.dev.am.immersive_restraints.item.TooltipProperty;
import com.dev.am.immersive_restraints.item.CreativeTabProperty;
import com.dev.am.immersive_restraints.item.ColorProperty;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.RegisterCommandsEvent;

public class NiceNTightSettings {
    public static final String[] ROOTS = new String[]{"", "nnt", "nice-n-tight"};

    public static void registerCommands(RegisterCommandsEvent registerCommandsEvent) {
        CommandDispatcher commandDispatcher = registerCommandsEvent.getDispatcher();
        for (String string : ROOTS) {
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, ResistanceProperty.getBuilder("textureBondage", PlayerEventHandler.DYNAMIC_TEXTURE_TAG, false));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, ResistanceProperty.getBuilder("textureBlindfold", PlayerEventHandler.DYNAMIC_BLINDFOLD_TEXTURE_TAG, false));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, ResistanceProperty.getBuilder("textureClothes", PlayerEventHandler.DYNAMIC_CLOTHES_TEXTURE_TAG, true));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, ResistanceProperty.getBuilder("textureItemIcon", PlayerEventHandler.DYNAMIC_ITEM_TEXTURE_TAG, false));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, ResistanceProperty.getBuilder("dc", PlayerEventHandler.DYNAMIC_CLOTHES_TEXTURE_TAG, true));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, BindLegsProperty.getBuilder("textureArmSize"));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, BindLegsProperty.getBuilder("dc"));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, ClothesProtectionCommand.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, SaveClothesCommand.getBuilder("dc"));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, VulnerabilityProperty.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)Commands.literal((String)"dc").then(VulnerabilityProperty.getBuilder())));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, CreativeTabProperty.getBuilder("utm"));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, CreativeTabProperty.getBuilder("untie"));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, ColorProperty.getBuilder("yank"));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, ColorProperty.getBuilder("yoink"));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, ColorProperty.getBuilder("pull"));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, KeyPredicate.getBuilder("norp"));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, KeyPredicate.getBuilder("safeword"));
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, SetCollarNameCommand.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, SoundProperty.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, LegsTyingCommand.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, TooltipProperty.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, HelplessCommand.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, GagScriptEngine.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, GagTextStyleCommand.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, AdjustCommand.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, PartVisibilityType.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, AutoLockProperty.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, PopOffProperty.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, BindHandsProperty.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, ArmBindStyleCommand.getBuilder());
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, ArmorVisibilityCommand.getBuilder());
        }
    }

    public static void registerCommand(CommandDispatcher<CommandSourceStack> commandDispatcher, String string, LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder) {
        if (string.isEmpty()) {
            commandDispatcher.register(literalArgumentBuilder);
            return;
        }
        LiteralArgumentBuilder literalArgumentBuilder2 = Commands.literal((String)string);
        commandDispatcher.register((LiteralArgumentBuilder)literalArgumentBuilder2.then(literalArgumentBuilder));
    }

    public static int inappropriateItemError(CommandSourceStack commandSourceStack) {
        commandSourceStack.sendFailure((Component)Component.translatable("command.wrong_item").withStyle(ChatFormatting.RED));
        return 1;
    }

    public static void sendOkay(CommandContext<CommandSourceStack> commandContext) {
        ((CommandSourceStack)commandContext.getSource()).sendSuccess((Component)Component.translatable("command.okay").withStyle(ChatFormatting.GREEN), false);
    }
}

