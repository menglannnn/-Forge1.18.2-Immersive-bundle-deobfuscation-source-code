package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.v;
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
  private static ModelLayerLocation createLocation(String paramString) {
    return null;
  }
  
  @Inject(method = {"<clinit>"}, at = {@At("TAIL")})
  private static void clinitMix(CallbackInfo paramCallbackInfo) {
    v.BONDAGE_INNER_ARMOR = createLocation("bondage_inner");
    v.BONDAGE_INNER_ARMOR_SLIM = createLocation("bondage_inner_slim");
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\ModelLayersMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */