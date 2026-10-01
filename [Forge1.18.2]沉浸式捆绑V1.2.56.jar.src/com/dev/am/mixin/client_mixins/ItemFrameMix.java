package com.dev.am.mixin.client_mixins;

import abductionmod.content.br;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ItemFrame.class})
public abstract class ItemFrameMix extends HangingEntity {
  protected ItemFrameMix(EntityType<? extends HangingEntity> paramEntityType, Level paramLevel) {
    super(paramEntityType, paramLevel);
  }
  
  @Inject(method = {"getItem"}, at = {@At("RETURN")}, cancellable = true)
  private void getItem(CallbackInfoReturnable<ItemStack> paramCallbackInfoReturnable) {
    if ((m_183503_()).f_46443_) {
      ItemStack itemStack1 = (ItemStack)paramCallbackInfoReturnable.getReturnValue();
      ItemStack itemStack2 = br.getDynamicIconStack(itemStack1);
      if (itemStack2 != null)
        paramCallbackInfoReturnable.setReturnValue(itemStack2); 
      return;
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\ItemFrameMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */