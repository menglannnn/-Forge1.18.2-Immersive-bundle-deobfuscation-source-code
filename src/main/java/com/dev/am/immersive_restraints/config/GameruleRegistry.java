package com.dev.am.immersive_restraints.config;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "immersive_restraints", bus = Mod.EventBusSubscriber.Bus.MOD)
public class GameruleRegistry {
  public static Map<String, GameRules.Key<?>> gamerules;
  
  public static Map<String, RuleDataType> gameruleDataTypes;
  
  public GameruleRegistry() {
    gamerules = new HashMap<>();
    gameruleDataTypes = new HashMap<>();
  }
  
  public static boolean isGameruleEnabled(Level paramLevel, String paramString) {
    return (paramLevel.isClientSide && Gamerules.gameruleValuesClient.containsKey(paramString)) ? ((((Integer)Gamerules.gameruleValuesClient.get(paramString)).intValue() != 0)) : ((gameruleDataTypes.get(paramString) != RuleDataType.BOOLEAN) ? false : paramLevel.getGameRules().getBoolean(gamerules.get(paramString)));
  }
  
  public static Integer getGameruleValue(Level paramLevel, String paramString) {
    return (paramLevel.isClientSide && Gamerules.gameruleValuesClient.containsKey(paramString)) ? Gamerules.gameruleValuesClient.get(paramString) : ((gameruleDataTypes.get(paramString) != RuleDataType.INTEGER) ? Integer.valueOf(0) : Integer.valueOf(paramLevel.getGameRules().getInt(gamerules.get(paramString))));
  }
  
  public void registerGamerule(String paramString, GameRules.Category paramCategory, boolean paramBoolean) {
    GameRules.Key<?> key = GameRules.register(paramString, paramCategory, GameRules.BooleanValue.create(paramBoolean));
    gamerules.put(paramString, key);
    gameruleDataTypes.put(paramString, RuleDataType.BOOLEAN);
  }
  
  public void registerGamerule(String paramString, GameRules.Category paramCategory, int paramInt) {
    GameRules.Key<?> key = GameRules.register(paramString, paramCategory, GameRules.IntegerValue.create(paramInt));
    gamerules.put(paramString, key);
    gameruleDataTypes.put(paramString, RuleDataType.INTEGER);
  }
  
  public enum RuleDataType {
    BOOLEAN, INTEGER;
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mod\gamerule\GameruleRegistry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */