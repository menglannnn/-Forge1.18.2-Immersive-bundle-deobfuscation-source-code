package abductionmod.content;

import com.google.common.hash.Hashing;
import java.io.File;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.HttpTexture;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class br {
  private static final ResourceLocation NO_SKIN = new ResourceLocation("am", "textures/skins/none.png");
  
  public static HashMap<String, ResourceLocation> textures = new HashMap<>();
  
  public static HashMap<String, BakedModel> models = new HashMap<>();
  
  public static ResourceLocation downloadTexture(String paramString) {
    textures.put(paramString, null);
    String str = Hashing.sha1().hashUnencodedChars(paramString).toString();
    ResourceLocation resourceLocation = new ResourceLocation("textures/" + str);
    AbstractTexture abstractTexture = (Minecraft.m_91087_()).f_90987_.m_174786_(resourceLocation, (AbstractTexture)MissingTextureAtlasSprite.m_118080_());
    if (abstractTexture == MissingTextureAtlasSprite.m_118080_()) {
      File file1 = new File((Minecraft.m_91087_().m_91109_()).f_118808_, (str.length() > 2) ? str.substring(0, 2) : "xx");
      File file2 = new File(file1, str);
      HttpTexture httpTexture = new HttpTexture(file2, paramString, DefaultPlayerSkin.m_118626_(), false, () -> onAvailable(paramString, paramResourceLocation));
      (Minecraft.m_91087_()).f_90987_.m_118495_(resourceLocation, (AbstractTexture)httpTexture);
    } else {
      onAvailable(paramString, resourceLocation);
    } 
    return resourceLocation;
  }
  
  private static void onAvailable(String paramString, ResourceLocation paramResourceLocation) {
    textures.put(paramString, paramResourceLocation);
  }
  
  public static String getFilename(String paramString) {
    String str = paramString.substring(paramString.lastIndexOf('/') + 1);
    if (str.contains("?"))
      str = str.substring(0, str.indexOf("?")); 
    return str;
  }
  
  @Nullable
  public static ResourceLocation getResourceLocationFromTextureTag(ItemStack paramItemStack, String paramString) {
    String str = bl.getCustomTag(paramItemStack, paramString);
    if (str != null) {
      if (textures.containsKey(str)) {
        ResourceLocation resourceLocation = textures.get(str);
        return (resourceLocation == null) ? NO_SKIN : resourceLocation;
      } 
      downloadTexture(str);
    } 
    return null;
  }
  
  @Nullable
  public static ItemStack getDynamicIconStack(ItemStack paramItemStack) {
    if (!paramItemStack.m_41619_() && paramItemStack.m_41720_() instanceof aC && bl.hasCustomTag(paramItemStack, bl.DYNAMIC_ITEM_TEXTURE_TAG)) {
      ItemStack itemStack = ((Item)aJ.DYNAMIC_MODEL_ITEM.get()).m_7968_();
      itemStack.m_41751_(paramItemStack.m_41783_());
      return itemStack;
    } 
    return null;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\br.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */