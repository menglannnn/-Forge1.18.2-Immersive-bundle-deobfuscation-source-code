package abductionmod.content;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;

public class bq {
  public static ItemStack icon(int paramInt) {
    ItemStack itemStack = new ItemStack((ItemLike)Items.f_42616_);
    CompoundTag compoundTag = new CompoundTag();
    compoundTag.m_128405_("CustomModelData", paramInt);
    itemStack.m_41751_(compoundTag);
    return itemStack;
  }
  
  public static ItemStack icon(Item paramItem) {
    return icon(paramItem, 0, null);
  }
  
  public static ItemStack icon(Item paramItem, Enchantment paramEnchantment) {
    return icon(paramItem, 0, paramEnchantment);
  }
  
  public static ItemStack icon(Item paramItem, int paramInt) {
    return icon(paramItem, paramInt, null);
  }
  
  public static ItemStack icon(Item paramItem, int paramInt, Enchantment paramEnchantment) {
    ItemStack itemStack = new ItemStack((ItemLike)paramItem);
    if (paramEnchantment != null)
      itemStack.m_41663_(paramEnchantment, 5); 
    return itemStack;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bq.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */