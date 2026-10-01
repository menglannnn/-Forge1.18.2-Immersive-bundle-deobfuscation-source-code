package abductionmod.content;

import java.util.function.BiConsumer;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

public class at extends ChestLoot {
  private static final ResourceLocation CUSTOM_CHEST_LOOT = new ResourceLocation("am", "chests/custom_chest_loot");
  
  public void accept(BiConsumer<ResourceLocation, LootTable.Builder> paramBiConsumer) {}
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\at.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */