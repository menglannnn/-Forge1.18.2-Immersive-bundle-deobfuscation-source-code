package abductionmod.content;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class b {
  public HashMap<aR.a, Float> vulnerabilityMap = new HashMap<>();
  
  private boolean bindHands;
  
  private boolean needsKeyForUntie;
  
  private boolean hasSeparateSlimTexture;
  
  private boolean bindLegs;
  
  private boolean deafen;
  
  private boolean gag;
  
  private boolean blind;
  
  private boolean noDropOnDeath;
  
  private String armAnimation = "am_tied";
  
  private int resistance;
  
  private int lockedResistance;
  
  private boolean forcedSlimArms;
  
  private Supplier<SoundEvent> sound = () -> SoundEvents.f_11678_;
  
  private Consumer<h> modelVisibilityConsumer = paramh -> {
    
    };
  
  private boolean drawsInFirstPerson;
  
  private Supplier<k.a> struggleAnimation = null;
  
  public static b create() {
    return new b();
  }
  
  public b withBindHands() {
    this.bindHands = true;
    return this;
  }
  
  public b withNeedsKeyForUntie() {
    this.needsKeyForUntie = true;
    return this;
  }
  
  public b withSeparateSlimTexture() {
    this.hasSeparateSlimTexture = true;
    return this;
  }
  
  public b withDrawsInFirstPerson() {
    this.drawsInFirstPerson = true;
    return this;
  }
  
  public boolean isDrawsInFirstPerson() {
    return this.drawsInFirstPerson;
  }
  
  public b withDeafen() {
    this.deafen = true;
    return this;
  }
  
  public b withNoDropOnDeath() {
    this.noDropOnDeath = true;
    return this;
  }
  
  public b withForcedSlimArms() {
    this.forcedSlimArms = true;
    return this;
  }
  
  public b withBindLegs() {
    this.bindLegs = true;
    return this;
  }
  
  public b withGag() {
    this.gag = true;
    return this;
  }
  
  public b withBlind() {
    this.blind = true;
    return this;
  }
  
  public b withResistance(int paramInt) {
    this.resistance = paramInt;
    return this;
  }
  
  public b withSound(Supplier<SoundEvent> paramSupplier) {
    this.sound = paramSupplier;
    return this;
  }
  
  public b withArmAnimation(String paramString) {
    this.armAnimation = paramString;
    return this;
  }
  
  public b withLockedResistance(int paramInt) {
    this.lockedResistance = paramInt;
    return this;
  }
  
  public b withVulnerableTo(aR.a parama, float paramFloat) {
    this.vulnerabilityMap.put(parama, Float.valueOf(paramFloat));
    return this;
  }
  
  public b withPartVisibility(Consumer<h> paramConsumer) {
    this.modelVisibilityConsumer = paramConsumer;
    return this;
  }
  
  public boolean isBindHands(ItemStack paramItemStack) {
    return this.bindHands;
  }
  
  public boolean isBindLegs(ItemStack paramItemStack) {
    return this.bindLegs;
  }
  
  public boolean isDeafen(ItemStack paramItemStack) {
    return this.deafen;
  }
  
  public boolean isNeedsKeyForUntie(ItemStack paramItemStack) {
    return (this.needsKeyForUntie && bl.hasCustomTag(paramItemStack, "keyValue"));
  }
  
  public boolean hasSeparateSlimTexture(ItemStack paramItemStack) {
    return this.hasSeparateSlimTexture;
  }
  
  public boolean isGag(ItemStack paramItemStack) {
    return this.gag;
  }
  
  public boolean isBlind(ItemStack paramItemStack) {
    return this.blind;
  }
  
  public boolean isNoDropOnDeath(ItemStack paramItemStack) {
    return (this.noDropOnDeath || bl.hasCustomTag(paramItemStack, "noDropOnDeath"));
  }
  
  public boolean isForcedSlimArms(ItemStack paramItemStack) {
    return this.forcedSlimArms;
  }
  
  public String getArmAnimation(ItemStack paramItemStack) {
    return bl.hasCustomTag(paramItemStack, bl.ARM_BIND_TYPE_TAG) ? bl.getCustomTag(paramItemStack, bl.ARM_BIND_TYPE_TAG) : this.armAnimation;
  }
  
  public int getBaseResistance(ItemStack paramItemStack) {
    return (bl.hasCustomTag(paramItemStack, "keyValue") && this.lockedResistance > 0) ? (this.resistance + this.lockedResistance) : this.resistance;
  }
  
  public Consumer<h> getModelVisibilityConsumer(ItemStack paramItemStack) {
    return this.modelVisibilityConsumer;
  }
  
  public SoundEvent getSound(ItemStack paramItemStack) {
    return this.sound.get();
  }
  
  public int getResistance(ItemStack paramItemStack) {
    int i = getBaseResistance(paramItemStack);
    Integer integer = bl.getCustomTagInt(paramItemStack, "struggleAddedResistance");
    if (integer != null)
      i += integer.intValue(); 
    return i;
  }
  
  public b withStruggleAnimation(Supplier<k.a> paramSupplier) {
    this.struggleAnimation = paramSupplier;
    return this;
  }
  
  @Nullable
  public k.a getStruggleAnimation(ItemStack paramItemStack) {
    return (this.struggleAnimation == null) ? null : this.struggleAnimation.get();
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\b.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */