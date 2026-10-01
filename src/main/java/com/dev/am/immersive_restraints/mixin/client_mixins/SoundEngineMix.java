package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.config.EarplugsMuteAudio;
import com.dev.am.immersive_restraints.config.GameruleRegistry;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({SoundEngine.class})
public abstract class SoundEngineMix {
  @Inject(method = {"play"}, at = {@At("HEAD")})
  private void playMix(SoundInstance paramSoundInstance, CallbackInfo paramCallbackInfo) {
    Minecraft minecraft = Minecraft.getInstance();
    if (minecraft.player != null && bm.isDeafened((LivingEntity)minecraft.player) && GameruleRegistry.isGameruleEnabled((Level)Objects.requireNonNull(minecraft.level), EarplugsMuteAudio.ID) && paramSoundInstance instanceof AbstractSoundInstance) {
      AbstractSoundInstance abstractSoundInstance = (AbstractSoundInstance)paramSoundInstance;
      abstractSoundInstance.volume /= 55.0F;
      abstractSoundInstance.pitch /= 1.5F;
    } 
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\SoundEngineMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */