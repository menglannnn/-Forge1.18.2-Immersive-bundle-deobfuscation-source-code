/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.Util
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.network.chat.ChatType
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TextComponent
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.Mth
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.data;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import java.util.function.Supplier;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraftforge.network.NetworkEvent;

/*
 * Renamed from abductionmod.content.bc
 */
public class ModRecipes {
    private final float change;

    public ModRecipes(FriendlyByteBuf friendlyByteBuf) {
        this.change = friendlyByteBuf.readFloat();
    }

    public ModRecipes(float f) {
        this.change = f;
    }

    public static void encode(ModRecipes bc_02, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeFloat(bc_02.change);
    }

    public static void handle(ModRecipes bc_02, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer serverPlayer = ((NetworkEvent.Context)supplier.get()).getSender();
            if (serverPlayer instanceof CurioSlotType) {
                CurioSlotType aG2 = (CurioSlotType)serverPlayer;
                float f = Mth.clamp((float)(aG2.abduction$getLeashLength() + bc_02.change), (float)1.0f, (float)40.0f);
                aG2.abduction$setLeashLength(f);
                serverPlayer.sendSystemMessage((Component)Component.translatable("message.leash.length.changed").withStyle(ChatFormatting.GRAY).append((Component)Component.literal(String.valueOf(f)).withStyle(ChatFormatting.GOLD)), ChatType.GAME_INFO, Util.NIL_UUID);
            }
        });
        context.setPacketHandled(true);
    }
}

