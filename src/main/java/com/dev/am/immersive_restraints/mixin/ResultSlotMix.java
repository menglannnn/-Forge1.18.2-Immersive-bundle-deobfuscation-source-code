package com.dev.am.immersive_restraints.mixin;

import com.dev.am.immersive_restraints.aJ;
import com.dev.am.immersive_restraints.bl;
import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.q;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ResultSlot.class})
public abstract class ResultSlotMix extends Slot {
  @Shadow
  @Final
  private CraftingContainer container;
  
  public ResultSlotMix(Container paramContainer, int paramInt1, int paramInt2, int paramInt3) {
    super(paramContainer, paramInt1, paramInt2, paramInt3);
  }
  
  @Inject(method = {"remove"}, at = {@At("RETURN")}, cancellable = true)
  private void assembly(int paramInt, CallbackInfoReturnable<ItemStack> paramCallbackInfoReturnable) {
    ItemStack itemStack = (ItemStack)paramCallbackInfoReturnable.getReturnValue();
    assignKeysToBondage(itemStack, (Item)aJ.HANDCUFFS.get(), (Item)aJ.STEEL_KEY.get());
    assignKeysToBondage(itemStack, (Item)aJ.MITTENS.get(), (Item)aJ.STEEL_KEY.get());
    assignKeysToBondage(itemStack, (Item)aJ.COLLAR.get(), (Item)aJ.COLLAR_KEY.get());
    reverseDoor(paramCallbackInfoReturnable, itemStack);
  }
  
  @Unique
  private void reverseDoor(CallbackInfoReturnable<ItemStack> paramCallbackInfoReturnable, ItemStack paramItemStack) {
    if (paramItemStack.getItem() instanceof abductionmod.content.aK) {
      ItemStack itemStack = paramItemStack;
      for (ItemStack itemStack1 : this.container.items) {
        if (itemStack1.getItem() instanceof abductionmod.content.aK) {
          itemStack = itemStack1;
          break;
        } 
      } 
      paramCallbackInfoReturnable.setReturnValue(q.getReversedStack(itemStack));
    } 
  }
  
  @Unique
  private void assignKeysToBondage(ItemStack paramItemStack, Item paramItem1, Item paramItem2) {
    if (paramItemStack.is(paramItem1))
      for (ItemStack itemStack : this.container.items) {
        if (itemStack.getItem() == paramItem2) {
          String str = bm.generateRandomKey();
          bl.setCustomTag(paramItemStack, str, "keyValue");
          bl.setCustomTag(itemStack, str, "keyValue");
          break;
        } 
      }  
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\ResultSlotMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */