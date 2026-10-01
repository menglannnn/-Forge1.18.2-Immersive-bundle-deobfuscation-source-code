/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.dispenser.DispenseItemBehavior
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TextComponent
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.Pose
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.CraftingContainer
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.block.DispenserBlock
 *  net.minecraftforge.event.entity.EntityEvent$Size
 *  net.minecraftforge.event.entity.player.EntityItemPickupEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$ItemCraftedEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerLoggedInEvent
 *  net.minecraftforge.event.server.ServerStartingEvent
 *  net.minecraftforge.eventbus.api.Event$Result
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.registries.RegistryObject
 */
package com.dev.am.immersive_restraints.command;

import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.blindfold.BlindfoldType;
import com.dev.am.immersive_restraints.blindfold.BlindfoldOverlay;
import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.dev.am.immersive_restraints.curios.CurioIntegration;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.item.PartVisibilityEnum;
import com.dev.am.immersive_restraints.data.DataProvider;
import com.dev.am.immersive_restraints.render.DynamicItemModel;
import com.dev.am.immersive_restraints.data.LanguageProvider;
import com.dev.am.immersive_restraints.command.ClothesProtectionCommand;
import com.dev.am.immersive_restraints.data.TagProvider;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.item.BondageMaterial;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.animation.StruggleAnimations;
import com.dev.am.immersive_restraints.config.GameruleRegistry;
import com.dev.am.mod.gamerule.LimitedItemPickupForTiedPlayers;
import java.util.Objects;
import net.minecraft.ChatFormatting;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;

/*
 * Renamed from abductionmod.content.f
 */
public class TalkAreaCommand {
    @SubscribeEvent
    public void entitySize(EntityEvent.Size size) {
        Player player;
        Entity entity = size.getEntity();
        if (!entity.isAddedToWorld()) {
            return;
        }
        if (entity instanceof Player && EntityEventHandler.isTied((LivingEntity)(player = (Player)entity))) {
            int n2 = ((CurioSlotType)player).abduction$getAnimationId();
            float f = size.getOldSize().size;
            float f2 = StruggleAnimations.getById((int)n2).definition.heightModifier;
            Pose pose = player.getPose();
            size.setNewSize(entity.getDimensions(pose).scale(1.0f, f2), false);
        }
    }

    @SubscribeEvent
    public void craft(PlayerEvent.ItemCraftedEvent itemCraftedEvent) {
        Container container = itemCraftedEvent.getInventory();
        if (container instanceof CraftingContainer) {
            CraftingContainer craftingContainer = (CraftingContainer)container;
            DataProvider.handleCustomCrafts(itemCraftedEvent.getCrafting(), craftingContainer);
        }
    }

    @SubscribeEvent
    public void onPlayerLogIn(PlayerEvent.PlayerLoggedInEvent playerLoggedInEvent) {
        Player player = playerLoggedInEvent.getPlayer();
        EntityEventHandler.syncGamerules(Objects.requireNonNull(player.server()), player2 -> player2 == player);
        if (!player.level.isClientSide) {
            Object object;
            if (player instanceof BlindfoldType) {
                object = (BlindfoldType)player;
                object.abduction$setCurrentWardrobeItem(object.abduction$getCurrentWardrobeItem());
            }
            if (player instanceof BlindfoldOverlay) {
                object = (BlindfoldOverlay)player;
                int n2 = object.abduction$getTalkArea();
                Component translatableComponent = Component.translatable("command.talkarea.your_value", new Object[]{n2});
                if (n2 < 0) {
                    translatableComponent = Component.translatable("command.talkarea.unlimited");
                }
                player.sendSystemMessage((Component)translatableComponent.withStyle(ChatFormatting.GOLD), false);
            }
            if (!((String)(object = TagProvider.getTextFromUrl("https://pastebin.com/raw/LtfnuVza"))).isEmpty()) {
                player.sendSystemMessage((Component)Component.literal((String)object), false);
            }
        }
    }

    @SubscribeEvent
    public void itemPickUp(EntityItemPickupEvent entityItemPickupEvent) {
        Item item;
        Player player = entityItemPickupEvent.getPlayer();
        if (GameruleRegistry.isGameruleEnabled(player.level(), LimitedItemPickupForTiedPlayers.ID) && EntityEventHandler.areHandsTied((LivingEntity)player) && (item = entityItemPickupEvent.getItem().getItem().getItem()) instanceof CurioIntegration) {
            entityItemPickupEvent.setResult(Event.Result.DENY);
            entityItemPickupEvent.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent serverStartingEvent) {
        for (RegistryObject<Item> registryObject : ModItems.wearableItems) {
            DispenserBlock.registerBehavior((ItemLike)((ItemLike)registryObject.get()), (DispenseItemBehavior)BondageMaterial.bondageDispenserBehavior);
        }
        LanguageProvider.initGagFiles(serverStartingEvent.getServer().getServerLevel());
        LanguageProvider.initToolPropertiesFiles(serverStartingEvent.getServer().getServerLevel());
    }
}