package com.dev.am.immersive_restraints.mixin.leash;

import com.dev.am.immersive_restraints.aw;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({Mob.class})
public abstract class MobLeashingMix extends LivingEntity implements aw {
  @Shadow
  public abstract boolean canBeLeashed(Player paramPlayer);
  
  protected MobLeashingMix(EntityType<? extends LivingEntity> paramEntityType, Level paramLevel) {
    super(paramEntityType, paramLevel);
  }
  
  @Nullable
  public Entity abduction$getLeashHolder() {
    Mob mob = (Mob)this;
    return mob.isLeashed();
  }
  
  public void abduction$setLeashedTo(Entity paramEntity, boolean paramBoolean) {
    Mob mob = (Mob)this;
    mob.setLeashedTo(paramEntity, paramBoolean);
  }
  
  public boolean abduction$canBeLeashedInstantly(Player paramPlayer) {
    return canBeLeashed(paramPlayer);
  }
  
  public void abduction$dropLeash(boolean paramBoolean1, boolean paramBoolean2) {
    Mob mob = (Mob)this;
    mob.dropLeash(paramBoolean1, paramBoolean2);
  }
  
  public void abduction$setDelayedLeashHolderId(int paramInt) {
    Mob mob = (Mob)this;
    mob.setDelayedLeashHolderId(paramInt);
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\leash\MobLeashingMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */