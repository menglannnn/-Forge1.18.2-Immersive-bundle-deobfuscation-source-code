package com.dev.am;

import abductionmod.content.P;
import abductionmod.content.aJ;
import abductionmod.content.aZ;
import abductionmod.content.c;
import abductionmod.content.d;
import abductionmod.content.f;
import abductionmod.content.g;
import abductionmod.content.o;
import com.mojang.logging.LogUtils;
import java.util.ArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod("am")
public class Abduction {
  public static final String MOD_ID = "am";
  
  public static final Logger LOGGER = LogUtils.getLogger();
  
  public static ArrayList<String> slotNames = new ArrayList<>();
  
  public Abduction() {
    GeckoLib.initialize();
    IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    iEventBus.addListener(this::commonSetup);
    iEventBus.addListener(this::clientSetup);
    iEventBus.addListener(this::enqueueIMC);
    g.SOUND_EVENTS.register(iEventBus);
    o.BLOCKS.register(iEventBus);
    aJ.ITEMS.register(iEventBus);
    ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, (IConfigSpec)c.SPEC, "am.toml");
    MinecraftForge.EVENT_BUS.register(this);
    MinecraftForge.EVENT_BUS.addListener(P::registerCommands);
    MinecraftForge.EVENT_BUS.register(new f());
    MinecraftForge.EVENT_BUS.register(new d());
    aZ.registerPackets();
  }
  
  private static void createCurioSlot(String paramString1, String paramString2) {
    slotNames.add(paramString1);
    InterModComms.sendTo("curios", "register_type", () -> (new SlotTypeMessage.Builder(paramString1)).size(1).icon(new ResourceLocation(paramString2)).build());
  }
  
  private void commonSetup(FMLCommonSetupEvent paramFMLCommonSetupEvent) {}
  
  private void clientSetup(FMLClientSetupEvent paramFMLClientSetupEvent) {}
  
  private void enqueueIMC(InterModEnqueueEvent paramInterModEnqueueEvent) {
    createCurioSlot("blindfold", "curios:slot/blindfold");
    createCurioSlot("gag", "curios:slot/gag");
    createCurioSlot("earplugs", "curios:slot/earplugs");
    createCurioSlot("collar", "curios:slot/collar");
    createCurioSlot("hands", "curios:slot/hands");
    createCurioSlot("binds", "curios:slot/binds");
    createCurioSlot("clothes", "curios:slot/clothes");
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\Abduction.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */