package abductionmod.content;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;

public class q extends DoorBlock {
  public static final BooleanProperty LOCKED = BooleanProperty.m_61465_("locked");
  
  public static final BooleanProperty REVERSED = BooleanProperty.m_61465_("reversed");
  
  public q(BlockBehaviour.Properties paramProperties) {
    super(paramProperties);
    m_49959_((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)f_52726_, (Comparable)Direction.NORTH)).m_61124_((Property)f_52727_, Boolean.FALSE)).m_61124_((Property)f_52728_, (Comparable)DoorHingeSide.LEFT)).m_61124_((Property)f_52729_, Boolean.FALSE)).m_61124_((Property)f_52730_, (Comparable)DoubleBlockHalf.LOWER)).m_61124_((Property)LOCKED, Boolean.FALSE)).m_61124_((Property)REVERSED, Boolean.FALSE));
  }
  
  public List<ItemStack> m_7381_(BlockState paramBlockState, LootContext.Builder paramBuilder) {
    ItemStack itemStack = m_5456_().m_7968_();
    if (paramBlockState.m_60734_() instanceof DoorBlock) {
      if (paramBlockState.m_61143_((Property)DoorBlock.f_52730_) == DoubleBlockHalf.UPPER)
        return List.of(); 
      if (((Boolean)paramBlockState.m_61143_((Property)REVERSED)).booleanValue())
        itemStack = getReversedStack(itemStack); 
    } 
    return List.of(itemStack);
  }
  
  public static ItemStack getReversedStack(ItemStack paramItemStack) {
    boolean bool = paramItemStack.m_41784_().m_128471_("reversed");
    ItemStack itemStack = paramItemStack.m_41720_().m_7968_();
    if (!bool)
      itemStack.m_41784_().m_128379_("reversed", true); 
    return itemStack;
  }
  
  protected void m_7926_(StateDefinition.Builder<Block, BlockState> paramBuilder) {
    paramBuilder.m_61104_(new Property[] { (Property)f_52730_, (Property)f_52726_, (Property)f_52727_, (Property)f_52728_, (Property)f_52729_, (Property)LOCKED, (Property)REVERSED });
  }
  
  public InteractionResult m_6227_(BlockState paramBlockState, Level paramLevel, BlockPos paramBlockPos, Player paramPlayer, InteractionHand paramInteractionHand, BlockHitResult paramBlockHitResult) {
    Direction direction1 = (Direction)paramBlockState.m_61143_((Property)f_52726_);
    boolean bool = (paramBlockState.m_61143_((Property)f_52730_) == DoubleBlockHalf.UPPER) ? true : false;
    BlockPos blockPos = paramBlockPos.m_6625_(bool ? 1 : 0);
    Direction direction2 = paramBlockHitResult.m_82434_();
    BlockState blockState1 = paramLevel.m_8055_(blockPos);
    BlockState blockState2 = paramLevel.m_8055_(blockPos.m_7494_());
    Boolean bool1 = (Boolean)blockState1.m_61143_((Property)LOCKED);
    ItemStack itemStack = paramPlayer.m_21120_(paramInteractionHand);
    if (itemStack.m_150930_((Item)aJ.STEEL_KEY.get())) {
      if (paramPlayer.m_6144_()) {
        if (bool1.booleanValue()) {
          sendError(paramLevel, paramPlayer, "action.door_key_already_locked");
          return InteractionResult.FAIL;
        } 
        String str = bl.getCustomTag(itemStack, "keyValue");
        if (str != null) {
          sendError(paramLevel, paramPlayer, "action.door_key_already_assigned");
          return InteractionResult.FAIL;
        } 
        if (!paramLevel.f_46443_) {
          String str1 = bm.generateRandomKey(Long.valueOf(blockPos.m_121878_()));
          bl.setCustomTag(itemStack, str1, "keyValue");
        } 
        paramLevel.m_7731_(blockPos, (BlockState)blockState1.m_61122_((Property)LOCKED), 2);
        paramLevel.m_7731_(blockPos.m_7494_(), (BlockState)blockState2.m_61122_((Property)LOCKED), 2);
        paramLevel.m_5594_(null, paramBlockPos, (SoundEvent)g.COLLAR_KEY_CLOSE.get(), SoundSource.BLOCKS, 0.75F, 1.0F);
        return InteractionResult.SUCCESS;
      } 
      if (bool1.booleanValue()) {
        String str1 = bl.getCustomTag(itemStack, "keyValue");
        if (str1 == null) {
          sendError(paramLevel, paramPlayer, "action.door_key_wrong");
          return InteractionResult.FAIL;
        } 
        String str2 = bm.generateRandomKey(Long.valueOf(blockPos.m_121878_()));
        if (!str1.equals(str2)) {
          sendError(paramLevel, paramPlayer, "action.door_key_wrong");
          return InteractionResult.FAIL;
        } 
      } 
    } else if (bool1.booleanValue()) {
      sendError(paramLevel, paramPlayer, "action.door_key_locked");
      return InteractionResult.FAIL;
    } 
    if (!bool1.booleanValue()) {
      Boolean bool2 = (Boolean)paramBlockState.m_61143_((Property)f_52727_);
      Boolean bool3 = (Boolean)paramBlockState.m_61143_((Property)REVERSED);
      if (!bool2.booleanValue())
        if (bool3.booleanValue()) {
          if (direction1 != direction2.m_122424_())
            return InteractionResult.FAIL; 
        } else if (direction1 != direction2) {
          return InteractionResult.FAIL;
        }  
    } 
    return super.m_6227_(paramBlockState, paramLevel, paramBlockPos, paramPlayer, paramInteractionHand, paramBlockHitResult);
  }
  
  private static void sendError(Level paramLevel, Player paramPlayer, String paramString) {
    if (!paramLevel.f_46443_)
      paramPlayer.m_5661_((Component)(new TranslatableComponent(paramString)).m_130940_(ChatFormatting.RED), true); 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\q.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */