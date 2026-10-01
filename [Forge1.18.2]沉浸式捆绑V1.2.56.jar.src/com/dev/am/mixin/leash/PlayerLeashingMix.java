package com.dev.am.mixin.leash;

import abductionmod.content.aw;
import abductionmod.content.ay;
import abductionmod.content.bm;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Player.class})
public abstract class PlayerLeashingMix extends LivingEntity implements aw {
  @Unique
  @Nullable
  private CompoundTag abduction$leashInfoTag;
  
  @Unique
  private Entity abduction$leashHolder;
  
  @Unique
  private int abduction$delayedLeashHolderId;
  
  protected PlayerLeashingMix(EntityType<? extends LivingEntity> paramEntityType, Level paramLevel) {
    super(paramEntityType, paramLevel);
  }
  
  @Unique
  private static void abduction$updateLeash(Entity paramEntity1, Entity paramEntity2) {
    if (paramEntity1 != null && paramEntity1.f_19853_ == paramEntity2.f_19853_) {
      float f1 = 5.0F;
      if (paramEntity1 instanceof ay) {
        ay ay = (ay)paramEntity1;
        f1 = ay.abduction$getLeashLength();
      } 
      float f2 = paramEntity2.m_20270_(paramEntity1);
      if (f2 > f1) {
        double d1 = (paramEntity1.m_20185_() - paramEntity2.m_20185_()) / f2;
        double d2 = (paramEntity1.m_20186_() - paramEntity2.m_20186_()) / f2;
        double d3 = (paramEntity1.m_20189_() - paramEntity2.m_20189_()) / f2;
        paramEntity2.m_20256_(paramEntity2.m_20184_().m_82520_(Math.copySign(d1 * d1 * 0.4D, d1), Math.copySign(d2 * d2 * 0.4D, d2), Math.copySign(d3 * d3 * 0.4D, d3)));
      } 
    } 
  }
  
  @Shadow
  public abstract void m_6135_();
  
  @Shadow
  public abstract void m_6038_();
  
  @Inject(method = {"tick"}, at = {@At("HEAD")})
  private void tickForLeash(CallbackInfo paramCallbackInfo) {
    if (!this.f_19853_.f_46443_)
      abduction$tickLeash(); 
    Player player = (Player)this;
    if (player instanceof aw) {
      aw aw1 = (aw)player;
      Entity entity = aw1.abduction$getLeashHolder();
      if (entity != null) {
        abduction$updateLeash(entity, (Entity)player);
        this.f_19793_ = 1.25F;
      } else if (player instanceof ServerPlayer) {
        this.f_19793_ = 1.0F;
      } else {
        this.f_19793_ = 0.6F;
      } 
    } 
  }
  
  @Inject(method = {"addAdditionalSaveData"}, at = {@At("RETURN")})
  private void addSaveData(CompoundTag paramCompoundTag, CallbackInfo paramCallbackInfo) {
    if (this.abduction$leashHolder != null) {
      CompoundTag compoundTag = new CompoundTag();
      if (this.abduction$leashHolder instanceof LivingEntity) {
        UUID uUID = this.abduction$leashHolder.m_142081_();
        compoundTag.m_128362_("UUID", uUID);
      } else if (this.abduction$leashHolder instanceof HangingEntity) {
        BlockPos blockPos = ((HangingEntity)this.abduction$leashHolder).m_31748_();
        compoundTag.m_128405_("X", blockPos.m_123341_());
        compoundTag.m_128405_("Y", blockPos.m_123342_());
        compoundTag.m_128405_("Z", blockPos.m_123343_());
      } 
      paramCompoundTag.m_128365_("Leash", (Tag)compoundTag);
    } else if (this.abduction$leashInfoTag != null) {
      paramCompoundTag.m_128365_("Leash", (Tag)this.abduction$leashInfoTag.m_6426_());
    } 
  }
  
  @Inject(method = {"readAdditionalSaveData"}, at = {@At("RETURN")})
  private void readSaveData(CompoundTag paramCompoundTag, CallbackInfo paramCallbackInfo) {
    if (paramCompoundTag.m_128425_("Leash", 10))
      this.abduction$leashInfoTag = paramCompoundTag.m_128469_("Leash"); 
  }
  
  @NotNull
  public Vec3 m_7939_() {
    return new Vec3(0.0D, m_20206_() * 0.77D, (m_20205_() * 0.16F));
  }
  
  @Unique
  protected void abduction$tickLeash() {
    if (this.abduction$leashInfoTag != null)
      abduction$restoreLeashFromSave(); 
    float f = 5.0F;
    Entity entity = this.abduction$leashHolder;
    if (entity instanceof ay) {
      ay ay = (ay)entity;
      f = ay.abduction$getLeashLength();
    } 
    if (this.abduction$leashHolder != null)
      if (!m_6084_() || !this.abduction$leashHolder.m_6084_() || m_20270_(this.abduction$leashHolder) > Math.max(f * 2.0F, 10.0F)) {
        abduction$dropLeash(true, true);
      } else if (m_20270_(this.abduction$leashHolder) > f * 1.3F) {
        m_8127_();
      }  
  }
  
  public void abduction$dropLeash(boolean paramBoolean1, boolean paramBoolean2) {
    if (this.abduction$leashHolder != null) {
      this.abduction$leashHolder = null;
      this.abduction$leashInfoTag = null;
      if (!this.f_19853_.f_46443_ && paramBoolean2)
        m_19983_(Items.f_42655_.m_7968_()); 
      if (!this.f_19853_.f_46443_ && paramBoolean1 && this.f_19853_ instanceof ServerLevel)
        for (ServerPlayer serverPlayer : this.f_19853_.m_142572_().m_6846_().m_11314_())
          ((ServerLevel)this.f_19853_).m_7726_().m_8394_((Entity)serverPlayer, (Packet)new ClientboundSetEntityLinkPacket((Entity)this, null));  
    } 
  }
  
  public boolean abduction$canBeLeashedInstantly(Player paramPlayer) {
    Player player = (Player)this;
    return (!abduction$isLeashed() && bm.areHandsTied((LivingEntity)player));
  }
  
  @Unique
  public boolean abduction$isLeashed() {
    return (this.abduction$leashHolder != null);
  }
  
  @Nullable
  public Entity abduction$getLeashHolder() {
    if (this.abduction$leashHolder == null && this.abduction$delayedLeashHolderId != 0 && this.f_19853_.f_46443_)
      this.abduction$leashHolder = this.f_19853_.m_6815_(this.abduction$delayedLeashHolderId); 
    return this.abduction$leashHolder;
  }
  
  public void abduction$setLeashedTo(Entity paramEntity, boolean paramBoolean) {
    this.abduction$leashHolder = paramEntity;
    this.abduction$leashInfoTag = null;
    if (!this.f_19853_.f_46443_ && paramBoolean && this.f_19853_ instanceof ServerLevel)
      for (ServerPlayer serverPlayer : this.f_19853_.m_142572_().m_6846_().m_11314_())
        ((ServerLevel)this.f_19853_).m_7726_().m_8394_((Entity)serverPlayer, (Packet)new ClientboundSetEntityLinkPacket((Entity)this, this.abduction$leashHolder));  
  }
  
  public void abduction$setDelayedLeashHolderId(int paramInt) {
    this.abduction$delayedLeashHolderId = paramInt;
    abduction$dropLeash(false, false);
  }
  
  @Unique
  private void abduction$restoreLeashFromSave() {
    if (this.abduction$leashInfoTag != null && this.f_19853_ instanceof ServerLevel) {
      if (this.abduction$leashInfoTag.m_128403_("UUID")) {
        UUID uUID = this.abduction$leashInfoTag.m_128342_("UUID");
        Entity entity = ((ServerLevel)this.f_19853_).m_8791_(uUID);
        if (entity != null) {
          abduction$setLeashedTo(entity, true);
          return;
        } 
      } else if (this.abduction$leashInfoTag.m_128425_("X", 99) && this.abduction$leashInfoTag.m_128425_("Y", 99) && this.abduction$leashInfoTag.m_128425_("Z", 99)) {
        BlockPos blockPos = NbtUtils.m_129239_(this.abduction$leashInfoTag);
        abduction$setLeashedTo((Entity)LeashFenceKnotEntity.m_31844_(this.f_19853_, blockPos), true);
        return;
      } 
      if (this.f_19797_ > 100) {
        m_19998_((ItemLike)Items.f_42655_);
        this.abduction$leashInfoTag = null;
      } 
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\leash\PlayerLeashingMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */