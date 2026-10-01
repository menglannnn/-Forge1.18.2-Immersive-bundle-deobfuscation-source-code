package abductionmod.content;

import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface av {
  @Nullable
  ItemStack abduction$getWardrobeItem(String paramString);
  
  @Nonnull
  ItemStack abduction$getCurrentWardrobeItem();
  
  void abduction$saveWardrobeItem(String paramString, @Nonnull ItemStack paramItemStack);
  
  void abduction$setCurrentWardrobeItem(@Nonnull ItemStack paramItemStack);
  
  void abduction$removeWardrobeItem(String paramString);
  
  String[] abduction$getAllWardrobeKeys();
  
  void abduction$copyWardrobe(Player paramPlayer);
  
  HashMap<String, ItemStack> abduction$getMap();
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\av.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */