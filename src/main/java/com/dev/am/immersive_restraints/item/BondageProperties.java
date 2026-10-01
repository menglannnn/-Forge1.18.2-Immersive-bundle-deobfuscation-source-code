/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.item.ItemStack
 *  org.jetbrains.annotations.Nullable
 */
package com.dev.am.immersive_restraints.item;

import com.dev.am.immersive_restraints.command.SetCollarNameCommand;
import com.dev.am.immersive_restraints.item.VulnerabilityType;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.item.PartVisibility;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.animation.StruggleAnimations;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/*
 * Renamed from abductionmod.content.b
 */
public class BondageProperties {
    public HashMap<VulnerabilityType.a, Float> vulnerabilityMap = new HashMap();
    private boolean bindHands;
    private boolean hasSeparateSlimTexture;
    private boolean bindLegs;
    private boolean untiedLegsDefault;
    private boolean deafen;
    private boolean gag;
    private boolean blind;
    private boolean noDropOnDeath;
    private String armAnimation = "am_tied";
    private int resistance;
    private int sortWeight;
    private int lockedResistance;
    private boolean forcedSlimArms;
    private Supplier<SoundEvent> sound = () -> SoundEvents.ITEM_EMPTY;
    private Consumer<PartVisibility> modelVisibilityConsumer = h_02 -> {};
    private boolean drawsInFirstPerson;
    private boolean popsOffAfterUnlock;
    private Supplier<StruggleAnimations.a> struggleAnimation = null;
    private boolean autoLocksOnEqup;

    private BondageProperties() {
    }

    public static BondageProperties create() {
        return new BondageProperties();
    }

    public BondageProperties withSortWeight(int n2) {
        this.sortWeight = n2;
        return this;
    }

    public BondageProperties withBindHands() {
        this.bindHands = true;
        return this;
    }

    public BondageProperties withSeparateSlimTexture() {
        this.hasSeparateSlimTexture = true;
        return this;
    }

    public BondageProperties withDrawsInFirstPerson() {
        this.drawsInFirstPerson = true;
        return this;
    }

    public boolean isDrawsInFirstPerson() {
        return this.drawsInFirstPerson;
    }

    public boolean isPopsOffAfterUnlock() {
        return this.popsOffAfterUnlock;
    }

    public boolean isAutoLocksOnEquip() {
        return this.autoLocksOnEqup;
    }

    public BondageProperties withDeafen() {
        this.deafen = true;
        return this;
    }

    public BondageProperties withPopsOffAfterUnlockWithKey() {
        this.popsOffAfterUnlock = true;
        return this;
    }

    public BondageProperties withAutoLocksOnEquip() {
        this.autoLocksOnEqup = true;
        return this;
    }

    public BondageProperties withNoDropOnDeath() {
        this.noDropOnDeath = true;
        return this;
    }

    public BondageProperties withForcedSlimArms() {
        this.forcedSlimArms = true;
        return this;
    }

    public BondageProperties withBindLegs() {
        this.bindLegs = true;
        return this;
    }

    public BondageProperties withUntiedDefaultLegs() {
        this.untiedLegsDefault = true;
        return this;
    }

    public BondageProperties withGag() {
        this.gag = true;
        return this;
    }

    public BondageProperties withBlind() {
        this.blind = true;
        return this;
    }

    public BondageProperties withResistance(int n2) {
        this.resistance = n2;
        return this;
    }

    public BondageProperties withSound(Supplier<SoundEvent> supplier) {
        this.sound = supplier;
        return this;
    }

    public BondageProperties withArmAnimation(String string) {
        this.armAnimation = string;
        return this;
    }

    public BondageProperties withLockedResistance(int n2) {
        this.lockedResistance = n2;
        return this;
    }

    public BondageProperties withVulnerableTo(VulnerabilityType.a a2, float f) {
        this.vulnerabilityMap.put(a2, Float.valueOf(f));
        return this;
    }

    public BondageProperties withPartVisibility(Consumer<PartVisibility> consumer) {
        this.modelVisibilityConsumer = consumer;
        return this;
    }

    public boolean isUntiedLegsDefault(ItemStack itemStack) {
        return this.untiedLegsDefault;
    }

    public boolean isBindHands(ItemStack itemStack) {
        return this.bindHands;
    }

    public int getSortWeight(ItemStack itemStack) {
        return this.sortWeight;
    }

    public boolean isBindLegs(ItemStack itemStack) {
        return this.bindLegs;
    }

    public boolean isDeafen(ItemStack itemStack) {
        return this.deafen;
    }

    public boolean isNeedsKeyForUntie(ItemStack itemStack) {
        return this.isLocked(itemStack);
    }

    public boolean hasSeparateSlimTexture(ItemStack itemStack) {
        return this.hasSeparateSlimTexture;
    }

    public boolean isGag(ItemStack itemStack) {
        return this.gag;
    }

    public boolean isBlind(ItemStack itemStack) {
        return this.blind;
    }

    public boolean isNoDropOnDeath(ItemStack itemStack) {
        return this.noDropOnDeath || PlayerEventHandler.hasCustomTag(itemStack, "noDropOnDeath");
    }

    public boolean isForcedSlimArms(ItemStack itemStack) {
        return this.forcedSlimArms;
    }

    public String getArmAnimation(ItemStack itemStack) {
        return PlayerEventHandler.hasCustomTag(itemStack, PlayerEventHandler.ARM_BIND_TYPE_TAG) ? PlayerEventHandler.getCustomTag(itemStack, PlayerEventHandler.ARM_BIND_TYPE_TAG) : this.armAnimation;
    }

    public int getBaseResistance(ItemStack itemStack) {
        if (this.lockedResistance > 0 && this.isLocked(itemStack)) {
            return this.resistance + this.lockedResistance;
        }
        return this.resistance;
    }

    public boolean isLocked(ItemStack itemStack) {
        if (PlayerEventHandler.hasCustomTag(itemStack, "isLocked")) {
            return PlayerEventHandler.getCustomTagBool(itemStack, "isLocked");
        }
        return false;
    }

    public Consumer<PartVisibility> getModelVisibilityConsumer(ItemStack itemStack) {
        return this.modelVisibilityConsumer;
    }

    public SoundEvent getSound(ItemStack itemStack) {
        return this.sound.get();
    }

    public int getResistance(ItemStack itemStack) {
        int n2 = this.getBaseResistance(itemStack);
        Integer n3 = PlayerEventHandler.getCustomTagInt(itemStack, "struggleAddedResistance");
        if (n3 != null) {
            n2 += n3.intValue();
        }
        return n2;
    }

    public BondageProperties withStruggleAnimation(Supplier<StruggleAnimations.a> supplier) {
        this.struggleAnimation = supplier;
        return this;
    }

    @Nullable
    public StruggleAnimations.a getStruggleAnimation(ItemStack itemStack) {
        if (this.struggleAnimation == null) {
            return null;
        }
        return this.struggleAnimation.get();
    }
}

