package abductionmod.content;

import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface ay {
  int am$getTyingTimeout();
  
  void am$setTyingTimeout(int paramInt);
  
  int abduction$getAnimationId();
  
  void abduction$setAnimationId(int paramInt);
  
  int abduction$getTyerHash();
  
  void abduction$setTyerHash(int paramInt);
  
  int abduction$getTyingTime();
  
  int abduction$getMaxTyingTime();
  
  int abduction$getUntyingPowerSpent();
  
  float abduction$getUntyingProgressPercent();
  
  void abduction$addUntyingPower(int paramInt);
  
  float abduction$getTyingProgressPercent();
  
  int abduction$getUntyingCoolDown();
  
  void abduction$setUntyingCoolDown(int paramInt);
  
  @Nullable
  LivingEntity abduction$getTyingEntity();
  
  void abduction$setTyingEntity(LivingEntity paramLivingEntity);
  
  void abduction$addTyingTime(int paramInt);
  
  void abduction$setPlayerPressing(ItemStack paramItemStack);
  
  boolean abduction$isFinishedTying();
  
  boolean abduction$isFinishedUnTying();
  
  boolean abduction$isBeingTied();
  
  boolean abduction$isUntying();
  
  void abduction$setUntying(boolean paramBoolean);
  
  boolean abduction$isHoldingChangePositionKey();
  
  void abduction$setHoldingChangePositionKey(boolean paramBoolean);
  
  void abduction$stopTyingProcess();
  
  CompoundTag abduction$getAdjustmentOffsetsMap();
  
  void abduction$setAdjustmentOffsetsMap(CompoundTag paramCompoundTag);
  
  float abduction$getLeashLength();
  
  void abduction$setLeashLength(float paramFloat);
  
  void abduction$setUntieClicks(int paramInt);
  
  int abduction$getUntieClicks();
  
  boolean abduction$getProtectedClothes();
  
  void abduction$setProtectedClothes(boolean paramBoolean);
  
  boolean abduction$getLegsUntied();
  
  void abduction$setLegsUntied(boolean paramBoolean);
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\ay.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */