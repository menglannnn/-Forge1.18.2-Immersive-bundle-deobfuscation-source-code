package abductionmod.content;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.kosmx.playerAnim.impl.IUpperPartHelper;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotResult;

@OnlyIn(Dist.CLIENT)
public class v<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
  public static ModelLayerLocation BONDAGE_INNER_ARMOR;
  
  public static ModelLayerLocation BONDAGE_INNER_ARMOR_SLIM;
  
  public static HumanoidModel<?> modelBeingRendered = null;
  
  private final A innerModel;
  
  public boolean renderingHand;
  
  public HumanoidArm renderingArmSide;
  
  public v(RenderLayerParent<T, M> paramRenderLayerParent, A paramA) {
    super(paramRenderLayerParent);
    this.innerModel = paramA;
    ((IUpperPartHelper)this).setUpperPart(false);
  }
  
  @NotNull
  public static ResourceLocation getTextureLocation(ItemStack paramItemStack, LivingEntity paramLivingEntity) {
    ResourceLocation resourceLocation = br.getResourceLocationFromTextureTag(paramItemStack, bl.DYNAMIC_TEXTURE_TAG);
    if (resourceLocation != null)
      return resourceLocation; 
    String str = ((aC)paramItemStack.m_41720_()).name;
    aC aC = (aC)paramItemStack.m_41720_();
    if (aC.properties.hasSeparateSlimTexture(paramItemStack)) {
      EntityRenderer entityRenderer1 = Minecraft.m_91087_().m_91290_().m_114382_((Entity)paramLivingEntity);
      EntityRenderer entityRenderer2 = entityRenderer1;
      if (entityRenderer2 instanceof PlayerRenderer) {
        PlayerRenderer playerRenderer = (PlayerRenderer)entityRenderer2;
        if (((PlayerModel)playerRenderer.m_7200_()).f_103380_)
          str = str + "_slim"; 
      } 
    } 
    return new ResourceLocation("am", "textures/models/bondage/" + str + ".png");
  }
  
  public void render(@NotNull PoseStack paramPoseStack, @NotNull MultiBufferSource paramMultiBufferSource, int paramInt, @NotNull T paramT, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    List<SlotResult> list = bm.getBondagesForRendering((LivingEntity)paramT);
    for (SlotResult slotResult : list) {
      ItemStack itemStack = slotResult.stack();
      float f = 0.0F;
      if (paramT instanceof ay) {
        ay ay = (ay)paramT;
        if (slotResult.slotContext() != null) {
          f = ay.abduction$getAdjustmentOffsetsMap().m_128457_(slotResult.slotContext().identifier());
          Float float_ = bl.getCustomTagFloat(itemStack, bl.DATA_AM_ADJUSTMENT_MAP_TAG);
          if (float_ != null && float_.floatValue() != 0.0F)
            f = float_.floatValue(); 
        } 
      } 
      renderBondagePiece(paramPoseStack, paramMultiBufferSource, paramT, f, EquipmentSlot.LEGS, paramInt, this.innerModel, getTextureLocation(itemStack, (LivingEntity)paramT), itemStack);
    } 
  }
  
  private void renderBondagePiece(PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, T paramT, float paramFloat, EquipmentSlot paramEquipmentSlot, int paramInt, A paramA, ResourceLocation paramResourceLocation, ItemStack paramItemStack) {
    ((HumanoidModel)m_117386_()).m_102872_((HumanoidModel)paramA);
    setPartVisibility(paramA, paramT, paramItemStack, paramEquipmentSlot);
    HumanoidModel humanoidModel = (HumanoidModel)getArmorModelHook(paramT, paramT.m_6844_(paramEquipmentSlot), paramEquipmentSlot, paramA);
    humanoidModel.f_102610_ = false;
    renderModel(paramPoseStack, paramMultiBufferSource, paramT, paramFloat, paramInt, false, (Model)humanoidModel, 1.0F, 1.0F, 1.0F, paramResourceLocation, paramItemStack);
  }
  
  protected void setPartVisibility(A paramA, T paramT, ItemStack paramItemStack, EquipmentSlot paramEquipmentSlot) {
    if (this.renderingHand) {
      paramA.m_8009_(false);
      if (this.renderingArmSide == HumanoidArm.LEFT) {
        ((HumanoidModel)paramA).f_102812_.f_104207_ = true;
      } else {
        ((HumanoidModel)paramA).f_102811_.f_104207_ = true;
      } 
      return;
    } 
    paramA.m_8009_(true);
    ay ay = (ay)paramT;
    if (!(paramItemStack.m_41720_() instanceof aG) && ay.abduction$getLegsUntied()) {
      ((HumanoidModel)paramA).f_102814_.f_104207_ = false;
      ((HumanoidModel)paramA).f_102813_.f_104207_ = false;
    } 
  }
  
  private void renderModel(PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, T paramT, float paramFloat1, int paramInt, boolean paramBoolean, Model paramModel, float paramFloat2, float paramFloat3, float paramFloat4, ResourceLocation paramResourceLocation, ItemStack paramItemStack) {
    VertexConsumer vertexConsumer = ItemRenderer.m_115184_(paramMultiBufferSource, RenderType.m_110473_(paramResourceLocation), false, paramBoolean);
    if (paramModel instanceof HumanoidModel) {
      HumanoidModel<?> humanoidModel = (HumanoidModel)paramModel;
      modelBeingRendered = humanoidModel;
      applyHeadOffsetAdjustments(paramFloat1);
      C c = new C(humanoidModel);
      Item item = paramItemStack.m_41720_();
      if (item instanceof aC) {
        aC aC = (aC)item;
        C c1 = new C(humanoidModel);
        aC.properties.getModelVisibilityConsumer(paramItemStack).accept(c1);
        List<SlotResult> list = bm.getBondagesForRendering((LivingEntity)paramT);
        for (SlotResult slotResult : list) {
          Item item1 = slotResult.stack().m_41720_();
          if (item1 instanceof aC) {
            aC aC1 = (aC)item1;
            aC1.hideBodyParts.accept(c1);
          } 
        } 
        c1.unloadInto(humanoidModel);
      } 
      paramModel.m_7695_(paramPoseStack, vertexConsumer, paramInt, OverlayTexture.f_118083_, paramFloat2, paramFloat3, paramFloat4, 1.0F);
      c.unloadInto(humanoidModel);
      modelBeingRendered = null;
    } 
  }
  
  private void applyHeadOffsetAdjustments(float paramFloat) {
    if (paramFloat != 0.0F) {
      float f1 = ((HumanoidModel)this.innerModel).f_102808_.f_104203_;
      float f2 = ((HumanoidModel)this.innerModel).f_102808_.f_104204_;
      float f3 = ((HumanoidModel)this.innerModel).f_102808_.f_104205_;
      float f4 = ((HumanoidModel)this.innerModel).f_102808_.f_104201_;
      ((HumanoidModel)this.innerModel).f_102808_.f_104203_ = 0.0F;
      ((HumanoidModel)this.innerModel).f_102808_.f_104204_ = 0.0F;
      ((HumanoidModel)this.innerModel).f_102808_.f_104205_ = 0.0F;
      ((HumanoidModel)this.innerModel).f_102808_.f_104201_ -= paramFloat;
      rotateAroundPivot(((HumanoidModel)this.innerModel).f_102808_, ((HumanoidModel)this.innerModel).f_102808_.f_104200_, f4, ((HumanoidModel)this.innerModel).f_102808_.f_104202_, f1, f2, f3);
    } 
  }
  
  public void rotateAroundPivot(ModelPart paramModelPart, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6) {
    paramModelPart.f_104200_ -= paramFloat1;
    paramModelPart.f_104201_ -= paramFloat2;
    paramModelPart.f_104202_ -= paramFloat3;
    float f1 = (float)(paramModelPart.f_104201_ * Math.cos(paramFloat4) - paramModelPart.f_104202_ * Math.sin(paramFloat4));
    float f2 = (float)(paramModelPart.f_104201_ * Math.sin(paramFloat4) + paramModelPart.f_104202_ * Math.cos(paramFloat4));
    paramModelPart.f_104201_ = f1;
    paramModelPart.f_104202_ = f2;
    float f3 = (float)(paramModelPart.f_104202_ * Math.sin(paramFloat5) + paramModelPart.f_104200_ * Math.cos(paramFloat5));
    f2 = (float)(paramModelPart.f_104202_ * Math.cos(paramFloat5) - paramModelPart.f_104200_ * Math.sin(paramFloat5));
    paramModelPart.f_104200_ = f3;
    paramModelPart.f_104202_ = f2;
    f3 = (float)(paramModelPart.f_104200_ * Math.cos(paramFloat6) - paramModelPart.f_104201_ * Math.sin(paramFloat6));
    f1 = (float)(paramModelPart.f_104200_ * Math.sin(paramFloat6) + paramModelPart.f_104201_ * Math.cos(paramFloat6));
    paramModelPart.f_104200_ = f3;
    paramModelPart.f_104201_ = f1;
    paramModelPart.f_104200_ += paramFloat1;
    paramModelPart.f_104201_ += paramFloat2;
    paramModelPart.f_104202_ += paramFloat3;
    paramModelPart.f_104203_ += paramFloat4;
    paramModelPart.f_104204_ += paramFloat5;
    paramModelPart.f_104205_ += paramFloat6;
  }
  
  protected Model getArmorModelHook(T paramT, ItemStack paramItemStack, EquipmentSlot paramEquipmentSlot, A paramA) {
    return ForgeHooksClient.getArmorModel((LivingEntity)paramT, paramItemStack, paramEquipmentSlot, (HumanoidModel)paramA);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\v.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */