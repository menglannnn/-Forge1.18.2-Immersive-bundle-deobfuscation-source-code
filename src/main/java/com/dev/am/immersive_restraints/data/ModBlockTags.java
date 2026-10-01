/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.data;

import com.dev.am.mod.gamerule.Gamerules;
import java.nio.charset.Charset;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

/*
 * Renamed from abductionmod.content.bf
 */
public class ModBlockTags {
    private final String key;
    private final int value;

    public ModBlockTags(FriendlyByteBuf friendlyByteBuf) {
        this.key = friendlyByteBuf.readCharSequence(friendlyByteBuf.readInt(), Charset.defaultCharset()).toString();
        this.value = friendlyByteBuf.readInt();
    }

    public ModBlockTags(String string, int n2) {
        this.key = string;
        this.value = n2;
    }

    public static void encode(ModBlockTags bf_02, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(bf_02.key.length());
        friendlyByteBuf.writeCharSequence((CharSequence)bf_02.key, Charset.defaultCharset());
        friendlyByteBuf.writeInt(bf_02.value);
    }

    public static void handle(ModBlockTags bf_02, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> Gamerules.gameruleValuesClient.put(bf_02.key, bf_02.value));
        context.setPacketHandled(true);
    }
}

