/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  it.unimi.dsi.fastutil.booleans.BooleanConsumer
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.gui.Font
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.Checkbox
 *  net.minecraft.client.gui.components.MultiLineLabel
 *  net.minecraft.client.gui.components.events.GuiEventListener
 *  net.minecraft.client.gui.screens.ConfirmScreen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.FormattedText
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 */
package com.dev.am.immersive_restraints.event;

import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value=Dist.CLIENT)
public class EventHandler
extends ConfirmScreen {
    private static final Component ASK_AGAIN_TEXT = Component.translatable("ui.am.ask_age_again");
    private static final Component WARNING_TEXT = Component.translatable("ui.am.ask_age_proceed_warn");
    private static final Component TOP_WARN = Component.translatable("ui.am.ask_age_warning");
    private static final Component WARNING_TEXT_TOP = Component.translatable("ui.am.ask_age_18");
    private final boolean showWarning;
    private MultiLineLabel warnBottom = MultiLineLabel.EMPTY;

    public EventHandler(BooleanConsumer booleanConsumer) {
        super(booleanConsumer, TOP_WARN, WARNING_TEXT_TOP);
        this.title = Component.translatable("ui.am.proceed");
        this.title = Component.translatable("ui.am.go_back");
        this.showWarning = true;
    }

    protected void init(int n2) {
        this.warnBottom = MultiLineLabel.create((Font)this.font, (FormattedText)WARNING_TEXT.copy().withStyle(ChatFormatting.RED), (int)(this.width - 50));
        int n3 = this.warnBottom.getLineCount() * 9;
        Checkbox checkbox = (Checkbox)this.addRenderableWidget((GuiEventListener)new Checkbox(this.width / 2 - 50 - 105 + 45, this.height / 6 + 96 + n3, 100, 20, ASK_AGAIN_TEXT, ((Boolean)c_0.warnOnStartup.get()).booleanValue()));
        this.addRenderableWidget((GuiEventListener)new Button(this.width / 2 - 50 - 105 + 45, this.height / 6 + 96 + 30 + n3, 100, 20, this.title, button -> {
            this.callback.accept(true);
            boolean bl2 = checkbox.isChecked();
            EntityEventHandler.debugLog("Ask again: " + bl2);
            c_0.warnOnStartup.set((Object)bl2);
        }));
        this.addRenderableWidget((GuiEventListener)new Button(this.width / 2 - 50 + 105 - 45, this.height / 6 + 96 + 30 + n3, 100, 20, this.title, button -> this.minecraft.popScreen()));
    }

    public void render(PoseStack poseStack, int n2, int n3, float f) {
        super.render(poseStack, n2, n3, f);
        poseStack.translate(0.0, 30.0, 0.0);
        this.warnBottom.renderCentered(poseStack, this.width / 2, 90);
    }
}

