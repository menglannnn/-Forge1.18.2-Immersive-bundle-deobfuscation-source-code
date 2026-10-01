/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.DoorBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition$Builder
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.DoorHingeSide
 *  net.minecraft.world.level.block.state.properties.DoubleBlockHalf
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.storage.loot.LootContext$Builder
 *  net.minecraft.world.phys.BlockHitResult
 */
package com.dev.am.immersive_restraints.block;

import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.init.ModSounds;
import com.dev.am.immersive_restraints.block.ModBlockProperties;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
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

/*
 * Renamed from abductionmod.content.r
 */
public class LockableDoorBlock
extends DoorBlock
implements ModBlockProperties {
    public static final BooleanProperty LOCKED = BooleanProperty.create((String)"locked");
    public static final BooleanProperty REVERSED = BooleanProperty.create((String)"reversed");

    public LockableDoorBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue((Property)FACING, (Comparable)Direction.NORTH)).setValue((Property)OPEN, (Comparable)Boolean.FALSE)).setValue((Property)HINGE, (Comparable)DoorHingeSide.LEFT)).setValue((Property)POWERED, (Comparable)Boolean.FALSE)).setValue((Property)HALF, (Comparable)DoubleBlockHalf.LOWER)).setValue((Property)LOCKED, (Comparable)Boolean.FALSE)).setValue((Property)REVERSED, (Comparable)Boolean.FALSE));
    }

    public List<ItemStack> getDrops(BlockState blockState, LootContext.Builder builder) {
        ItemStack itemStack = this.asItem().getDefaultInstance();
        if (blockState.getBlock() instanceof DoorBlock) {
            if (blockState.getValue((Property)DoorBlock.HALF) == DoubleBlockHalf.UPPER) {
                return List.of();
            }
            if (((Boolean)blockState.getValue((Property)REVERSED)).booleanValue()) {
                itemStack = LockableDoorBlock.getReversedStack(itemStack);
            }
        }
        return List.of(itemStack);
    }

    public static ItemStack getReversedStack(ItemStack itemStack) {
        boolean bl2 = itemStack.getOrCreateTag().getBoolean("reversed");
        ItemStack itemStack2 = itemStack.getItem().getDefaultInstance();
        if (!bl2) {
            itemStack2.getOrCreateTag().putBoolean("reversed", true);
        }
        return itemStack2;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{HALF, FACING, OPEN, HINGE, POWERED, LOCKED, REVERSED});
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        Object object;
        Object object2;
        Direction direction = (Direction)blockState.getValue((Property)FACING);
        boolean bl2 = blockState.getValue((Property)HALF) == DoubleBlockHalf.UPPER;
        BlockPos blockPos2 = blockPos.above(bl2 ? 1 : 0);
        Direction direction2 = blockHitResult.getDirection();
        BlockState blockState2 = level.getBlockState(blockPos2);
        BlockState blockState3 = level.getBlockState(blockPos2.below());
        Boolean bl3 = (Boolean)blockState2.getValue((Property)LOCKED);
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (itemStack.is((Item)ModItems.STEEL_KEY.get())) {
            if (player.isShiftKeyDown()) {
                if (bl3.booleanValue()) {
                    LockableDoorBlock.sendError(level, player, "action.door_key_already_locked");
                    return InteractionResult.FAIL;
                }
                String string = PlayerEventHandler.getCustomTag(itemStack, "keyValue");
                if (string != null) {
                    LockableDoorBlock.sendError(level, player, "action.door_key_already_assigned");
                    return InteractionResult.FAIL;
                }
                if (!level.isClientSide) {
                    String string2 = EntityEventHandler.generateRandomKey(blockPos2.asLong());
                    PlayerEventHandler.setCustomTag(itemStack, string2, "keyValue");
                }
                level.setBlock(blockPos2, (BlockState)blockState2.cycle((Property)LOCKED), 2);
                level.setBlock(blockPos2.below(), (BlockState)blockState3.cycle((Property)LOCKED), 2);
                level.playSound(null, blockPos, (SoundEvent)ModSounds.COLLAR_KEY_CLOSE.get(), SoundSource.BLOCKS, 0.75f, 1.0f);
                return InteractionResult.SUCCESS;
            }
            if (bl3.booleanValue()) {
                object2 = PlayerEventHandler.getCustomTag(itemStack, "keyValue");
                if (object2 == null) {
                    LockableDoorBlock.sendError(level, player, "action.door_key_wrong");
                    return InteractionResult.FAIL;
                }
                object = EntityEventHandler.generateRandomKey(blockPos2.asLong());
                if (!((String)object2).equals(object)) {
                    LockableDoorBlock.sendError(level, player, "action.door_key_wrong");
                    return InteractionResult.FAIL;
                }
            }
        } else if (bl3.booleanValue()) {
            LockableDoorBlock.sendError(level, player, "action.door_key_locked");
            return InteractionResult.FAIL;
        }
        if (!bl3.booleanValue()) {
            object2 = (Boolean)blockState.getValue((Property)OPEN);
            object = (Boolean)blockState.getValue((Property)REVERSED);
            if (!((Boolean)object2).booleanValue() && (((Boolean)object).booleanValue() ? direction != direction2.getOpposite() : direction != direction2)) {
                return InteractionResult.FAIL;
            }
        }
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    private static void sendError(Level level, Player player, String string) {
        if (!level.isClientSide) {
            player.sendSystemMessage((Component)Component.translatable(string).withStyle(ChatFormatting.RED), true);
        }
    }
}

