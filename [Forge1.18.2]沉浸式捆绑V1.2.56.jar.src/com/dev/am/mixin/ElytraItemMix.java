package com.dev.am.mixin;

import abductionmod.content.bm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {ElytraItem.class}, remap = false)
public abstract class ElytraItemMix {
  @Inject(method = {"canElytraFly"}, at = {@At("HEAD")}, cancellable = true)
  private void canElytraFly(ItemStack paramItemStack, LivingEntity paramLivingEntity, CallbackInfoReturnable<Boolean> paramCallbackInfoReturnable) {
    if (bm.isTied(paramLivingEntity))
      paramCallbackInfoReturnable.setReturnValue(Boolean.valueOf(false)); 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\ElytraItemMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */