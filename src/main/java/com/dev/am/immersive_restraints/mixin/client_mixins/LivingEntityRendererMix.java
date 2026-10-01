package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.C;
import com.dev.am.immersive_restraints.aC;
import com.dev.am.immersive_restraints.aG;
import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.bo;
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
  protected M model;
  
  @Shadow
  public static int getOverlayCoords(LivingEntity paramLivingEntity, float paramFloat) {
    return 0;
  }
  
  @Shadow
  protected abstract float getFlipDegrees(T paramT, float paramFloat);
  
  @Shadow
  protected abstract boolean isShaking(T paramT);
  
  @Inject(method = {"render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isSpectator()Z", shift = At.Shift.BEFORE)})
  private void tickMix(T paramT, float paramFloat1, float paramFloat2, PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, int paramInt, CallbackInfo paramCallbackInfo) {
    Player player;
    if (paramT instanceof Player) {
      player = (Player)paramT;
    } else {
      return;
    } 
    if (player.isInvisibleTo())
      return; 
    abduction$renderClothes(paramT, paramFloat2, paramPoseStack, paramMultiBufferSource, paramInt, player);
  }
  
  @Unique
  private void abduction$renderClothes(T paramT, float paramFloat, PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, int paramInt, Player paramPlayer) {
    List list = bm.getBondagesForRendering((LivingEntity)paramPlayer);
    for (SlotResult slotResult : list) {
      ItemStack itemStack = slotResult.stack();
      Item item = itemStack.getItem();
      if (item instanceof aG) {
        aG aG = (aG)item;
        M m = this.model;
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
            Item item1 = itemStack1.getItem();
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
    RenderType renderType = RenderType.entityTranslucent(paramResourceLocation);
    Minecraft minecraft = Minecraft.getInstance();
    boolean bool = isShaking(paramT);
    boolean bool1 = (!bool && !paramT.isInvisibleTo((Player)minecraft.player)) ? true : false;
    VertexConsumer vertexConsumer = paramMultiBufferSource.getBuffer(renderType);
    int i = getOverlayCoords((LivingEntity)paramT, getFlipDegrees(paramT, paramFloat));
    this.model.renderToBuffer(paramPoseStack, vertexConsumer, paramInt, i, 1.0F, 1.0F, 1.0F, bool1 ? 0.15F : 1.0F);
  }
  
  @Redirect(method = {"render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;IIFFFF)V"))
  private void injected(EntityModel paramEntityModel, PoseStack paramPoseStack1, VertexConsumer paramVertexConsumer, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, T paramT, float paramFloat5, float paramFloat6, PoseStack paramPoseStack2, MultiBufferSource paramMultiBufferSource, int paramInt3) {
    if (paramT instanceof Player) {
      Player player = (Player)paramT;
      if (paramEntityModel instanceof PlayerModel) {
        PlayerModel playerModel = (PlayerModel)paramEntityModel;
        C c = bo.hidePartsInModel(player, playerModel, null);
        paramEntityModel.renderToBuffer(paramPoseStack1, paramVertexConsumer, paramInt1, paramInt2, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
        c.unloadInto(playerModel);
        return;
      } 
    } 
    paramEntityModel.renderToBuffer(paramPoseStack1, paramVertexConsumer, paramInt1, paramInt2, paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\LivingEntityRendererMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */