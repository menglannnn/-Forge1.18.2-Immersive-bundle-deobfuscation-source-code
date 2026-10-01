/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  abductionmod.content.x
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package com.dev.am.immersive_restraints.config;

import com.dev.am.immersive_restraints.render.RenderHelper;
import com.dev.am.immersive_restraints.command.ArmBindStyleCommand;
import java.nio.charset.Charset;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class CommonConfig {
    private final a type;
    private final float value;
    private final int barId;
    private final String tiedEntityName;

    public CommonConfig(FriendlyByteBuf friendlyByteBuf) {
        this.type = a.valueOf(friendlyByteBuf.readCharSequence(friendlyByteBuf.readInt(), Charset.defaultCharset()).toString());
        this.value = friendlyByteBuf.readFloat();
        this.barId = friendlyByteBuf.readInt();
        this.tiedEntityName = friendlyByteBuf.readCharSequence(friendlyByteBuf.readInt(), Charset.defaultCharset()).toString();
    }

    public CommonConfig(a a2, float f, int n2, String string) {
        this.type = a2;
        this.value = f;
        this.barId = n2;
        this.tiedEntityName = string;
    }

    public static void encode(CommonConfig bs2, FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(bs2.type.toString().length());
        friendlyByteBuf.writeCharSequence((CharSequence)bs2.type.toString(), Charset.defaultCharset());
        friendlyByteBuf.writeFloat(bs2.value);
        friendlyByteBuf.writeInt(bs2.barId);
        friendlyByteBuf.writeInt(bs2.tiedEntityName.length());
        friendlyByteBuf.writeCharSequence((CharSequence)bs2.tiedEntityName, Charset.defaultCharset());
    }

    public static void handle(CommonConfig bs2, Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> x.tyingBars.put(bs2.barId, new RenderHelper.a(80, bs2.value, bs2.type, bs2.tiedEntityName)));
        context.setPacketHandled(true);
    }

    public static enum a {
        TYING(1),
        BEING_TIED(2),
        UNTYING(3),
        STRUGGLING(4),
        STRUGGLING_OVERCHARGE(5),
        NONE(6);

        private final int offset;

        private a(int n3) {
            this.offset = n3;
        }

        public int getOffset() {
            return this.offset;
        }
    }
}

