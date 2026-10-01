package com.dev.am.mixin.leash;

import abductionmod.content.aw;
import abductionmod.content.ay;
import abductionmod.content.az;
import abductionmod.content.bm;
import com.dev.am.mod.gamerule.CanLeashCollar;
import com.dev.am.mod.gamerule.CollarLeashTime;
import com.dev.am.mod.gamerule.GameruleRegistry;
import java.util.List;
import java.util.Objects;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.LeadItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({LeadItem.class})
public abstract class LeadItemMix extends Item implements az {
  public boolean am$goesIntoSlot() {
    return false;
  }
  
  public LeadItemMix(Item.Properties paramProperties) {
    super(paramProperties);
  }
  
  @NotNull
  public InteractionResult m_6880_(ItemStack paramItemStack, Player paramPlayer, LivingEntity paramLivingEntity, InteractionHand paramInteractionHand) {
    if (GameruleRegistry.isGameruleEnabled(Objects.<Level>requireNonNull(paramPlayer.f_19853_), CanLeashCollar.ID)) {
      if (paramLivingEntity instanceof aw) {
        aw aw = (aw)paramLivingEntity;
        if (aw.abduction$getLeashHolder() != null)
          return super.m_6880_(paramItemStack, paramPlayer, paramLivingEntity, paramInteractionHand); 
      } 
      if (!bm.areHandsTied(paramLivingEntity) && bm.getBondageInSlot(paramLivingEntity, "collar") != null)
        return am$interactWithTyingItem(paramItemStack, paramPlayer, paramLivingEntity); 
    } 
    return super.m_6880_(paramItemStack, paramPlayer, paramLivingEntity, paramInteractionHand);
  }
  
  public void am$onFinishedTying(ItemStack paramItemStack, Player paramPlayer, LivingEntity paramLivingEntity) {
    if (paramLivingEntity instanceof aw) {
      aw aw = (aw)paramLivingEntity;
      if (paramItemStack.m_150930_(Items.f_42655_)) {
        aw.abduction$setLeashedTo((Entity)paramPlayer, true);
        paramItemStack.m_41774_(1);
        ay ay = (ay)paramPlayer;
        ay.am$setTyingTimeout(30);
      } 
    } 
  }
  
  public int am$getTyingTimeSeconds(LivingEntity paramLivingEntity, ItemStack paramItemStack) {
    return GameruleRegistry.getGameruleValue(paramLivingEntity.f_19853_, CollarLeashTime.ID).intValue();
  }
  
  public void m_7373_(ItemStack paramItemStack, @Nullable Level paramLevel, List<Component> paramList, TooltipFlag paramTooltipFlag) {
    paramList.add((new TranslatableComponent("item.leash.desc")).m_130940_(ChatFormatting.GRAY));
    super.m_7373_(paramItemStack, paramLevel, paramList, paramTooltipFlag);
  }
  
  @Inject(method = {"bindPlayerMobs"}, at = {@At("HEAD")}, cancellable = true)
  private static void bindPlayerMobsMix(Player paramPlayer, Level paramLevel, BlockPos paramBlockPos, CallbackInfoReturnable<InteractionResult> paramCallbackInfoReturnable) {
    LeashFenceKnotEntity leashFenceKnotEntity = null;
    boolean bool = false;
    int i = paramBlockPos.m_123341_();
    int j = paramBlockPos.m_123342_();
    int k = paramBlockPos.m_123343_();
    AABB aABB = new AABB(i - 7.0D, j - 7.0D, k - 7.0D, i + 7.0D, j + 7.0D, k + 7.0D);
    for (Player player : paramLevel.m_45976_(Player.class, aABB)) {
      if (player != paramPlayer && player instanceof aw) {
        aw aw = (aw)player;
        if (aw.abduction$getLeashHolder() == paramPlayer)
          leashFenceKnotEntity = abduction$tieToFence(paramLevel, paramBlockPos, aw, leashFenceKnotEntity); 
      } 
    } 
    if (leashFenceKnotEntity == null) {
      for (LeashFenceKnotEntity leashFenceKnotEntity1 : paramLevel.m_45976_(LeashFenceKnotEntity.class, new AABB(i - 1.0D, j - 1.0D, k - 1.0D, i + 1.0D, j + 1.0D, k + 1.0D))) {
        if (leashFenceKnotEntity1.m_31748_().equals(paramBlockPos))
          leashFenceKnotEntity = leashFenceKnotEntity1; 
      } 
      if (leashFenceKnotEntity == null) {
        for (Mob mob : paramLevel.m_45976_(Mob.class, aABB)) {
          if (mob.m_21524_() == paramPlayer)
            bool = true; 
        } 
        if (paramPlayer.m_21205_().m_41720_() instanceof LeadItem && !bool && paramPlayer instanceof aw) {
          aw aw = (aw)paramPlayer;
          paramPlayer.m_21205_().m_41774_(1);
          abduction$tieToFence(paramLevel, paramBlockPos, aw, leashFenceKnotEntity);
          paramCallbackInfoReturnable.setReturnValue(InteractionResult.SUCCESS);
        } 
      } 
    } 
  }
  
  @Unique
  private static LeashFenceKnotEntity abduction$tieToFence(Level paramLevel, BlockPos paramBlockPos, aw paramaw, LeashFenceKnotEntity paramLeashFenceKnotEntity) {
    if (paramLeashFenceKnotEntity == null) {
      paramLeashFenceKnotEntity = LeashFenceKnotEntity.m_31844_(paramLevel, paramBlockPos);
      paramLeashFenceKnotEntity.m_7084_();
    } 
    paramaw.abduction$setLeashedTo((Entity)paramLeashFenceKnotEntity, true);
    return paramLeashFenceKnotEntity;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\leash\LeadItemMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */