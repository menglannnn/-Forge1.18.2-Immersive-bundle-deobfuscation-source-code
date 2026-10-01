package com.dev.am.mixin.client_mixins;

import abductionmod.content.v;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({LayerDefinitions.class})
public abstract class LayerDefinitionsMix {
  @Unique
  private static final CubeDeformation INNER_BONDAGE_DEFORMATION = new CubeDeformation(0.251F);
  
  @Redirect(method = {"createRoots"}, at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap$Builder;put(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableMap$Builder;", ordinal = 0))
  private static ImmutableMap.Builder rootsMix(ImmutableMap.Builder paramBuilder, Object paramObject1, Object paramObject2) {
    paramBuilder.put(paramObject1, paramObject2);
    LayerDefinition layerDefinition1 = LayerDefinition.m_171565_(createMeshCustom(INNER_BONDAGE_DEFORMATION, 0.5F, false), 64, 32);
    LayerDefinition layerDefinition2 = LayerDefinition.m_171565_(createMeshCustom(INNER_BONDAGE_DEFORMATION, 0.0F, true), 64, 32);
    paramBuilder.put(v.BONDAGE_INNER_ARMOR, layerDefinition1);
    paramBuilder.put(v.BONDAGE_INNER_ARMOR_SLIM, layerDefinition2);
    return paramBuilder;
  }
  
  private static MeshDefinition createMeshCustom(CubeDeformation paramCubeDeformation, float paramFloat, boolean paramBoolean) {
    MeshDefinition meshDefinition = new MeshDefinition();
    PartDefinition partDefinition = meshDefinition.m_171576_();
    partDefinition.m_171599_("head", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, paramCubeDeformation), PartPose.m_171419_(0.0F, 0.0F + paramFloat, 0.0F));
    partDefinition.m_171599_("hat", CubeListBuilder.m_171558_().m_171514_(32, 0).m_171488_(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, paramCubeDeformation.m_171469_(0.5F)), PartPose.m_171419_(0.0F, 0.0F + paramFloat, 0.0F));
    partDefinition.m_171599_("body", CubeListBuilder.m_171558_().m_171514_(16, 16).m_171488_(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.m_171419_(0.0F, 0.0F + paramFloat, 0.0F));
    partDefinition.m_171599_("right_leg", CubeListBuilder.m_171558_().m_171514_(0, 16).m_171488_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.m_171419_(-1.9F, 12.0F + paramFloat, 0.0F));
    partDefinition.m_171599_("left_leg", CubeListBuilder.m_171558_().m_171514_(0, 16).m_171480_().m_171488_(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.m_171419_(1.9F, 12.0F + paramFloat, 0.0F));
    if (paramBoolean) {
      partDefinition.m_171599_("right_arm", CubeListBuilder.m_171558_().m_171514_(40, 16).m_171488_(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.m_171419_(-5.0F, 2.0F + paramFloat, 0.0F));
      partDefinition.m_171599_("left_arm", CubeListBuilder.m_171558_().m_171514_(40, 16).m_171480_().m_171488_(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.m_171419_(5.0F, 2.0F + paramFloat, 0.0F));
    } else {
      partDefinition.m_171599_("right_arm", CubeListBuilder.m_171558_().m_171514_(40, 16).m_171488_(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.m_171419_(-5.0F, 2.0F + paramFloat, 0.0F));
      partDefinition.m_171599_("left_arm", CubeListBuilder.m_171558_().m_171514_(40, 16).m_171480_().m_171488_(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.m_171419_(5.0F, 2.0F + paramFloat, 0.0F));
    } 
    return meshDefinition;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\LayerDefinitionsMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */