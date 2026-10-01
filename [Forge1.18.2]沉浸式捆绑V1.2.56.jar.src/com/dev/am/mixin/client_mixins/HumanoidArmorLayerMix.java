package com.dev.am.mixin.client_mixins;

import abductionmod.content.aC;
import abductionmod.content.aP;
import abductionmod.content.bm;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.List;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.SlotResult;

@Mixin({HumanoidArmorLayer.class})
public abstract class HumanoidArmorLayerMix<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
  @Shadow
  @Final
  private A f_117071_;
  
  @Shadow
  protected abstract Model getArmorModelHook(T paramT, ItemStack paramItemStack, EquipmentSlot paramEquipmentSlot, A paramA);
  
  @Shadow
  protected abstract void renderModel(PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, int paramInt, boolean paramBoolean, Model paramModel, float paramFloat1, float paramFloat2, float paramFloat3, ResourceLocation paramResourceLocation);
  
  @Shadow
  public abstract ResourceLocation getArmorResource(Entity paramEntity, ItemStack paramItemStack, EquipmentSlot paramEquipmentSlot, @Nullable String paramString);
  
  @Shadow
  protected abstract void m_117125_(A paramA, EquipmentSlot paramEquipmentSlot);
  
  @Shadow
  protected abstract boolean m_117128_(EquipmentSlot paramEquipmentSlot);
  
  @Shadow
  protected abstract A m_117078_(EquipmentSlot paramEquipmentSlot);
  
  public HumanoidArmorLayerMix(RenderLayerParent<T, M> paramRenderLayerParent) {
    super(paramRenderLayerParent);
  }
  
  @Inject(method = {"renderArmorPiece"}, at = {@At("HEAD")}, cancellable = true)
  private void renderArmorPieceMix(PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, T paramT, EquipmentSlot paramEquipmentSlot, int paramInt, A paramA, CallbackInfo paramCallbackInfo) {
    if (!bm.shouldDrawArmorStack((LivingEntity)paramT, paramT.m_6844_(paramEquipmentSlot)))
      paramCallbackInfo.cancel(); 
  }
  
  @Inject(method = {"render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V"}, at = {@At("HEAD")})
  private void render(PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, int paramInt, T paramT, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, CallbackInfo paramCallbackInfo) {
    List list = bm.getBondagesForRendering((LivingEntity)paramT);
    for (SlotResult slotResult : list) {
      ItemStack itemStack = slotResult.stack();
      Item item = itemStack.m_41720_();
      if (item instanceof aC) {
        aC aC = (aC)item;
        if (aC.arrayOf3dItems != null)
          for (Item item1 : (Item[])aC.arrayOf3dItems.get()) {
            ItemStack itemStack1 = item1.m_7968_();
            itemStack1.m_41751_(itemStack.m_41783_());
            render3DBondageItem(itemStack1, paramPoseStack, paramMultiBufferSource, paramT, paramInt);
          }  
      } 
    } 
  }
  
  @Unique
  private void render3DBondageItem(ItemStack paramItemStack, PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, T paramT, int paramInt) {
    EquipmentSlot equipmentSlot = ((aP)paramItemStack.m_41720_()).m_40402_();
    A a = m_117078_(equipmentSlot);
    ((HumanoidModel)m_117386_()).m_102872_((HumanoidModel)a);
    Model model = getArmorModelHook(paramT, paramItemStack, equipmentSlot, a);
    renderModel(paramPoseStack, paramMultiBufferSource, paramInt, false, model, 1.0F, 1.0F, 1.0F, getArmorResource((Entity)paramT, paramItemStack, equipmentSlot, null));
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\HumanoidArmorLayerMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */