package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.v;
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
    LayerDefinition layerDefinition1 = LayerDefinition.create(createMeshCustom(INNER_BONDAGE_DEFORMATION, 0.5F, false), 64, 32);
    LayerDefinition layerDefinition2 = LayerDefinition.create(createMeshCustom(INNER_BONDAGE_DEFORMATION, 0.0F, true), 64, 32);
    paramBuilder.put(v.BONDAGE_INNER_ARMOR, layerDefinition1);
    paramBuilder.put(v.BONDAGE_INNER_ARMOR_SLIM, layerDefinition2);
    return paramBuilder;
  }
  
  private static MeshDefinition createMeshCustom(CubeDeformation paramCubeDeformation, float paramFloat, boolean paramBoolean) {
    MeshDefinition meshDefinition = new MeshDefinition();
    PartDefinition partDefinition = meshDefinition.getRoot();
    partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, paramCubeDeformation), PartPose.offset(0.0F, 0.0F + paramFloat, 0.0F));
    partDefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, paramCubeDeformation.extend(0.5F)), PartPose.offset(0.0F, 0.0F + paramFloat, 0.0F));
    partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.offset(0.0F, 0.0F + paramFloat, 0.0F));
    partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.offset(-1.9F, 12.0F + paramFloat, 0.0F));
    partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.offset(1.9F, 12.0F + paramFloat, 0.0F));
    if (paramBoolean) {
      partDefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.offset(-5.0F, 2.0F + paramFloat, 0.0F));
      partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.offset(5.0F, 2.0F + paramFloat, 0.0F));
    } else {
      partDefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.offset(-5.0F, 2.0F + paramFloat, 0.0F));
      partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, paramCubeDeformation), PartPose.offset(5.0F, 2.0F + paramFloat, 0.0F));
    } 
    return meshDefinition;
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\LayerDefinitionsMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */