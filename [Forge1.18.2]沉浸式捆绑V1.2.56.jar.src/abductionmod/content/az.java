package abductionmod.content;

import com.dev.am.mod.gamerule.GameruleRegistry;
import com.dev.am.mod.gamerule.TyingTime;
import com.dev.am.mod.gamerule.TyingTimeWhenTied;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface az {
  default int am$getTyingTimeSeconds(LivingEntity paramLivingEntity, ItemStack paramItemStack) {
    int i = GameruleRegistry.getGameruleValue(paramLivingEntity.f_19853_, TyingTime.ID).intValue();
    if (paramLivingEntity instanceof Player) {
      Player player = (Player)paramLivingEntity;
      if (bm.isTied((LivingEntity)player))
        i = GameruleRegistry.getGameruleValue(paramLivingEntity.f_19853_, TyingTimeWhenTied.ID).intValue(); 
    } 
    return i;
  }
  
  default boolean am$goesIntoSlot() {
    return true;
  }
  
  default InteractionResult am$interactWithBondage(ItemStack paramItemStack, Player paramPlayer, LivingEntity paramLivingEntity, ay paramay, int paramInt, az paramaz) {
    bm.a a = bm.canPutBondage(paramLivingEntity, paramItemStack);
    if (!paramay.abduction$isBeingTied() && paramaz.am$goesIntoSlot() && a == bm.a.FAIL)
      return InteractionResult.FAIL; 
    if (paramay.abduction$isFinishedTying() || a == bm.a.SWAP) {
      am$onFinishedTying(paramItemStack, paramPlayer, paramLivingEntity);
      paramay.abduction$stopTyingProcess();
      return InteractionResult.SUCCESS;
    } 
    paramay.abduction$setPlayerPressing(paramItemStack.m_41777_());
    paramay.abduction$setTyerHash(paramInt);
    paramay.abduction$setTyingEntity((LivingEntity)paramPlayer);
    paramay.abduction$setUntying(false);
    return InteractionResult.PASS;
  }
  
  default void am$onFinishedTying(ItemStack paramItemStack, Player paramPlayer, LivingEntity paramLivingEntity) {
    if (paramPlayer instanceof ServerPlayer) {
      ServerPlayer serverPlayer = (ServerPlayer)paramPlayer;
      ay ay = (ay)paramLivingEntity;
      ay.am$setTyingTimeout(10);
      ay.abduction$setUntyingCoolDown(20);
    } 
    bm.putBondageItemInto(paramLivingEntity, (LivingEntity)paramPlayer, paramItemStack.m_41620_(1), null, false);
  }
  
  default InteractionResult am$interactWithTyingItem(ItemStack paramItemStack, Player paramPlayer, LivingEntity paramLivingEntity) {
    if (paramLivingEntity.f_19853_.f_46443_)
      return InteractionResult.FAIL; 
    if (paramLivingEntity instanceof ay) {
      ay ay = (ay)paramLivingEntity;
      if (ay.abduction$isUntying()) {
        if (paramPlayer instanceof ServerPlayer) {
          ServerPlayer serverPlayer = (ServerPlayer)paramPlayer;
          serverPlayer.m_9146_((Component)(new TranslatableComponent("error.player_is_untying")).m_130940_(ChatFormatting.RED), ChatType.GAME_INFO, Util.f_137441_);
        } 
        return InteractionResult.FAIL;
      } 
      int i = bm.calculateTyerHash((LivingEntity)paramPlayer, paramLivingEntity, paramItemStack);
      if (ay.abduction$isBeingTied() && i != ay.abduction$getTyerHash()) {
        if (paramPlayer instanceof ServerPlayer) {
          ServerPlayer serverPlayer = (ServerPlayer)paramPlayer;
          serverPlayer.m_9146_((Component)(new TranslatableComponent("error.already_being_tied")).m_130940_(ChatFormatting.RED), ChatType.GAME_INFO, Util.f_137441_);
        } 
        return InteractionResult.FAIL;
      } 
      Item item = paramItemStack.m_41720_();
      if (item instanceof az) {
        az az1 = (az)item;
        return am$interactWithBondage(paramItemStack, paramPlayer, paramLivingEntity, ay, i, az1);
      } 
    } 
    return InteractionResult.PASS;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\az.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */