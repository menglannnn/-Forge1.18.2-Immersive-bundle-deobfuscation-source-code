package com.dev.am.immersive_restraints.mixin;

import com.dev.am.immersive_restraints.aC;
import com.dev.am.immersive_restraints.av;
import com.dev.am.immersive_restraints.ax;
import com.dev.am.immersive_restraints.ay;
import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ServerPlayer.class})
public abstract class ServerPlayerMix extends Player implements ax {
  @Shadow
  @Final
  public MinecraftServer serverLevel;
  
  @Unique
  ax.a abduction$currentRpStatus;
  
  @Unique
  long abduction$noRpCommandTime;
  
  @Unique
  long abduction$noRpImmunityUntil;
  
  @Unique
  int abduction$talkArea;
  
  @Unique
  int abduction$talkAreaPreference;
  
  public void abduction$setTalkArea(int paramInt) {
    this.abduction$talkArea = paramInt;
  }
  
  public int abduction$getTalkArea() {
    return this.abduction$talkArea;
  }
  
  public void abduction$setTalkAreaPreference(int paramInt) {
    this.abduction$talkAreaPreference = paramInt;
  }
  
  public int abduction$getTalkAreaPreference() {
    return this.abduction$talkAreaPreference;
  }
  
  public ServerPlayerMix(Level paramLevel, BlockPos paramBlockPos, float paramFloat, GameProfile paramGameProfile) {
    super(paramLevel, paramBlockPos, paramFloat, paramGameProfile);
  }
  
  public ax.a abduction$getRpStatus() {
    return (this.abduction$currentRpStatus == null) ? ax.a.NORMAL : this.abduction$currentRpStatus;
  }
  
  public void abduction$setRpStatus(ax.a parama) {
    this.abduction$currentRpStatus = parama;
  }
  
  public long abduction$getNoRpCommandTime() {
    return this.abduction$noRpCommandTime;
  }
  
  public void abduction$setNoRpCommandTime(long paramLong) {
    this.abduction$noRpCommandTime = paramLong;
  }
  
  public long abduction$getNoRpImmunityUntilTicks() {
    return this.abduction$noRpImmunityUntil;
  }
  
  public void abduction$setNoRpImmunityUntilTicks(long paramLong) {
    this.abduction$noRpImmunityUntil = paramLong;
  }
  
  public boolean abduction$isImmuneToBondage() {
    return (abduction$getNoRpImmunityUntilTicks() > this.serverLevel.getGameTime());
  }
  
  @Inject(method = {"tick()V"}, at = {@At("TAIL")})
  private void tick(CallbackInfo paramCallbackInfo) {}
  
  @Inject(method = {"<init>"}, at = {@At("TAIL")})
  private void init(CallbackInfo paramCallbackInfo) {
    this.abduction$talkArea = -1;
  }
  
  @Inject(method = {"restoreFrom"}, at = {@At("TAIL")})
  private void restoreFrom(ServerPlayer paramServerPlayer, boolean paramBoolean, CallbackInfo paramCallbackInfo) {
    ServerPlayer serverPlayer = (ServerPlayer)this;
    ((av)serverPlayer).abduction$copyWardrobe((Player)paramServerPlayer);
    if (!this.level.getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) && !paramServerPlayer.isSpectator())
      abduction$keepNoDropItems(getInventory(), paramServerPlayer.getInventory()); 
    ((ax)serverPlayer).abduction$setNoRpCommandTime(((ax)paramServerPlayer).abduction$getNoRpCommandTime());
    ((ax)serverPlayer).abduction$setRpStatus(((ax)paramServerPlayer).abduction$getRpStatus());
    ((ax)serverPlayer).abduction$setTalkArea(((ax)paramServerPlayer).abduction$getTalkArea());
    ((ay)serverPlayer).abduction$setAdjustmentOffsetsMap(((ay)paramServerPlayer).abduction$getAdjustmentOffsetsMap());
    ((ay)serverPlayer).abduction$setProtectedClothes(((ay)paramServerPlayer).abduction$getProtectedClothes());
    ((ay)serverPlayer).abduction$setLegsUntied(((ay)paramServerPlayer).abduction$getLegsUntied());
    ((ax)serverPlayer).abduction$setTalkAreaPreference(((ax)paramServerPlayer).abduction$getTalkAreaPreference());
  }
  
  @Unique
  public void abduction$keepNoDropItems(Inventory paramInventory1, Inventory paramInventory2) {
    for (byte b = 0; b < paramInventory1.getContainerSize(); b++) {
      ItemStack itemStack = paramInventory2.getItem(b);
      if (!itemStack.isEmpty()) {
        Item item = itemStack.getItem();
        if (item instanceof aC) {
          aC aC = (aC)item;
          if (aC.properties.isNoDropOnDeath(itemStack))
            paramInventory1.setItem(b, itemStack); 
        } 
      } 
    } 
    paramInventory1.armor = paramInventory2.armor;
  }
  
  @Inject(method = {"readAdditionalSaveData"}, at = {@At("RETURN")})
  private void readSaveData(CompoundTag paramCompoundTag, CallbackInfo paramCallbackInfo) {
    if (paramCompoundTag.contains("abduction$talkArea"))
      abduction$setTalkArea(paramCompoundTag.getInt("abduction$talkArea")); 
    if (paramCompoundTag.contains("abduction$talkAreaPreference"))
      abduction$setTalkAreaPreference(paramCompoundTag.getInt("abduction$talkAreaPreference")); 
  }
  
  @Inject(method = {"addAdditionalSaveData"}, at = {@At("RETURN")})
  private void addSaveData(CompoundTag paramCompoundTag, CallbackInfo paramCallbackInfo) {
    paramCompoundTag.putInt("abduction$talkArea", abduction$getTalkArea());
    paramCompoundTag.putInt("abduction$talkAreaPreference", abduction$getTalkAreaPreference());
  }
}


/* Location:              C:\Users\灏忎寒鍌婚€糪Desktop\[Forge1.18.2]娌夋蹈寮忔崋缁慥1.2.56.jar!\com\dev\am\mixin\ServerPlayerMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */