/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.hash.Hashing
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.HttpTexture
 *  net.minecraft.client.renderer.texture.MissingTextureAtlasSprite
 *  net.minecraft.client.resources.DefaultPlayerSkin
 *  net.minecraft.client.resources.model.BakedModel
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  org.jetbrains.annotations.Nullable
 */
package com.dev.am.immersive_restraints.data;

import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.item.BondageItem;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.init.ModItems;
import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
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

public class SkinProvider {
    public static final ResourceLocation NO_SKIN = new ResourceLocation("immersive_restraints", "textures/skins/none_skin.png");
    public static final ResourceLocation NO_ITEM_SKIN = new ResourceLocation("immersive_restraints", "textures/skins/none_item.png");
    public static final ResourceLocation NO_OVERLAY_SKIN = new ResourceLocation("immersive_restraints", "textures/skins/none_overlay.png");
    public static final ResourceLocation NO_SKIN_OTHER = new ResourceLocation("immersive_restraints", "textures/skins/none_other.png");
    public static HashMap<String, ResourceLocation> textures = new HashMap();
    public static HashMap<String, BakedModel> models = new HashMap();

    public static ResourceLocation downloadTexture(String string) {
        textures.put(string, null);
        String string2 = Hashing.sha1().hashUnencodedChars((CharSequence)string).toString();
        ResourceLocation resourceLocation = new ResourceLocation("textures/" + string2);
        AbstractTexture abstractTexture = Minecraft.getInstance().textureManager.getTexture(resourceLocation, (AbstractTexture)MissingTextureAtlasSprite.getMissingTexture());
        if (abstractTexture == MissingTextureAtlasSprite.getMissingTexture()) {
            File file = new File(Minecraft.getInstance().getGameDirectory().gameDirectory, string2.length() > 2 ? string2.substring(0, 2) : "xx");
            File file2 = new File(file, string2);
            HttpTexture httpTexture = new HttpTexture(file2, string, DefaultPlayerSkin.getDefaultSkin(), false, () -> SkinProvider.onAvailable(string, resourceLocation));
            Minecraft.getInstance().textureManager.register(resourceLocation, (AbstractTexture)httpTexture);
        } else {
            SkinProvider.onAvailable(string, resourceLocation);
        }
        return resourceLocation;
    }

    private static void onAvailable(String string, ResourceLocation resourceLocation) {
        textures.put(string, resourceLocation);
    }

    public static String getFilename(String string) {
        String string2 = string.substring(string.lastIndexOf(47) + 1);
        if (string2.contains("?")) {
            string2 = string2.substring(0, string2.indexOf("?"));
        }
        return string2;
    }

    @Nullable
    public static ResourceLocation getResourceLocationFromTextureTag(ItemStack itemStack, String string, ResourceLocation resourceLocation) {
        String string2 = PlayerEventHandler.getCustomTag(itemStack, string);
        if (string2 != null) {
            if (textures.containsKey(string2)) {
                ResourceLocation resourceLocation2 = textures.get(string2);
                if (resourceLocation2 == null) {
                    return resourceLocation;
                }
                return resourceLocation2;
            }
            SkinProvider.downloadTexture(string2);
        }
        return null;
    }

    @Nullable
    public static ItemStack getDynamicIconStack(ItemStack itemStack) {
        if (!itemStack.isEmpty() && itemStack.getItem() instanceof BondageItem && PlayerEventHandler.hasCustomTag(itemStack, PlayerEventHandler.DYNAMIC_ITEM_TEXTURE_TAG)) {
            ItemStack itemStack2 = ((Item)ModItems.DYNAMIC_MODEL_ITEM.get()).getDefaultInstance();
            itemStack2.setTag(itemStack.getTag());
            return itemStack2;
        }
        return null;
    }
}

