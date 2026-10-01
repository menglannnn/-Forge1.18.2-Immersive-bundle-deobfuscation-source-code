package com.dev.am.immersive_restraints.config;

import net.minecraft.world.level.GameRules;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = "immersive_restraints", bus = Mod.EventBusSubscriber.Bus.MOD)
public class NorpUntiesPlayersAfter2ndRun {
  public static final String ID = "." + NorpUntiesPlayersAfter2ndRun.class.getSimpleName();
  
  public static final boolean DEFAULT_VALUE = true;
  
  public static final GameRules.Category CATEGORY = GameRules.Category.PLAYER;
  
  @SubscribeEvent
  public static void commonSetup(FMLCommonSetupEvent paramFMLCommonSetupEvent) {
    Gamerules.GAMERULE_REGISTRY.registerGamerule(ID, CATEGORY, true);
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mod\gamerule\NorpUntiesPlayersAfter2ndRun.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */