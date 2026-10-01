/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.chunk.LevelChunk
 *  net.minecraftforge.fml.ModList
 *  net.minecraftforge.network.NetworkDirection
 *  net.minecraftforge.network.NetworkRegistry
 *  net.minecraftforge.network.PacketDistributor
 *  net.minecraftforge.network.simple.SimpleChannel
 */
package com.dev.am.immersive_restraints.network;

import com.dev.am.immersive_restraints.data.ModRecipes;
import com.dev.am.immersive_restraints.data.ModLootTables;
import com.dev.am.immersive_restraints.data.ModBlockTags;
import com.dev.am.immersive_restraints.data.ModItemTags;
import com.dev.am.immersive_restraints.block.ModBlocks;
import com.dev.am.immersive_restraints.block.BondageBlock;
import com.dev.am.immersive_restraints.block.CrossBlock;
import com.dev.am.immersive_restraints.block.StAndrewsCrossBlock;
import com.dev.am.immersive_restraints.blockentity.ModBlockEntities;
import com.dev.am.immersive_restraints.blockentity.StocksBlockEntity;
import com.dev.am.immersive_restraints.blockentity.CrossBlockEntity;
import com.dev.am.immersive_restraints.config.ModConfig;
import com.dev.am.immersive_restraints.config.ClientConfig;
import com.dev.am.immersive_restraints.config.CommonConfig;
import com.dev.am.immersive_restraints.config.ServerConfig;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class bj {
    private static final String PROTOCOL_VERSION = String.valueOf(ModList.get().getMods().stream().filter(iModInfo -> "immersive_restraints".equals(iModInfo.getModId())).findFirst().get().getVersion());
    public static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
        .named(new ResourceLocation("immersive_restraints", "main_channel"))
        .networkProtocolVersion(() -> PROTOCOL_VERSION)
        .clientAcceptedVersions(PROTOCOL_VERSION::equals)
        .serverAcceptedVersions(PROTOCOL_VERSION::equals)
        .simpleChannel();
    private static int disc = 0;

    public static void registerPackets() {
        HANDLER.messageBuilder(bo.class, disc++)
            .encoder(bo::encode)
            .decoder(bo::new)
            .consumerMainThread(bo::handle)
            .add();
        HANDLER.messageBuilder(bl.class, disc++)
            .encoder(bl::encode)
            .decoder(bl::new)
            .consumerMainThread(bl::handle)
            .add();
        HANDLER.messageBuilder(bq.class, disc++)
            .encoder(bq::encode)
            .decoder(bq::new)
            .consumerMainThread(bq::handle)
            .add();
        HANDLER.messageBuilder(bg_0.class, disc++)
            .encoder(bg_0::encode)
            .decoder(bg_0::new)
            .consumerMainThread(bg_0::handle)
            .add();
        HANDLER.messageBuilder(bm.class, disc++)
            .encoder(bm::encode)
            .decoder(bm::new)
            .consumerMainThread(bm::handle)
            .add();
        HANDLER.messageBuilder(bd_0.class, disc++)
            .encoder(bd_0::encode)
            .decoder(bd_0::new)
            .consumerMainThread(bd_0::handle)
            .add();
        HANDLER.messageBuilder(bc_0.class, disc++)
            .encoder(bc_0::encode)
            .decoder(bc_0::new)
            .consumerMainThread(bc_0::handle)
            .add();
        HANDLER.messageBuilder(bt.class, disc++)
            .encoder(bt::encode)
            .decoder(bt::new)
            .consumerMainThread(bt::handle)
            .add();
        HANDLER.messageBuilder(br.class, disc++)
            .encoder(br::encode)
            .decoder(br::new)
            .consumerMainThread(br::handle)
            .add();
        HANDLER.messageBuilder(bf_0.class, disc++)
            .encoder(bf_0::encode)
            .decoder(bf_0::new)
            .consumerMainThread(bf_0::handle)
            .add();
        HANDLER.messageBuilder(bs.class, disc++)
            .encoder(bs::encode)
            .decoder(bs::new)
            .consumerMainThread(bs::handle)
            .add();
        HANDLER.messageBuilder(bn.class, disc++)
            .encoder(bn::encode)
            .decoder(bn::new)
            .consumerMainThread(bn::handle)
            .add();
        HANDLER.messageBuilder(bk.class, disc++)
            .encoder(bk::encode)
            .decoder(bk::new)
            .consumerMainThread(bk::handle)
            .add();
        HANDLER.messageBuilder(bi.class, disc++)
            .encoder(bi::encode)
            .decoder(bi::new)
            .consumerMainThread(bi::handle)
            .add();
        HANDLER.messageBuilder(bh.class, disc++)
            .encoder(bh::encode)
            .decoder(bh::new)
            .consumerMainThread(bh::handle)
            .add();
    }

    public static void sendToClient(ServerPlayer serverPlayer, Object object) {
        HANDLER.sendTo(object, serverPlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToChunk(Object object, Supplier<LevelChunk> supplier) {
        HANDLER.send(PacketDistributor.TRACKING_CHUNK.with(supplier), object);
    }

    public static void sendToTrackingEntity(Object object, Supplier<Entity> supplier) {
        HANDLER.send(PacketDistributor.TRACKING_ENTITY.with(supplier), object);
    }

    public static void sendToAll(Object object) {
        HANDLER.send(PacketDistributor.ALL.noArg(), object);
    }

    public static void sendToServer(Object object) {
        HANDLER.sendToServer(object);
    }
}

