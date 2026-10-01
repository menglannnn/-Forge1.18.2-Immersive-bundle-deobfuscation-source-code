/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  dev.kosmx.playerAnim.core.util.MathHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.client.event.EntityViewRenderEvent$CameraSetup
 *  net.minecraftforge.client.event.InputEvent$MouseScrollEvent
 *  net.minecraftforge.client.event.RegisterClientCommandsEvent
 *  net.minecraftforge.event.entity.EntityJoinLevelEvent
 *  net.minecraftforge.event.entity.EntityLeaveLevelEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package com.dev.am.immersive_restraints.mechanic;

import com.dev.am.immersive_restraints.config.NiceNTightConfig;
import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.blindfold.BlindfoldEffect;
import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.dev.am.immersive_restraints.item.PartVisibilityEnum;
import com.dev.am.immersive_restraints.item.DyeableProperty;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.data.ModRecipes;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.block.StocksBlock;
import com.dev.am.immersive_restraints.core.ModCore;
import com.dev.am.immersive_restraints.animation.AnimationParser;
import com.dev.am.immersive_restraints.mechanic.MinigameCamera;
import com.mojang.brigadier.CommandDispatcher;
import dev.kosmx.playerAnim.core.util.MathHelper;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/*
 * Renamed from abductionmod.content.y
 */
@Mod.EventBusSubscriber(modid="immersive_restraints", bus=Mod.EventBusSubscriber.Bus.FORGE, value={Dist.CLIENT})
public class TyingProgress {
    private static float targetYaw = 0.0f;
    private static float targetPitch = 0.0f;
    private static float targetRoll = 0.0f;
    private static float currentYaw = 0.0f;
    private static float currentPitch = 0.0f;
    private static float currentRoll = 0.0f;

    @SubscribeEvent
    public static void playerJoin(EntityJoinLevelEvent entityJoinLevelEvent) {
        Entity entity = entityJoinLevelEvent.getEntity();
        if (entity.level().isClientSide && entity instanceof Player) {
            Player player = (Player)entity;
            AnimationParser.playAnimation(player, null, false, false);
        }
    }

    @SubscribeEvent
    public static void cameraEvent(EntityViewRenderEvent.CameraSetup cameraSetup) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.options.getCameraType().isFirstPerson()) {
            MinigameCamera n_02 = AnimationParser.currentLocalAnimation;
            if (n_02 != null) {
                targetPitch = n_02.camPitch;
                targetYaw = n_02.camYaw;
                targetRoll = n_02.camRoll;
            } else {
                targetYaw = 0.0f;
                targetPitch = 0.0f;
                targetRoll = 0.0f;
            }
            float f = Minecraft.getInstance().getDeltaFrameTime();
            float f2 = f / 5.0f;
            if (f2 == 0.0f) {
                f2 = 0.0022857143f;
            }
            currentPitch = MathHelper.lerp((float)f2, (float)currentPitch, (float)targetPitch);
            currentYaw = MathHelper.lerp((float)f2, (float)currentYaw, (float)targetYaw);
            currentRoll = MathHelper.lerp((float)f2, (float)currentRoll, (float)targetRoll);
            cameraSetup.setPitch(cameraSetup.getPitch() + currentPitch);
            cameraSetup.setYaw(cameraSetup.getYaw() + currentYaw);
            cameraSetup.setRoll(cameraSetup.getRoll() + currentRoll);
        }
    }

    @SubscribeEvent
    public static void playerLeft(EntityLeaveLevelEvent entityLeaveLevelEvent) {
        Entity entity = entityLeaveLevelEvent.getEntity();
        if (entity.level().isClientSide && entity instanceof Player) {
            Player player = (Player)entity;
            AnimationParser.playAnimation(player, null, false, false);
        }
    }

    @SubscribeEvent
    public static void onScroll(InputEvent.MouseScrollEvent mouseScrollEvent) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        if (localPlayer == null) {
            return;
        }
        CurioSlotType aG2 = (CurioSlotType)localPlayer;
        if (aG2.abduction$isHoldingChangePositionKey()) {
            double d = mouseScrollEvent.getScrollDelta();
            List list = localPlayer.level.getEntitiesOfClass((Entity)localPlayer, localPlayer.getBoundingBox().inflate(60.0));
            for (Entity entity : list) {
                BlindfoldEffect aE2;
                if (!(entity instanceof BlindfoldEffect) || (aE2 = (BlindfoldEffect)entity).abduction$getLeashHolder() != localPlayer) continue;
                StocksBlock.sendToServer(new ModRecipes((float)d / 2.0f));
                mouseScrollEvent.setCanceled(true);
                return;
            }
        }
    }

    @SubscribeEvent
    public static void registerCommands(RegisterClientCommandsEvent registerClientCommandsEvent) {
        CommandDispatcher commandDispatcher = registerClientCommandsEvent.getDispatcher();
        for (String string : NiceNTightSettings.ROOTS) {
            NiceNTightSettings.registerCommand((CommandDispatcher<CommandSourceStack>)commandDispatcher, string, DyeableProperty.getBuilder());
        }
    }
}

