package abductionmod.content;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class o {
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "am");
  
  public static HashMap<String, RegistryObject<Block>> blocks = new LinkedHashMap<>();
  
  private static <T extends Block> RegistryObject<T> registerBlock(String paramString, Supplier<T> paramSupplier) {
    return registerBlock(paramString, paramSupplier, paramBlock -> new BlockItem(paramBlock, (new Item.Properties()).m_41491_(aJ.ABDUCTION_ITEM_GROUP)));
  }
  
  private static <T extends Block> RegistryObject<T> registerBlock(String paramString, Supplier<T> paramSupplier, Function<Block, BlockItem> paramFunction) {
    RegistryObject<Block> registryObject = BLOCKS.register(paramString, paramSupplier);
    blocks.put(paramString, registryObject);
    registerBlockItem(paramString, registryObject, paramFunction);
    return (RegistryObject)registryObject;
  }
  
  private static <T extends Block> RegistryObject<Item> registerBlockItem(String paramString, RegistryObject<T> paramRegistryObject, Function<Block, BlockItem> paramFunction) {
    return aJ.ITEMS.register(paramString, () -> (Item)paramFunction.apply((Block)paramRegistryObject.get()));
  }
  
  static {
    BlockBehaviour.Properties properties = BlockBehaviour.Properties.m_60926_((BlockBehaviour)Blocks.f_50109_).m_60978_(1.5F);
    for (DyeColor dyeColor : DyeColor.values())
      registerBlock("" + dyeColor + "_padded_block", () -> new p(paramProperties)); 
    for (DyeColor dyeColor : DyeColor.values()) {
      String str = "" + dyeColor + "_padded_block";
      registerBlock(str + "_stairs", () -> new u((), paramProperties));
    } 
    for (DyeColor dyeColor : DyeColor.values())
      registerBlock("" + dyeColor + "_padded_block_slab", () -> new t(paramProperties)); 
    for (DyeColor dyeColor : DyeColor.values())
      registerBlock("" + dyeColor + "_padded_block_door", () -> new q(paramProperties.m_60978_(3.0F)), paramBlock -> new aK(paramBlock, (new Item.Properties()).m_41491_(aJ.ABDUCTION_ITEM_GROUP))); 
    for (DyeColor dyeColor : DyeColor.values())
      registerBlock("" + dyeColor + "_padded_pane", () -> new r(paramProperties.m_60955_())); 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\o.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */