package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.aC;
import com.dev.am.immersive_restraints.aJ;
import com.dev.am.immersive_restraints.bl;
import com.dev.am.immersive_restraints.br;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ItemRenderer.class})
public abstract class ItemRendererMix {
  @Shadow
  protected abstract void renderGuiItem(@Nullable LivingEntity paramLivingEntity, ItemStack paramItemStack, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  @Inject(method = {"tryRenderGuiItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;III)V"}, at = {@At("RETURN")})
  private void renderDetail(LivingEntity paramLivingEntity, ItemStack paramItemStack, int paramInt1, int paramInt2, int paramInt3, CallbackInfo paramCallbackInfo) {
    if (aC.isModified(paramItemStack) && !bl.hasCustomTag(paramItemStack, bl.DYNAMIC_ITEM_TEXTURE_TAG)) {
      ItemStack itemStack = ((Item)aJ.COLOR_MARK.get()).getDefaultInstance();
      itemStack.setTag(paramItemStack.getTag());
      itemStack.getOrCreateTag().putString("item", paramItemStack.getItem().getRegistryName().toString());
      renderGuiItem(paramLivingEntity, itemStack, paramInt1, paramInt2, paramInt3, 0);
    } 
  }
  
  @ModifyVariable(method = {"tryRenderGuiItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;III)V"}, at = @At("HEAD"), argsOnly = true, ordinal = 0)
  private ItemStack tryRenderGuiItem(ItemStack paramItemStack) {
    ItemStack itemStack = br.getDynamicIconStack(paramItemStack);
    return (itemStack != null) ? itemStack : paramItemStack;
  }
  
  @ModifyVariable(method = {"renderStatic(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemTransforms$TransformType;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/level/Level;III)V"}, at = @At("HEAD"), argsOnly = true, ordinal = 0)
  private ItemStack renderStatic(ItemStack paramItemStack) {
    ItemStack itemStack = br.getDynamicIconStack(paramItemStack);
    return (itemStack != null) ? itemStack : paramItemStack;
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\ItemRendererMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */