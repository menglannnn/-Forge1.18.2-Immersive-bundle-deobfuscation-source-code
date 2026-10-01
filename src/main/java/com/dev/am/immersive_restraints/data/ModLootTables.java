/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.data;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.mechanic.MinigameDirection;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.animation.StruggleAnimations;
import com.dev.am.immersive_restraints.render.StruggleGeoRenderer;
import com.dev.am.immersive_restraints.mechanic.KeyValidation;
import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

/*
 * Renamed from abductionmod.content.bd
 */
public class ModLootTables {
    private final MinigameDirection direction;
    private final UUID playerId;

    public ModLootTables(FriendlyByteBuf friendlyByteBuf) {
        this.direction = MinigameDirection.getById(friendlyByteBuf.readInt());
        this.playerId = friendlyByteBuf.readInt();
    }

    public ModLootTables(UUID uUID, MinigameDirection i_02) {
        this.direction = i_02;
        this.playerId = uUID;
    }

    public static void encode(ModLootTables bd_02, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(bd_02.direction.id);
        friendlyByteBuf.writeInt(bd_02.playerId);
    }

    public static void handle(ModLootTables bd_02, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Player player;
            ServerPlayer serverPlayer = context.getSender();
            if (serverPlayer != null && (player = serverPlayer.level().getPlayer(bd_02.playerId)) instanceof CurioSlotType) {
                CurioSlotType aG2 = (CurioSlotType)player;
                if (player.distanceTo((Entity)serverPlayer) > 8.0f) {
                    return;
                }
                if (player.getLeashHolder() != null) {
                    player.dropLeash(player.getX(), player.getY(), player.getZ());
                    return;
                }
                KeyValidation l_02 = StruggleAnimations.getById(aG2.abduction$getAnimationId());
                Integer n2 = null;
                switch (bd_02.direction) {
                    case UP: {
                        n2 = l_02.up;
                        break;
                    }
                    case DOWN: {
                        n2 = l_02.down;
                        break;
                    }
                    case LEFT: {
                        n2 = l_02.left;
                        break;
                    }
                    case RIGHT: {
                        n2 = l_02.right;
                        break;
                    }
                    case FORWARD: {
                        n2 = l_02.forward;
                        break;
                    }
                    case BACKWARD: {
                        n2 = l_02.backward;
                    }
                }
                if (n2 != null) {
                    aG2.abduction$setAnimationId(n2);
                }
            }
        });
        context.setPacketHandled(true);
    }
}

