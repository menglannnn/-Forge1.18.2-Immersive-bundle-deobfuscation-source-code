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
package com.dev.am.immersive_restraints.block;

import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.data.ModRecipes;
import com.dev.am.immersive_restraints.data.ModLootTables;
import com.dev.am.immersive_restraints.data.ModBlockTags;
import com.dev.am.immersive_restraints.data.ModItemTags;
import com.dev.am.immersive_restraints.block.ModBlocks;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.block.BondageBlock;
import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.block.CrossBlock;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
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

public class StocksBlock {
    private static final String PROTOCOL_VERSION = String.valueOf(ModList.get().getMods().stream().filter(iModInfo -> "immersive_restraints".equals(iModInfo.getModId())).findFirst().get().getVersion());
    public static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
        .named(new ResourceLocation("immersive_restraints", "main_channel"))
        .networkProtocolVersion(() -> PROTOCOL_VERSION)
        .clientAcceptedVersions(PROTOCOL_VERSION::equals)
        .serverAcceptedVersions(PROTOCOL_VERSION::equals)
        .simpleChannel();
    private static int disc = 0;

    public static void registerPackets() {
        HANDLER.messageBuilder(CrossBlockEntity.class, disc++)
            .encoder(CrossBlockEntity::encode)
            .decoder(CrossBlockEntity::new)
            .consumerMainThread(CrossBlockEntity::handle)
            .add();
        HANDLER.messageBuilder(StAndrewsCrossBlock.class, disc++)
            .encoder(StAndrewsCrossBlock::encode)
            .decoder(StAndrewsCrossBlock::new)
            .consumerMainThread(StAndrewsCrossBlock::handle)
            .add();
        HANDLER.messageBuilder(ModConfig.class, disc++)
            .encoder(ModConfig::encode)
            .decoder(ModConfig::new)
            .consumerMainThread(ModConfig::handle)
            .add();
        HANDLER.messageBuilder(ModItemTags.class, disc++)
            .encoder(ModItemTags::encode)
            .decoder(ModItemTags::new)
            .consumerMainThread(ModItemTags::handle)
            .add();
        HANDLER.messageBuilder(ModBlockEntities.class, disc++)
            .encoder(ModBlockEntities::encode)
            .decoder(ModBlockEntities::new)
            .consumerMainThread(ModBlockEntities::handle)
            .add();
        HANDLER.messageBuilder(ModLootTables.class, disc++)
            .encoder(ModLootTables::encode)
            .decoder(ModLootTables::new)
            .consumerMainThread(ModLootTables::handle)
            .add();
        HANDLER.messageBuilder(ModRecipes.class, disc++)
            .encoder(ModRecipes::encode)
            .decoder(ModRecipes::new)
            .consumerMainThread(ModRecipes::handle)
            .add();
        HANDLER.messageBuilder(ServerConfig.class, disc++)
            .encoder(ServerConfig::encode)
            .decoder(ServerConfig::new)
            .consumerMainThread(ServerConfig::handle)
            .add();
        HANDLER.messageBuilder(ClientConfig.class, disc++)
            .encoder(ClientConfig::encode)
            .decoder(ClientConfig::new)
            .consumerMainThread(ClientConfig::handle)
            .add();
        HANDLER.messageBuilder(ModBlockTags.class, disc++)
            .encoder(ModBlockTags::encode)
            .decoder(ModBlockTags::new)
            .consumerMainThread(ModBlockTags::handle)
            .add();
        HANDLER.messageBuilder(CommonConfig.class, disc++)
            .encoder(CommonConfig::encode)
            .decoder(CommonConfig::new)
            .consumerMainThread(CommonConfig::handle)
            .add();
        HANDLER.messageBuilder(StocksBlockEntity.class, disc++)
            .encoder(StocksBlockEntity::encode)
            .decoder(StocksBlockEntity::new)
            .consumerMainThread(StocksBlockEntity::handle)
            .add();
        HANDLER.messageBuilder(CrossBlock.class, disc++)
            .encoder(CrossBlock::encode)
            .decoder(CrossBlock::new)
            .consumerMainThread(CrossBlock::handle)
            .add();
        HANDLER.messageBuilder(BondageBlock.class, disc++)
            .encoder(BondageBlock::encode)
            .decoder(BondageBlock::new)
            .consumerMainThread(BondageBlock::handle)
            .add();
        HANDLER.messageBuilder(ModBlocks.class, disc++)
            .encoder(ModBlocks::encode)
            .decoder(ModBlocks::new)
            .consumerMainThread(ModBlocks::handle)
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

