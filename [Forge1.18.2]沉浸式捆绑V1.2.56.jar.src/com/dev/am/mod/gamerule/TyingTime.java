package com.dev.am.mod.gamerule;

import net.minecraft.world.level.GameRules;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = "am", bus = Mod.EventBusSubscriber.Bus.MOD)
public class TyingTime {
  public static final String ID = "." + TyingTime.class.getSimpleName();
  
  public static final int DEFAULT_VALUE = 5;
  
  public static final GameRules.Category CATEGORY = GameRules.Category.PLAYER;
  
  @SubscribeEvent
  public static void commonSetup(FMLCommonSetupEvent paramFMLCommonSetupEvent) {
    Gamerules.GAMERULE_REGISTRY.registerGamerule(ID, CATEGORY, 5);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mod\gamerule\TyingTime.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */