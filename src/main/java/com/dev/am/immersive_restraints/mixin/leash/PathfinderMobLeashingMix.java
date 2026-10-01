package com.dev.am.immersive_restraints.mixin.leash;

import com.dev.am.immersive_restraints.ay;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin({PathfinderMob.class})
public abstract class PathfinderMobLeashingMix extends Mob {
  protected PathfinderMobLeashingMix(EntityType<? extends Mob> paramEntityType, Level paramLevel) {
    super(paramEntityType, paramLevel);
  }
  
  @ModifyConstant(method = {"tickLeash"}, constant = {@Constant(floatValue = 10.0F)})
  private float maxRangeMod(float paramFloat) {
    Entity entity = isLeashed();
    if (entity instanceof ay) {
      ay ay = (ay)entity;
      return Math.max(ay.abduction$getLeashLength() * 2.0F, 10.0F);
    } 
    return paramFloat;
  }
  
  @ModifyConstant(method = {"tickLeash"}, constant = {@Constant(floatValue = 6.0F)})
  private float rangeMod(float paramFloat) {
    Entity entity = isLeashed();
    if (entity instanceof ay) {
      ay ay = (ay)entity;
      return ay.abduction$getLeashLength();
    } 
    return paramFloat;
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\leash\PathfinderMobLeashingMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */