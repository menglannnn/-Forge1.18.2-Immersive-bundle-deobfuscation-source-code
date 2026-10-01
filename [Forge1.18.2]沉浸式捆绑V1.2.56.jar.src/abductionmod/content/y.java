package abductionmod.content;

import java.util.function.Supplier;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@EventBusSubscriber(modid = "am", bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class y {
  @SubscribeEvent
  public static void onClientSetup(FMLClientSetupEvent paramFMLClientSetupEvent) {
    if (!bm.isDevEnvironment())
      A.discordStartGameCallback(); 
    o.blocks.values().forEach(paramRegistryObject -> ItemBlockRenderTypes.setRenderLayer((Block)paramRegistryObject.get(), RenderType.m_110457_()));
    B.init();
  }
  
  @SubscribeEvent
  public static void registerArmorRenderers(EntityRenderersEvent.AddLayers paramAddLayers) {
    GeoArmorRenderer.registerArmorRenderer(aP.class, G::new);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\y.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */