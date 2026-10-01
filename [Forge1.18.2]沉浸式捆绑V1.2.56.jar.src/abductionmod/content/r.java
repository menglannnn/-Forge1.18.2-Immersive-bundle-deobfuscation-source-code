package abductionmod.content;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class r extends DirectionalBlock {
  public static final DirectionProperty FACING = BlockStateProperties.f_61372_;
  
  public static final BooleanProperty OPEN = BlockStateProperties.f_61446_;
  
  protected static final VoxelShape EAST_SHAPE = Block.m_49796_(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
  
  protected static final VoxelShape WEST_SHAPE = Block.m_49796_(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
  
  protected static final VoxelShape SOUTH_SHAPE = Block.m_49796_(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
  
  protected static final VoxelShape NORTH_SHAPE = Block.m_49796_(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
  
  protected static final VoxelShape UP_SHAPE = Block.m_49796_(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);
  
  protected static final VoxelShape DOWN_SHAPE = Block.m_49796_(0.0D, 13.0D, 0.0D, 16.0D, 16.0D, 16.0D);
  
  protected static final VoxelShape OPEN_EAST_SHAPE = makeEastShape();
  
  protected static final VoxelShape OPEN_WEST_SHAPE = makeWestShape();
  
  protected static final VoxelShape OPEN_SOUTH_SHAPE = makeSouthShape();
  
  protected static final VoxelShape OPEN_NORTH_SHAPE = makeNorthShape();
  
  protected static final VoxelShape OPEN_UP_SHAPE = makeUpShape();
  
  protected static final VoxelShape OPEN_DOWN_SHAPE = makeDownShape();
  
  public static VoxelShape makeWestShape() {
    null = Shapes.m_83040_();
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.8125D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D), BooleanOp.f_82695_);
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.8125D, 0.8125D, 0.1875D, 1.0D, 1.0D, 0.8125D), BooleanOp.f_82695_);
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D), BooleanOp.f_82695_);
    return Shapes.m_83113_(null, Shapes.m_83048_(0.8125D, 0.0D, 0.1875D, 1.0D, 0.1875D, 0.8125D), BooleanOp.f_82695_);
  }
  
  public static VoxelShape makeEastShape() {
    null = Shapes.m_83040_();
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.0D, 0.0D, 0.8125D, 0.1875D, 1.0D, 1.0D), BooleanOp.f_82695_);
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.0D, 0.8125D, 0.1875D, 0.1875D, 1.0D, 0.8125D), BooleanOp.f_82695_);
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 0.1875D), BooleanOp.f_82695_);
    return Shapes.m_83113_(null, Shapes.m_83048_(0.0D, 0.0D, 0.1875D, 0.1875D, 0.1875D, 0.8125D), BooleanOp.f_82695_);
  }
  
  public static VoxelShape makeSouthShape() {
    null = Shapes.m_83040_();
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D), BooleanOp.f_82695_);
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.1875D, 0.8125D, 0.0D, 0.8125D, 1.0D, 0.1875D), BooleanOp.f_82695_);
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 0.1875D), BooleanOp.f_82695_);
    return Shapes.m_83113_(null, Shapes.m_83048_(0.1875D, 0.0D, 0.0D, 0.8125D, 0.1875D, 0.1875D), BooleanOp.f_82695_);
  }
  
  public static VoxelShape makeNorthShape() {
    null = Shapes.m_83040_();
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.8125D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D), BooleanOp.f_82695_);
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.1875D, 0.8125D, 0.8125D, 0.8125D, 1.0D, 1.0D), BooleanOp.f_82695_);
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.0D, 0.0D, 0.8125D, 0.1875D, 1.0D, 1.0D), BooleanOp.f_82695_);
    return Shapes.m_83113_(null, Shapes.m_83048_(0.1875D, 0.0D, 0.8125D, 0.8125D, 0.1875D, 1.0D), BooleanOp.f_82695_);
  }
  
  public static VoxelShape makeDownShape() {
    null = Shapes.m_83040_();
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.8125D, 0.8125D, 0.0D, 1.0D, 1.0D, 1.0D), BooleanOp.f_82695_);
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.1875D, 0.8125D, 0.8125D, 0.8125D, 1.0D, 1.0D), BooleanOp.f_82695_);
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.0D, 0.8125D, 0.0D, 0.1875D, 1.0D, 1.0D), BooleanOp.f_82695_);
    return Shapes.m_83113_(null, Shapes.m_83048_(0.1875D, 0.8125D, 0.0D, 0.8125D, 1.0D, 0.1875D), BooleanOp.f_82695_);
  }
  
  public static VoxelShape makeUpShape() {
    null = Shapes.m_83040_();
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.8125D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), BooleanOp.f_82695_);
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.1875D, 0.0D, 0.8125D, 0.8125D, 0.1875D, 1.0D), BooleanOp.f_82695_);
    null = Shapes.m_83113_(null, Shapes.m_83048_(0.0D, 0.0D, 0.0D, 0.1875D, 0.1875D, 1.0D), BooleanOp.f_82695_);
    return Shapes.m_83113_(null, Shapes.m_83048_(0.1875D, 0.0D, 0.0D, 0.8125D, 0.1875D, 0.1875D), BooleanOp.f_82695_);
  }
  
  public r(BlockBehaviour.Properties paramProperties) {
    super(paramProperties);
    m_49959_((BlockState)((BlockState)((BlockState)this.f_49792_.m_61090_()).m_61124_((Property)FACING, (Comparable)Direction.NORTH)).m_61124_((Property)OPEN, Boolean.FALSE));
  }
  
  public float m_7749_(BlockState paramBlockState, BlockGetter paramBlockGetter, BlockPos paramBlockPos) {
    return ((Boolean)paramBlockState.m_61143_((Property)OPEN)).booleanValue() ? 1.0F : super.m_7749_(paramBlockState, paramBlockGetter, paramBlockPos);
  }
  
  public boolean m_7420_(BlockState paramBlockState, BlockGetter paramBlockGetter, BlockPos paramBlockPos) {
    return ((Boolean)paramBlockState.m_61143_((Property)OPEN)).booleanValue() ? true : super.m_7420_(paramBlockState, paramBlockGetter, paramBlockPos);
  }
  
  public VoxelShape m_5940_(BlockState paramBlockState, BlockGetter paramBlockGetter, BlockPos paramBlockPos, CollisionContext paramCollisionContext) {
    if (((Boolean)paramBlockState.m_61143_((Property)OPEN)).booleanValue()) {
      switch (s.$SwitchMap$net$minecraft$core$Direction[((Direction)paramBlockState.m_61143_((Property)FACING)).ordinal()]) {
        default:
        
        case 1:
        
        case 2:
        
        case 3:
        
        case 4:
        
        case 5:
          break;
      } 
      return OPEN_UP_SHAPE;
    } 
    switch (s.$SwitchMap$net$minecraft$core$Direction[((Direction)paramBlockState.m_61143_((Property)FACING)).ordinal()]) {
      default:
      
      case 1:
      
      case 2:
      
      case 3:
      
      case 4:
      
      case 5:
        break;
    } 
    return UP_SHAPE;
  }
  
  public InteractionResult m_6227_(BlockState paramBlockState, Level paramLevel, BlockPos paramBlockPos, Player paramPlayer, InteractionHand paramInteractionHand, BlockHitResult paramBlockHitResult) {
    paramBlockState = (BlockState)paramBlockState.m_61122_((Property)OPEN);
    paramLevel.m_7731_(paramBlockPos, paramBlockState, 2);
    return InteractionResult.m_19078_(paramLevel.f_46443_);
  }
  
  public BlockState m_5573_(BlockPlaceContext paramBlockPlaceContext) {
    BlockState blockState = m_49966_();
    Direction direction = paramBlockPlaceContext.m_43719_();
    if (!paramBlockPlaceContext.m_7058_())
      blockState = (BlockState)blockState.m_61124_((Property)FACING, (Comparable)direction); 
    return blockState;
  }
  
  protected void m_7926_(StateDefinition.Builder<Block, BlockState> paramBuilder) {
    paramBuilder.m_61104_(new Property[] { (Property)FACING, (Property)OPEN });
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\r.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */