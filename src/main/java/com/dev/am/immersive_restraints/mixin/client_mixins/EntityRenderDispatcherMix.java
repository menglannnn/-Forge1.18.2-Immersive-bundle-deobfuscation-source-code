package com.dev.am.immersive_restraints.mixin.client_mixins;

import com.dev.am.immersive_restraints.aC;
import com.dev.am.immersive_restraints.bl;
import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.bo;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import top.theillusivec4.curios.api.SlotResult;

@Mixin({EntityRenderDispatcher.class})
public abstract class EntityRenderDispatcherMix {
  @Unique
  private static final Map<String, EntityRendererProvider<AbstractClientPlayer>> SLIM_PLAYER_PROVIDERS;
  
  @Unique
  private static final Map<String, EntityRendererProvider<AbstractClientPlayer>> FAT_PLAYER_PROVIDERS;
  
  @Unique
  private static Map<String, EntityRenderer<? extends Player>> abduction$createPlayerRenderers(EntityRendererProvider.Context paramContext, Map<String, EntityRendererProvider<AbstractClientPlayer>> paramMap) {
    ImmutableMap.Builder builder = ImmutableMap.builder();
    paramMap.forEach((paramString, paramEntityRendererProvider) -> {
          try {
            paramBuilder.put(paramString, paramEntityRendererProvider.create(paramContext));
          } catch (Exception exception) {
            throw new IllegalArgumentException("Failed to create player model for " + paramString, exception);
          } 
        });
    return (Map<String, EntityRenderer<? extends Player>>)builder.build();
  }
  
  @Inject(method = {"getRenderer"}, at = {@At("HEAD")}, cancellable = true)
  private void getRendererMix(Entity paramEntity, CallbackInfoReturnable<EntityRenderer<? super Entity>> paramCallbackInfoReturnable) {
    if (paramEntity instanceof AbstractClientPlayer) {
      AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer)paramEntity;
      String str = ((AbstractClientPlayer)paramEntity).getModelName();
      EntityRenderer entityRenderer = null;
      List list = bm.getBondagesForRendering((LivingEntity)abstractClientPlayer);
      for (SlotResult slotResult : list) {
        ItemStack itemStack = slotResult.stack();
        Item item = itemStack.getItem();
        if (item instanceof aC) {
          aC aC = (aC)item;
          if (aC.properties.isForcedSlimArms(itemStack)) {
            entityRenderer = (EntityRenderer)bo.abduction$playerRenderersSlim.get(str);
            continue;
          } 
        } 
        String str1 = bl.getCustomTag(itemStack, bl.ARM_SHAPE_TAG);
        if (str1 == null)
          continue; 
        if (str1.equals("slim")) {
          entityRenderer = (EntityRenderer)bo.abduction$playerRenderersSlim.get(str);
          continue;
        } 
        if (str1.equals("full"))
          entityRenderer = (EntityRenderer)bo.abduction$playerRenderersFat.get(str); 
      } 
      if (entityRenderer != null)
        paramCallbackInfoReturnable.setReturnValue(entityRenderer); 
    } 
  }
  
  @Inject(method = {"onResourceManagerReload"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/EntityRenderers;createPlayerRenderers(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;)Ljava/util/Map;", shift = At.Shift.AFTER)}, locals = LocalCapture.CAPTURE_FAILHARD)
  private void reloadMix(ResourceManager paramResourceManager, CallbackInfo paramCallbackInfo, EntityRendererProvider.Context paramContext) {
    bo.abduction$playerRenderersSlim = abduction$createPlayerRenderers(paramContext, SLIM_PLAYER_PROVIDERS);
    bo.abduction$playerRenderersFat = abduction$createPlayerRenderers(paramContext, FAT_PLAYER_PROVIDERS);
  }
  
  static {
    SLIM_PLAYER_PROVIDERS = (Map<String, EntityRendererProvider<AbstractClientPlayer>>)ImmutableMap.of("default", paramContext -> new PlayerRenderer(paramContext, true), "slim", paramContext -> new PlayerRenderer(paramContext, true));
    FAT_PLAYER_PROVIDERS = (Map<String, EntityRendererProvider<AbstractClientPlayer>>)ImmutableMap.of("default", paramContext -> new PlayerRenderer(paramContext, false), "slim", paramContext -> new PlayerRenderer(paramContext, false));
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\client_mixins\EntityRenderDispatcherMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */