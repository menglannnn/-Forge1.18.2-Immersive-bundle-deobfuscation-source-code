package abductionmod.content;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class aq extends LootTableProvider {
  private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> loot_tables = (List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>>)ImmutableList.of(Pair.of(as::new, LootContextParamSets.f_81421_), Pair.of(at::new, LootContextParamSets.f_81411_));
  
  public aq(DataGenerator paramDataGenerator) {
    super(paramDataGenerator);
  }
  
  protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
    return this.loot_tables;
  }
  
  protected void validate(Map<ResourceLocation, LootTable> paramMap, ValidationContext paramValidationContext) {
    paramMap.forEach((paramResourceLocation, paramLootTable) -> LootTables.m_79202_(paramValidationContext, paramResourceLocation, paramLootTable));
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\aq.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */