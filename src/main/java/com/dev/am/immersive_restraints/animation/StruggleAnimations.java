/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  abductionmod.content.n
 *  net.minecraft.world.entity.player.Player
 */
package com.dev.am.immersive_restraints.animation;

import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.render.StruggleGeoRenderer;
import com.dev.am.immersive_restraints.mechanic.KeyValidation;

import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.mechanic.MinigameCamera;
import java.util.HashMap;
import java.util.function.Function;
import net.minecraft.world.entity.player.Player;

/*
 * Renamed from com.dev.am.immersive_restraints.animation.StruggleAnimations
 */
public class StruggleAnimations {
    public static final HashMap<String, String> armBindStyles = new HashMap();
    private static final HashMap<Integer, KeyValidation> states = new HashMap();
    static Function<Player, String> getArmFunction = EntityEventHandler::getArmTyingStyle;
    public static final a standingStruggle = new a((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_standing_struggle_left")}), (MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_standing_struggle_right")}), (MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_standing_struggle_front")}), (MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_standing_struggle_back")}));
    public static final a kneelingStruggle = new a((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("kneeling_a_struggles")}), (MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("kneeling_w_struggles")}), (MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("kneeling_a_struggles")}), (MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("kneeling_w_struggles")}));
    public static final a mittensStruggle = new a((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.a("am_mittsescape")}));
    public static final a collarStruggle = new a((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.a("am_collarescape")}));
    public static String ARM_ANIM_FLAT_INTO_BODY = "am_arms_bag2";
    public static String ARM_ANIM_INTO_BODY_SHOULDERS = "am_arms_bag1";
    public static String ARM_ANIM_STRAITJACKET = "am_arms_bag4_straightjacket";
    public static String ARM_ANIM_ARMBINDER = "am_arms_armbinder";

    public static KeyValidation getById(int n2) {
        return states.get(n2);
    }

    static {
        armBindStyles.put(ARM_ANIM_ARMBINDER, "Armbinder");
        armBindStyles.put(ARM_ANIM_INTO_BODY_SHOULDERS, "Bag 1 with shoulders");
        armBindStyles.put(ARM_ANIM_FLAT_INTO_BODY, "Bag 2 kidnap rp style");
        armBindStyles.put("am_arms_bag4_straightjacket", "Bag 4 Straightjacket");
        armBindStyles.put("am_tied_arms2_3", "Boxtie");
        armBindStyles.put("am_arms_boxtie_front", "Boxtie Front (villager)");
        armBindStyles.put("am_arms_front_n_back", "Front and Back");
        armBindStyles.put("am_front_tied_arms", "Front Tied");
        states.put(0, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction)})).withDown(2).withStruggle(standingStruggle));
        states.put(1, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_legs_tied_kneel_to_stand")})).copy(0).returnTo(0));
        states.put(2, new KeyValidation(new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_legs_tied_stand_to_kneel")}).withCamY(-0.45f).withHeightModifier(0.85f).withJumpHeightModifier(0.7f)).withUp(1).withLeft(3).withRight(3).withDown(5));
        states.put(4, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_spread_to_kneel")})).copy(2));
        states.put(6, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_sit_to_kneel")})).copy(2));
        states.put(3, new KeyValidation(new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_kneel_to_spread")}).copy(2)).withLeft(4).withRight(4).withUp(1).withDown(18).withForward(30));
        states.put(19, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_backkneel_to_kneeling-legsspread")})).copy(3));
        states.put(29, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("b-lying-belly_to_kneeling-legsspread")})).copy(3));
        states.put(5, new KeyValidation(new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_kneel_to_sit")}).withCamY(-0.8f).withHeightModifier(0.7f).withJumpHeightModifier(0.2f)).withLeftRight(23).withUp(6).withDown(16));
        states.put(17, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_sitting_legclose_to_legbend")})).copy(5));
        states.put(24, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_sitting-legsspread_to_sitting")})).copy(5));
        states.put(16, new KeyValidation(new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_sitting_legbend_to_legclose")}).copy(5)).withUp(17).withDown(21).withLeftRight(28));
        states.put(22, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_lying_front_to_legsclose")})).copy(16));
        states.put(27, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_legsspread_to_legsclose")})).copy(16));
        states.put(23, new KeyValidation(new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_sitting_to_sitting-legsspread")}).copy(16)).withLeftRight(24).withDown(25).withUp(6));
        states.put(26, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_legsspread_to_sitting-legsspread")})).copy(23));
        states.put(25, new KeyValidation(new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_sitting-legsspread_to_legsspread")}).copy(16)).withLeftRight(27).withUp(26).withDown(21));
        states.put(28, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_legsclose_to_legsspread")})).copy(25));
        states.put(7, new KeyValidation(new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("lying_front1-wip")}).withCamY(-1.3f).withCamPitch(-90.0f).withJumpHeightModifier(0.0f).withHeightModifier(0.12f)).withUp(22).withRight(8).withLeft(10));
        states.put(9, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("lying_right_to_front_wip")})).copy(7));
        states.put(11, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("lying_left_to_front_wip")})).copy(7));
        states.put(20, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_backkneel_to_lying-front")})).copy(7));
        states.put(21, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_legsclose_to_lying_front")})).copy(7));
        states.put(8, new KeyValidation(new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("lying_front_to_right-wip")}).copy(7).withCamRoll(90.0f).withCamYaw(90.0f)).withLeft(9).withRight(14));
        states.put(15, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("lying_belly-to-right-wip")})).copy(8));
        states.put(10, new KeyValidation(new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("lying_front_to_left-wip")}).copy(8).withCamRoll(-90.0f).withCamYaw(-90.0f)).withRight(11).withLeft(12));
        states.put(13, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("lying_belly_to_left-wip")})).copy(10));
        states.put(12, new KeyValidation(new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("lying_left_to_belly-wip")}).copy(7).withCamPitch(90.0f)).withRight(13).withLeft(15).withUp(29));
        states.put(14, new KeyValidation(new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("lying_right_to_belly-wip")}).withCamPitch(90.0f)).copy(12));
        states.put(30, new KeyValidation((MinigameCamera)new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("kneeling-legsspread_to_b-lying-belly")})).copy(12));
        states.put(18, new KeyValidation(new n(new MinigameCamera.a[]{new MinigameCamera.b(getArmFunction).instant(), new MinigameCamera.a("am_kneeling-legsspread_to_backkneel")}).withJumpHeightModifier(0.0f).withCamPitch(-90.0f).withHeightModifier(0.2f)).withDown(20).withUp(19));
    }

    public record a(MinigameCamera left, MinigameCamera right, MinigameCamera up, MinigameCamera down) {
        a(MinigameCamera n_02) {
            this(n_02, n_02, n_02, n_02);
        }
    }
}

