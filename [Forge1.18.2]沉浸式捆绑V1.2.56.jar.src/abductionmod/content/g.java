package abductionmod.content;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class g {
  public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "am");
  
  public static final RegistryObject<SoundEvent> COLLAR_KEY_CLOSE = registerSoundEvent("collar_key_close");
  
  public static final RegistryObject<SoundEvent> COLLAR_KEY_OPEN = registerSoundEvent("collar_key_open");
  
  public static final RegistryObject<SoundEvent> COLLAR_PUT = registerSoundEvent("collar_put");
  
  public static final RegistryObject<SoundEvent> ELECTRIC_SHOCK = registerSoundEvent("electric_shock");
  
  public static final RegistryObject<SoundEvent> SHOCKER_ACTIVATED = registerSoundEvent("shocker_activated");
  
  public static final RegistryObject<SoundEvent> STICKY = registerSoundEvent("sticky");
  
  public static final RegistryObject<SoundEvent> DUCT_TAPE = registerSoundEvent("duct_tape");
  
  public static final RegistryObject<SoundEvent> SLAP = registerSoundEvent("slap");
  
  public static final RegistryObject<SoundEvent> WHIP = registerSoundEvent("whip");
  
  public static final RegistryObject<SoundEvent> CHAIN = registerSoundEvent("chain");
  
  public static final RegistryObject<SoundEvent> SLIME = registerSoundEvent("slime");
  
  public static final RegistryObject<SoundEvent> VINE = registerSoundEvent("vine");
  
  private static RegistryObject<SoundEvent> registerSoundEvent(String paramString) {
    return SOUND_EVENTS.register(paramString, () -> new SoundEvent(new ResourceLocation("am", paramString)));
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\g.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */