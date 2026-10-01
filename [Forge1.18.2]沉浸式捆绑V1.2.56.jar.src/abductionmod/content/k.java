package abductionmod.content;

import java.util.HashMap;
import java.util.function.Function;
import net.minecraft.world.entity.player.Player;

public class k {
  public static final HashMap<String, String> armBindStyles = new HashMap<>();
  
  private static final HashMap<Integer, l> states = new HashMap<>();
  
  static Function<Player, String> getArmFunction = bm::getArmTyingStyle;
  
  public static final a standingStruggle = new a(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_standing_struggle_left") }, ), new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_standing_struggle_right") }, ), new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_standing_struggle_front") }, ), new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_standing_struggle_back") }));
  
  public static final a kneelingStruggle = new a(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("kneeling_a_struggles") }, ), new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("kneeling_w_struggles") }, ), new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("kneeling_a_struggles") }, ), new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("kneeling_w_struggles") }));
  
  public static final a mittensStruggle = new a(new n(new n.a[] { new n.a("am_mittsescape") }));
  
  public static final a collarStruggle = new a(new n(new n.a[] { new n.a("am_collarescape") }));
  
  public static String ARM_ANIM_FLAT_INTO_BODY = "am_arms_bag2";
  
  public static String ARM_ANIM_INTO_BODY_SHOULDERS = "am_arms_bag1";
  
  public static String ARM_ANIM_STRAITJACKET = "am_arms_bag3_straightjacket";
  
  public static String ARM_ANIM_ARMBINDER = "am_arms_armbinder";
  
  public static l getById(int paramInt) {
    return states.get(Integer.valueOf(paramInt));
  }
  
  static {
    armBindStyles.put(ARM_ANIM_ARMBINDER, "Armbinder");
    armBindStyles.put(ARM_ANIM_INTO_BODY_SHOULDERS, "Bag 1 with shoulders");
    armBindStyles.put(ARM_ANIM_FLAT_INTO_BODY, "Bag 2 kidnap rp style");
    armBindStyles.put(ARM_ANIM_STRAITJACKET, "Bag 3 Straightjacket");
    armBindStyles.put("am_arms_bag4_straightjacket", "Bag 4 Straightjacket");
    armBindStyles.put("am_tied_arms2_3", "Boxtie");
    armBindStyles.put("am_arms_boxtie_front", "Boxtie Front (villager)");
    armBindStyles.put("am_arms_front_n_back", "Front and Back");
    states.put(Integer.valueOf(0), (new l(new n(new n.a[] { new n.b(getArmFunction) }))).withDown(Integer.valueOf(2)).withStruggle(standingStruggle));
    states.put(Integer.valueOf(1), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_legs_tied_kneel_to_stand") }))).copy(Integer.valueOf(0)).returnTo(Integer.valueOf(0)));
    states.put(Integer.valueOf(2), (new l((new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_legs_tied_stand_to_kneel") })).withCamY(-0.45F).withHeightModifier(0.85F).withJumpHeightModifier(0.7F))).withUp(Integer.valueOf(1)).withLeft(Integer.valueOf(3)).withRight(Integer.valueOf(3)).withDown(Integer.valueOf(5)));
    states.put(Integer.valueOf(4), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_spread_to_kneel") }))).copy(Integer.valueOf(2)));
    states.put(Integer.valueOf(6), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_sit_to_kneel") }))).copy(Integer.valueOf(2)));
    states.put(Integer.valueOf(3), (new l((new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_kneel_to_spread") })).copy(2))).withLeft(Integer.valueOf(4)).withRight(Integer.valueOf(4)).withUp(Integer.valueOf(1)).withDown(Integer.valueOf(18)).withForward(Integer.valueOf(30)));
    states.put(Integer.valueOf(19), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_backkneel_to_kneeling-legsspread") }))).copy(Integer.valueOf(3)));
    states.put(Integer.valueOf(29), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("b-lying-belly_to_kneeling-legsspread") }))).copy(Integer.valueOf(3)));
    states.put(Integer.valueOf(5), (new l((new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_kneel_to_sit") })).withCamY(-0.8F).withHeightModifier(0.7F).withJumpHeightModifier(0.2F))).withLeftRight(Integer.valueOf(23)).withUp(Integer.valueOf(6)).withDown(Integer.valueOf(16)));
    states.put(Integer.valueOf(17), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_sitting_legclose_to_legbend") }))).copy(Integer.valueOf(5)));
    states.put(Integer.valueOf(24), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_sitting-legsspread_to_sitting") }))).copy(Integer.valueOf(5)));
    states.put(Integer.valueOf(16), (new l((new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_sitting_legbend_to_legclose") })).copy(5))).withUp(Integer.valueOf(17)).withDown(Integer.valueOf(21)).withLeftRight(Integer.valueOf(28)));
    states.put(Integer.valueOf(22), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_lying_front_to_legsclose") }))).copy(Integer.valueOf(16)));
    states.put(Integer.valueOf(27), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_legsspread_to_legsclose") }))).copy(Integer.valueOf(16)));
    states.put(Integer.valueOf(23), (new l((new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_sitting_to_sitting-legsspread") })).copy(16))).withLeftRight(Integer.valueOf(24)).withDown(Integer.valueOf(25)).withUp(Integer.valueOf(6)));
    states.put(Integer.valueOf(26), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_legsspread_to_sitting-legsspread") }))).copy(Integer.valueOf(23)));
    states.put(Integer.valueOf(25), (new l((new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_sitting-legsspread_to_legsspread") })).copy(16))).withLeftRight(Integer.valueOf(27)).withUp(Integer.valueOf(26)).withDown(Integer.valueOf(21)));
    states.put(Integer.valueOf(28), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_legsclose_to_legsspread") }))).copy(Integer.valueOf(25)));
    states.put(Integer.valueOf(7), (new l((new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("lying_front1-wip") })).withCamY(-1.3F).withCamPitch(-90.0F).withJumpHeightModifier(0.0F).withHeightModifier(0.12F))).withUp(Integer.valueOf(22)).withRight(Integer.valueOf(8)).withLeft(Integer.valueOf(10)));
    states.put(Integer.valueOf(9), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("lying_right_to_front_wip") }))).copy(Integer.valueOf(7)));
    states.put(Integer.valueOf(11), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("lying_left_to_front_wip") }))).copy(Integer.valueOf(7)));
    states.put(Integer.valueOf(20), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_backkneel_to_lying-front") }))).copy(Integer.valueOf(7)));
    states.put(Integer.valueOf(21), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_legsclose_to_lying_front") }))).copy(Integer.valueOf(7)));
    states.put(Integer.valueOf(8), (new l((new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("lying_front_to_right-wip") })).copy(7).withCamRoll(90.0F).withCamYaw(90.0F))).withLeft(Integer.valueOf(9)).withRight(Integer.valueOf(14)));
    states.put(Integer.valueOf(15), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("lying_belly-to-right-wip") }))).copy(Integer.valueOf(8)));
    states.put(Integer.valueOf(10), (new l((new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("lying_front_to_left-wip") })).copy(8).withCamRoll(-90.0F).withCamYaw(-90.0F))).withRight(Integer.valueOf(11)).withLeft(Integer.valueOf(12)));
    states.put(Integer.valueOf(13), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("lying_belly_to_left-wip") }))).copy(Integer.valueOf(10)));
    states.put(Integer.valueOf(12), (new l((new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("lying_left_to_belly-wip") })).copy(7).withCamPitch(90.0F))).withRight(Integer.valueOf(13)).withLeft(Integer.valueOf(15)).withUp(Integer.valueOf(29)));
    states.put(Integer.valueOf(14), (new l((new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("lying_right_to_belly-wip") })).withCamPitch(90.0F))).copy(Integer.valueOf(12)));
    states.put(Integer.valueOf(30), (new l(new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("kneeling-legsspread_to_b-lying-belly") }))).copy(Integer.valueOf(12)));
    states.put(Integer.valueOf(18), (new l((new n(new n.a[] { (new n.b(getArmFunction)).instant(), new n.a("am_kneeling-legsspread_to_backkneel") })).withJumpHeightModifier(0.0F).withCamPitch(-90.0F).withHeightModifier(0.2F))).withDown(Integer.valueOf(20)).withUp(Integer.valueOf(19)));
  }
  
  public static final class a extends Record {
    private final n left;
    
    private final n right;
    
    private final n up;
    
    private final n down;
    
    a(n param1n) {
      this(param1n, param1n, param1n, param1n);
    }
    
    public a(n param1n1, n param1n2, n param1n3, n param1n4) {
      this.left = param1n1;
      this.right = param1n2;
      this.up = param1n3;
      this.down = param1n4;
    }
    
    public final String toString() {
      // Byte code:
      //   0: aload_0
      //   1: <illegal opcode> toString : (Labductionmod/content/k$a;)Ljava/lang/String;
      //   6: areturn
    }
    
    public final int hashCode() {
      // Byte code:
      //   0: aload_0
      //   1: <illegal opcode> hashCode : (Labductionmod/content/k$a;)I
      //   6: ireturn
    }
    
    public final boolean equals(Object param1Object) {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: <illegal opcode> equals : (Labductionmod/content/k$a;Ljava/lang/Object;)Z
      //   7: ireturn
    }
    
    public n left() {
      return this.left;
    }
    
    public n right() {
      return this.right;
    }
    
    public n up() {
      return this.up;
    }
    
    public n down() {
      return this.down;
    }
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\k.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */