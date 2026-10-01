package com.dev.am.mixin;

import abductionmod.content.aJ;
import abductionmod.content.aY;
import abductionmod.content.aZ;
import abductionmod.content.av;
import abductionmod.content.aw;
import abductionmod.content.ay;
import abductionmod.content.bm;
import abductionmod.content.m;
import java.util.HashMap;
import javax.annotation.Nonnull;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
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
  private static final EntityDataAccessor<ItemStack> DATA_WARDROBE_ITEM = SynchedEntityData.m_135353_(Player.class, EntityDataSerializers.f_135033_);
  
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
    paramay.abduction$setPlayerPressing(ItemStack.f_41583_);
    paramay.abduction$setTyerHash(paramInt);
    paramay.abduction$setTyingEntity((LivingEntity)paramPlayer);
    paramay.abduction$setUntying(true);
    return InteractionResult.PASS;
  }
  
  @NotNull
  public ItemStack abduction$getCurrentWardrobeItem() {
    return (ItemStack)this.f_19804_.m_135370_(DATA_WARDROBE_ITEM);
  }
  
  public void abduction$setCurrentWardrobeItem(@Nonnull ItemStack paramItemStack) {
    this.f_19804_.m_135381_(DATA_WARDROBE_ITEM, paramItemStack.m_41777_());
  }
  
  @Inject(method = {"defineSynchedData"}, at = {@At("TAIL")})
  private void defineSyncData(CallbackInfo paramCallbackInfo) {
    this.f_19804_.m_135372_(DATA_WARDROBE_ITEM, ((Item)aJ.CLOTHES.get()).m_7968_());
  }
  
  @Inject(method = {"tick"}, at = {@At("HEAD")})
  private void tickMix(CallbackInfo paramCallbackInfo) {
    Player player = (Player)this;
    if (player.f_19853_.f_46443_)
      m.applyAnimationFromItems(player, (player.f_19797_ < 10)); 
  }
  
  @Inject(method = {"interactOn"}, at = {@At("RETURN")}, cancellable = true)
  private void interactMix(Entity paramEntity, InteractionHand paramInteractionHand, CallbackInfoReturnable<InteractionResult> paramCallbackInfoReturnable) {
    Player player = (Player)this;
    if (paramEntity.f_19853_.f_46443_)
      return; 
    if (paramEntity instanceof LivingEntity) {
      LivingEntity livingEntity = (LivingEntity)paramEntity;
      if (paramEntity instanceof ay) {
        ay ay = (ay)paramEntity;
        if (paramEntity instanceof aw) {
          aw aw = (aw)paramEntity;
          if (paramInteractionHand == InteractionHand.MAIN_HAND) {
            ItemStack itemStack = player.m_21120_(paramInteractionHand);
            int i = bm.calculateTyerHash((LivingEntity)player, livingEntity, itemStack);
            if (player.m_6144_()) {
              aZ.sendToClient((ServerPlayer)player, new aY(paramEntity.m_142081_()));
              return;
            } 
            if (ay.abduction$isBeingTied() && i != ay.abduction$getTyerHash()) {
              paramCallbackInfoReturnable.setReturnValue(InteractionResult.FAIL);
              return;
            } 
            if (!itemStack.m_41619_() && itemStack.m_41614_() && livingEntity instanceof Player) {
              Player player1 = (Player)livingEntity;
              if (!player1.m_36324_().m_38721_()) {
                ((ServerPlayer)player).m_9146_((Component)(new TranslatableComponent("feeding.not_hungry", new Object[] { paramEntity.m_6302_() })).m_130940_(ChatFormatting.RED), ChatType.GAME_INFO, this.f_19820_);
                return;
              } 
              player1.m_5584_(player.f_19853_, itemStack);
              if (!(player.m_150110_()).f_35937_)
                itemStack.m_41774_(1); 
              player1.m_146859_(GameEvent.f_157806_, livingEntity.m_146901_());
              return;
            } 
            if (itemStack.m_41720_() instanceof abductionmod.content.aF && livingEntity instanceof Player) {
              Player player1 = (Player)livingEntity;
              bm.useKeyOnPlayer(player1, itemStack);
            } else if (itemStack.m_41619_() && ay.abduction$getUntyingCoolDown() == 0) {
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
    return (itemStack != null) ? itemStack.m_41777_() : null;
  }
  
  public void abduction$saveWardrobeItem(String paramString, @NotNull ItemStack paramItemStack) {
    abduction$getMap().put(paramString, paramItemStack.m_41777_());
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
    if (paramCompoundTag.m_128441_("abduction$Wardrobe")) {
      CompoundTag compoundTag = paramCompoundTag.m_128469_("abduction$Wardrobe");
      abduction$getMap().clear();
      for (String str : compoundTag.m_128431_()) {
        ItemStack itemStack = ((Item)aJ.CLOTHES.get()).m_7968_();
        itemStack.m_41751_((CompoundTag)compoundTag.m_128423_(str));
        abduction$getMap().put(str, itemStack);
      } 
    } 
    if (paramCompoundTag.m_128441_("abduction$WardrobeCurrent")) {
      Tag tag = paramCompoundTag.m_128423_("abduction$WardrobeCurrent");
      ItemStack itemStack = ((Item)aJ.CLOTHES.get()).m_7968_();
      itemStack.m_41751_((CompoundTag)tag);
      abduction$setCurrentWardrobeItem(itemStack);
    } 
  }
  
  @Inject(method = {"addAdditionalSaveData"}, at = {@At("RETURN")})
  private void addSaveData(CompoundTag paramCompoundTag, CallbackInfo paramCallbackInfo) {
    CompoundTag compoundTag = new CompoundTag();
    for (String str : abduction$getMap().keySet())
      compoundTag.m_128365_(str, (Tag)((ItemStack)abduction$getMap().get(str)).m_41784_()); 
    paramCompoundTag.m_128365_("abduction$Wardrobe", (Tag)compoundTag);
    paramCompoundTag.m_128365_("abduction$WardrobeCurrent", (Tag)abduction$getCurrentWardrobeItem().m_41784_());
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\PlayerMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */