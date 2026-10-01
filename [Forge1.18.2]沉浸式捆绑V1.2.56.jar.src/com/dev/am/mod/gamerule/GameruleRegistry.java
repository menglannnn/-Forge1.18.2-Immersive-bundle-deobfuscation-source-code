package com.dev.am.mod.gamerule;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "am", bus = Mod.EventBusSubscriber.Bus.MOD)
public class GameruleRegistry {
  public static Map<String, GameRules.Key<?>> gamerules;
  
  public static Map<String, RuleDataType> gameruleDataTypes;
  
  public GameruleRegistry() {
    gamerules = new HashMap<>();
    gameruleDataTypes = new HashMap<>();
  }
  
  public static boolean isGameruleEnabled(Level paramLevel, String paramString) {
    return (paramLevel.f_46443_ && Gamerules.gameruleValuesClient.containsKey(paramString)) ? ((((Integer)Gamerules.gameruleValuesClient.get(paramString)).intValue() != 0)) : ((gameruleDataTypes.get(paramString) != RuleDataType.BOOLEAN) ? false : paramLevel.m_46469_().m_46207_(gamerules.get(paramString)));
  }
  
  public static Integer getGameruleValue(Level paramLevel, String paramString) {
    return (paramLevel.f_46443_ && Gamerules.gameruleValuesClient.containsKey(paramString)) ? Gamerules.gameruleValuesClient.get(paramString) : ((gameruleDataTypes.get(paramString) != RuleDataType.INTEGER) ? Integer.valueOf(0) : Integer.valueOf(paramLevel.m_46469_().m_46215_(gamerules.get(paramString))));
  }
  
  public void registerGamerule(String paramString, GameRules.Category paramCategory, boolean paramBoolean) {
    GameRules.Key<?> key = GameRules.m_46189_(paramString, paramCategory, GameRules.BooleanValue.m_46250_(paramBoolean));
    gamerules.put(paramString, key);
    gameruleDataTypes.put(paramString, RuleDataType.BOOLEAN);
  }
  
  public void registerGamerule(String paramString, GameRules.Category paramCategory, int paramInt) {
    GameRules.Key<?> key = GameRules.m_46189_(paramString, paramCategory, GameRules.IntegerValue.m_46312_(paramInt));
    gamerules.put(paramString, key);
    gameruleDataTypes.put(paramString, RuleDataType.INTEGER);
  }
  
  public enum RuleDataType {
    BOOLEAN, INTEGER;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mod\gamerule\GameruleRegistry.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */