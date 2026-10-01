package abductionmod.content;

import java.util.function.Function;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class an extends BlockStateProvider {
  public an(DataGenerator paramDataGenerator, ExistingFileHelper paramExistingFileHelper) {
    super(paramDataGenerator, "am", paramExistingFileHelper);
  }
  
  protected void registerStatesAndModels() {
    for (RegistryObject<Block> registryObject : o.blocks.values()) {
      if (registryObject.get() instanceof p)
        simpleBlockWithItem(registryObject); 
      if (registryObject.get() instanceof u)
        stairsBlockWithItem(registryObject); 
      if (registryObject.get() instanceof t)
        slabBlockWithItem(registryObject); 
      if (registryObject.get() instanceof q)
        doorBlockWithItem(registryObject); 
    } 
  }
  
  private void simpleBlockWithItem(RegistryObject<Block> paramRegistryObject) {
    String str = getBlockName(paramRegistryObject);
    simpleBlock((Block)paramRegistryObject.get());
    itemModels().withExistingParent(str, new ResourceLocation("am", "block/" + str));
  }
  
  private void stairsBlockWithItem(RegistryObject<Block> paramRegistryObject) {
    String str = getBlockName(paramRegistryObject);
    StairBlock stairBlock = (StairBlock)paramRegistryObject.get();
    stairsBlock(stairBlock, blockTexture((Block)Registry.f_122824_.m_7745_(new ResourceLocation("am", str.replace("_stairs", "")))));
    itemModels().withExistingParent(str, new ResourceLocation("am", "block/" + str));
  }
  
  private void doorBlockWithItem(RegistryObject<Block> paramRegistryObject) {
    String str = getBlockName(paramRegistryObject);
    q q = (q)paramRegistryObject.get();
    ResourceLocation resourceLocation = blockTexture((Block)Registry.f_122824_.m_7745_(new ResourceLocation("am", str.replace("_door", ""))));
    doorBlock(q, resourceLocation, resourceLocation);
  }
  
  private void slabBlockWithItem(RegistryObject<Block> paramRegistryObject) {
    String str = getBlockName(paramRegistryObject);
    SlabBlock slabBlock = (SlabBlock)paramRegistryObject.get();
    ResourceLocation resourceLocation = blockTexture((Block)Registry.f_122824_.m_7745_(new ResourceLocation("am", str.replace("_slab", ""))));
    slabBlock(slabBlock, resourceLocation, resourceLocation);
    itemModels().withExistingParent(str, new ResourceLocation("am", "block/" + str));
  }
  
  private void horizontalBlockWithItem(RegistryObject<Block> paramRegistryObject) {
    String str = getBlockName(paramRegistryObject);
    horizontalBlock((Block)paramRegistryObject.get(), (ModelFile)models().getExistingFile(new ResourceLocation("am", "block/" + str)));
    itemModels().withExistingParent(str, new ResourceLocation("am", "block/" + str));
  }
  
  private String getBlockName(RegistryObject<Block> paramRegistryObject) {
    return paramRegistryObject.getId().m_135815_();
  }
  
  public ModelFile flowerPotCross(String paramString) {
    return (ModelFile)models().withExistingParent(paramString, "flower_pot_cross");
  }
  
  public void makeCrop(CropBlock paramCropBlock, String paramString1, String paramString2) {
    Function function = paramBlockState -> states(paramBlockState, paramCropBlock, paramString1, paramString2);
    getVariantBuilder((Block)paramCropBlock).forAllStates(function);
  }
  
  private ConfiguredModel[] states(BlockState paramBlockState, CropBlock paramCropBlock, String paramString1, String paramString2) {
    ConfiguredModel[] arrayOfConfiguredModel = new ConfiguredModel[1];
    arrayOfConfiguredModel[0] = new ConfiguredModel((ModelFile)models().crop(paramString1 + paramString1, new ResourceLocation("am", "block/" + paramString2 + paramBlockState.m_61143_((Property)paramCropBlock.m_7959_()))));
    return arrayOfConfiguredModel;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\an.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */