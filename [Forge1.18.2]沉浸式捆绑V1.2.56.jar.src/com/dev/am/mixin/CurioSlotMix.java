package com.dev.am.mixin;

import com.dev.am.Abduction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import top.theillusivec4.curios.common.inventory.CurioSlot;

@Mixin({CurioSlot.class})
public abstract class CurioSlotMix extends SlotItemHandler {
  @Shadow
  @Final
  private String identifier;
  
  public CurioSlotMix(IItemHandler paramIItemHandler, int paramInt1, int paramInt2, int paramInt3) {
    super(paramIItemHandler, paramInt1, paramInt2, paramInt3);
  }
  
  public int m_6641_() {
    return Abduction.slotNames.contains(this.identifier) ? 1 : super.m_6641_();
  }
  
  public int m_5866_(@NotNull ItemStack paramItemStack) {
    return Abduction.slotNames.contains(this.identifier) ? 1 : super.m_5866_(paramItemStack);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\CurioSlotMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */