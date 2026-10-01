package com.dev.am.mixin;

import abductionmod.content.aZ;
import abductionmod.content.aw;
import abductionmod.content.ay;
import abductionmod.content.az;
import abductionmod.content.bi;
import abductionmod.content.bm;
import abductionmod.content.k;
import com.dev.am.mod.gamerule.GameruleRegistry;
import com.dev.am.mod.gamerule.UntyingStrengthPercent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({LivingEntity.class})
public abstract class LivingMix extends Entity implements ay {
  @Unique
  private int untieClicks = 0;
  
  @Unique
  private ItemStack itemUsedOnEntity = null;
  
  @Unique
  private static final EntityDataAccessor<Float> DATA_AM_LEASH_LENGTH_ID = SynchedEntityData.m_135353_(LivingEntity.class, EntityDataSerializers.f_135029_);
  
  @Unique
  private static final EntityDataAccessor<Boolean> PROTECTED_CLOTHES_ID = SynchedEntityData.m_135353_(LivingEntity.class, EntityDataSerializers.f_135035_);
  
  @Unique
  private static final EntityDataAccessor<Boolean> ARE_LEGS_UNTIED_ID = SynchedEntityData.m_135353_(LivingEntity.class, EntityDataSerializers.f_135035_);
  
  @Unique
  private static final EntityDataAccessor<Integer> DATA_AM_ANIMATION_ID = SynchedEntityData.m_135353_(LivingEntity.class, EntityDataSerializers.f_135028_);
  
  @Unique
  private static final EntityDataAccessor<Integer> TYING_TIMEOUT_ID = SynchedEntityData.m_135353_(LivingEntity.class, EntityDataSerializers.f_135028_);
  
  @Unique
  private static final EntityDataAccessor<CompoundTag> DATA_AM_ADJUSTMENT_MAP = SynchedEntityData.m_135353_(LivingEntity.class, EntityDataSerializers.f_135042_);
  
  @Unique
  private int abduction$tyerHash;
  
  @Unique
  private int abduction$tyingTime;
  
  @Unique
  private int abduction$untyingPowerSpent;
  
  @Unique
  private int abduction$untyingCoolDown;
  
  @Unique
  private int abduction$timeBeforeTickBack;
  
  @Unique
  private boolean abduction$isPlayerPressing;
  
  @Unique
  private boolean abduction$isUntying;
  
  @Unique
  private boolean abduction$isHoldingChangePositionKey;
  
  @Unique
  private int abduction$untilUnpressing;
  
  @Unique
  private LivingEntity abduction$tyer;
  
  public int am$getTyingTimeout() {
    return ((Integer)this.f_19804_.m_135370_(TYING_TIMEOUT_ID)).intValue();
  }
  
  public void am$setTyingTimeout(int paramInt) {
    this.f_19804_.m_135381_(TYING_TIMEOUT_ID, Integer.valueOf(paramInt));
  }
  
  public void abduction$setUntieClicks(int paramInt) {
    this.untieClicks = paramInt;
  }
  
  public int abduction$getUntieClicks() {
    return this.untieClicks;
  }
  
  public LivingMix(EntityType<?> paramEntityType, Level paramLevel) {
    super(paramEntityType, paramLevel);
  }
  
  @Inject(method = {"<init>"}, at = {@At("TAIL")})
  private void init(CallbackInfo paramCallbackInfo) {
    this.itemUsedOnEntity = ItemStack.f_41583_;
  }
  
  public boolean abduction$getProtectedClothes() {
    return ((Boolean)this.f_19804_.m_135370_(PROTECTED_CLOTHES_ID)).booleanValue();
  }
  
  public void abduction$setProtectedClothes(boolean paramBoolean) {
    this.f_19804_.m_135381_(PROTECTED_CLOTHES_ID, Boolean.valueOf(paramBoolean));
  }
  
  public CompoundTag abduction$getAdjustmentOffsetsMap() {
    return (CompoundTag)this.f_19804_.m_135370_(DATA_AM_ADJUSTMENT_MAP);
  }
  
  public void abduction$setAdjustmentOffsetsMap(CompoundTag paramCompoundTag) {
    this.f_19804_.m_135381_(DATA_AM_ADJUSTMENT_MAP, paramCompoundTag);
  }
  
  public float abduction$getLeashLength() {
    return ((Float)this.f_19804_.m_135370_(DATA_AM_LEASH_LENGTH_ID)).floatValue();
  }
  
  public void abduction$setLeashLength(float paramFloat) {
    this.f_19804_.m_135381_(DATA_AM_LEASH_LENGTH_ID, Float.valueOf(paramFloat));
  }
  
  public boolean abduction$getLegsUntied() {
    return ((Boolean)this.f_19804_.m_135370_(ARE_LEGS_UNTIED_ID)).booleanValue();
  }
  
  public void abduction$setLegsUntied(boolean paramBoolean) {
    this.f_19804_.m_135381_(ARE_LEGS_UNTIED_ID, Boolean.valueOf(paramBoolean));
  }
  
  @Inject(method = {"tick"}, at = {@At("HEAD")})
  private void tickMix(CallbackInfo paramCallbackInfo) {
    LivingEntity livingEntity = (LivingEntity)this;
    if (!livingEntity.f_19853_.m_5776_()) {
      if (am$getTyingTimeout() > 0)
        am$setTyingTimeout(am$getTyingTimeout() - 1); 
      if (abduction$getLegsUntied() && !bm.hasLegBinds(livingEntity))
        abduction$setLegsUntied(false); 
    } 
    if (this.abduction$untilUnpressing > 0) {
      this.abduction$untilUnpressing--;
    } else {
      this.abduction$isPlayerPressing = false;
    } 
    if (this.abduction$untyingCoolDown > 0)
      this.abduction$untyingCoolDown--; 
    if (this.abduction$timeBeforeTickBack > 0)
      this.abduction$timeBeforeTickBack--; 
    if (this.abduction$isUntying) {
      abduction$handleUntying(livingEntity);
    } else {
      abduction$handleTying(livingEntity);
    } 
    if (!bm.isTied(livingEntity) && abduction$getAnimationId() != 0)
      abduction$setAnimationId(0); 
  }
  
  @Inject(method = {"defineSynchedData"}, at = {@At("TAIL")})
  private void defineSyncData(CallbackInfo paramCallbackInfo) {
    this.f_19804_.m_135372_(DATA_AM_ANIMATION_ID, Integer.valueOf(0));
    this.f_19804_.m_135372_(TYING_TIMEOUT_ID, Integer.valueOf(0));
    this.f_19804_.m_135372_(DATA_AM_ADJUSTMENT_MAP, new CompoundTag());
    this.f_19804_.m_135372_(DATA_AM_LEASH_LENGTH_ID, Float.valueOf(3.0F));
    this.f_19804_.m_135372_(PROTECTED_CLOTHES_ID, Boolean.valueOf(false));
    this.f_19804_.m_135372_(ARE_LEGS_UNTIED_ID, Boolean.valueOf(false));
  }
  
  @Inject(method = {"onClimbable"}, at = {@At("HEAD")}, cancellable = true)
  private void onClimbable(CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    LivingEntity livingEntity = (LivingEntity)this;
    if (bm.areHandsTied(livingEntity)) {
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false));
      return;
    } 
  }
  
  @ModifyVariable(method = {"hurt"}, at = @At("HEAD"), ordinal = 0, argsOnly = true)
  private float modifyParam(float paramFloat1, DamageSource paramDamageSource, float paramFloat2) {
    LivingEntity livingEntity = (LivingEntity)this;
    if (!livingEntity.f_19853_.m_5776_()) {
      Entity entity = paramDamageSource.m_7639_();
      if (entity instanceof LivingEntity) {
        LivingEntity livingEntity1 = (LivingEntity)entity;
        if (bm.hasMittens(livingEntity1))
          return 0.001F; 
      } 
    } 
    return paramFloat1;
  }
  
  @Inject(method = {"hurt"}, at = {@At("HEAD")}, cancellable = true)
  private void injectedHead(DamageSource paramDamageSource, float paramFloat, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    LivingEntity livingEntity = (LivingEntity)this;
    if (!livingEntity.f_19853_.m_5776_()) {
      Entity entity = paramDamageSource.m_7639_();
      if (entity instanceof aw) {
        aw aw = (aw)entity;
        if (paramFloat > 0.0F && aw.abduction$getLeashHolder() == livingEntity)
          aw.abduction$dropLeash(true, true); 
      } 
    } 
  }
  
  @Unique
  private void abduction$handleUntying(LivingEntity paramLivingEntity) {
    int i = bm.getResistanceOfFirstBondage(paramLivingEntity, bm.untyingStackPredicate);
    if (i == 0) {
      abduction$stopTyingProcess();
      return;
    } 
    if (this.abduction$isPlayerPressing) {
      abduction$addUntyingPower((int)(2.0F * GameruleRegistry.getGameruleValue(paramLivingEntity.f_19853_, UntyingStrengthPercent.ID).intValue() / 100.0F));
      if (abduction$isFinishedUnTying() && this.abduction$isPlayerPressing) {
        abduction$sendNoneBars(paramLivingEntity);
      } else {
        abduction$sendBarStatusToPlayers(paramLivingEntity, bi.a.UNTYING, bi.a.UNTYING, abduction$getUntyingProgressPercent());
      } 
    } 
    abduction$tickUntyingBarDownIfPossible(paramLivingEntity);
  }
  
  public float abduction$getUntyingProgressPercent() {
    LivingEntity livingEntity = (LivingEntity)this;
    int i = bm.getResistanceOfFirstBondage(livingEntity, bm.untyingStackPredicate);
    return (i == 0) ? 1.0F : (this.abduction$untyingPowerSpent / i);
  }
  
  @Unique
  private void abduction$handleTying(LivingEntity paramLivingEntity) {
    abduction$tickTyingBarDownIfPossible(paramLivingEntity);
    if (this.abduction$isPlayerPressing)
      abduction$addTyingTime(1); 
    if (abduction$isFinishedTying() && this.abduction$isPlayerPressing) {
      abduction$sendNoneBars(paramLivingEntity);
      this.abduction$untyingCoolDown = 40;
    } else {
      abduction$sendBarStatusToPlayers(paramLivingEntity, bi.a.TYING, bi.a.BEING_TIED, abduction$getTyingProgressPercent());
    } 
    if (this.abduction$tyingTime == 0)
      this.abduction$tyerHash = 0; 
  }
  
  public float abduction$getTyingProgressPercent() {
    return this.abduction$tyingTime / abduction$getMaxTyingTime();
  }
  
  @Unique
  private void abduction$sendBarStatusToPlayers(LivingEntity paramLivingEntity, bi.a parama1, bi.a parama2, float paramFloat) {
    if (paramFloat <= 0.0F)
      return; 
    LivingEntity livingEntity = this.abduction$tyer;
    if (livingEntity instanceof ServerPlayer) {
      ServerPlayer serverPlayer = (ServerPlayer)livingEntity;
      aZ.sendToClient(serverPlayer, new bi(parama1, paramFloat, paramLivingEntity.m_142049_(), paramLivingEntity.m_7755_().getString()));
    } 
    if (paramLivingEntity instanceof ServerPlayer) {
      ServerPlayer serverPlayer = (ServerPlayer)paramLivingEntity;
      aZ.sendToClient(serverPlayer, new bi(parama2, paramFloat, paramLivingEntity.m_142049_(), paramLivingEntity.m_7755_().getString()));
    } 
  }
  
  @Unique
  private void abduction$tickTyingBarDownIfPossible(LivingEntity paramLivingEntity) {
    if (this.abduction$tyingTime > 0 && this.abduction$timeBeforeTickBack == 0) {
      this.abduction$tyingTime--;
      if (this.abduction$tyingTime == 0)
        abduction$sendNoneBars(paramLivingEntity); 
    } 
  }
  
  @Unique
  private void abduction$tickUntyingBarDownIfPossible(LivingEntity paramLivingEntity) {
    if (this.abduction$untyingPowerSpent > 0 && this.abduction$timeBeforeTickBack == 0) {
      this.abduction$untyingPowerSpent--;
      if (paramLivingEntity instanceof ServerPlayer) {
        ServerPlayer serverPlayer = (ServerPlayer)paramLivingEntity;
        if (this.abduction$untyingPowerSpent == 0) {
          abduction$sendNoneBars((LivingEntity)serverPlayer);
          abduction$stopTyingProcess();
        } else {
          abduction$sendBarStatusToPlayers(paramLivingEntity, bi.a.UNTYING, bi.a.BEING_TIED, abduction$getUntyingProgressPercent());
        } 
      } 
    } 
  }
  
  @Unique
  private void abduction$sendNoneBars(LivingEntity paramLivingEntity) {
    abduction$sendBarStatusToPlayers(paramLivingEntity, bi.a.NONE, bi.a.NONE, 1.0F);
  }
  
  @Inject(method = {"addAdditionalSaveData"}, at = {@At("RETURN")})
  private void addSaveData(CompoundTag paramCompoundTag, CallbackInfo paramCallbackInfo) {
    CompoundTag compoundTag = new CompoundTag();
    compoundTag.m_128405_("tyerHash", this.abduction$tyerHash);
    compoundTag.m_128405_("tyingTime", this.abduction$tyingTime);
    compoundTag.m_128405_("untyingPowerSpent", this.abduction$untyingPowerSpent);
    compoundTag.m_128405_("animationId", abduction$getAnimationId());
    paramCompoundTag.m_128350_("abduction$leashLength", abduction$getLeashLength());
    paramCompoundTag.m_128365_("abduction$AdjustmentData", (Tag)abduction$getAdjustmentOffsetsMap());
    paramCompoundTag.m_128365_("abduction$TyingData", (Tag)compoundTag);
    paramCompoundTag.m_128379_("abduction$protectedClothes", abduction$getProtectedClothes());
    paramCompoundTag.m_128379_("abduction$areLegsUntied", abduction$getLegsUntied());
  }
  
  @Inject(method = {"readAdditionalSaveData"}, at = {@At("RETURN")})
  private void readSaveData(CompoundTag paramCompoundTag, CallbackInfo paramCallbackInfo) {
    if (paramCompoundTag.m_128441_("abduction$TyingData")) {
      CompoundTag compoundTag = paramCompoundTag.m_128469_("abduction$TyingData");
      this.abduction$tyerHash = compoundTag.m_128451_("tyerHash");
      this.abduction$tyingTime = compoundTag.m_128451_("tyingTime");
      this.abduction$untyingPowerSpent = compoundTag.m_128451_("untyingPowerSpent");
      abduction$setAnimationId(compoundTag.m_128451_("animationId"));
    } 
    if (paramCompoundTag.m_128441_("abduction$leashLength"))
      abduction$setLeashLength(paramCompoundTag.m_128457_("abduction$leashLength")); 
    if (paramCompoundTag.m_128441_("abduction$AdjustmentData"))
      abduction$setAdjustmentOffsetsMap(paramCompoundTag.m_128469_("abduction$AdjustmentData")); 
    if (paramCompoundTag.m_128441_("abduction$protectedClothes"))
      abduction$setProtectedClothes(paramCompoundTag.m_128471_("abduction$protectedClothes")); 
    if (paramCompoundTag.m_128441_("abduction$areLegsUntied"))
      abduction$setLegsUntied(paramCompoundTag.m_128471_("abduction$areLegsUntied")); 
  }
  
  @ModifyArg(method = {"jumpFromGround"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(DDD)V"), index = 1)
  private double injected(double paramDouble) {
    LivingEntity livingEntity = (LivingEntity)this;
    if (livingEntity instanceof Player) {
      Player player = (Player)livingEntity;
      if (bm.isTied((LivingEntity)player))
        return paramDouble * (k.getById(abduction$getAnimationId())).definition.jumpHeightModifier; 
    } 
    return paramDouble;
  }
  
  public int abduction$getAnimationId() {
    return ((Integer)this.f_19804_.m_135370_(DATA_AM_ANIMATION_ID)).intValue();
  }
  
  public void abduction$setAnimationId(int paramInt) {
    this.f_19804_.m_135381_(DATA_AM_ANIMATION_ID, Integer.valueOf(paramInt));
    m_6210_();
  }
  
  public void m_7350_(EntityDataAccessor<?> paramEntityDataAccessor) {
    super.m_7350_(paramEntityDataAccessor);
    if (paramEntityDataAccessor.equals(DATA_AM_ANIMATION_ID))
      m_6210_(); 
  }
  
  public int abduction$getTyerHash() {
    return this.abduction$tyerHash;
  }
  
  public void abduction$setTyerHash(int paramInt) {
    this.abduction$tyerHash = paramInt;
  }
  
  public int abduction$getTyingTime() {
    return this.abduction$tyingTime;
  }
  
  public int abduction$getMaxTyingTime() {
    LivingEntity livingEntity = (LivingEntity)this;
    int i = 5;
    Item item = this.itemUsedOnEntity.m_41720_();
    if (item instanceof az) {
      az az = (az)item;
      i = az.am$getTyingTimeSeconds(livingEntity, this.itemUsedOnEntity);
    } 
    return 20 * i;
  }
  
  @Nullable
  public LivingEntity abduction$getTyingEntity() {
    return this.abduction$tyer;
  }
  
  public void abduction$setTyingEntity(LivingEntity paramLivingEntity) {
    this.abduction$tyer = paramLivingEntity;
  }
  
  public void abduction$addTyingTime(int paramInt) {
    this.abduction$tyingTime += paramInt;
    if (this.abduction$tyingTime > abduction$getMaxTyingTime())
      this.abduction$tyingTime = abduction$getMaxTyingTime(); 
    this.abduction$timeBeforeTickBack = 40;
  }
  
  public void abduction$setPlayerPressing(ItemStack paramItemStack) {
    this.abduction$isPlayerPressing = true;
    this.abduction$untilUnpressing = 5;
    this.itemUsedOnEntity = paramItemStack;
  }
  
  public boolean abduction$isFinishedTying() {
    return (abduction$isBeingTied() && this.abduction$tyingTime >= abduction$getMaxTyingTime());
  }
  
  public boolean abduction$isFinishedUnTying() {
    LivingEntity livingEntity = (LivingEntity)this;
    return (this.abduction$untyingPowerSpent >= bm.getResistanceOfFirstBondage(livingEntity, bm.untyingStackPredicate));
  }
  
  public boolean abduction$isBeingTied() {
    return (this.abduction$tyerHash != 0);
  }
  
  public boolean abduction$isUntying() {
    return this.abduction$isUntying;
  }
  
  public void abduction$setUntying(boolean paramBoolean) {
    this.abduction$isUntying = paramBoolean;
  }
  
  public boolean abduction$isHoldingChangePositionKey() {
    return this.abduction$isHoldingChangePositionKey;
  }
  
  public void abduction$setHoldingChangePositionKey(boolean paramBoolean) {
    this.abduction$isHoldingChangePositionKey = paramBoolean;
  }
  
  public void abduction$stopTyingProcess() {
    this.abduction$tyerHash = 0;
    this.abduction$tyingTime = 0;
    this.abduction$timeBeforeTickBack = 0;
    this.abduction$isPlayerPressing = false;
    this.abduction$untilUnpressing = 0;
    this.abduction$untyingPowerSpent = 0;
    this.abduction$tyer = null;
    this.abduction$isUntying = false;
  }
  
  public int abduction$getUntyingPowerSpent() {
    return this.abduction$untyingPowerSpent;
  }
  
  public void abduction$addUntyingPower(int paramInt) {
    this.abduction$timeBeforeTickBack = 40;
    this.abduction$untyingPowerSpent += paramInt;
    if (this.abduction$untyingPowerSpent < 0)
      this.abduction$untyingPowerSpent = 0; 
  }
  
  public int abduction$getUntyingCoolDown() {
    return this.abduction$untyingCoolDown;
  }
  
  public void abduction$setUntyingCoolDown(int paramInt) {
    this.abduction$untyingCoolDown = paramInt;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\LivingMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */