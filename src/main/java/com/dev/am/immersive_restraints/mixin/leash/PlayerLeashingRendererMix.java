package com.dev.am.immersive_restraints.mixin.leash;

import com.dev.am.immersive_restraints.aw;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerRenderer.class})
public abstract class PlayerLeashingRendererMix extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
  public PlayerLeashingRendererMix(EntityRendererProvider.Context paramContext, PlayerModel<AbstractClientPlayer> paramPlayerModel, float paramFloat) {
    super(paramContext, (EntityModel)paramPlayerModel, paramFloat);
  }
  
  private static void addVertexPair(VertexConsumer paramVertexConsumer, Matrix4f paramMatrix4f, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, int paramInt5, boolean paramBoolean) {
    float f1 = paramInt5 / 24.0F;
    int i = (int)Mth.lerp(f1, paramInt1, paramInt2);
    int j = (int)Mth.lerp(f1, paramInt3, paramInt4);
    int k = LightTexture.pack(i, j);
    float f2 = (paramInt5 % 2 == (paramBoolean ? 1 : 0)) ? 0.7F : 1.0F;
    float f3 = 0.5F * f2;
    float f4 = 0.4F * f2;
    float f5 = 0.3F * f2;
    float f6 = paramFloat1 * f1;
    float f7 = (paramFloat2 > 0.0F) ? (paramFloat2 * f1 * f1) : (paramFloat2 - paramFloat2 * (1.0F - f1) * (1.0F - f1));
    float f8 = paramFloat3 * f1;
    paramVertexConsumer.vertex(paramMatrix4f, f6 - paramFloat6, f7 + paramFloat5, f8 + paramFloat7).color(f3, f4, f5, 1.0F).uv2(k).endVertex();
    paramVertexConsumer.vertex(paramMatrix4f, f6 + paramFloat6, f7 + paramFloat4 - paramFloat5, f8 - paramFloat7).color(f3, f4, f5, 1.0F).uv2(k).endVertex();
  }
  
  @Inject(method = {"render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"}, at = {@At("HEAD")})
  private void renderMix(AbstractClientPlayer paramAbstractClientPlayer, float paramFloat1, float paramFloat2, PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, int paramInt, CallbackInfo paramCallbackInfo) {
    Entity entity = ((aw)paramAbstractClientPlayer).abduction$getLeashHolder();
    if (entity != null) {
      renderLeash(paramAbstractClientPlayer, paramFloat2, paramPoseStack, paramMultiBufferSource, entity, false);
      return;
    } 
    Minecraft minecraft = Minecraft.getInstance();
    if (minecraft.options.getCameraType().isFirstPerson()) {
      LocalPlayer localPlayer = minecraft.player;
      if (localPlayer instanceof aw) {
        aw aw = (aw)localPlayer;
        Entity entity1 = aw.abduction$getLeashHolder();
        if (entity1 == paramAbstractClientPlayer)
          renderLeash(paramAbstractClientPlayer, paramFloat2, paramPoseStack, paramMultiBufferSource, (Entity)minecraft.player, true); 
      } 
    } 
  }
  
  private <E extends Entity> void renderLeash(AbstractClientPlayer paramAbstractClientPlayer, float paramFloat, PoseStack paramPoseStack, MultiBufferSource paramMultiBufferSource, Entity paramEntity, boolean paramBoolean) {
    paramPoseStack.pushPose();
    Vec3 vec31 = paramEntity.getPosition(paramFloat);
    double d1 = (Mth.lerp(paramFloat, paramAbstractClientPlayer.xRotO, paramAbstractClientPlayer.xRot) * 0.017453292F) + 1.5707963267948966D;
    Vec3 vec32 = paramBoolean ? new Vec3(paramAbstractClientPlayer.getBbWidth() * 0.35D, (paramAbstractClientPlayer.getBbHeight() / 2.0F), (paramAbstractClientPlayer.getBbWidth() * 0.4F)) : paramAbstractClientPlayer.getLeashOffset();
    double d2 = Math.cos(d1) * vec32.z + Math.sin(d1) * vec32.x;
    double d3 = Math.sin(d1) * vec32.z - Math.cos(d1) * vec32.x;
    double d4 = Mth.lerp(paramFloat, paramAbstractClientPlayer.xOld, paramAbstractClientPlayer.getX()) + d2;
    double d5 = Mth.lerp(paramFloat, paramAbstractClientPlayer.yOld, paramAbstractClientPlayer.getY()) + vec32.y;
    double d6 = Mth.lerp(paramFloat, paramAbstractClientPlayer.zOld, paramAbstractClientPlayer.getZ()) + d3;
    paramPoseStack.translate(d2, vec32.y, d3);
    float f1 = (float)(vec31.x - d4);
    float f2 = (float)(vec31.y - d5);
    float f3 = (float)(vec31.z - d6);
    float f4 = 0.025F;
    VertexConsumer vertexConsumer = paramMultiBufferSource.getBuffer(RenderType.leash());
    Matrix4f matrix4f = paramPoseStack.last().pose();
    float f5 = Mth.sqrt(f1 * f1 + f3 * f3) * 0.025F / 2.0F;
    float f6 = f3 * f5;
    float f7 = f1 * f5;
    BlockPos blockPos1 = new BlockPos(paramAbstractClientPlayer.getEyePosition(paramFloat));
    BlockPos blockPos2 = new BlockPos(paramEntity.getEyePosition(paramFloat));
    int i = getBlockLightLevel((Entity)paramAbstractClientPlayer, blockPos1);
    int j = getBlockLightLevel((Entity)paramAbstractClientPlayer, blockPos2);
    int k = paramAbstractClientPlayer.level.getBrightness(LightLayer.SKY, blockPos1);
    int m = paramAbstractClientPlayer.level.getBrightness(LightLayer.SKY, blockPos2);
    byte b;
    for (b = 0; b <= 24; b++)
      addVertexPair(vertexConsumer, matrix4f, f1, f2, f3, i, j, k, m, 0.025F, 0.025F, f6, f7, b, false); 
    for (b = 24; b >= 0; b--)
      addVertexPair(vertexConsumer, matrix4f, f1, f2, f3, i, j, k, m, 0.025F, 0.0F, f6, f7, b, true); 
    paramPoseStack.popPose();
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\leash\PlayerLeashingRendererMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */