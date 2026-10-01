package abductionmod.content;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

class e extends OptionalDispenseItemBehavior {
  @NotNull
  protected ItemStack m_7498_(BlockSource paramBlockSource, @NotNull ItemStack paramItemStack) {
    BlockPos blockPos = paramBlockSource.m_7961_().m_142300_((Direction)paramBlockSource.m_6414_().m_61143_((Property)DispenserBlock.f_52659_));
    for (Player player : paramBlockSource.m_7727_().m_6443_(Player.class, new AABB(blockPos), paramPlayer -> (paramPlayer.m_6084_() && bm.canPutBondage((LivingEntity)paramPlayer, paramItemStack) != bm.a.FAIL))) {
      if (bm.canPutBondage((LivingEntity)player, paramItemStack) == bm.a.TIE) {
        bm.putBondageItemInto((LivingEntity)player, null, paramItemStack.m_41620_(1), null, false);
        m_123573_(true);
        return paramItemStack;
      } 
    } 
    return super.m_7498_(paramBlockSource, paramItemStack);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\e.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */