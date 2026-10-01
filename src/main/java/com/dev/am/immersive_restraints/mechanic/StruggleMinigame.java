/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.FormattedText
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.Level
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Unique
 */
package com.dev.am.immersive_restraints.mechanic;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.config.NiceNTightConfig;
import com.dev.am.immersive_restraints.mechanic.MinigameDirection;
import com.dev.am.immersive_restraints.config.GameruleRegistry;
import com.dev.am.immersive_restraints.config.StruggleMinigameSpeedPercent;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Unique;

public class StruggleMinigame {
    @Unique
    private static final ResourceLocation TEXTURE = new ResourceLocation("immersive_restraints", "textures/gui/arrow_minigame.png");
    private static final int unitSizePx = 32;
    private static final float keyPressThreshold = 0.05f;
    public static float speed = 0.01f;
    public static float spawnIntervalMs = 65.0f;
    public static int currentLastIndex = 4;
    public static ArrayList<a> arrows = new ArrayList();
    public static ArrayList<b> floatingTexts = new ArrayList();
    private static long nextSpawnAt = 0L;
    private static float circleFadeIn = 0.0f;
    private static boolean isRunning;
    private static Consumer<MinigameDirection> failCallback;
    private static Consumer<MinigameDirection> successCallback;
    private static BooleanSupplier shouldContinuePredicate;
    private static ArrayList<MinigameDirection> currentSequence;
    private static final float frameWidth = 480.0f;

    public static boolean isIsRunning() {
        return isRunning;
    }

    public static void start(Consumer<MinigameDirection> consumer, Consumer<MinigameDirection> consumer2, BooleanSupplier booleanSupplier) {
        StruggleMinigame.reset();
        failCallback = consumer;
        successCallback = consumer2;
        shouldContinuePredicate = booleanSupplier;
        isRunning = true;
    }

    public static void tick() {
        if (!StruggleMinigame.shouldTick()) {
            return;
        }
        if (shouldContinuePredicate != null && !shouldContinuePredicate.getAsBoolean()) {
            StruggleMinigame.stop();
        }
    }

    public static void tickFrame() {
        if (!StruggleMinigame.shouldTick()) {
            return;
        }
        if (Minecraft.getInstance().level == null) {
            StruggleMinigame.stop();
            return;
        }
        long l = System.currentTimeMillis();
        ConfigBase ConfigBase = StruggleMinigame.getArrowGenerator();
        if (currentSequence.isEmpty()) {
            currentSequence = ConfigBase.generateSequence();
        }
        if (l > nextSpawnAt) {
            Object object = currentSequence.remove(0);
            if (object != null) {
                arrows.add(new a((MinigameDirection)((Object)object)));
            }
            nextSpawnAt = l + (long)(spawnIntervalMs / StruggleMinigame.getSpeedModifier());
        }
        if (circleFadeIn < 1.0f) {
            circleFadeIn += 0.05f * Minecraft.getInstance().getDeltaFrameTime();
        }
        for (a object : arrows) {
            object.position -= speed * StruggleMinigame.getSpeedModifier() * ConfigBase.getSpeedModifier() * Minecraft.getInstance().getDeltaFrameTime();
            if (!object.isSkipped && !object.isRemoved && (double)object.position < 0.44999999925494194) {
                object.isSkipped = true;
                StruggleMinigame.onFail(object, object.arrowDirection);
            }
            if (object.isRemoved) {
                if (!(object.opacity > 0.0f)) continue;
                object.opacity -= 0.15f * Minecraft.getInstance().getDeltaFrameTime();
                continue;
            }
            object.opacity = 1.0f - Math.abs(0.5f - object.position) * 2.0f;
        }
        arrows.removeIf(a2 -> a2.position < 0.0f);
        for (b b3 : floatingTexts) {
            b3.position -= 0.15f * Minecraft.getInstance().getDeltaFrameTime();
        }
        floatingTexts.removeIf(b2 -> b2.position < 0.0f);
    }

    @NotNull
    private static ConfigBase getArrowGenerator() {
        return new NiceNTightConfig();
    }

    private static boolean shouldTick() {
        if (!isRunning) {
            return false;
        }
        return !Minecraft.getInstance().isPaused();
    }

    private static void onFail(@Nullable a a2, MinigameDirection i_02) {
        if (failCallback != null) {
            failCallback.accept(i_02);
        }
        StruggleMinigame.setRedCircle();
    }

    private static void onSuccess(@Nullable a a2, MinigameDirection i_02) {
        if (successCallback != null) {
            successCallback.accept(i_02);
        }
        StruggleMinigame.setNormalCircle();
    }

    public static void stop() {
        StruggleMinigame.reset();
    }

    public static void reset() {
        arrows.clear();
        floatingTexts.clear();
        isRunning = false;
        circleFadeIn = 0.0f;
        StruggleMinigame.setNormalCircle();
        currentSequence.clear();
        nextSpawnAt = 0L;
    }

    public static boolean keyPress(MinigameDirection i_02) {
        boolean bl2 = false;
        for (a a2 : arrows) {
            double d;
            if (a2.isRemoved || a2.arrowDirection != i_02 || !((d = Math.abs(0.5 - (double)Math.abs(a2.position))) < (double)0.05f)) continue;
            bl2 = true;
            a2.isRemoved = true;
            StruggleMinigame.onSuccess(a2, a2.arrowDirection);
            break;
        }
        if (!bl2) {
            StruggleMinigame.onFail(null, MinigameDirection.randomKeypadDirection());
        }
        return bl2;
    }

    private static float getSpeedModifier() {
        float f = (float)GameruleRegistry.getGameruleValue((Level)Minecraft.getInstance().level, StruggleMinigameSpeedPercent.ID).intValue() / 100.0f;
        if (f <= 0.0f) {
            f = 1.0f;
        }
        return f;
    }

    private static void setNormalCircle() {
        currentLastIndex = 4;
    }

    private static void setRedCircle() {
        currentLastIndex = 5;
    }

    public static void render(PoseStack poseStack, Gui gui) {
        int n2;
        RenderSystem.setShaderTexture((int)0, (ResourceLocation)TEXTURE);
        int n3 = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        float f = (float)n3 - 480.0f;
        int n4 = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        int n5 = n4 - 58 - 32;
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)circleFadeIn);
        gui.blit(poseStack, n3 / 2 - 16, n5, 32 * currentLastIndex, 96, 32, 32);
        for (a object : arrows) {
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)object.opacity);
            n2 = (int)(480.0f * object.position - 16.0f + f / 2.0f);
            gui.blit(poseStack, n2, n5, 32 * object.arrowDirection.id, 0, 32, 32);
            if (!object.isRemoved) continue;
            gui.blit(poseStack, n2, n5, 32 * object.arrowDirection.id, 32, 32, 32);
        }
        for (b b2 : floatingTexts) {
            RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            n2 = n3 / 2;
            gui.getFont().draw(poseStack, b2.text, (float)n2 - (float)gui.getFont().width((FormattedText)b2.text) / 2.0f, (float)n5 + b2.position * 10.0f - 10.0f, 0xFFFFFF);
        }
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    static {
        currentSequence = new ArrayList();
    }

    public static class a {
        public float position;
        public float opacity;
        public boolean isRemoved;
        public boolean isSkipped;
        public MinigameDirection arrowDirection;
        public int _id;

        public String toString() {
            return "Arrow[" + this._id + "]";
        }

        public a(MinigameDirection i_02) {
            this.arrowDirection = i_02;
            this.position = 1.0f;
            this.opacity = 0.0f;
            this._id = (int)(Math.random() * 2.147483647E9);
        }
    }

    public static class b {
        public Component text;
        public float position;

        public b(Component component) {
            this.text = component;
            this.position = 1.0f;
        }
    }
}

