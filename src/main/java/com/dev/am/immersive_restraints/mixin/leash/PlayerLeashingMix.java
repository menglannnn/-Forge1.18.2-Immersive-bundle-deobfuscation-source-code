package com.dev.am.immersive_restraints.mixin.leash;

import com.dev.am.immersive_restraints.aw;
import com.dev.am.immersive_restraints.ay;
import com.dev.am.immersive_restraints.bm;
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
    if (paramEntity1 != null && paramEntity1.level == paramEntity2.level) {
      float f1 = 5.0F;
      if (paramEntity1 instanceof ay) {
        ay ay = (ay)paramEntity1;
        f1 = ay.abduction$getLeashLength();
      } 
      float f2 = paramEntity2.distanceTo(paramEntity1);
      if (f2 > f1) {
        double d1 = (paramEntity1.getX() - paramEntity2.getX()) / f2;
        double d2 = (paramEntity1.getY() - paramEntity2.getY()) / f2;
        double d3 = (paramEntity1.getZ() - paramEntity2.getZ()) / f2;
        paramEntity2.setDeltaMovement(paramEntity2.getDeltaMovement().add(Math.copySign(d1 * d1 * 0.4D, d1), Math.copySign(d2 * d2 * 0.4D, d2), Math.copySign(d3 * d3 * 0.4D, d3)));
      } 
    } 
  }
  
  @Shadow
  public abstract void tick();
  
  @Shadow
  public abstract void baseTick();
  
  @Inject(method = {"tick"}, at = {@At("HEAD")})
  private void tickForLeash(CallbackInfo paramCallbackInfo) {
    if (!this.level.isClientSide)
      abduction$tickLeash(); 
    Player player = (Player)this;
    if (player instanceof aw) {
      aw aw1 = (aw)player;
      Entity entity = aw1.abduction$getLeashHolder();
      if (entity != null) {
        abduction$updateLeash(entity, (Entity)player);
        this.entityData = 1.25F;
      } else if (player instanceof ServerPlayer) {
        this.entityData = 1.0F;
      } else {
        this.entityData = 0.6F;
      } 
    } 
  }
  
  @Inject(method = {"addAdditionalSaveData"}, at = {@At("RETURN")})
  private void addSaveData(CompoundTag paramCompoundTag, CallbackInfo paramCallbackInfo) {
    if (this.abduction$leashHolder != null) {
      CompoundTag compoundTag = new CompoundTag();
      if (this.abduction$leashHolder instanceof LivingEntity) {
        UUID uUID = this.abduction$leashHolder.getId();
        compoundTag.putUUID("UUID", uUID);
      } else if (this.abduction$leashHolder instanceof HangingEntity) {
        BlockPos blockPos = ((HangingEntity)this.abduction$leashHolder).getPos();
        compoundTag.putInt("X", blockPos.getX());
        compoundTag.putInt("Y", blockPos.getY());
        compoundTag.putInt("Z", blockPos.getZ());
      } 
      paramCompoundTag.put("Leash", (Tag)compoundTag);
    } else if (this.abduction$leashInfoTag != null) {
      paramCompoundTag.put("Leash", (Tag)this.abduction$leashInfoTag.copy());
    } 
  }
  
  @Inject(method = {"readAdditionalSaveData"}, at = {@At("RETURN")})
  private void readSaveData(CompoundTag paramCompoundTag, CallbackInfo paramCallbackInfo) {
    if (paramCompoundTag.contains("Leash", 10))
      this.abduction$leashInfoTag = paramCompoundTag.getCompound("Leash"); 
  }
  
  @NotNull
  public Vec3 getLeashOffset() {
    return new Vec3(0.0D, getBbHeight() * 0.77D, (getBbWidth() * 0.16F));
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
      if (!isAlive() || !this.abduction$leashHolder.isAlive() || distanceTo(this.abduction$leashHolder) > Math.max(f * 2.0F, 10.0F)) {
        abduction$dropLeash(true, true);
      } else if (distanceTo(this.abduction$leashHolder) > f * 1.3F) {
        dropLeash();
      }  
  }
  
  public void abduction$dropLeash(boolean paramBoolean1, boolean paramBoolean2) {
    if (this.abduction$leashHolder != null) {
      this.abduction$leashHolder = null;
      this.abduction$leashInfoTag = null;
      if (!this.level.isClientSide && paramBoolean2)
        spawnAtLocation(Items.LEAD.getDefaultInstance()); 
      if (!this.level.isClientSide && paramBoolean1 && this.level instanceof ServerLevel)
        for (ServerPlayer serverPlayer : this.level.getServer().getPlayerList().getPlayers())
          ((ServerLevel)this.level).getChunkSource().broadcastAndSend((Entity)serverPlayer, (Packet)new ClientboundSetEntityLinkPacket((Entity)this, null));  
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
    if (this.abduction$leashHolder == null && this.abduction$delayedLeashHolderId != 0 && this.level.isClientSide)
      this.abduction$leashHolder = this.level.getEntity(this.abduction$delayedLeashHolderId); 
    return this.abduction$leashHolder;
  }
  
  public void abduction$setLeashedTo(Entity paramEntity, boolean paramBoolean) {
    this.abduction$leashHolder = paramEntity;
    this.abduction$leashInfoTag = null;
    if (!this.level.isClientSide && paramBoolean && this.level instanceof ServerLevel)
      for (ServerPlayer serverPlayer : this.level.getServer().getPlayerList().getPlayers())
        ((ServerLevel)this.level).getChunkSource().broadcastAndSend((Entity)serverPlayer, (Packet)new ClientboundSetEntityLinkPacket((Entity)this, this.abduction$leashHolder));  
  }
  
  public void abduction$setDelayedLeashHolderId(int paramInt) {
    this.abduction$delayedLeashHolderId = paramInt;
    abduction$dropLeash(false, false);
  }
  
  @Unique
  private void abduction$restoreLeashFromSave() {
    if (this.abduction$leashInfoTag != null && this.level instanceof ServerLevel) {
      if (this.abduction$leashInfoTag.hasUUID("UUID")) {
        UUID uUID = this.abduction$leashInfoTag.getUUID("UUID");
        Entity entity = ((ServerLevel)this.level).getEntity(uUID);
        if (entity != null) {
          abduction$setLeashedTo(entity, true);
          return;
        } 
      } else if (this.abduction$leashInfoTag.contains("X", 99) && this.abduction$leashInfoTag.contains("Y", 99) && this.abduction$leashInfoTag.contains("Z", 99)) {
        BlockPos blockPos = NbtUtils.readBlockPos(this.abduction$leashInfoTag);
        abduction$setLeashedTo((Entity)LeashFenceKnotEntity.create(this.level, blockPos), true);
        return;
      } 
      if (this.tickCount > 100) {
        spawnAtLocation((ItemLike)Items.LEAD);
        this.abduction$leashInfoTag = null;
      } 
    } 
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\leash\PlayerLeashingMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */