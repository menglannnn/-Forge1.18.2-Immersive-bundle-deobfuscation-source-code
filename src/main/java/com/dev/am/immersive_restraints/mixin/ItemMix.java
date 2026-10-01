package com.dev.am.immersive_restraints.mixin;

import com.dev.am.immersive_restraints.aC;
import com.dev.am.immersive_restraints.bo;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Item.class})
public abstract class ItemMix {
  @Inject(method = {"appendHoverText"}, at = {@At("HEAD")})
  private void hover(ItemStack paramItemStack, Level paramLevel, List<Component> paramList, TooltipFlag paramTooltipFlag, CallbackInfo paramCallbackInfo) {
    Item item = paramItemStack.getItem();
    if (item instanceof aC) {
      aC aC = (aC)item;
      if (aC.properties.isNoDropOnDeath(paramItemStack))
        paramList.add(Component.literal(ChatFormatting.BLUE.toString() + ChatFormatting.BLUE.toString() + ChatFormatting.ITALIC)); 
    } 
    if (paramLevel != null && paramLevel.isClientSide)
      bo.appendArmorVisibilityTooltip(paramItemStack, paramList); 
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\ItemMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */