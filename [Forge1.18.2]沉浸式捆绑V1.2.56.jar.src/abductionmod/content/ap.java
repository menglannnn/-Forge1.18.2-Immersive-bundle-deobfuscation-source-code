package abductionmod.content;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.text.WordUtils;

public class ap extends LanguageProvider {
  public ap(DataGenerator paramDataGenerator) {
    super(paramDataGenerator, "am_gen", "en_us");
  }
  
  protected void addTranslations() {
    for (RegistryObject<Block> registryObject : o.blocks.values()) {
      if (registryObject.get() instanceof p || registryObject.get() instanceof u || registryObject.get() instanceof t || registryObject.get() instanceof q) {
        String str = WordUtils.capitalize(registryObject.getId().m_135815_().replace("_", " ")).replace("Block ", "");
        add((Block)registryObject.get(), str);
      } 
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\ap.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */