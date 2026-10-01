package com.dev.am.immersive_restraints.mixin;

import com.dev.am.immersive_restraints.aC;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({Inventory.class})
public abstract class InventoryMix {
  @Redirect(method = {"dropAll"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z"))
  private boolean isEmpty(ItemStack paramItemStack) {
    if (!paramItemStack.isEmpty()) {
      Item item = paramItemStack.getItem();
      if (item instanceof aC) {
        aC aC = (aC)item;
        return aC.properties.isNoDropOnDeath(paramItemStack);
      } 
    } 
    return paramItemStack.isEmpty();
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\InventoryMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */