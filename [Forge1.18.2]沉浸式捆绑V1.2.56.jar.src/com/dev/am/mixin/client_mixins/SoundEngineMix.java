package com.dev.am.mixin.client_mixins;

import abductionmod.content.bm;
import com.dev.am.mod.gamerule.EarplugsMuteAudio;
import com.dev.am.mod.gamerule.GameruleRegistry;
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
    Minecraft minecraft = Minecraft.m_91087_();
    if (minecraft.f_91074_ != null && bm.isDeafened((LivingEntity)minecraft.f_91074_) && GameruleRegistry.isGameruleEnabled((Level)Objects.requireNonNull(minecraft.f_91073_), EarplugsMuteAudio.ID) && paramSoundInstance instanceof AbstractSoundInstance) {
      AbstractSoundInstance abstractSoundInstance = (AbstractSoundInstance)paramSoundInstance;
      abstractSoundInstance.f_119573_ /= 55.0F;
      abstractSoundInstance.f_119574_ /= 1.5F;
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\SoundEngineMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */