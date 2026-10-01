package com.dev.am.immersive_restraints.mixin.client_mixins.compat;

import com.dev.am.immersive_restraints.ak;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ModelPart.class})
public class MpmCompatModelRendererMix {
  @Unique
  private static Boolean abduction$isLoaded = null;
  
  @Unique
  private static boolean abduction$isModLoaded() {
    if (abduction$isLoaded == null)
      abduction$isLoaded = Boolean.valueOf(ModList.get().isLoaded("moreplayermodels")); 
    return abduction$isLoaded.booleanValue();
  }
  
  @Inject(at = {@At("TAIL")}, method = {"translateAndRotate"})
  private void abduction$translateAndRotatePost(PoseStack paramPoseStack, CallbackInfo paramCallbackInfo) {
    if (abduction$isModLoaded())
      ak.scale(paramPoseStack, (ModelPart)this); 
  }
  
  @Inject(at = {@At("HEAD")}, method = {"translateAndRotate"})
  private void abduction$translateAndRotatePre(PoseStack paramPoseStack, CallbackInfo paramCallbackInfo) {
    if (abduction$isModLoaded())
      ak.translate(paramPoseStack, (ModelPart)this); 
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\compat\MpmCompatModelRendererMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */