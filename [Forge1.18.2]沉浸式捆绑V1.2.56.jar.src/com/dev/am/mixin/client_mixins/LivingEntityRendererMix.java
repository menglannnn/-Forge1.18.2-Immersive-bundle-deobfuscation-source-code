package com.dev.am.mixin.client_mixins;

import abductionmod.content.C;
import abductionmod.content.aC;
import abductionmod.content.aG;
import abductionmod.content.bm;
import abductionmod.content.bo;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.SlotResult;

@Mixin({LivingEntityRenderer.class})
public abstract class LivingEntityRendererMix<T extends LivingEntity, M extends EntityModel<T>> {
  @Shadow
  protected M f_115290_;
  
  @Shadow
  public static int m_115338_(LivingEntity paramLivingEntity, float paramFloat) {
    return 0;
  }
  
  @Shadow
  protected abstract float m_6931_(T paramT, float paramFloat);
  
  @Shadow
  protected abstract boolean m_5933_(T paramT);
  
  @Inject(method = {"render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isSpectator()Z", shift = At.Shift.BEFORE)})
  private void tickMix(T paramT, float paramFloat1, float paramFloat2, PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, int paramInt, CallbackInfo paramCallbackInfo) {
    Player player;
    if (paramT instanceof Player) {
      player = (Player)paramT;
    } else {
      return;
    } 
    if (player.m_20145_())
      return; 
    abduction$renderClothes(paramT, paramFloat2, paramPoseStack, paramMultiBufferSource, paramInt, player);
  }
  
  @Unique
  private void abduction$renderClothes(T paramT, float paramFloat, PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, int paramInt, Player paramPlayer) {
    List list = bm.getBondagesForRendering((LivingEntity)paramPlayer);
    for (SlotResult slotResult : list) {
      ItemStack itemStack = slotResult.stack();
      Item item = itemStack.m_41720_();
      if (item instanceof aG) {
        aG aG = (aG)item;
        M m = this.f_115290_;
        if (m instanceof PlayerModel) {
          PlayerModel playerModel = (PlayerModel)m;
          if (itemStack == bm.getMainClothes((LivingEntity)paramPlayer)) {
            C c = bo.hidePartsInModel(paramPlayer, playerModel, itemStack);
            abduction$renderClothesPiece(paramT, paramFloat, paramPoseStack, paramMultiBufferSource, paramInt, bm.abduction$getTextureLocationClothes(itemStack));
            c.unloadInto(playerModel);
            continue;
          } 
          C c1 = new C(playerModel);
          C c2 = new C(playerModel);
          for (SlotResult slotResult1 : list) {
            ItemStack itemStack1 = slotResult1.stack();
            Item item1 = itemStack1.m_41720_();
            if (item1 instanceof aC) {
              aC aC = (aC)item1;
              aC.hideBodyParts.accept(c2);
            } 
          } 
          c2.unloadInto(playerModel);
          abduction$renderClothesPiece(paramT, paramFloat, paramPoseStack, paramMultiBufferSource, paramInt, bm.abduction$getTextureLocationClothes(itemStack));
          c1.unloadInto(playerModel);
        } 
      } 
    } 
  }
  
  @Unique
  private void abduction$renderClothesPiece(T paramT, float paramFloat, PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, int paramInt, @Nullable ResourceLocation paramResourceLocation) {
    if (paramResourceLocation == null)
      return; 
    RenderType renderType = RenderType.m_110467_(paramResourceLocation);
    Minecraft minecraft = Minecraft.m_91087_();
    boolean bool = m_5933_(paramT);
    boolean bool1 = (!bool && !paramT.m_20177_((Player)minecraft.f_91074_)) ? true : false;
    VertexConsumer vertexConsumer = paramMultiBufferSource.m_6299_(renderType);
    int i = m_115338_((LivingEntity)paramT, m_6931_(paramT, paramFloat));
    this.f_115290_.m_7695_(paramPoseStack, vertexConsumer, paramInt, i, 1.0F, 1.0F, 1.0F, bool1 ? 0.15F : 1.0F);
  }
  
  @Redirect(method = {"render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;IIFFFF)V"))
  private void injected(EntityModel paramEntityModel, PoseStack paramPoseStack1, VertexConsumer paramVertexConsumer, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, T paramT, float paramFloat5, float paramFloat6, PoseStack paramPoseStack2, MultiBufferSource paramMultiBufferSource, int paramInt3) {
    if (paramT instanceof Player) {
      Player player = (Player)paramT;
      if (paramEntityModel instanceof PlayerModel) {
        PlayerModel playerModel = (PlayerModel)paramEntityModel;
        C c = bo.hidePartsInModel(player, playerModel, null);
        paramEntityModel.m_7695_(paramPoseStack1, paramVertexConsumer, paramInt1, paramInt2, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
        c.unloadInto(playerModel);
        return;
      } 
    } 
    paramEntityModel.m_7695_(paramPoseStack1, paramVertexConsumer, paramInt1, paramInt2, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\LivingEntityRendererMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */