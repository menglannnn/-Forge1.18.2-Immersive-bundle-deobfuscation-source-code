package abductionmod.content;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public class a extends CreativeModeTab {
  public a() {
    super("am");
  }
  
  public boolean hasSearchBar() {
    return true;
  }
  
  @NotNull
  public ItemStack m_6976_() {
    return new ItemStack((ItemLike)aJ.HANDCUFFS.get());
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\a.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */