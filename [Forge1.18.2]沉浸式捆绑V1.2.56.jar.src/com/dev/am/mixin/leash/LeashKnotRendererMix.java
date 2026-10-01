package com.dev.am.mixin.leash;

import abductionmod.content.aw;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LeashKnotRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LeashKnotRenderer.class})
public abstract class LeashKnotRendererMix extends EntityRenderer<LeashFenceKnotEntity> {
  protected LeashKnotRendererMix(EntityRendererProvider.Context paramContext) {
    super(paramContext);
  }
  
  @Unique
  private static void abduction$addVertexPair(VertexConsumer paramVertexConsumer, Matrix4f paramMatrix4f, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, int paramInt5, boolean paramBoolean) {
    float f1 = paramInt5 / 24.0F;
    int i = (int)Mth.m_14179_(f1, paramInt1, paramInt2);
    int j = (int)Mth.m_14179_(f1, paramInt3, paramInt4);
    int k = LightTexture.m_109885_(i, j);
    float f2 = (paramInt5 % 2 == (paramBoolean ? 1 : 0)) ? 0.7F : 1.0F;
    float f3 = 0.5F * f2;
    float f4 = 0.4F * f2;
    float f5 = 0.3F * f2;
    float f6 = paramFloat1 * f1;
    float f7 = (paramFloat2 > 0.0F) ? (paramFloat2 * f1 * f1) : (paramFloat2 - paramFloat2 * (1.0F - f1) * (1.0F - f1));
    float f8 = paramFloat3 * f1;
    paramVertexConsumer.m_85982_(paramMatrix4f, f6 - paramFloat6, f7 + paramFloat5, f8 + paramFloat7).m_85950_(f3, f4, f5, 1.0F).m_85969_(k).m_5752_();
    paramVertexConsumer.m_85982_(paramMatrix4f, f6 + paramFloat6, f7 + paramFloat4 - paramFloat5, f8 - paramFloat7).m_85950_(f3, f4, f5, 1.0F).m_85969_(k).m_5752_();
  }
  
  @Inject(method = {"render(Lnet/minecraft/world/entity/decoration/LeashFenceKnotEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"}, at = {@At("HEAD")})
  private void renderMix(LeashFenceKnotEntity paramLeashFenceKnotEntity, float paramFloat1, float paramFloat2, PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, int paramInt, CallbackInfo paramCallbackInfo) {
    Minecraft minecraft = Minecraft.m_91087_();
    if (minecraft.f_91066_.m_92176_().m_90612_()) {
      LocalPlayer localPlayer = minecraft.f_91074_;
      if (localPlayer instanceof aw) {
        aw aw = (aw)localPlayer;
        Entity entity = aw.abduction$getLeashHolder();
        if (entity == paramLeashFenceKnotEntity)
          abduction$renderLeash(paramLeashFenceKnotEntity, paramFloat2, paramPoseStack, paramMultiBufferSource, (Entity)minecraft.f_91074_, false); 
      } 
    } 
  }
  
  @Unique
  private <E extends Entity> void abduction$renderLeash(LeashFenceKnotEntity paramLeashFenceKnotEntity, float paramFloat, PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, Entity paramEntity, boolean paramBoolean) {
    paramPoseStack.m_85836_();
    Vec3 vec31 = paramEntity.m_7398_(paramFloat);
    double d1 = (Mth.m_14179_(paramFloat, 0.0F, 0.0F) * 0.017453292F) + 1.5707963267948966D;
    Vec3 vec32 = paramBoolean ? new Vec3(paramLeashFenceKnotEntity.m_20205_() * 0.35D, (paramLeashFenceKnotEntity.m_20206_() / 2.0F), (paramLeashFenceKnotEntity.m_20205_() * 0.4F)) : paramLeashFenceKnotEntity.m_7939_();
    double d2 = Math.cos(d1) * vec32.f_82481_ + Math.sin(d1) * vec32.f_82479_;
    double d3 = Math.sin(d1) * vec32.f_82481_ - Math.cos(d1) * vec32.f_82479_;
    double d4 = Mth.m_14139_(paramFloat, paramLeashFenceKnotEntity.f_19854_, paramLeashFenceKnotEntity.m_20185_()) + d2;
    double d5 = Mth.m_14139_(paramFloat, paramLeashFenceKnotEntity.f_19855_, paramLeashFenceKnotEntity.m_20186_()) + vec32.f_82480_;
    double d6 = Mth.m_14139_(paramFloat, paramLeashFenceKnotEntity.f_19856_, paramLeashFenceKnotEntity.m_20189_()) + d3;
    paramPoseStack.m_85837_(d2, vec32.f_82480_, d3);
    float f1 = (float)(vec31.f_82479_ - d4);
    float f2 = (float)(vec31.f_82480_ - d5);
    float f3 = (float)(vec31.f_82481_ - d6);
    float f4 = 0.025F;
    VertexConsumer vertexConsumer = paramMultiBufferSource.m_6299_(RenderType.m_110475_());
    Matrix4f matrix4f = paramPoseStack.m_85850_().m_85861_();
    float f5 = Mth.m_14195_(f1 * f1 + f3 * f3) * 0.025F / 2.0F;
    float f6 = f3 * f5;
    float f7 = f1 * f5;
    BlockPos blockPos1 = new BlockPos(paramLeashFenceKnotEntity.m_20299_(paramFloat));
    BlockPos blockPos2 = new BlockPos(paramEntity.m_20299_(paramFloat));
    int i = m_6086_((Entity)paramLeashFenceKnotEntity, blockPos1);
    int j = m_6086_((Entity)paramLeashFenceKnotEntity, blockPos2);
    int k = paramLeashFenceKnotEntity.f_19853_.m_45517_(LightLayer.SKY, blockPos1);
    int m = paramLeashFenceKnotEntity.f_19853_.m_45517_(LightLayer.SKY, blockPos2);
    byte b;
    for (b = 0; b <= 24; b++)
      abduction$addVertexPair(vertexConsumer, matrix4f, f1, f2, f3, i, j, k, m, 0.025F, 0.025F, f6, f7, b, false); 
    for (b = 24; b >= 0; b--)
      abduction$addVertexPair(vertexConsumer, matrix4f, f1, f2, f3, i, j, k, m, 0.025F, 0.0F, f6, f7, b, true); 
    paramPoseStack.m_85849_();
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\leash\LeashKnotRendererMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */