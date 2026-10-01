package abductionmod.content;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelPart;
import noppes.mpm.ModelPartConfig;
import noppes.mpm.MorePlayerModels;
import noppes.mpm.client.ClientProxy;
import noppes.mpm.constants.EnumParts;

public class ak {
  public static ModelPartConfig getMpmconfig(ModelPart paramModelPart) {
    if (ClientProxy.data == null)
      return null; 
    if (v.modelBeingRendered != null) {
      if (paramModelPart == v.modelBeingRendered.f_102810_)
        return ClientProxy.data.getPartConfig(EnumParts.BODY); 
      if (paramModelPart == v.modelBeingRendered.f_102809_ || paramModelPart == v.modelBeingRendered.f_102808_)
        return ClientProxy.data.getPartConfig(EnumParts.HEAD); 
      if (paramModelPart == v.modelBeingRendered.f_102812_)
        return ClientProxy.data.getPartConfig(EnumParts.ARM_LEFT); 
      if (paramModelPart == v.modelBeingRendered.f_102811_)
        return ClientProxy.data.getPartConfig(EnumParts.ARM_RIGHT); 
      if (paramModelPart == v.modelBeingRendered.f_102814_)
        return ClientProxy.data.getPartConfig(EnumParts.LEG_LEFT); 
      if (paramModelPart == v.modelBeingRendered.f_102813_)
        return ClientProxy.data.getPartConfig(EnumParts.LEG_RIGHT); 
    } 
    return null;
  }
  
  public static void translate(PoseStack paramPoseStack, ModelPart paramModelPart) {
    if (!MorePlayerModels.Compatibility) {
      ModelPartConfig modelPartConfig = getMpmconfig(paramModelPart);
      if (modelPartConfig != null)
        paramPoseStack.m_85837_(modelPartConfig.transX, modelPartConfig.transY, modelPartConfig.transZ); 
    } 
  }
  
  public static void scale(PoseStack paramPoseStack, ModelPart paramModelPart) {
    if (!MorePlayerModels.Compatibility) {
      ModelPartConfig modelPartConfig = getMpmconfig(paramModelPart);
      if (modelPartConfig != null)
        paramPoseStack.m_85841_(modelPartConfig.scaleX, modelPartConfig.scaleY, modelPartConfig.scaleZ); 
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\ak.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */