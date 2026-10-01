package abductionmod.content;

import java.util.Objects;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class as extends BlockLoot {
  protected void addTables() {
    for (RegistryObject<Block> registryObject : o.blocks.values()) {
      if (registryObject.get() instanceof p || registryObject.get() instanceof u || registryObject.get() instanceof t || registryObject.get() instanceof q || registryObject.get() instanceof r)
        m_124288_((Block)registryObject.get()); 
    } 
  }
  
  protected Iterable<Block> getKnownBlocks() {
    Objects.requireNonNull(o.BLOCKS.getEntries().stream().map(RegistryObject::get));
    return o.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\as.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */