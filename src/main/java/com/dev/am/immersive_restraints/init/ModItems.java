/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dev.am.immersive_restraints.animation.StruggleAnimations
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.item.CreativeModeTab
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 *  org.jetbrains.annotations.NotNull
 */
package com.dev.am.immersive_restraints.init;

import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.item.PartVisibilityEnum;
import com.dev.am.immersive_restraints.network.PacketHandler;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.item.BondageItem;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.network.StrugglePacket;
import com.dev.am.immersive_restraints.network.StateSyncPacket;
import com.dev.am.immersive_restraints.network.SkinSyncPacket;
import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.network.ConfigSyncPacket;
import com.dev.am.immersive_restraints.core.ModCore;
import com.dev.am.immersive_restraints.render.ArmorLayerRenderer;
import com.dev.am.immersive_restraints.render.BondageRenderer;
import com.dev.am.immersive_restraints.render.GeoArmorModelBase;
import com.dev.am.immersive_restraints.render.GeoArmorRenderer;
import com.dev.am.immersive_restraints.item.GeoArmorBase;
import com.dev.am.immersive_restraints.core.CreativeTab;
import com.dev.am.immersive_restraints.item.BondageProperties;
import com.dev.am.immersive_restraints.command.SetCollarNameCommand;
import com.dev.am.immersive_restraints.item.VulnerabilityType;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModSounds;
import com.dev.am.immersive_restraints.item.PartVisibility;
import com.dev.am.immersive_restraints.animation.StruggleAnimations;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModItems {
    public static final ArrayList<RegistryObject<Item>> wearableItems = new ArrayList();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, "immersive_restraints");
    public static final RegistryObject<Item> COLOR_MARK = ITEMS.register("color_mark", () -> new BondageRenderer(new Item.Properties()));
    public static final RegistryObject<Item> CHLOROFORM = ITEMS.register("chloroform", StrugglePacket::new);
    public static final RegistryObject<Item> RAG = ITEMS.register("rag", ArmorLayerRenderer::new);
    public static final RegistryObject<Item> STRAITJACKET_MODEL_ITEM = ITEMS.register("straitjacket_model", () -> new GeoArmorBase(new Item.Properties(), EquipmentSlot.CHEST, "straitjacket"));
    public static final RegistryObject<Item> DYNAMIC_MODEL_ITEM = ITEMS.register("dynamic_item_model", GeoArmorRenderer::new);
    public static final RegistryObject<Item> CLOTHES = ModItems.registerClothes("clothes", BondageProperties.create());
    public static final RegistryObject<Item> EARPLUGS = ModItems.registerBondage("earplugs", BondageProperties.create().withDeafen().withPartVisibility(PartVisibility.modelHeadOnly), null, 16);
    public static CreativeModeTab ABDUCTION_ITEM_GROUP = CreativeTab.ABDUCTION_ITEM_GROUP;
    public static final RegistryObject<Item> STEEL_KEY = ITEMS.register("steel_key", PacketHandler::new);
    public static final RegistryObject<Item> HANDCUFFS = ModItems.registerBondage("handcuffs", BondageProperties.create().withAutoLocksOnEquip().withPopsOffAfterUnlockWithKey().withPartVisibility(PartVisibility.modelArmsOnly).withBindHands().withResistance(350).withSound((Supplier<SoundEvent>)ModSounds.COLLAR_PUT).withVulnerableTo(VulnerabilityType.Kind.METAL_CUTTING, 2.0f).withVulnerableTo(VulnerabilityType.Kind.METAL_FILING, 1.0f), ModItems.keyPredicate(STEEL_KEY), 16);
    public static final RegistryObject<Item> MITTENS = ModItems.registerBondage("mittens", BondageProperties.create().withDrawsInFirstPerson().withStruggleAnimation(() -> k.mittensStruggle).withSeparateSlimTexture().withPartVisibility(PartVisibility.modelArmsOnly).withResistance(50).withSortWeight(50).withLockedResistance(350).withSound(() -> SoundEvents.ARMOR_EQUIP_LEATHER).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 2.0f).withVulnerableTo(VulnerabilityType.Kind.FIRE, 1.0f), ModItems.keyPredicate(STEEL_KEY), 16);
    public static final RegistryObject<Item> COLLAR_KEY = ITEMS.register("collar_key", PacketHandler::new);
    public static final RegistryObject<Item> COLLAR = ModItems.registerBondage("collar", BondageProperties.create().withPartVisibility(PartVisibility.torsoOnly).withStruggleAnimation(() -> k.collarStruggle).withLockedResistance(450).withVulnerableTo(VulnerabilityType.Kind.METAL_CUTTING, 1.0f).withVulnerableTo(VulnerabilityType.Kind.METAL_FILING, 1.0f), ModItems.keyPredicate(COLLAR_KEY), 1);
    public static final RegistryObject<Item> KNIFE = ITEMS.register("knife", SkinSyncPacket::new);
    public static final RegistryObject<Item> METAL_FILE = ITEMS.register("metal_file", ConfigSyncPacket::new);
    public static final RegistryObject<Item> ARMBINDER = ModItems.registerBondage("armbinder", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(150).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f).withArmAnimation(k.ARM_ANIM_ARMBINDER).withSeparateSlimTexture(), ModItems.keyPredicate(STEEL_KEY), 16);
    public static final RegistryObject<Item> LATEX_SACK = ModItems.registerBondage("latex_sack", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(400).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f).withArmAnimation(k.ARM_ANIM_FLAT_INTO_BODY), ModItems.keyPredicate(STEEL_KEY), 16);
    public static final RegistryObject<Item> CHAIN = ModItems.registerBondage("chain", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(500).withSound((Supplier<SoundEvent>)ModSounds.CHAIN).withVulnerableTo(VulnerabilityType.Kind.METAL_CUTTING, 1.0f).withVulnerableTo(VulnerabilityType.Kind.METAL_FILING, 1.0f), ModItems.keyPredicate(STEEL_KEY), 16);
    public static final RegistryObject<Item> STRAITJACKET = ModItems.registerBondage("straitjacket", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(300).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f).withUntiedDefaultLegs().withArmAnimation(k.ARM_ANIM_STRAITJACKET), ModItems.keyPredicate(STEEL_KEY), 16, () -> new Item[]{(Item)STRAITJACKET_MODEL_ITEM.get()}, PartVisibilityEnum.hideArms);

    @NotNull
    private static BiPredicate<ItemStack, ItemStack> keyPredicate(RegistryObject<Item> registryObject) {
        return (itemStack, itemStack2) -> {
            if (registryObject.get() == itemStack2.getItem()) {
                String string = PlayerEventHandler.getCustomTag(itemStack, "keyValue");
                String string2 = PlayerEventHandler.getCustomTag(itemStack2, "keyValue");
                return Objects.equals(string, string2);
            }
            return false;
        };
    }

    static RegistryObject<Item> registerBondage(String string, BondageProperties b_02, BiPredicate<ItemStack, ItemStack> biPredicate, int n2) {
        return ModItems.registerBondage(string, b_02, biPredicate, n2, null, null);
    }

    static RegistryObject<Item> registerBondage(String string, BondageProperties b_02, BiPredicate<ItemStack, ItemStack> biPredicate, int n2, @Nullable Supplier<Item[]> supplier, @Nullable Consumer<PartVisibility> consumer) {
        RegistryObject registryObject = ITEMS.register(string, () -> new BondageItem(string, b_02, biPredicate, n2, supplier, consumer));
        wearableItems.add((RegistryObject<Item>)registryObject);
        return registryObject;
    }

    static RegistryObject<Item> registerClothes(String string, BondageProperties b_02) {
        RegistryObject registryObject = ITEMS.register(string, () -> new StateSyncPacket(string, b_02));
        wearableItems.add((RegistryObject<Item>)registryObject);
        return registryObject;
    }

    static {
        ModItems.registerBondage("wrap", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(150).withArmAnimation(k.ARM_ANIM_INTO_BODY_SHOULDERS).withSound((Supplier<SoundEvent>)ModSounds.STICKY).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f).withArmAnimation(k.ARM_ANIM_FLAT_INTO_BODY), null, 16);
        ModItems.registerBondage("vine_seed", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(150).withArmAnimation(k.ARM_ANIM_INTO_BODY_SHOULDERS).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f), null, 16);
        ModItems.registerBondage("ribbon", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(50).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f).withVulnerableTo(VulnerabilityType.Kind.SCISSORING, 1.0f), null, 16);
        ModItems.registerBondage("web", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(200).withSound((Supplier<SoundEvent>)ModSounds.STICKY).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f).withVulnerableTo(VulnerabilityType.Kind.SCISSORING, 1.0f).withVulnerableTo(VulnerabilityType.Kind.FIRE, 1.0f), null, 16);
        ModItems.registerBondage("beam_cuffs", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(250), null, 16);
        ModItems.registerBondage("leather_straps", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(200).withArmAnimation(k.ARM_ANIM_INTO_BODY_SHOULDERS).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f).withVulnerableTo(VulnerabilityType.Kind.SCISSORING, 1.0f), null, 16);
        ModItems.registerBondage("medical_straps", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(300).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f).withVulnerableTo(VulnerabilityType.Kind.SCISSORING, 1.0f), null, 16);
        ModItems.registerBondage("slime", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(150).withSound((Supplier<SoundEvent>)ModSounds.SLIME).withVulnerableTo(VulnerabilityType.Kind.FIRE, 1.0f), null, 16);
        ArrayList<Pair> arrayList = new ArrayList<Pair>(List.of(new Pair((Object)"caution_duct_tape_gag", ModSounds.DUCT_TAPE), new Pair((Object)"clear_duct_tape_gag", ModSounds.DUCT_TAPE), new Pair((Object)"chain_gag", ModSounds.CHAIN), new Pair((Object)"panel_gag", () -> SoundEvents.ITEM_EMPTY), new Pair((Object)"latex_gag", () -> SoundEvents.ITEM_EMPTY), new Pair((Object)"wrap_gag", ModSounds.STICKY), new Pair((Object)"ribbon_gag", () -> SoundEvents.ITEM_EMPTY), new Pair((Object)"tube_gag", () -> SoundEvents.ITEM_EMPTY), new Pair((Object)"vine_gag", ModSounds.VINE), new Pair((Object)"slime_gag", ModSounds.SLIME), new Pair((Object)"bite_gag", () -> SoundEvents.ITEM_EMPTY), new Pair((Object)"web_gag", ModSounds.STICKY), new Pair((Object)"beam_panel_gag", () -> SoundEvents.ITEM_EMPTY), new Pair((Object)"baguette_gag", () -> SoundEvents.ITEM_EMPTY), new Pair((Object)"sponge_gag", () -> SoundEvents.ITEM_EMPTY)));
        for (Pair pair : arrayList) {
            ModItems.registerBondage((String)pair.getFirst(), BondageProperties.create().withPartVisibility(PartVisibility.modelHeadOnly).withGag().withSound((Supplier)pair.getSecond()), null, 16);
        }
        for (String string : new String[]{"medical_gag", "latex_hood"}) {
            ModItems.registerBondage(string, BondageProperties.create().withPartVisibility(PartVisibility.modelHeadOnly).withGag().withBlind(), null, 16);
        }
        for (String string : DyeColor.values()) {
            ModItems.registerBondage(string.getSerializedName() + "_cloth_gag", BondageProperties.create().withGag().withPartVisibility(PartVisibility.modelHeadOnly), null, 16);
        }
        for (String string : DyeColor.values()) {
            ModItems.registerBondage(string.getSerializedName() + "_ropes_gag", BondageProperties.create().withGag().withPartVisibility(PartVisibility.modelHeadOnly), null, 16);
        }
        for (String string : DyeColor.values()) {
            ModItems.registerBondage(string.getSerializedName() + "_ball_gag", BondageProperties.create().withGag().withPartVisibility(PartVisibility.modelHeadOnly), null, 16);
        }
        for (String string : DyeColor.values()) {
            ModItems.registerBondage(string.getSerializedName() + "_ball_gag_strap", BondageProperties.create().withGag().withPartVisibility(PartVisibility.modelHeadOnly), null, 16);
        }
        for (String string : DyeColor.values()) {
            ModItems.registerBondage(string.getSerializedName() + "_duct_tape_gag", BondageProperties.create().withGag().withPartVisibility(PartVisibility.modelHeadOnly), null, 16);
        }
        ModItems.registerBondage("caution_duct_tape", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(100).withSound((Supplier<SoundEvent>)ModSounds.DUCT_TAPE).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f).withVulnerableTo(VulnerabilityType.Kind.SCISSORING, 1.0f), null, 16);
        ModItems.registerBondage("clear_duct_tape", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(100).withSound((Supplier<SoundEvent>)ModSounds.DUCT_TAPE).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f).withVulnerableTo(VulnerabilityType.Kind.SCISSORING, 1.0f), null, 16);
        for (String string : DyeColor.values()) {
            ModItems.registerBondage(string.getSerializedName() + "_cleave_gag", BondageProperties.create().withPartVisibility(PartVisibility.modelHeadOnly).withGag(), null, 16);
        }
        for (String string : DyeColor.values()) {
            ModItems.registerBondage(string.getSerializedName() + "_cloth_blindfold", BondageProperties.create().withPartVisibility(PartVisibility.modelHeadOnly).withBlind(), null, 16);
        }
        for (String string : DyeColor.values()) {
            ModItems.registerBondage(string.getSerializedName() + "_blindfold_mask", BondageProperties.create().withPartVisibility(PartVisibility.modelHeadOnly).withBlind(), null, 16);
        }
        for (String string : DyeColor.values()) {
            ModItems.registerBondage(string.getSerializedName() + "_ropes", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(150).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f).withVulnerableTo(VulnerabilityType.Kind.SCISSORING, 2.0f).withVulnerableTo(VulnerabilityType.Kind.FIRE, 1.0f), null, 16);
        }
        for (String string : DyeColor.values()) {
            ModItems.registerBondage(string.getSerializedName() + "_shibari", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(250).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f).withVulnerableTo(VulnerabilityType.Kind.SCISSORING, 2.0f).withVulnerableTo(VulnerabilityType.Kind.FIRE, 1.0f), null, 16);
        }
        for (String string : DyeColor.values()) {
            ModItems.registerBondage(string.getSerializedName() + "_duct_tape", BondageProperties.create().withPartVisibility(PartVisibility.bodyOnly).withBindHands().withBindLegs().withResistance(150).withVulnerableTo(VulnerabilityType.Kind.CUTTING, 1.0f).withVulnerableTo(VulnerabilityType.Kind.SCISSORING, 2.0f).withVulnerableTo(VulnerabilityType.Kind.FIRE, 1.0f), null, 16);
        }
    }

    public static void register(IEventBus modBus) {
        ITEMS.register(modBus);
    }
}