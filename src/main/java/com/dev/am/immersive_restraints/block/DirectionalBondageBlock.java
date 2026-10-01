/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.DirectionalBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.BlockStateProperties
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.DirectionProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.shapes.BooleanOp
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  net.minecraft.world.phys.shapes.Shapes
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package com.dev.am.immersive_restraints.block;

import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.block.ModBlockProperties;
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

/*
 * Renamed from abductionmod.content.s
 */
public class DirectionalBondageBlock
extends DirectionalBlock
implements ModBlockProperties {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    protected static final VoxelShape EAST_SHAPE = Block.box((double)0.0, (double)0.0, (double)0.0, (double)3.0, (double)16.0, (double)16.0);
    protected static final VoxelShape WEST_SHAPE = Block.box((double)13.0, (double)0.0, (double)0.0, (double)16.0, (double)16.0, (double)16.0);
    protected static final VoxelShape SOUTH_SHAPE = Block.box((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)16.0, (double)3.0);
    protected static final VoxelShape NORTH_SHAPE = Block.box((double)0.0, (double)0.0, (double)13.0, (double)16.0, (double)16.0, (double)16.0);
    protected static final VoxelShape UP_SHAPE = Block.box((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)3.0, (double)16.0);
    protected static final VoxelShape DOWN_SHAPE = Block.box((double)0.0, (double)13.0, (double)0.0, (double)16.0, (double)16.0, (double)16.0);
    protected static final VoxelShape OPEN_EAST_SHAPE = DirectionalBondageBlock.makeEastShape();
    protected static final VoxelShape OPEN_WEST_SHAPE = DirectionalBondageBlock.makeWestShape();
    protected static final VoxelShape OPEN_SOUTH_SHAPE = DirectionalBondageBlock.makeSouthShape();
    protected static final VoxelShape OPEN_NORTH_SHAPE = DirectionalBondageBlock.makeNorthShape();
    protected static final VoxelShape OPEN_UP_SHAPE = DirectionalBondageBlock.makeUpShape();
    protected static final VoxelShape OPEN_DOWN_SHAPE = DirectionalBondageBlock.makeDownShape();

    public static VoxelShape makeWestShape() {
        VoxelShape voxelShape = Shapes.empty();
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.8125, (double)0.0, (double)0.8125, (double)1.0, (double)1.0, (double)1.0), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.8125, (double)0.8125, (double)0.1875, (double)1.0, (double)1.0, (double)0.8125), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.8125, (double)0.0, (double)0.0, (double)1.0, (double)1.0, (double)0.1875), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.8125, (double)0.0, (double)0.1875, (double)1.0, (double)0.1875, (double)0.8125), (BooleanOp)BooleanOp.ONLY_FIRST);
        return voxelShape;
    }

    public static VoxelShape makeEastShape() {
        VoxelShape voxelShape = Shapes.empty();
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.0, (double)0.0, (double)0.8125, (double)0.1875, (double)1.0, (double)1.0), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.0, (double)0.8125, (double)0.1875, (double)0.1875, (double)1.0, (double)0.8125), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.0, (double)0.0, (double)0.0, (double)0.1875, (double)1.0, (double)0.1875), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.0, (double)0.0, (double)0.1875, (double)0.1875, (double)0.1875, (double)0.8125), (BooleanOp)BooleanOp.ONLY_FIRST);
        return voxelShape;
    }

    public static VoxelShape makeSouthShape() {
        VoxelShape voxelShape = Shapes.empty();
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.8125, (double)0.0, (double)0.0, (double)1.0, (double)1.0, (double)0.1875), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.1875, (double)0.8125, (double)0.0, (double)0.8125, (double)1.0, (double)0.1875), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.0, (double)0.0, (double)0.0, (double)0.1875, (double)1.0, (double)0.1875), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.1875, (double)0.0, (double)0.0, (double)0.8125, (double)0.1875, (double)0.1875), (BooleanOp)BooleanOp.ONLY_FIRST);
        return voxelShape;
    }

    public static VoxelShape makeNorthShape() {
        VoxelShape voxelShape = Shapes.empty();
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.8125, (double)0.0, (double)0.8125, (double)1.0, (double)1.0, (double)1.0), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.1875, (double)0.8125, (double)0.8125, (double)0.8125, (double)1.0, (double)1.0), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.0, (double)0.0, (double)0.8125, (double)0.1875, (double)1.0, (double)1.0), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.1875, (double)0.0, (double)0.8125, (double)0.8125, (double)0.1875, (double)1.0), (BooleanOp)BooleanOp.ONLY_FIRST);
        return voxelShape;
    }

    public static VoxelShape makeDownShape() {
        VoxelShape voxelShape = Shapes.empty();
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.8125, (double)0.8125, (double)0.0, (double)1.0, (double)1.0, (double)1.0), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.1875, (double)0.8125, (double)0.8125, (double)0.8125, (double)1.0, (double)1.0), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.0, (double)0.8125, (double)0.0, (double)0.1875, (double)1.0, (double)1.0), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.1875, (double)0.8125, (double)0.0, (double)0.8125, (double)1.0, (double)0.1875), (BooleanOp)BooleanOp.ONLY_FIRST);
        return voxelShape;
    }

    public static VoxelShape makeUpShape() {
        VoxelShape voxelShape = Shapes.empty();
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.8125, (double)0.0, (double)0.0, (double)1.0, (double)0.1875, (double)1.0), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.1875, (double)0.0, (double)0.8125, (double)0.8125, (double)0.1875, (double)1.0), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.0, (double)0.0, (double)0.0, (double)0.1875, (double)0.1875, (double)1.0), (BooleanOp)BooleanOp.ONLY_FIRST);
        voxelShape = Shapes.join((VoxelShape)voxelShape, (VoxelShape)Shapes.create((double)0.1875, (double)0.0, (double)0.0, (double)0.8125, (double)0.1875, (double)0.1875), (BooleanOp)BooleanOp.ONLY_FIRST);
        return voxelShape;
    }

    public DirectionalBondageBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue((Property)FACING, (Comparable)Direction.NORTH)).setValue((Property)OPEN, (Comparable)Boolean.FALSE));
    }

    public float getShadeBrightness(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        if (((Boolean)blockState.getValue((Property)OPEN)).booleanValue()) {
            return 1.0f;
        }
        return super.getShadeBrightness(blockState, blockGetter, blockPos);
    }

    public boolean isCollisionShapeFullBlock(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        if (((Boolean)blockState.getValue((Property)OPEN)).booleanValue()) {
            return true;
        }
        return super.isCollisionShapeFullBlock(blockState, blockGetter, blockPos);
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (((Boolean)blockState.getValue((Property)OPEN)).booleanValue()) {
            return switch ((Direction)blockState.getValue((Property)FACING)) {
                default -> OPEN_NORTH_SHAPE;
                case Direction.SOUTH -> OPEN_SOUTH_SHAPE;
                case Direction.WEST -> OPEN_WEST_SHAPE;
                case Direction.EAST -> OPEN_EAST_SHAPE;
                case Direction.DOWN -> OPEN_DOWN_SHAPE;
                case Direction.UP -> OPEN_UP_SHAPE;
            };
        }
        return switch ((Direction)blockState.getValue((Property)FACING)) {
            default -> NORTH_SHAPE;
            case Direction.SOUTH -> SOUTH_SHAPE;
            case Direction.WEST -> WEST_SHAPE;
            case Direction.EAST -> EAST_SHAPE;
            case Direction.DOWN -> DOWN_SHAPE;
            case Direction.UP -> UP_SHAPE;
        };
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        blockState = (BlockState)blockState.cycle((Property)OPEN);
        level.setBlock(blockPos, blockState, 2);
        return InteractionResult.sidedSuccess((boolean)level.isClientSide);
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState blockState = this.defaultBlockState();
        Direction direction = blockPlaceContext.getNearestFacingDirection();
        if (!blockPlaceContext.isSecondaryUseActive()) {
            blockState = (BlockState)blockState.setValue((Property)FACING, (Comparable)direction);
        }
        return blockState;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, OPEN});
    }
}

