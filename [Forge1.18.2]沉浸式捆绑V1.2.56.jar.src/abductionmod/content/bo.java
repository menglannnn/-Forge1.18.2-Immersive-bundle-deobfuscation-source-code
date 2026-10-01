package abductionmod.content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotResult;

public class bo {
  public static Map<String, EntityRenderer<? extends Player>> abduction$playerRenderersSlim = new HashMap<>();
  
  public static Map<String, EntityRenderer<? extends Player>> abduction$playerRenderersFat = new HashMap<>();
  
  public static void appendArmorVisibilityTooltip(ItemStack paramItemStack, List<Component> paramList) {
    LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
    if (localPlayer != null && bm.isTied((LivingEntity)localPlayer)) {
      boolean bool = false;
      for (ItemStack itemStack : localPlayer.m_6168_()) {
        if (itemStack == paramItemStack) {
          bool = true;
          break;
        } 
      } 
      if (bool)
        paramList.add(new TextComponent("" + ChatFormatting.GRAY + ChatFormatting.GRAY + ChatFormatting.ITALIC.toString())); 
    } 
  }
  
  @NotNull
  public static C hidePartsInModel(Player paramPlayer, PlayerModel<?> paramPlayerModel, @Nullable ItemStack paramItemStack) {
    C c = new C(paramPlayerModel);
    List<SlotResult> list = bm.getBondagesForRendering((LivingEntity)paramPlayer);
    for (SlotResult slotResult : list) {
      ItemStack itemStack = slotResult.stack();
      if (itemStack != paramItemStack) {
        String str = bl.getCustomTag(itemStack, bl.HIDE_ORIGINAL_SKIN_PARTS_TAG);
        if (str != null && str.length() == 7)
          if (str.charAt(0) == '0') {
            paramPlayerModel.m_8009_(false);
          } else {
            for (byte b = 1; b < str.length(); b++) {
              boolean bool = (str.charAt(b) != '0') ? true : false;
              switch (b) {
                case 1:
                  paramPlayerModel.f_102809_.f_104207_ = bool;
                  break;
                case 2:
                  paramPlayerModel.f_103378_.f_104207_ = bool;
                  break;
                case 3:
                  paramPlayerModel.f_103374_.f_104207_ = bool;
                  break;
                case 4:
                  paramPlayerModel.f_103375_.f_104207_ = bool;
                  break;
                case 5:
                  paramPlayerModel.f_103376_.f_104207_ = bool;
                  break;
                case 6:
                  paramPlayerModel.f_103377_.f_104207_ = bool;
                  break;
              } 
            } 
          }  
      } 
      Item item = itemStack.m_41720_();
      if (item instanceof aC) {
        aC aC = (aC)item;
        C c1 = new C(paramPlayerModel);
        aC.hideBodyParts.accept(c1);
        c1.unloadInto(paramPlayerModel);
      } 
    } 
    return c;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bo.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */