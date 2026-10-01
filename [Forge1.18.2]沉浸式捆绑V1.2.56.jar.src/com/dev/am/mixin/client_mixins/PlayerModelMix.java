package com.dev.am.mixin.client_mixins;

import abductionmod.content.bm;
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
  public ModelPart f_103374_;
  
  @Shadow
  @Final
  public ModelPart f_103375_;
  
  public PlayerModelMix(ModelPart paramModelPart) {
    super(paramModelPart);
  }
  
  @Inject(method = {"setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V"}, at = {@At("TAIL")}, cancellable = true)
  private void initMix(T paramT, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, CallbackInfo paramCallbackInfo) {
    if (paramT instanceof Player) {
      Player player = (Player)paramT;
      if (bm.areHandsTied((LivingEntity)player)) {
        this.f_102811_.f_104202_ = Mth.m_14031_(this.f_102810_.f_104204_) * 5.0F;
        this.f_102811_.f_104200_ = -Mth.m_14089_(this.f_102810_.f_104204_) * 5.0F;
        this.f_102812_.f_104202_ = -Mth.m_14031_(this.f_102810_.f_104204_) * 5.0F;
        this.f_102812_.f_104200_ = Mth.m_14089_(this.f_102810_.f_104204_) * 5.0F;
        this.f_102811_.f_104204_ += this.f_102810_.f_104204_;
        this.f_102812_.f_104204_ += this.f_102810_.f_104204_;
        this.f_102812_.f_104203_ += this.f_102810_.f_104203_;
        this.f_103374_.f_104200_ = this.f_102812_.f_104200_;
        this.f_103374_.f_104201_ = this.f_102812_.f_104201_;
        this.f_103374_.f_104202_ = this.f_102812_.f_104202_;
        this.f_103374_.f_104204_ = this.f_102812_.f_104204_;
        this.f_103374_.f_104203_ = this.f_102812_.f_104203_;
        this.f_103375_.f_104200_ = this.f_102811_.f_104200_;
        this.f_103375_.f_104201_ = this.f_102811_.f_104201_;
        this.f_103375_.f_104202_ = this.f_102811_.f_104202_;
        this.f_103375_.f_104204_ = this.f_102811_.f_104204_;
        this.f_103375_.f_104203_ = this.f_102811_.f_104203_;
        paramCallbackInfo.cancel();
      } 
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\PlayerModelMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */