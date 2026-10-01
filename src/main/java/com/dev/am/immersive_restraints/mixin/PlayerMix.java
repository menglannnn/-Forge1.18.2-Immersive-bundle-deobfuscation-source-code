package com.dev.am.immersive_restraints.mixin;

import com.dev.am.immersive_restraints.aJ;
import com.dev.am.immersive_restraints.aY;
import com.dev.am.immersive_restraints.aZ;
import com.dev.am.immersive_restraints.av;
import com.dev.am.immersive_restraints.aw;
import com.dev.am.immersive_restraints.bm;
import com.dev.am.immersive_restraints.m;
import java.util.HashMap;
import javax.annotation.Nonnull;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Player.class})
public abstract class PlayerMix extends LivingEntity implements av {
  @Unique
  private static final EntityDataAccessor<ItemStack> DATA_WARDROBE_ITEM = SynchedEntityData.defineId(Player.class, EntityDataSerializers.ITEM_STACK);
  
  @Unique
  private HashMap<String, ItemStack> abduction$wardrobeMap = null;
  
  protected PlayerMix(EntityType<? extends LivingEntity> paramEntityType, Level paramLevel) {
    super(paramEntityType, paramLevel);
  }
  
  @Unique
  @NotNull
  private static InteractionResult abduction$interactForUntying(Player paramPlayer, LivingEntity paramLivingEntity, ay paramay, int paramInt) {
    InteractionResult interactionResult = bm.checkFinishedUntying(paramPlayer, paramLivingEntity, paramay);
    if (interactionResult != null)
      return interactionResult; 
    paramay.abduction$setPlayerPressing(ItemStack.EMPTY);
    paramay.abduction$setTyerHash(paramInt);
    paramay.abduction$setTyingEntity((LivingEntity)paramPlayer);
    paramay.abduction$setUntying(true);
    return InteractionResult.PASS;
  }
  
  @NotNull
  public ItemStack abduction$getCurrentWardrobeItem() {
    return (ItemStack)this.entityData.get(DATA_WARDROBE_ITEM);
  }
  
  public void abduction$setCurrentWardrobeItem(@Nonnull ItemStack paramItemStack) {
    this.entityData.set(DATA_WARDROBE_ITEM, paramItemStack.getOrCreateTagElement());
  }
  
  @Inject(method = {"defineSynchedData"}, at = {@At("TAIL")})
  private void defineSyncData(CallbackInfo paramCallbackInfo) {
    this.entityData.define(DATA_WARDROBE_ITEM, ((Item)aJ.CLOTHES.get()).getDefaultInstance());
  }
  
  @Inject(method = {"tick"}, at = {@At("HEAD")})
  private void tickMix(CallbackInfo paramCallbackInfo) {
    Player player = (Player)this;
    if (player.level.isClientSide)
      m.applyAnimationFromItems(player, (player.tickCount < 10)); 
  }
  
  @Inject(method = {"interactOn"}, at = {@At("RETURN")}, cancellable = true)
  private void interactMix(Entity paramEntity, InteractionHand paramInteractionHand, CallbackInfoReturnable<InteractionResult> paramCallbackInfoReturnable) {
    Player player = (Player)this;
    if (paramEntity.level.isClientSide)
      return; 
    if (paramEntity instanceof LivingEntity) {
      LivingEntity livingEntity = (LivingEntity)paramEntity;
      if (paramEntity instanceof ay) {
        ay ay = (ay)paramEntity;
        if (paramEntity instanceof aw) {
          aw aw = (aw)paramEntity;
          if (paramInteractionHand == InteractionHand.MAIN_HAND) {
            ItemStack itemStack = player.getItemInHand(paramInteractionHand);
            int i = bm.calculateTyerHash((LivingEntity)player, livingEntity, itemStack);
            if (player.isShiftKeyDown()) {
              aZ.sendToClient((ServerPlayer)player, new aY(paramEntity.getId()));
              return;
            } 
            if (ay.abduction$isBeingTied() && i != ay.abduction$getTyerHash()) {
              paramCallbackInfoReturnable.setReturnValue(InteractionResult.FAIL);
              return;
            } 
            if (!itemStack.isEmpty() && itemStack.isEdible() && livingEntity instanceof Player) {
              Player player1 = (Player)livingEntity;
              if (!player1.getFoodData().needsFood()) {
                ((ServerPlayer)player).sendSystemMessage((Component)(Component.translatable("feeding.not_hungry", new Object[] { paramEntity.getName() })).withStyle(ChatFormatting.RED), ChatType.GAME_INFO, this.UUID_TAG);
                return;
              } 
              player1.eat(player.level, itemStack);
              if (!(player.getAbilities()).instabuild)
                itemStack.shrink(1); 
              player1.gameEvent(GameEvent.EAT, livingEntity.blockPosition());
              return;
            } 
            if (itemStack.getItem() instanceof abductionmod.content.aF && livingEntity instanceof Player) {
              Player player1 = (Player)livingEntity;
              bm.useKeyOnPlayer(player1, itemStack);
            } else if (itemStack.isEmpty() && ay.abduction$getUntyingCoolDown() == 0) {
              paramCallbackInfoReturnable.setReturnValue(abduction$interactForUntying(player, livingEntity, ay, i));
              return;
            } 
          } 
        } 
      } 
    } 
  }
  
  @Nullable
  public ItemStack abduction$getWardrobeItem(String paramString) {
    ItemStack itemStack = this.abduction$wardrobeMap.get(paramString);
    return (itemStack != null) ? itemStack.getOrCreateTagElement() : null;
  }
  
  public void abduction$saveWardrobeItem(String paramString, @NotNull ItemStack paramItemStack) {
    abduction$getMap().put(paramString, paramItemStack.getOrCreateTagElement());
  }
  
  public void abduction$removeWardrobeItem(String paramString) {
    this.abduction$wardrobeMap.remove(paramString);
  }
  
  public String[] abduction$getAllWardrobeKeys() {
    return (String[])abduction$getMap().keySet().toArray((Object[])new String[0]);
  }
  
  public HashMap<String, ItemStack> abduction$getMap() {
    if (this.abduction$wardrobeMap == null)
      this.abduction$wardrobeMap = new HashMap<>(); 
    return this.abduction$wardrobeMap;
  }
  
  public void abduction$copyWardrobe(Player paramPlayer) {
    this.abduction$wardrobeMap = ((av)paramPlayer).abduction$getMap();
    abduction$setCurrentWardrobeItem(((av)paramPlayer).abduction$getCurrentWardrobeItem());
  }
  
  @Inject(method = {"readAdditionalSaveData"}, at = {@At("RETURN")})
  private void readSaveData(CompoundTag paramCompoundTag, CallbackInfo paramCallbackInfo) {
    if (paramCompoundTag.contains("abduction$Wardrobe")) {
      CompoundTag compoundTag = paramCompoundTag.getCompound("abduction$Wardrobe");
      abduction$getMap().clear();
      for (String str : compoundTag.getAllKeys()) {
        ItemStack itemStack = ((Item)aJ.CLOTHES.get()).getDefaultInstance();
        itemStack.setTag((CompoundTag)compoundTag.get(str));
        abduction$getMap().put(str, itemStack);
      } 
    } 
    if (paramCompoundTag.contains("abduction$WardrobeCurrent")) {
      Tag tag = paramCompoundTag.get("abduction$WardrobeCurrent");
      ItemStack itemStack = ((Item)aJ.CLOTHES.get()).getDefaultInstance();
      itemStack.setTag((CompoundTag)tag);
      abduction$setCurrentWardrobeItem(itemStack);
    } 
  }
  
  @Inject(method = {"addAdditionalSaveData"}, at = {@At("RETURN")})
  private void addSaveData(CompoundTag paramCompoundTag, CallbackInfo paramCallbackInfo) {
    CompoundTag compoundTag = new CompoundTag();
    for (String str : abduction$getMap().keySet())
      compoundTag.put(str, (Tag)((ItemStack)abduction$getMap().get(str)).getOrCreateTag()); 
    paramCompoundTag.put("abduction$Wardrobe", (Tag)compoundTag);
    paramCompoundTag.put("abduction$WardrobeCurrent", (Tag)abduction$getCurrentWardrobeItem().getOrCreateTag());
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\PlayerMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */