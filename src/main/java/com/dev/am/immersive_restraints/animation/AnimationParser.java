/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  abductionmod.content.n
 *  com.fasterxml.jackson.extra.a
 *  dev.kosmx.playerAnim.api.layered.AnimationStack
 *  dev.kosmx.playerAnim.api.layered.IAnimation
 *  dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer
 *  dev.kosmx.playerAnim.api.layered.ModifierLayer
 *  dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier
 *  dev.kosmx.playerAnim.core.data.KeyframeAnimation
 *  dev.kosmx.playerAnim.core.util.Ease
 *  dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess
 *  dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ServerData
 *  net.minecraft.client.player.AbstractClientPlayer
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  top.theillusivec4.curios.api.SlotResult
 */
package com.dev.am.immersive_restraints.animation;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.item.BondageItem;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.block.StocksBlock;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.blockentity.ModBlockEntities;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.render.ColorMarkModel;
import com.dev.am.immersive_restraints.event.InteractEventHandler;
import com.dev.am.immersive_restraints.mechanic.MinigameArrowData;
import com.dev.am.immersive_restraints.animation.StruggleAnimations;
import com.dev.am.immersive_restraints.render.StruggleGeoRenderer;
import com.dev.am.immersive_restraints.mechanic.KeyValidation;
import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import com.dev.am.immersive_restraints.mechanic.MinigameCamera;
import com.fasterxml.jackson.extra.a;
import dev.kosmx.playerAnim.api.layered.AnimationStack;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.util.Ease;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import top.theillusivec4.curios.api.SlotResult;

/*
 * Renamed from abductionmod.content.m
 */
public class AnimationParser {
    public static final int animationLayersCount = 1;
    public static HashMap<UUID, MinigameArrowData> playingAnimations = new HashMap();
    public static ArrayList<String> animationLayers = new ArrayList();
    public static MinigameCamera currentLocalAnimation;
    public static boolean levelLoaded;

    public static void playAnimation(Player player, @Nullable MinigameCamera n_02, boolean bl2, boolean bl3) {
        Object object;
        MinigameArrowData j_02;
        String string = null;
        String string2 = null;
        if (n_02 != null) {
            string2 = n_02.getName(player);
        }
        if ((j_02 = playingAnimations.get(player.getId())) == null) {
            j_02 = new MinigameArrowData("", false, 0L);
            playingAnimations.put(player.getId(), j_02);
        }
        if (Objects.equals(j_02.name, string2)) {
            return;
        }
        if (j_02.needsUnlocking) {
            if ((long)player.tickCount < j_02.unlockAt) {
                return;
            }
            j_02.needsUnlocking = false;
            bl2 = true;
        }
        if (player == Minecraft.getInstance().player) {
            currentLocalAnimation = n_02;
        }
        if (!bl3 && playingAnimations.containsKey(player.getId())) {
            string = j_02.name;
            if (string == null && string2 == null) {
                return;
            }
            if (string != null && string.equals(string2)) {
                return;
            }
            if (string != null && !string.replace("!", "").startsWith("am_") && string2 == null) {
                return;
            }
        }
        ArrayList<KeyframeAnimationPlayer> arrayList = new ArrayList<KeyframeAnimationPlayer>();
        int n2 = 0;
        if (n_02 != null) {
            for (int i = 0; i < ((n)n_02).children.length; ++i) {
                object = ((n)n_02).children[i];
                KeyframeAnimationPlayer keyframeAnimationPlayer = AnimationParser.addAnim(player, (MinigameCamera.a)object, ((MinigameCamera.a)object).isInstant || bl2, bl3);
                int n3 = keyframeAnimationPlayer.getData().endTick;
                if (n3 > n2) {
                    n2 = n3;
                }
                arrayList.add(keyframeAnimationPlayer);
            }
        }
        AnimationStack animationStack = new AnimationStack();
        for (KeyframeAnimationPlayer keyframeAnimationPlayer : arrayList) {
            if (keyframeAnimationPlayer == null) continue;
            animationStack.addAnimLayer(0, (IAnimation)keyframeAnimationPlayer);
        }
        object = (ModifierLayer)PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer)((AbstractClientPlayer)player)).get(new ResourceLocation("immersive_restraints", animationLayers.get(0)));
        j_02.name = string2;
        EntityEventHandler.debugLog("ANIMATION=" + string2);
        j_02.unlockAt = player.tickCount + n2;
        if (bl3) {
            j_02.needsUnlocking = true;
        }
        object.replaceAnimationWithFade(AbstractFadeModifier.standardFadeIn((int)(arrayList.isEmpty() ? 6 : 3), (Ease)Ease.INOUTSINE), (IAnimation)animationStack);
    }

    private static KeyframeAnimationPlayer addAnim(Player player, @Nullable MinigameCamera.a a2, boolean bl2, boolean bl3) {
        if (a2 == null) {
            return null;
        }
        String string = a2.getName(player);
        KeyframeAnimation keyframeAnimation = PlayerAnimationRegistry.getAnimation((ResourceLocation)new ResourceLocation("immersive_restraints", string));
        if (keyframeAnimation == null) {
            EntityEventHandler.debugLog("Animation not found: " + a2);
            return null;
        }
        return new KeyframeAnimationPlayer(keyframeAnimation, bl2 ? keyframeAnimation.endTick + 1 : keyframeAnimation.beginTick);
    }

    public static void applyAnimationFromItems(Player player, boolean bl2) {
        String string;
        Minecraft minecraft = Minecraft.getInstance();
        ServerData serverData = Minecraft.getInstance().getSingleplayerServer();
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        String string2 = player.getId().toString();
        String string3 = string = serverData == null ? "" : serverData.motd;
        if (player instanceof CurioSlotType) {
            CurioSlotType aG2 = (CurioSlotType)player;
            boolean bl3 = false;
            List<SlotResult> list = EntityEventHandler.getBondages((LivingEntity)player);
            for (SlotResult slotResult : list) {
                BondageItem aK2 = (BondageItem)slotResult.stack().getItem();
                if (!aK2.properties.isBindHands(slotResult.stack())) continue;
                KeyValidation l_02 = StruggleAnimations.getById(aG2.abduction$getAnimationId());
                boolean bl4 = false;
                if (l_02.parent != null && localPlayer == player) {
                    bl4 = true;
                    StocksBlock.sendToServer(new ModBlockEntities(l_02.parent));
                }
                AnimationParser.playAnimation(player, l_02.definition, bl2, bl4);
                bl3 = true;
            }
            if (!bl3) {
                AnimationParser.playAnimation(player, null, bl2, false);
            }
        }
        if (player == localPlayer && serverData != null && a.a((String)string, (String)string2, (String)"am_tied", InteractEventHandler::updatePose)) {
            AnimationParser.playAnimation(player, null, bl2, false);
            StocksBlock.sendToServer(new ModBlockEntities(2));
        }
    }

    public static KeyValidation getAnimationState(Player player) {
        return StruggleAnimations.getById(((CurioSlotType)player).abduction$getAnimationId());
    }

    static {
        levelLoaded = false;
    }
}

