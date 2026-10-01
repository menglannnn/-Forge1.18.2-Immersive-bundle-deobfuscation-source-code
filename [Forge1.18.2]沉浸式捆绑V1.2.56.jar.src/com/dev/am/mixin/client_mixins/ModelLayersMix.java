package com.dev.am.mixin.client_mixins;

import abductionmod.content.v;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ModelLayers.class})
public abstract class ModelLayersMix {
  @Shadow
  private static ModelLayerLocation m_171293_(String paramString) {
    return null;
  }
  
  @Inject(method = {"<clinit>"}, at = {@At("TAIL")})
  private static void clinitMix(CallbackInfo paramCallbackInfo) {
    v.BONDAGE_INNER_ARMOR = m_171293_("bondage_inner");
    v.BONDAGE_INNER_ARMOR_SLIM = m_171293_("bondage_inner_slim");
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\ModelLayersMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */