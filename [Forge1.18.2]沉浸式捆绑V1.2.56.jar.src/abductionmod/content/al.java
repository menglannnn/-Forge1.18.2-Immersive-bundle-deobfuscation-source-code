package abductionmod.content;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = "am", bus = Mod.EventBusSubscriber.Bus.MOD)
public class al {
  @SubscribeEvent
  public static void gatherData(GatherDataEvent paramGatherDataEvent) {
    DataGenerator dataGenerator = paramGatherDataEvent.getGenerator();
    ExistingFileHelper existingFileHelper = paramGatherDataEvent.getExistingFileHelper();
    dataGenerator.m_123914_((DataProvider)new aq(dataGenerator));
    dataGenerator.m_123914_((DataProvider)new an(dataGenerator, existingFileHelper));
    dataGenerator.m_123914_((DataProvider)new ao(dataGenerator, existingFileHelper));
    dataGenerator.m_123914_((DataProvider)new ap(dataGenerator));
    dataGenerator.m_123914_((DataProvider)new ar(dataGenerator));
    dataGenerator.m_123917_();
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\al.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */