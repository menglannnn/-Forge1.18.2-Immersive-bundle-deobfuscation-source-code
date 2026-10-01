package com.dev.am.mixin.leash;

import abductionmod.content.aw;
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
  public abstract boolean m_6573_(Player paramPlayer);
  
  protected MobLeashingMix(EntityType<? extends LivingEntity> paramEntityType, Level paramLevel) {
    super(paramEntityType, paramLevel);
  }
  
  @Nullable
  public Entity abduction$getLeashHolder() {
    Mob mob = (Mob)this;
    return mob.m_21524_();
  }
  
  public void abduction$setLeashedTo(Entity paramEntity, boolean paramBoolean) {
    Mob mob = (Mob)this;
    mob.m_21463_(paramEntity, paramBoolean);
  }
  
  public boolean abduction$canBeLeashedInstantly(Player paramPlayer) {
    return m_6573_(paramPlayer);
  }
  
  public void abduction$dropLeash(boolean paramBoolean1, boolean paramBoolean2) {
    Mob mob = (Mob)this;
    mob.m_21455_(paramBoolean1, paramBoolean2);
  }
  
  public void abduction$setDelayedLeashHolderId(int paramInt) {
    Mob mob = (Mob)this;
    mob.m_21506_(paramInt);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\leash\MobLeashingMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */