package abductionmod.content;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ao extends ItemModelProvider {
  public ao(DataGenerator paramDataGenerator, ExistingFileHelper paramExistingFileHelper) {
    super(paramDataGenerator, "am", paramExistingFileHelper);
  }
  
  protected void registerModels() {
    for (RegistryObject<Block> registryObject : o.blocks.values()) {
      if (registryObject.get() instanceof q)
        simpleItem(((Block)registryObject.get()).m_5456_()); 
    } 
  }
  
  private ItemModelBuilder simpleItem(Item paramItem) {
    return (ItemModelBuilder)((ItemModelBuilder)withExistingParent(paramItem.getRegistryName().m_135815_(), new ResourceLocation("item/generated"))).texture("layer0", new ResourceLocation("am", "item/" + paramItem.getRegistryName().m_135815_()));
  }
  
  private ItemModelBuilder handheldItem(Item paramItem) {
    return (ItemModelBuilder)((ItemModelBuilder)withExistingParent(paramItem.getRegistryName().m_135815_(), new ResourceLocation("item/handheld"))).texture("layer0", new ResourceLocation("am", "item/" + paramItem.getRegistryName().m_135815_()));
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\ao.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */