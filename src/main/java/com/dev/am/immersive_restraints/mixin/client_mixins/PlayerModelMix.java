package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.bm;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerModel.class})
public abstract class PlayerModelMix<T extends LivingEntity> extends HumanoidModel<T> {
  @Shadow
  @Final
  public ModelPart leftSleeve;
  
  @Shadow
  @Final
  public ModelPart rightSleeve;
  
  public PlayerModelMix(ModelPart paramModelPart) {
    super(paramModelPart);
  }
  
  @Inject(method = {"setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V"}, at = {@At("TAIL")}, cancellable = true)
  private void initMix(T paramT, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, CallbackInfo paramCallbackInfo) {
    if (paramT instanceof Player) {
      Player player = (Player)paramT;
      if (bm.areHandsTied((LivingEntity)player)) {
        this.rightArm.xRot = Mth.cos(this.body.xRot) * 5.0F;
        this.rightArm.zRot = -Mth.sin(this.body.xRot) * 5.0F;
        this.leftArm.xRot = -Mth.cos(this.body.xRot) * 5.0F;
        this.leftArm.zRot = Mth.sin(this.body.xRot) * 5.0F;
        this.rightArm.xRot += this.body.xRot;
        this.leftArm.xRot += this.body.xRot;
        this.leftArm.yRot += this.body.yRot;
        this.leftSleeve.zRot = this.leftArm.zRot;
        this.leftSleeve.yScale = this.leftArm.yScale;
        this.leftSleeve.xRot = this.leftArm.xRot;
        this.leftSleeve.xRot = this.leftArm.xRot;
        this.leftSleeve.yRot = this.leftArm.yRot;
        this.rightSleeve.zRot = this.rightArm.zRot;
        this.rightSleeve.yScale = this.rightArm.yScale;
        this.rightSleeve.xRot = this.rightArm.xRot;
        this.rightSleeve.xRot = this.rightArm.xRot;
        this.rightSleeve.yRot = this.rightArm.yRot;
        paramCallbackInfo.cancel();
      } 
    } 
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\PlayerModelMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */