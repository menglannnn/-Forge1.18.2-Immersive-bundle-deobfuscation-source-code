/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dev.am.immersive_restraints.item.VulnerabilityType
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.ChatFormatting
 *  net.minecraft.core.NonNullList
 *  net.minecraft.core.Registry
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.Style
 *  net.minecraft.network.chat.TextComponent
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.ai.behavior.BehaviorUtils
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.Event$Result
 *  net.minecraftforge.fml.ModContainer
 *  net.minecraftforge.fml.ModList
 *  org.jetbrains.annotations.NotNull
 *  org.spongepowered.asm.mixin.Unique
 *  top.theillusivec4.curios.api.CuriosApi
 *  top.theillusivec4.curios.api.SlotContext
 *  top.theillusivec4.curios.api.SlotResult
 *  top.theillusivec4.curios.api.event.CurioEquipEvent
 *  top.theillusivec4.curios.api.type.capability.ICurio
 *  top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler
 *  top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler
 *  top.theillusivec4.curios.api.type.util.ICuriosHelper
 */
package com.dev.am.immersive_restraints.mechanic;

import com.dev.am.immersive_restraints.blindfold.BlindfoldType;
import com.dev.am.immersive_restraints.blindfold.BlindfoldEffect;
import com.dev.am.immersive_restraints.blindfold.BlindfoldOverlay;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.dev.am.immersive_restraints.item.BondageItem;
import com.dev.am.immersive_restraints.network.StateSyncPacket;
import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.data.SkinProvider;
import com.dev.am.immersive_restraints.data.LanguageProvider;
import com.dev.am.immersive_restraints.animation.AnimationType;
import com.dev.am.immersive_restraints.item.VulnerabilityType;
import com.dev.am.immersive_restraints.data.ModBlockTags;
import com.dev.am.immersive_restraints.block.StocksBlock;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.init.ModSounds;
import com.dev.am.immersive_restraints.render.RenderHelper;
import com.dev.am.immersive_restraints.ImmersiveRestraints;
import com.dev.am.immersive_restraints.config.ArmorIsHiddenWhenTied;
import com.dev.am.immersive_restraints.config.GameruleRegistry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Unique;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.event.CurioEquipEvent;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;

public class bx {
    private static final Comparator<ItemStack> weightComparator = (itemStack, itemStack2) -> {
        int n2;
        int n3 = bx.getBondageSortWeight(itemStack);
        if (n3 < (n2 = bx.getBondageSortWeight(itemStack2))) {
            return 1;
        }
        if (n3 > n2) {
            return -1;
        }
        return 0;
    };
    public static Predicate<ItemStack> untyingStackPredicate = itemStack -> {
        Item item = itemStack.getItem();
        if (item instanceof aK) {
            aK aK2 = (aK)item;
            if (aK2.properties.isNeedsKeyForUntie((ItemStack)itemStack)) {
                return false;
            }
        }
        return true;
    };
    public static Predicate<ItemStack> struggleStackPredicate = itemStack -> true;
    private static Boolean checkedDevEnv = null;

    public static List<SlotResult> getBondages(LivingEntity livingEntity) {
        if (livingEntity == null) {
            return List.of();
        }
        return CuriosApi.getCuriosHelper().findCurios(livingEntity, itemStack -> itemStack.getItem() instanceof aK);
    }

    @Nullable
    public static ItemStack getBondageInSlot(LivingEntity livingEntity, @Nonnull String string) {
        for (SlotResult slotResult : bx.getBondages(livingEntity)) {
            if (!string.equals(slotResult.slotContext().identifier())) continue;
            return slotResult.stack();
        }
        return null;
    }

    @Nullable
    public static ItemStack getMainClothes(LivingEntity livingEntity) {
        if (livingEntity instanceof aD) {
            aD aD2 = (aD)livingEntity;
            return aD2.abduction$getCurrentWardrobeItem();
        }
        return null;
    }

    @Nonnull
    public static String getArmTyingStyle(LivingEntity livingEntity) {
        String string = "am_tied";
        for (SlotResult slotResult : bx.getBondages(livingEntity)) {
            ItemStack itemStack = slotResult.stack();
            Object object = itemStack.getItem();
            if (!(object instanceof aK)) continue;
            aK aK2 = (aK)object;
            object = aK2.properties.getArmAnimation(itemStack);
            if (object == null || ((String)object).equals(string)) continue;
            return object;
        }
        return string;
    }

    public static void setMainClothes(LivingEntity livingEntity, ItemStack itemStack) {
        if (livingEntity instanceof aD) {
            aD aD2 = (aD)livingEntity;
            aD2.abduction$setCurrentWardrobeItem(itemStack);
        }
    }

    public static boolean areHandsTied(LivingEntity livingEntity) {
        if (livingEntity.isSpectator()) {
            return false;
        }
        return bx.getBondages(livingEntity).stream().anyMatch(slotResult -> ((aK)slotResult.stack().getItem()).properties.isBindHands(slotResult.stack()));
    }

    public static boolean isGagged(LivingEntity livingEntity) {
        if (livingEntity.isSpectator()) {
            return false;
        }
        return bx.getBondages(livingEntity).stream().anyMatch(slotResult -> ((aK)slotResult.stack().getItem()).properties.isGag(slotResult.stack()));
    }

    public static boolean hasMittens(LivingEntity livingEntity) {
        if (livingEntity.isSpectator()) {
            return false;
        }
        return bx.getBondages(livingEntity).stream().anyMatch(slotResult -> slotResult.stack().is((Item)aS.MITTENS.get()));
    }

    public static boolean areLegsTied(LivingEntity livingEntity) {
        aG aG2 = (aG)livingEntity;
        if (aG2.abduction$getLegsUntied()) {
            return false;
        }
        if (livingEntity.isSpectator()) {
            return false;
        }
        return bx.hasLegBinds(livingEntity);
    }

    public static boolean hasLegBinds(LivingEntity livingEntity) {
        return bx.getBondages(livingEntity).stream().anyMatch(slotResult -> ((aK)slotResult.stack().getItem()).properties.isBindLegs(slotResult.stack()));
    }

    public static boolean isBlindfolded(LivingEntity livingEntity) {
        if (livingEntity.isSpectator()) {
            return false;
        }
        return bx.getBondages(livingEntity).stream().anyMatch(slotResult -> ((aK)slotResult.stack().getItem()).properties.isBlind(slotResult.stack()));
    }

    @Nullable
    public static ItemStack getBlindfold(LivingEntity livingEntity) {
        for (SlotResult slotResult : bx.getBondages(livingEntity)) {
            if (!((aK)slotResult.stack().getItem()).properties.isBlind(slotResult.stack())) continue;
            return slotResult.stack();
        }
        return null;
    }

    public static boolean isDeafened(LivingEntity livingEntity) {
        if (livingEntity.isSpectator()) {
            return false;
        }
        return bx.getBondages(livingEntity).stream().anyMatch(slotResult -> ((aK)slotResult.stack().getItem()).properties.isDeafen(slotResult.stack()));
    }

    public static boolean isFullyTied(LivingEntity livingEntity) {
        return bx.areLegsTied(livingEntity) && bx.areHandsTied(livingEntity);
    }

    public static boolean isTied(LivingEntity livingEntity) {
        return bx.areHandsTied(livingEntity) || bx.areLegsTied(livingEntity);
    }

    public static void syncGamerules(MinecraftServer minecraftServer, Predicate<Player> predicate) {
        for (ServerPlayer serverPlayer : minecraftServer.getPlayerList().getPlayers()) {
            if (!predicate.test((Player)serverPlayer)) continue;
            for (String string : GameruleRegistry.gamerules.keySet()) {
                int n2 = 0;
                switch (GameruleRegistry.gameruleDataTypes.get(string)) {
                    case BOOLEAN: {
                        n2 = GameruleRegistry.isGameruleEnabled(serverPlayer.level, string) ? 1 : 0;
                        break;
                    }
                    case INTEGER: {
                        n2 = GameruleRegistry.getGameruleValue(serverPlayer.level, string);
                    }
                }
                bj.sendToClient(serverPlayer, new bf_0(string, n2));
            }
        }
    }

    public static int calculateTyerHash(LivingEntity livingEntity, LivingEntity livingEntity2, ItemStack itemStack) {
        String string = itemStack.getOrCreateTagElement().getOrCreateTag().toString();
        int n2 = (livingEntity.getId().toString() + livingEntity2.getId() + itemStack.getItem().getRegistryName()).hashCode() + string.hashCode();
        if (n2 == 0) {
            ++n2;
        }
        return n2;
    }

    public static boolean canUseElytra(LivingEntity livingEntity) {
        return !bx.isTied(livingEntity);
    }

    public static a canPutBondage(LivingEntity livingEntity, ItemStack itemStack) {
        aF aF2;
        aG aG2 = (aG)livingEntity;
        if (livingEntity instanceof aF && (aF2 = (aF)livingEntity).abduction$isImmuneToBondage()) {
            return a.FAIL;
        }
        if (EnchantmentHelper.hasBindingCurse((ItemStack)itemStack) && !bx.isTied(livingEntity)) {
            return a.FAIL;
        }
        if (aG2.abduction$getProtectedClothes() && itemStack.getItem() instanceof aP) {
            return a.FAIL;
        }
        aF2 = CuriosApi.getCuriosHelper();
        AtomicReference<a> atomicReference = new AtomicReference<a>(a.FAIL);
        aF2.getCurio(itemStack).ifPresent(arg_0 -> bx.lambda$canPutBondage$9((ICuriosHelper)aF2, livingEntity, itemStack, atomicReference, arg_0));
        return atomicReference.get();
    }

    public static void putBondageItemInto(LivingEntity livingEntity, @Nullable LivingEntity livingEntity2, ItemStack itemStack, @Nullable String string, boolean bl2) {
        ICuriosHelper iCuriosHelper = CuriosApi.getCuriosHelper();
        iCuriosHelper.getCurio(itemStack).ifPresent(iCurio -> iCuriosHelper.getCuriosHandler(livingEntity).ifPresent(iCuriosItemHandler -> {
            Map map = iCuriosItemHandler.getCurios();
            block0: for (Map.Entry entry : map.entrySet()) {
                IDynamicStackHandler iDynamicStackHandler = ((ICurioStacksHandler)entry.getValue()).getStacks();
                for (int i = 0; i < iDynamicStackHandler.getSlots(); ++i) {
                    ItemStack itemStack2;
                    ItemStack itemStack3;
                    boolean bl3;
                    String string2 = (String)entry.getKey();
                    NonNullList nonNullList = ((ICurioStacksHandler)entry.getValue()).getRenders();
                    SlotContext slotContext = new SlotContext(string2, livingEntity, i, false, nonNullList.size() > i && (Boolean)nonNullList.get(i) != false);
                    if (string != null && !slotContext.identifier().equals(string)) continue;
                    CurioEquipEvent curioEquipEvent = new CurioEquipEvent(itemStack, slotContext);
                    MinecraftForge.EVENT_BUS.post((Event)curioEquipEvent);
                    Event.Result result = curioEquipEvent.getResult();
                    if (result == Event.Result.DENY || result != Event.Result.ALLOW && !iCuriosHelper.isStackValid(slotContext, itemStack) || !(bl3 = (itemStack3 = iDynamicStackHandler.getStackInSlot(i)).isEmpty()) && !bl2 && !bx.canBeSwapped(itemStack3, itemStack)) continue;
                    if (!bl3) {
                        itemStack2 = itemStack3.getOrCreateTagElement();
                        if (livingEntity instanceof Player) {
                            Player player = (Player)livingEntity;
                            player.add(itemStack2, false, true);
                        } else {
                            BehaviorUtils.throwItem((LivingEntity)livingEntity, (ItemStack)itemStack2, (Vec3)new Vec3(0.0, 0.0, 0.0));
                        }
                    }
                    iDynamicStackHandler.setStackInSlot(i, itemStack.getOrCreateTagElement());
                    iCurio.onEquipFromUse(slotContext);
                    if (livingEntity2 == null || livingEntity2 instanceof Player && (itemStack2 = (Player)livingEntity2).isSpectator()) break block0;
                    int n2 = itemStack.getUseDuration();
                    itemStack.shrink(n2);
                    break block0;
                }
            }
        }));
    }

    @Nullable
    public static ItemStack getFirstBondageWithBaseResistance(LivingEntity livingEntity, Predicate<ItemStack> predicate) {
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        if (livingEntity instanceof Player) {
            Player player = (Player)livingEntity;
            for (SlotResult slotResult : bx.getBondages((LivingEntity)player)) {
                Item item;
                if (!predicate.test(slotResult.stack()) || !((item = slotResult.stack().getItem()) instanceof aK)) continue;
                aK aK2 = (aK)item;
                if (aK2.properties.getBaseResistance(slotResult.stack()) <= 0) continue;
                arrayList.add(slotResult.stack());
            }
        }
        if (!arrayList.isEmpty()) {
            arrayList.sort(weightComparator);
            return (ItemStack)arrayList.get(0);
        }
        return null;
    }

    private static int getBondageSortWeight(ItemStack itemStack) {
        aK aK2 = (aK)itemStack.getItem();
        int n2 = aK2.properties.getSortWeight(itemStack);
        if (aK2.properties.isBindHands(itemStack)) {
            n2 += 10;
        }
        if (aK2.properties.isBindLegs(itemStack)) {
            n2 += 8;
        }
        if (aK2.properties.isBlind(itemStack)) {
            n2 += 6;
        }
        if (aK2.properties.isDeafen(itemStack)) {
            n2 += 4;
        }
        if (aK2.properties.isGag(itemStack)) {
            n2 += 2;
        }
        return n2;
    }

    public static List<SlotResult> getBondagesForRendering(LivingEntity livingEntity) {
        ItemStack itemStack;
        List<SlotResult> list = bx.getBondages(livingEntity);
        if (x_0.hoveredStack != null && !x_0.hoveredStack.isEmpty() && x_0.hoveredStack.getItem() instanceof aK) {
            list.add(new SlotResult(null, x_0.hoveredStack));
        }
        if ((itemStack = bx.getMainClothes(livingEntity)) != null && !itemStack.isEmpty()) {
            list.add(0, new SlotResult(null, itemStack));
        }
        return list;
    }

    @Unique
    @Nullable
    public static ResourceLocation abduction$getTextureLocationClothes(ItemStack itemStack) {
        return bD.getResourceLocationFromTextureTag(itemStack, bw.DYNAMIC_CLOTHES_TEXTURE_TAG, bD.NO_SKIN);
    }

    @Nullable
    public static InteractionResult checkFinishedUntying(Player player, LivingEntity livingEntity, aG aG2) {
        if (aG2.abduction$isFinishedUnTying()) {
            ItemStack itemStack2 = bx.getFirstBondageWithBaseResistance(livingEntity, untyingStackPredicate);
            if (itemStack2 == null) {
                return InteractionResult.FAIL;
            }
            bx.tryTakeOffBondage((LivingEntity)player, livingEntity, itemStack2, null, null, itemStack -> player.getInventory().add(itemStack));
            aG2.abduction$stopTyingProcess();
            return InteractionResult.SUCCESS;
        }
        return null;
    }

    public static int getResistanceOfFirstBondage(LivingEntity livingEntity, Predicate<ItemStack> predicate) {
        ItemStack itemStack = bx.getFirstBondageWithBaseResistance(livingEntity, predicate);
        if (itemStack == null) {
            return 0;
        }
        return ((aK)itemStack.getItem()).properties.getResistance(itemStack);
    }

    public static float getUntyingPower(ItemStack itemStack, ItemStack itemStack2) {
        if (itemStack == null || itemStack2 == null) {
            return 0.0f;
        }
        Object object = itemStack.getItem();
        if (object instanceof aK) {
            String string;
            aK aK2 = (aK)object;
            object = null;
            if (itemStack2.getItem() instanceof ba_0) {
                object = ((ba_0)itemStack2.getItem()).getToolMaterial();
            }
            if (bE.toolMaterials.containsKey(string = String.valueOf(BuiltInRegistries.ITEM.getKey((Object)itemStack2.getItem())))) {
                object = (bb)bE.toolMaterials.get(string);
            }
            if (object != null) {
                ArrayList<Float> arrayList = new ArrayList<Float>();
                for (bb_0.a a2 : object.vulnerabilityMap.keySet()) {
                    Float f = (Float)object.vulnerabilityMap.get((Object)a2);
                    if (!aK2.properties.vulnerabilityMap.containsKey((Object)a2)) continue;
                    arrayList.add(Float.valueOf(aK2.properties.vulnerabilityMap.get((Object)a2).floatValue() * f.floatValue()));
                }
                if (!arrayList.isEmpty()) {
                    arrayList.sort(Float::compareTo);
                    Collections.reverse(arrayList);
                    return ((Float)arrayList.get(0)).floatValue();
                }
            }
        }
        return 0.0f;
    }

    public static int getColorFromString(@Nonnull String string) {
        return Math.abs(string.hashCode()) % 0xFFFFFF;
    }

    public static String generateRandomKey() {
        return bx.generateRandomKey(null);
    }

    public static String generateRandomKey(@Nullable Long l) {
        String string = "\u2551\u2552\u2553\u2554\u2555\u2556\u2557\u2558\u2559\u255a\u255b\u255c\u255d\u255e\u255f\u2560\u2561\u2562\u2563\u2564\u2565\u2566\u2567\u2568\u2569\u256a\u256b\u256c";
        Random random = l == null ? new Random() : new Random(l);
        StringBuilder stringBuilder = new StringBuilder(6);
        for (int i = 0; i < 6; ++i) {
            stringBuilder.append(string.charAt((int)(random.nextFloat() * (float)string.length())));
        }
        return stringBuilder.toString();
    }

    public static void tryTakeOffBondage(@Nullable LivingEntity livingEntity, LivingEntity livingEntity2, @Nullable ItemStack itemStack, @Nullable ItemStack itemStack2, @Nullable SoundEvent soundEvent, Consumer<ItemStack> consumer) {
        Level level = livingEntity2.level();
        List<SlotResult> list = bx.getBondages(livingEntity2);
        for (SlotResult slotResult : list) {
            ItemStack itemStack3 = slotResult.stack();
            if (itemStack != null && itemStack3 != itemStack) continue;
            aK aK2 = (aK)itemStack3.getItem();
            if (itemStack2 != null && !aK2.takeOffItemPredicate.test(itemStack3, itemStack2)) continue;
            if (soundEvent != null) {
                bx.playSound(livingEntity2, soundEvent);
            }
            if (level.isClientSide) break;
            if (aK2.properties.isLocked(itemStack3) && itemStack2 != null) {
                bw.setCustomTag(itemStack3, false, "isLocked");
                if (!aK2.properties.isPopsOffAfterUnlock()) {
                    if (!(livingEntity instanceof Player)) break;
                    Player player = (Player)livingEntity;
                    player.sendSystemMessage((Component)Component.translatable("item.bondage.unlocked_bondage").withStyle(ChatFormatting.GOLD), true);
                    break;
                }
            }
            ItemStack itemStack4 = itemStack3.getOrCreateTagElement();
            bw.removeCustomTag(itemStack4, "struggleAddedResistance");
            consumer.accept(itemStack4);
            itemStack3.shrink(1);
            break;
        }
        bx.checkDropLeash(livingEntity2);
    }

    public static void playSound(LivingEntity livingEntity, SoundEvent soundEvent) {
        livingEntity.level.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), soundEvent, SoundSource.NEUTRAL, 0.5f, livingEntity.getRandom().nextFloat() * 0.4f + 0.6f);
    }

    public static void checkDropLeash(LivingEntity livingEntity) {
        if (!bx.isTied(livingEntity) && bx.getBondageInSlot(livingEntity, "collar") == null && livingEntity instanceof aE) {
            aE aE2 = (aE)livingEntity;
            aE2.abduction$dropLeash(true, true);
        }
    }

    public static boolean canBeSwapped(ItemStack itemStack, ItemStack itemStack2) {
        Item item = itemStack.getItem();
        if (!(item instanceof aK)) {
            return false;
        }
        aK aK2 = (aK)item;
        if (aK2.properties.isNeedsKeyForUntie(itemStack)) {
            return false;
        }
        return !ItemStack.isSameItemSameTags((ItemStack)itemStack, (ItemStack)itemStack2);
    }

    public static boolean canBeTakenOffFreely(ItemStack itemStack) {
        Item item = itemStack.getItem();
        if (!(item instanceof aK)) {
            return false;
        }
        aK aK2 = (aK)item;
        if (aK2.properties.getResistance(itemStack) > 0) {
            return false;
        }
        return !aK2.properties.isNeedsKeyForUntie(itemStack);
    }

    public static boolean isDevEnvironment() {
        if (checkedDevEnv != null) {
            return checkedDevEnv;
        }
        checkedDevEnv = ((ModContainer)ModList.get().getModContainerById("immersive_restraints").get()).getModInfo().getVersion().toString().contains("NONE");
        return checkedDevEnv;
    }

    public static void debugLog(String string) {
        if (bx.isDevEnvironment()) {
            Abduction.LOGGER.info("\ud83d\udcac " + string);
        }
    }

    public static boolean shouldDrawArmorStack(@Nonnull LivingEntity livingEntity, ItemStack itemStack) {
        if (bx.isTied(livingEntity)) {
            Boolean bl2 = bw.getCustomTagBool(itemStack, "armorVisibility");
            if (bl2 != null) {
                return bl2;
            }
            return !GameruleRegistry.isGameruleEnabled(livingEntity.level, ArmorIsHiddenWhenTied.ID);
        }
        return true;
    }

    public static void takeOffAllBondage(Player player) {
        List<SlotResult> list = bx.getBondages((LivingEntity)player);
        for (SlotResult slotResult : list) {
            if (slotResult.stack().isEmpty() || slotResult.stack().getItem() instanceof aP || slotResult.stack().getItem() == aS.COLLAR.get()) continue;
            bx.tryTakeOffBondage(null, (LivingEntity)player, slotResult.stack(), null, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, itemStack -> player.getInventory(itemStack, false));
        }
        bx.checkDropLeash((LivingEntity)player);
    }

    public static void useKeyOnPlayer(@Nullable Player player, Player player2, ItemStack itemStack2) {
        aK aK2;
        ItemStack itemStack3;
        boolean bl2 = false;
        List<SlotResult> list = bx.getBondages((LivingEntity)player2);
        for (SlotResult slotResult : list) {
            itemStack3 = slotResult.stack();
            aK2 = (aK)itemStack3.getItem();
            if (!aK2.takeOffItemPredicate.test(itemStack3, itemStack2)) continue;
            bl2 = aK2.properties.isLocked(itemStack3);
            break;
        }
        if (bl2) {
            bx.tryTakeOffBondage((LivingEntity)player, (LivingEntity)player2, null, itemStack2, (SoundEvent)g_0.COLLAR_KEY_OPEN.get(), itemStack -> player2.getInventory(itemStack, false));
        } else {
            for (SlotResult slotResult : list) {
                itemStack3 = slotResult.stack();
                aK2 = (aK)itemStack3.getItem();
                if (!aK2.takeOffItemPredicate.test(itemStack3, itemStack2)) continue;
                bw.setCustomTag(itemStack3, true, "isLocked");
                if (player != null) {
                    player.sendSystemMessage((Component)Component.translatable("item.bondage.locked_bondage").withStyle(ChatFormatting.GOLD), true);
                }
                bx.playSound((LivingEntity)player2, (SoundEvent)g_0.COLLAR_KEY_CLOSE.get());
                break;
            }
        }
    }

    @NotNull
    public static List<Entity> getLeashedList(ServerPlayer serverPlayer) {
        aE aE2 = (aE)serverPlayer;
        if (aE2.abduction$getLeashHolder() != null) {
            return Collections.emptyList();
        }
        return serverPlayer.server().getPlayerList().getPlayers().stream().filter(serverPlayer2 -> {
            aE aE2;
            return serverPlayer2 instanceof aE && (aE2 = (aE)serverPlayer2).abduction$getLeashHolder() == serverPlayer && serverPlayer != serverPlayer2;
        }).collect(Collectors.toList());
    }

    public static boolean hasCollarDisplayName(LivingEntity livingEntity) {
        ItemStack itemStack = bx.getBondageInSlot(livingEntity, "collar");
        return itemStack != null && bw.hasCustomTag(itemStack, bw.COLLAR_DISPLAY_NAME_TAG);
    }

    public static Component transformPlayerNameWithCollar(LivingEntity livingEntity, Component component) {
        if (bx.hasCollarDisplayName(livingEntity)) {
            ItemStack itemStack = bx.getBondageInSlot(livingEntity, "collar");
            String string = bw.getCustomTag(itemStack, bw.COLLAR_DISPLAY_NAME_TAG);
            Style style = component.getStyle();
            component = Component.literal(string).withStyle(style);
        }
        return component;
    }

    private static /* synthetic */ void lambda$canPutBondage$9(ICuriosHelper iCuriosHelper, LivingEntity livingEntity, ItemStack itemStack, AtomicReference atomicReference, ICurio iCurio) {
        iCuriosHelper.getCuriosHandler(livingEntity).ifPresent(iCuriosItemHandler -> {
            Map map = iCuriosItemHandler.getCurios();
            block0: for (Map.Entry entry : map.entrySet()) {
                IDynamicStackHandler iDynamicStackHandler = ((ICurioStacksHandler)entry.getValue()).getStacks();
                for (int i = 0; i < iDynamicStackHandler.getSlots(); ++i) {
                    String string = (String)entry.getKey();
                    NonNullList nonNullList = ((ICurioStacksHandler)entry.getValue()).getRenders();
                    SlotContext slotContext = new SlotContext(string, livingEntity, i, false, nonNullList.size() > i && (Boolean)nonNullList.get(i) != false);
                    CurioEquipEvent curioEquipEvent = new CurioEquipEvent(itemStack, slotContext);
                    MinecraftForge.EVENT_BUS.post((Event)curioEquipEvent);
                    Event.Result result = curioEquipEvent.getResult();
                    if (result == Event.Result.DENY || result != Event.Result.ALLOW && (!iCuriosHelper.isStackValid(slotContext, itemStack) || !iCurio.canEquip(slotContext))) continue;
                    ItemStack itemStack2 = iDynamicStackHandler.getStackInSlot(i);
                    if (itemStack2.isEmpty()) {
                        atomicReference.set(a.TIE);
                        break block0;
                    }
                    if (!bx.canBeSwapped(itemStack2, itemStack)) continue;
                    atomicReference.set(a.SWAP);
                    break block0;
                }
            }
        });
    }

    public static enum a {
        FAIL,
        SWAP,
        TIE;

    }
}

