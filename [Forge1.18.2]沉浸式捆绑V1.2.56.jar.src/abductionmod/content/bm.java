package abductionmod.content;

import com.dev.am.Abduction;
import com.dev.am.mod.gamerule.ArmorIsHiddenWhenTied;
import com.dev.am.mod.gamerule.GameruleRegistry;
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
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
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
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;
import top.theillusivec4.curios.api.type.util.ICuriosHelper;

public class bm {
  private static final Comparator<ItemStack> weightComparator;
  
  public static Predicate<ItemStack> untyingStackPredicate;
  
  public static Predicate<ItemStack> struggleStackPredicate = paramItemStack -> true;
  
  private static Boolean checkedDevEnv = null;
  
  public static List<SlotResult> getBondages(LivingEntity paramLivingEntity) {
    return (paramLivingEntity == null) ? List.of() : CuriosApi.getCuriosHelper().findCurios(paramLivingEntity, paramItemStack -> paramItemStack.m_41720_() instanceof aC);
  }
  
  @Nullable
  public static ItemStack getBondageInSlot(LivingEntity paramLivingEntity, @Nonnull String paramString) {
    for (SlotResult slotResult : getBondages(paramLivingEntity)) {
      if (paramString.equals(slotResult.slotContext().identifier()))
        return slotResult.stack(); 
    } 
    return null;
  }
  
  @Nullable
  public static ItemStack getMainClothes(LivingEntity paramLivingEntity) {
    if (paramLivingEntity instanceof av) {
      av av = (av)paramLivingEntity;
      return av.abduction$getCurrentWardrobeItem();
    } 
    return null;
  }
  
  @Nonnull
  public static String getArmTyingStyle(LivingEntity paramLivingEntity) {
    String str = "am_tied";
    for (SlotResult slotResult : getBondages(paramLivingEntity)) {
      ItemStack itemStack = slotResult.stack();
      Item item = itemStack.m_41720_();
      if (item instanceof aC) {
        aC aC = (aC)item;
        String str1 = aC.properties.getArmAnimation(itemStack);
        if (str1 != null && !str1.equals(str))
          return str1; 
      } 
    } 
    return str;
  }
  
  public static void setMainClothes(LivingEntity paramLivingEntity, ItemStack paramItemStack) {
    if (paramLivingEntity instanceof av) {
      av av = (av)paramLivingEntity;
      av.abduction$setCurrentWardrobeItem(paramItemStack);
    } 
  }
  
  public static boolean areHandsTied(LivingEntity paramLivingEntity) {
    return paramLivingEntity.m_5833_() ? false : getBondages(paramLivingEntity).stream().anyMatch(paramSlotResult -> ((aC)paramSlotResult.stack().m_41720_()).properties.isBindHands(paramSlotResult.stack()));
  }
  
  public static boolean isGagged(LivingEntity paramLivingEntity) {
    return paramLivingEntity.m_5833_() ? false : getBondages(paramLivingEntity).stream().anyMatch(paramSlotResult -> ((aC)paramSlotResult.stack().m_41720_()).properties.isGag(paramSlotResult.stack()));
  }
  
  public static boolean hasMittens(LivingEntity paramLivingEntity) {
    return paramLivingEntity.m_5833_() ? false : getBondages(paramLivingEntity).stream().anyMatch(paramSlotResult -> paramSlotResult.stack().m_150930_((Item)aJ.MITTENS.get()));
  }
  
  public static boolean areLegsTied(LivingEntity paramLivingEntity) {
    ay ay = (ay)paramLivingEntity;
    return ay.abduction$getLegsUntied() ? false : (paramLivingEntity.m_5833_() ? false : hasLegBinds(paramLivingEntity));
  }
  
  public static boolean hasLegBinds(LivingEntity paramLivingEntity) {
    return getBondages(paramLivingEntity).stream().anyMatch(paramSlotResult -> ((aC)paramSlotResult.stack().m_41720_()).properties.isBindLegs(paramSlotResult.stack()));
  }
  
  public static boolean isBlindfolded(LivingEntity paramLivingEntity) {
    return paramLivingEntity.m_5833_() ? false : getBondages(paramLivingEntity).stream().anyMatch(paramSlotResult -> ((aC)paramSlotResult.stack().m_41720_()).properties.isBlind(paramSlotResult.stack()));
  }
  
  @Nullable
  public static ItemStack getBlindfold(LivingEntity paramLivingEntity) {
    for (SlotResult slotResult : getBondages(paramLivingEntity)) {
      if (((aC)slotResult.stack().m_41720_()).properties.isBlind(slotResult.stack()))
        return slotResult.stack(); 
    } 
    return null;
  }
  
  public static boolean isDeafened(LivingEntity paramLivingEntity) {
    return paramLivingEntity.m_5833_() ? false : getBondages(paramLivingEntity).stream().anyMatch(paramSlotResult -> ((aC)paramSlotResult.stack().m_41720_()).properties.isDeafen(paramSlotResult.stack()));
  }
  
  public static boolean isFullyTied(LivingEntity paramLivingEntity) {
    return (areLegsTied(paramLivingEntity) && areHandsTied(paramLivingEntity));
  }
  
  public static boolean isTied(LivingEntity paramLivingEntity) {
    return (areHandsTied(paramLivingEntity) || areLegsTied(paramLivingEntity));
  }
  
  public static void syncGamerules(MinecraftServer paramMinecraftServer, Predicate<Player> paramPredicate) {
    for (ServerPlayer serverPlayer : paramMinecraftServer.m_6846_().m_11314_()) {
      if (paramPredicate.test(serverPlayer))
        for (String str : GameruleRegistry.gamerules.keySet()) {
          int i = 0;
          switch (bn.$SwitchMap$com$dev$am$mod$gamerule$GameruleRegistry$RuleDataType[((GameruleRegistry.RuleDataType)GameruleRegistry.gameruleDataTypes.get(str)).ordinal()]) {
            case 1:
              i = GameruleRegistry.isGameruleEnabled(serverPlayer.f_19853_, str) ? 1 : 0;
              break;
            case 2:
              i = GameruleRegistry.getGameruleValue(serverPlayer.f_19853_, str).intValue();
              break;
          } 
          aZ.sendToClient(serverPlayer, new aV(str, i));
        }  
    } 
  }
  
  public static int calculateTyerHash(LivingEntity paramLivingEntity1, LivingEntity paramLivingEntity2, ItemStack paramItemStack) {
    String str = paramItemStack.m_41777_().m_41784_().toString();
    int i = (paramLivingEntity1.m_142081_().toString() + paramLivingEntity1.m_142081_().toString() + paramLivingEntity2.m_142081_()).hashCode() + str.hashCode();
    if (i == 0)
      i++; 
    return i;
  }
  
  public static a canPutBondage(LivingEntity paramLivingEntity, ItemStack paramItemStack) {
    ay ay = (ay)paramLivingEntity;
    if (paramLivingEntity instanceof ax) {
      ax ax = (ax)paramLivingEntity;
      if (ax.abduction$isImmuneToBondage())
        return a.FAIL; 
    } 
    if (EnchantmentHelper.m_44920_(paramItemStack) && !isTied(paramLivingEntity))
      return a.FAIL; 
    if (ay.abduction$getProtectedClothes() && paramItemStack.m_41720_() instanceof aG)
      return a.FAIL; 
    ICuriosHelper iCuriosHelper = CuriosApi.getCuriosHelper();
    AtomicReference<a> atomicReference = new AtomicReference<>(a.FAIL);
    iCuriosHelper.getCurio(paramItemStack).ifPresent(paramICurio -> paramICuriosHelper.getCuriosHandler(paramLivingEntity).ifPresent(()));
    return atomicReference.get();
  }
  
  public static void putBondageItemInto(LivingEntity paramLivingEntity1, @Nullable LivingEntity paramLivingEntity2, ItemStack paramItemStack, @Nullable String paramString, boolean paramBoolean) {
    ICuriosHelper iCuriosHelper = CuriosApi.getCuriosHelper();
    iCuriosHelper.getCurio(paramItemStack).ifPresent(paramICurio -> paramICuriosHelper.getCuriosHandler(paramLivingEntity1).ifPresent(()));
  }
  
  @Nullable
  public static ItemStack getFirstBondageWithBaseResistance(LivingEntity paramLivingEntity, Predicate<ItemStack> paramPredicate) {
    ArrayList<ItemStack> arrayList = new ArrayList();
    if (paramLivingEntity instanceof Player) {
      Player player = (Player)paramLivingEntity;
      for (SlotResult slotResult : getBondages((LivingEntity)player)) {
        if (paramPredicate.test(slotResult.stack())) {
          Item item = slotResult.stack().m_41720_();
          if (item instanceof aC) {
            aC aC = (aC)item;
            if (aC.properties.getBaseResistance(slotResult.stack()) > 0)
              arrayList.add(slotResult.stack()); 
          } 
        } 
      } 
    } 
    if (!arrayList.isEmpty()) {
      arrayList.sort(weightComparator);
      return arrayList.get(0);
    } 
    return null;
  }
  
  private static int getBondageSortWeight(ItemStack paramItemStack) {
    boolean bool = false;
    aC aC = (aC)paramItemStack.m_41720_();
    if (aC.properties.isBindHands(paramItemStack))
      bool += true; 
    if (aC.properties.isBindLegs(paramItemStack))
      bool += true; 
    if (aC.properties.isBlind(paramItemStack))
      bool += true; 
    if (aC.properties.isDeafen(paramItemStack))
      bool += true; 
    if (aC.properties.isGag(paramItemStack))
      bool += true; 
    return bool;
  }
  
  public static List<SlotResult> getBondagesForRendering(LivingEntity paramLivingEntity) {
    List<SlotResult> list = getBondages(paramLivingEntity);
    if (w.hoveredStack != null && !w.hoveredStack.m_41619_() && w.hoveredStack.m_41720_() instanceof aC)
      list.add(new SlotResult(null, w.hoveredStack)); 
    ItemStack itemStack = getMainClothes(paramLivingEntity);
    if (itemStack != null && !itemStack.m_41619_())
      list.add(0, new SlotResult(null, itemStack)); 
    return list;
  }
  
  @Unique
  @Nullable
  public static ResourceLocation abduction$getTextureLocationClothes(ItemStack paramItemStack) {
    return br.getResourceLocationFromTextureTag(paramItemStack, bl.DYNAMIC_CLOTHES_TEXTURE_TAG);
  }
  
  @Nullable
  public static InteractionResult checkFinishedUntying(Player paramPlayer, LivingEntity paramLivingEntity, ay paramay) {
    if (paramay.abduction$isFinishedUnTying()) {
      ItemStack itemStack = getFirstBondageWithBaseResistance(paramLivingEntity, untyingStackPredicate);
      if (itemStack != null) {
        takeOffBondage(paramLivingEntity, itemStack, null, null, paramItemStack -> paramPlayer.m_150109_().m_36054_(paramItemStack));
      } else {
        return InteractionResult.FAIL;
      } 
      paramay.abduction$stopTyingProcess();
      return InteractionResult.SUCCESS;
    } 
    return null;
  }
  
  public static int getResistanceOfFirstBondage(LivingEntity paramLivingEntity, Predicate<ItemStack> paramPredicate) {
    ItemStack itemStack = getFirstBondageWithBaseResistance(paramLivingEntity, paramPredicate);
    return (itemStack == null) ? 0 : ((aC)itemStack.m_41720_()).properties.getResistance(itemStack);
  }
  
  public static float getUntyingPower(ItemStack paramItemStack1, ItemStack paramItemStack2) {
    if (paramItemStack1 == null || paramItemStack2 == null)
      return 0.0F; 
    Item item = paramItemStack1.m_41720_();
    if (item instanceof aC) {
      aR aR;
      aC aC = (aC)item;
      item = null;
      if (paramItemStack2.m_41720_() instanceof aQ)
        aR = ((aQ)paramItemStack2.m_41720_()).getToolMaterial(); 
      String str = String.valueOf(Registry.f_122827_.m_7981_(paramItemStack2.m_41720_()));
      if (bs.toolMaterials.containsKey(str))
        aR = bs.toolMaterials.get(str); 
      if (aR != null) {
        ArrayList<Float> arrayList = new ArrayList();
        for (aR.a a : aR.vulnerabilityMap.keySet()) {
          Float float_ = aR.vulnerabilityMap.get(a);
          if (aC.properties.vulnerabilityMap.containsKey(a))
            arrayList.add(Float.valueOf(((Float)aC.properties.vulnerabilityMap.get(a)).floatValue() * float_.floatValue())); 
        } 
        if (!arrayList.isEmpty()) {
          arrayList.sort(Float::compareTo);
          Collections.reverse(arrayList);
          return ((Float)arrayList.get(0)).floatValue();
        } 
      } 
    } 
    return 0.0F;
  }
  
  public static int getColorFromString(@Nonnull String paramString) {
    return Math.abs(paramString.hashCode()) % 16777215;
  }
  
  public static String generateRandomKey() {
    return generateRandomKey(null);
  }
  
  public static String generateRandomKey(@Nullable Long paramLong) {
    String str = "║╒╓╔╕╖╗╘╙╚╛╜╝╞╟╠╡╢╣╤╥╦╧╨╩╪╫╬";
    Random random = (paramLong == null) ? new Random() : new Random(paramLong.longValue());
    StringBuilder stringBuilder = new StringBuilder(6);
    for (byte b = 0; b < 6; b++)
      stringBuilder.append(str.charAt((int)(random.nextFloat() * str.length()))); 
    return stringBuilder.toString();
  }
  
  public static void takeOffBondage(LivingEntity paramLivingEntity, @Nullable ItemStack paramItemStack1, @Nullable ItemStack paramItemStack2, @Nullable SoundEvent paramSoundEvent, Consumer<ItemStack> paramConsumer) {
    Level level = paramLivingEntity.m_183503_();
    List<SlotResult> list = getBondages(paramLivingEntity);
    for (SlotResult slotResult : list) {
      ItemStack itemStack = slotResult.stack();
      if (paramItemStack1 != null && itemStack != paramItemStack1)
        continue; 
      aC aC = (aC)itemStack.m_41720_();
      if (paramItemStack2 == null || aC.takeOffItemPredicate.test(itemStack, paramItemStack2)) {
        if (paramSoundEvent != null)
          playSound(paramLivingEntity, paramSoundEvent); 
        if (!level.f_46443_) {
          ItemStack itemStack1 = itemStack.m_41777_();
          bl.removeCustomTag(itemStack1, "struggleAddedResistance");
          paramConsumer.accept(itemStack1);
          itemStack.m_41774_(1);
        } 
        break;
      } 
    } 
    checkDropLeash(paramLivingEntity);
  }
  
  public static void playSound(LivingEntity paramLivingEntity, SoundEvent paramSoundEvent) {
    paramLivingEntity.f_19853_.m_6263_(null, paramLivingEntity.m_20185_(), paramLivingEntity.m_20186_(), paramLivingEntity.m_20189_(), paramSoundEvent, SoundSource.NEUTRAL, 0.5F, paramLivingEntity.m_21187_().nextFloat() * 0.4F + 0.6F);
  }
  
  public static void checkDropLeash(LivingEntity paramLivingEntity) {
    if (!isTied(paramLivingEntity) && getBondageInSlot(paramLivingEntity, "collar") == null && paramLivingEntity instanceof aw) {
      aw aw = (aw)paramLivingEntity;
      aw.abduction$dropLeash(true, true);
    } 
  }
  
  public static boolean canBeSwapped(ItemStack paramItemStack1, ItemStack paramItemStack2) {
    aC aC;
    Item item = paramItemStack1.m_41720_();
    if (item instanceof aC) {
      aC = (aC)item;
    } else {
      return false;
    } 
    return aC.properties.isNeedsKeyForUntie(paramItemStack1) ? false : (!ItemStack.m_150942_(paramItemStack1, paramItemStack2));
  }
  
  public static boolean canBeTakenOffFreely(ItemStack paramItemStack) {
    aC aC;
    Item item = paramItemStack.m_41720_();
    if (item instanceof aC) {
      aC = (aC)item;
    } else {
      return false;
    } 
    return (aC.properties.getResistance(paramItemStack) > 0) ? false : (!aC.properties.isNeedsKeyForUntie(paramItemStack));
  }
  
  public static boolean isDevEnvironment() {
    if (checkedDevEnv != null)
      return checkedDevEnv.booleanValue(); 
    checkedDevEnv = Boolean.valueOf(((ModContainer)ModList.get().getModContainerById("am").get()).getModInfo().getVersion().toString().contains("NONE"));
    return checkedDevEnv.booleanValue();
  }
  
  public static void debugLog(String paramString) {
    if (isDevEnvironment())
      Abduction.LOGGER.info("💬 " + paramString); 
  }
  
  public static boolean shouldDrawArmorStack(@Nonnull LivingEntity paramLivingEntity, ItemStack paramItemStack) {
    if (isTied(paramLivingEntity)) {
      Boolean bool = bl.getCustomTagBool(paramItemStack, "armorVisibility");
      return (bool != null) ? bool.booleanValue() : (!GameruleRegistry.isGameruleEnabled(paramLivingEntity.f_19853_, ArmorIsHiddenWhenTied.ID));
    } 
    return true;
  }
  
  public static void takeOffAllBondage(Player paramPlayer) {
    List<SlotResult> list = getBondages((LivingEntity)paramPlayer);
    for (SlotResult slotResult : list) {
      if (slotResult.stack().m_41619_() || slotResult.stack().m_41720_() instanceof aG || slotResult.stack().m_41720_() == aJ.COLLAR.get())
        continue; 
      takeOffBondage((LivingEntity)paramPlayer, slotResult.stack(), null, SoundEvents.f_12639_, paramItemStack -> paramPlayer.m_36176_(paramItemStack, false));
    } 
    checkDropLeash((LivingEntity)paramPlayer);
  }
  
  public static void useKeyOnPlayer(Player paramPlayer, ItemStack paramItemStack) {
    takeOffBondage((LivingEntity)paramPlayer, null, paramItemStack, (SoundEvent)g.COLLAR_KEY_OPEN.get(), paramItemStack -> paramPlayer.m_36176_(paramItemStack, false));
  }
  
  @NotNull
  public static List<Entity> getLeashedList(ServerPlayer paramServerPlayer) {
    aw aw = (aw)paramServerPlayer;
    return (aw.abduction$getLeashHolder() != null) ? Collections.emptyList() : (List<Entity>)paramServerPlayer.m_20194_().m_6846_().m_11314_().stream().filter(paramServerPlayer2 -> {
          if (paramServerPlayer2 instanceof aw) {
            aw aw = (aw)paramServerPlayer2;
            if (aw.abduction$getLeashHolder() == paramServerPlayer1 && paramServerPlayer1 != paramServerPlayer2);
          } 
          return false;
        }).collect(Collectors.toList());
  }
  
  static {
    weightComparator = ((paramItemStack1, paramItemStack2) -> {
        int i = getBondageSortWeight(paramItemStack1);
        int j = getBondageSortWeight(paramItemStack2);
        return (i < j) ? 1 : ((i > j) ? -1 : 0);
      });
    untyingStackPredicate = (paramItemStack -> {
        Item item = paramItemStack.m_41720_();
        if (item instanceof aC) {
          aC aC = (aC)item;
          if (aC.properties.isNeedsKeyForUntie(paramItemStack))
            return false; 
        } 
        return true;
      });
  }
  
  public enum a {
    FAIL, SWAP, TIE;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bm.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */