package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.br;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({ItemEntityRenderer.class})
public abstract class ItemEntityRendererMix {
  @Redirect(method = {"render(Lnet/minecraft/world/entity/item/ItemEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/item/ItemEntity;getItem()Lnet/minecraft/world/item/ItemStack;"))
  private ItemStack injected(ItemEntity paramItemEntity) {
    ItemStack itemStack1 = paramItemEntity.getItem();
    ItemStack itemStack2 = br.getDynamicIconStack(itemStack1);
    return (itemStack2 != null) ? itemStack2 : itemStack1;
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\ItemEntityRendererMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */