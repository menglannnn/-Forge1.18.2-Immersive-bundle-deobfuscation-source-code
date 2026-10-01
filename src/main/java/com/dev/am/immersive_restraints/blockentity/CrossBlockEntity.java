/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dev.am.immersive_restraints.animation.StruggleAnimations
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.blockentity;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.item.BondageItem;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.block.ModBlocks;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.block.StocksBlock;
import com.dev.am.immersive_restraints.block.CrossBlock;
import com.dev.am.immersive_restraints.blockentity.StocksBlockEntity;
import com.dev.am.immersive_restraints.config.CommonConfig;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.mechanic.MinigameDirection;
import com.dev.am.immersive_restraints.animation.StruggleAnimations;
import com.dev.am.immersive_restraints.mechanic.MinigameCamera;
import com.dev.am.immersive_restraints.config.GameruleRegistry;
import com.dev.am.immersive_restraints.config.StruggleFailStrengthPercent;
import com.dev.am.immersive_restraints.config.StruggleSuccessStrengthPercent;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class CrossBlockEntity {
    private final boolean isSuccess;
    private final MinigameDirection direction;

    public CrossBlockEntity(FriendlyByteBuf friendlyByteBuf) {
        this.isSuccess = friendlyByteBuf.readBoolean();
        this.direction = MinigameDirection.getById(friendlyByteBuf.readInt());
    }

    public CrossBlockEntity(boolean bl2, MinigameDirection i_02) {
        this.isSuccess = bl2;
        this.direction = i_02;
    }

    public static void encode(CrossBlockEntity bo2, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeBoolean(bo2.isSuccess);
        friendlyByteBuf.writeInt(bo2.direction.id);
    }

    public static void handle(CrossBlockEntity bo2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer serverPlayer2 = ((NetworkEvent.Context)supplier.get()).getSender();
            if (serverPlayer2 instanceof CurioSlotType) {
                StruggleAnimations.a a2;
                CurioSlotType aG2 = (CurioSlotType)serverPlayer2;
                ItemStack itemStack2 = EntityEventHandler.getFirstBondageWithBaseResistance((LivingEntity)serverPlayer2, EntityEventHandler.struggleStackPredicate);
                if (itemStack2 == null) {
                    return;
                }
                float f = bo2.isSuccess ? (float)GameruleRegistry.getGameruleValue(serverPlayer2.level, StruggleSuccessStrengthPercent.ID).intValue() : (float)(-GameruleRegistry.getGameruleValue(serverPlayer2.level, StruggleFailStrengthPercent.ID).intValue());
                f /= 100.0f;
                BondageItem aK2 = (BondageItem)itemStack2.getItem();
                if (bo2.isSuccess) {
                    float f2 = EntityEventHandler.getUntyingPower(itemStack2, serverPlayer2.getMainHandItem());
                    if (f2 > 1.0f) {
                        if (serverPlayer2.getMainHandItem().isDamageable()) {
                            serverPlayer2.getMainHandItem().hurtAndBreak(1, (LivingEntity)serverPlayer2, serverPlayer -> serverPlayer.getItemBySlot(EquipmentSlot.MAINHAND));
                        }
                        f = (int)(f * f2);
                    }
                    aG2.abduction$setUntieClicks(aG2.abduction$getUntieClicks() + 1);
                }
                if (aG2.abduction$getUntieClicks() % 3 == 0 || !bo2.isSuccess) {
                    SoundEvent soundEvent = aK2.properties.getSound(itemStack2);
                    EntityEventHandler.playSound((LivingEntity)serverPlayer2, soundEvent);
                }
                int n2 = aK2.properties.getResistance(itemStack2);
                int n3 = aK2.properties.getBaseResistance(itemStack2);
                Integer n4 = PlayerEventHandler.getCustomTagInt(itemStack2, "struggleAddedResistance");
                if (n4 == null) {
                    n4 = 0;
                }
                n4 = n4 - (int)f;
                n2 -= (int)f;
                StocksBlock.sendToClient(serverPlayer2, new ModBlocks((int)f));
                boolean bl2 = false;
                if ((float)(n4 + n3) > (float)n3 * 1.2f) {
                    n4 = (int)((float)n3 * 0.2f);
                    bl2 = true;
                }
                PlayerEventHandler.setCustomTag(itemStack2, n4, "struggleAddedResistance");
                if (!bl2) {
                    if (n2 <= 0) {
                        EntityEventHandler.tryTakeOffBondage(null, (LivingEntity)serverPlayer2, itemStack2, null, SoundEvents.ENTITY_ITEM_BREAK, itemStack -> serverPlayer2.getInventory(itemStack, false));
                        StocksBlock.sendToClient(serverPlayer2, new StocksBlockEntity());
                    }
                    StocksBlock.sendToClient(serverPlayer2, new CommonConfig(CommonConfig.a.STRUGGLING, Mth.clamp((float)((float)n2 / (float)n3), (float)0.0f, (float)2.0f), serverPlayer2.getId(), serverPlayer2.getDisplayName().getString()));
                }
                if ((a2 = aK2.properties.getStruggleAnimation(itemStack2) != null ? aK2.properties.getStruggleAnimation(itemStack2) : k.getById((int)aG2.abduction$getAnimationId()).struggle) != null) {
                    MinigameCamera n_02 = null;
                    switch (bo2.direction) {
                        case UP: {
                            n_02 = a2.up();
                            break;
                        }
                        case LEFT: {
                            n_02 = a2.left();
                            break;
                        }
                        case RIGHT: {
                            n_02 = a2.right();
                            break;
                        }
                        case DOWN: {
                            n_02 = a2.down();
                        }
                    }
                    if (n_02 != null) {
                        for (Player player : serverPlayer2.level.players()) {
                            StocksBlock.sendToClient((ServerPlayer)player, new CrossBlock(n_02.toNbt((Player)serverPlayer2), serverPlayer2.getId()));
                        }
                    }
                }
            }
        });
        context.setPacketHandled(true);
    }
}

