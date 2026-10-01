package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.bl;
import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.v;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.SlotResult;

@Mixin({PlayerRenderer.class})
public abstract class PlayerRendererMix extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
  public PlayerRendererMix(EntityRendererProvider.Context paramContext, PlayerModel<AbstractClientPlayer> paramPlayerModel, float paramFloat) {
    super(paramContext, (EntityModel)paramPlayerModel, paramFloat);
  }
  
  @Inject(method = {"<init>"}, at = {@At("TAIL")})
  private void initMix(EntityRendererProvider.Context paramContext, boolean paramBoolean, CallbackInfo paramCallbackInfo) {
    addLayer((RenderLayer)new v((RenderLayerParent)this, new HumanoidModel(paramContext.bakeLayer(paramBoolean ? v.BONDAGE_INNER_ARMOR_SLIM : v.BONDAGE_INNER_ARMOR))));
  }
  
  @Redirect(method = {"renderHand"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/geom/ModelPart;render(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;II)V"))
  private void tryHideArmsFirstPerson(ModelPart paramModelPart1, PoseStack paramPoseStack1, VertexConsumer paramVertexConsumer, int paramInt1, int paramInt2, PoseStack paramPoseStack2, MultiBufferSource paramMultiBufferSource, int paramInt3, AbstractClientPlayer paramAbstractClientPlayer, ModelPart paramModelPart2, ModelPart paramModelPart3) {
    List list = bm.getBondagesForRendering((LivingEntity)paramAbstractClientPlayer);
    for (SlotResult slotResult : list) {
      ItemStack itemStack = slotResult.stack();
      String str = bl.getCustomTag(itemStack, bl.HIDE_ORIGINAL_SKIN_PARTS_TAG);
      if (str == null || str.length() != 7)
        continue; 
      if (str.charAt(0) == '0')
        return; 
      for (byte b = 0; b < str.length(); b++) {
        boolean bool = (str.charAt(b) != '0') ? true : false;
        switch (b) {
          case 3:
            if (!bool && paramModelPart2 == ((PlayerModel)this.model).leftArm)
              return; 
            break;
          case 4:
            if (!bool && paramModelPart2 == ((PlayerModel)this.model).rightArm)
              return; 
            break;
        } 
      } 
    } 
    paramModelPart1.render(paramPoseStack1, paramVertexConsumer, paramInt1, paramInt2);
  }
  
  @Inject(method = {"renderHand"}, at = {@At("TAIL")})
  private void tryDrawCustomClothes(PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, int paramInt, AbstractClientPlayer paramAbstractClientPlayer, ModelPart paramModelPart1, ModelPart paramModelPart2, CallbackInfo paramCallbackInfo) {
    List list = bm.getBondagesForRendering((LivingEntity)paramAbstractClientPlayer);
    for (SlotResult slotResult : list) {
      ItemStack itemStack = slotResult.stack();
      ResourceLocation resourceLocation = bm.abduction$getTextureLocationClothes(itemStack);
      if (resourceLocation != null) {
        paramModelPart1.yRot = 0.0F;
        paramModelPart1.render(paramPoseStack, paramMultiBufferSource.getBuffer(RenderType.armorCutoutNoCull(resourceLocation)), paramInt, OverlayTexture.NO_OVERLAY);
        paramModelPart2.yRot = 0.0F;
        paramModelPart2.render(paramPoseStack, paramMultiBufferSource.getBuffer(RenderType.armorCutoutNoCull(resourceLocation)), paramInt, OverlayTexture.NO_OVERLAY);
      } 
      Minecraft minecraft = Minecraft.getInstance();
      if (!paramAbstractClientPlayer.isSpectator())
        for (RenderLayer renderLayer : this.layers) {
          if (renderLayer instanceof v) {
            v v = (v)renderLayer;
            v.renderingHand = true;
            renderLayer.render(paramPoseStack, paramMultiBufferSource, paramInt, (Entity)paramAbstractClientPlayer, 0.0F, 0.0F, minecraft.getPartialTick(), 0.0F, 0.0F, 0.0F);
            v.renderingHand = false;
            v.renderingArmSide = (paramModelPart1 == ((PlayerModel)this.model).leftArm) ? HumanoidArm.LEFT : HumanoidArm.RIGHT;
          } 
        }  
    } 
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\PlayerRendererMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */