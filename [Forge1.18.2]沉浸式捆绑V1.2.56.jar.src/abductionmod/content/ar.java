package abductionmod.content;

import java.util.function.Consumer;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

public class ar extends RecipeProvider implements IConditionBuilder {
  public ar(DataGenerator paramDataGenerator) {
    super(paramDataGenerator);
  }
  
  protected static void door(Consumer<FinishedRecipe> paramConsumer, ItemLike paramItemLike1, ItemLike paramItemLike2) {
    m_176670_(paramItemLike1, Ingredient.m_43929_(new ItemLike[] { paramItemLike2 })).m_142284_(m_176602_(paramItemLike2), (CriterionTriggerInstance)m_125977_(paramItemLike2)).m_176498_(paramConsumer);
  }
  
  protected void m_176531_(Consumer<FinishedRecipe> paramConsumer) {
    for (RegistryObject<Block> registryObject : o.blocks.values()) {
      if (registryObject.get() instanceof p) {
        String str1 = registryObject.getId().m_135815_();
        String str2 = str1.replace("padded_block", "wool");
        Item item = (Item)Registry.f_122827_.m_7745_(new ResourceLocation(str2));
        if (item instanceof BlockItem) {
          BlockItem blockItem = (BlockItem)item;
          SimpleCookingRecipeBuilder.m_126272_(Ingredient.m_43929_(new ItemLike[] { (ItemLike)item }, ), (ItemLike)registryObject.get(), 0.35F, 200).m_142284_("has_" + str2, (CriterionTriggerInstance)m_125977_((ItemLike)blockItem.m_40614_())).m_176498_(paramConsumer);
        } 
      } 
      if (registryObject.get() instanceof u) {
        String str1 = registryObject.getId().m_135815_();
        String str2 = str1.replace("_stairs", "");
        Item item = ((Block)((RegistryObject)o.blocks.get(str2)).get()).m_5456_();
        if (item instanceof BlockItem) {
          BlockItem blockItem = (BlockItem)item;
          m_176710_((ItemLike)((Block)registryObject.get()).m_5456_(), Ingredient.m_43929_(new ItemLike[] { (ItemLike)item })).m_142284_("has_" + str2, (CriterionTriggerInstance)m_125977_((ItemLike)item)).m_176498_(paramConsumer);
        } 
      } 
      if (registryObject.get() instanceof t) {
        String str1 = registryObject.getId().m_135815_();
        String str2 = str1.replace("_slab", "");
        Item item = ((Block)((RegistryObject)o.blocks.get(str2)).get()).m_5456_();
        if (item instanceof BlockItem) {
          BlockItem blockItem = (BlockItem)item;
          m_176700_(paramConsumer, (ItemLike)registryObject.get(), (ItemLike)blockItem.m_40614_());
        } 
      } 
      if (registryObject.get() instanceof q) {
        String str1 = registryObject.getId().m_135815_();
        String str2 = str1.replace("_door", "");
        Item item = ((Block)((RegistryObject)o.blocks.get(str2)).get()).m_5456_();
        if (item instanceof BlockItem) {
          BlockItem blockItem = (BlockItem)item;
          door(paramConsumer, (ItemLike)registryObject.get(), (ItemLike)blockItem.m_40614_());
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\ar.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */