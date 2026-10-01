package com.dev.am.mixin.client_mixins;

import abductionmod.content.bm;
import java.util.function.Function;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({HumanoidModel.class})
public abstract class HumanoidModelMix<T extends LivingEntity> extends AgeableListModel<T> implements ArmedModel, HeadedModel {
  @Redirect(method = {"setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;cos(F)F", ordinal = 2))
  private float rightLeg(float paramFloat1, T paramT, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    return abduction$stopSwing(paramFloat1, paramT, bm::areLegsTied);
  }
  
  @Redirect(method = {"setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;cos(F)F", ordinal = 3))
  private float leftLeg(float paramFloat1, T paramT, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    return abduction$stopSwing(paramFloat1, paramT, bm::areLegsTied);
  }
  
  @Redirect(method = {"setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;cos(F)F", ordinal = 0))
  private float rightArm(float paramFloat1, T paramT, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    return abduction$stopSwing(paramFloat1, paramT, bm::areHandsTied);
  }
  
  @Redirect(method = {"setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;cos(F)F", ordinal = 1))
  private float leftArm(float paramFloat1, T paramT, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    return abduction$stopSwing(paramFloat1, paramT, bm::areHandsTied);
  }
  
  @Unique
  private <T extends LivingEntity> float abduction$stopSwing(float paramFloat, T paramT, Function<Player, Boolean> paramFunction) {
    if (paramT instanceof Player) {
      Player player = (Player)paramT;
      if (((Boolean)paramFunction.apply(player)).booleanValue())
        return 0.0F; 
    } 
    return Mth.m_14089_(paramFloat);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\HumanoidModelMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */