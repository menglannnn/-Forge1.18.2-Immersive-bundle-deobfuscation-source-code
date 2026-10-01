package abductionmod.content;

import java.util.ArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class bl {
  public static final String KEY_VALUE_TAG = "keyValue";
  
  public static final String NO_DROP_ON_DEATH_TAG = "noDropOnDeath";
  
  public static final String ARMOR_VISIBILITY_TAG = "armorVisibility";
  
  public static final ArrayList<String> allModifiedTags = new ArrayList<>();
  
  public static final String GAG_TYPE_TAG = registerModifiedTag("gagType");
  
  public static final String ARM_BIND_TYPE_TAG = registerModifiedTag("armBindType");
  
  public static final String DYNAMIC_TEXTURE_TAG = registerModifiedTag("dynamicTexture");
  
  public static final String DYNAMIC_BLINDFOLD_TEXTURE_TAG = registerModifiedTag("blindfoldDynamicTexture");
  
  public static final String DYNAMIC_CLOTHES_TEXTURE_TAG = registerModifiedTag("clothesDynamicTexture");
  
  public static final String DYNAMIC_ITEM_TEXTURE_TAG = registerModifiedTag("itemDynamicTexture");
  
  public static final String ARM_SHAPE_TAG = registerModifiedTag("armShape");
  
  public static final String HIDE_ORIGINAL_SKIN_PARTS_TAG = registerModifiedTag("hideOriginalSkin");
  
  public static final String STRUGGLE_ADDED_RESISTANCE_TAG = "struggleAddedResistance";
  
  public static final String DATA_AM_ADJUSTMENT_MAP_TAG = registerModifiedTag("abduction$AdjustmentData");
  
  public static void setCustomTag(ItemStack paramItemStack, String paramString1, String paramString2) {
    CompoundTag compoundTag = paramItemStack.m_41784_();
    compoundTag.m_128359_(paramString2, paramString1);
  }
  
  public static void setCustomTag(ItemStack paramItemStack, Integer paramInteger, String paramString) {
    CompoundTag compoundTag = paramItemStack.m_41784_();
    compoundTag.m_128405_(paramString, paramInteger.intValue());
  }
  
  public static void setCustomTag(ItemStack paramItemStack, CompoundTag paramCompoundTag, String paramString) {
    CompoundTag compoundTag = paramItemStack.m_41784_();
    compoundTag.m_128365_(paramString, (Tag)paramCompoundTag);
  }
  
  public static void setCustomTag(ItemStack paramItemStack, Float paramFloat, String paramString) {
    CompoundTag compoundTag = paramItemStack.m_41784_();
    compoundTag.m_128350_(paramString, paramFloat.floatValue());
  }
  
  public static void setCustomTag(ItemStack paramItemStack, Boolean paramBoolean, String paramString) {
    CompoundTag compoundTag = paramItemStack.m_41784_();
    compoundTag.m_128379_(paramString, paramBoolean.booleanValue());
  }
  
  public static boolean removeCustomTag(ItemStack paramItemStack, String paramString) {
    CompoundTag compoundTag = paramItemStack.m_41783_();
    if (compoundTag != null && compoundTag.m_128441_(paramString)) {
      compoundTag.m_128473_(paramString);
      return true;
    } 
    return false;
  }
  
  public static boolean hasCustomTag(ItemStack paramItemStack, String paramString) {
    if (paramItemStack.m_41783_() == null)
      return false; 
    CompoundTag compoundTag = paramItemStack.m_41783_();
    return compoundTag.m_128441_(paramString);
  }
  
  @Nullable
  public static String getCustomTag(ItemStack paramItemStack, String paramString) {
    CompoundTag compoundTag = paramItemStack.m_41783_();
    return (compoundTag != null && compoundTag.m_128441_(paramString)) ? compoundTag.m_128461_(paramString) : null;
  }
  
  @Nullable
  public static CompoundTag getCustomTagCompound(ItemStack paramItemStack, String paramString) {
    CompoundTag compoundTag = paramItemStack.m_41783_();
    return (compoundTag != null && compoundTag.m_128441_(paramString)) ? compoundTag.m_128469_(paramString) : null;
  }
  
  @Nullable
  public static Integer getCustomTagInt(ItemStack paramItemStack, String paramString) {
    CompoundTag compoundTag = paramItemStack.m_41783_();
    return (compoundTag != null && compoundTag.m_128441_(paramString)) ? Integer.valueOf(compoundTag.m_128451_(paramString)) : null;
  }
  
  @Nullable
  public static Float getCustomTagFloat(ItemStack paramItemStack, String paramString) {
    CompoundTag compoundTag = paramItemStack.m_41783_();
    return (compoundTag != null && compoundTag.m_128441_(paramString)) ? Float.valueOf(compoundTag.m_128457_(paramString)) : null;
  }
  
  @Nullable
  public static Boolean getCustomTagBool(ItemStack paramItemStack, String paramString) {
    CompoundTag compoundTag = paramItemStack.m_41783_();
    return (compoundTag != null && compoundTag.m_128441_(paramString)) ? Boolean.valueOf(compoundTag.m_128471_(paramString)) : null;
  }
  
  public static String registerModifiedTag(String paramString) {
    allModifiedTags.add(paramString);
    return paramString;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bl.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */