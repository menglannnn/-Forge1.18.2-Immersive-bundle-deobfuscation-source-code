package abductionmod.content;

import dev.kosmx.playerAnim.api.layered.AnimationStack;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.util.Ease;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import top.theillusivec4.curios.api.SlotResult;

public class m {
  public static final int animationLayersCount = 1;
  
  public static HashMap<UUID, j> playingAnimations = new HashMap<>();
  
  public static ArrayList<String> animationLayers = new ArrayList<>();
  
  public static n currentLocalAnimation;
  
  public static void playAnimation(Player paramPlayer, @Nullable n paramn, boolean paramBoolean1, boolean paramBoolean2) {
    String str1 = null;
    String str2 = null;
    if (paramn != null)
      str2 = paramn.getName(paramPlayer); 
    j j = playingAnimations.get(paramPlayer.m_142081_());
    if (j == null) {
      j = new j("", false, 0L);
      playingAnimations.put(paramPlayer.m_142081_(), j);
    } 
    if (Objects.equals(j.name, str2))
      return; 
    if (j.needsUnlocking) {
      if (paramPlayer.f_19797_ < j.unlockAt)
        return; 
      j.needsUnlocking = false;
      paramBoolean1 = true;
    } 
    if (paramPlayer == (Minecraft.m_91087_()).f_91074_)
      currentLocalAnimation = paramn; 
    if (!paramBoolean2 && playingAnimations.containsKey(paramPlayer.m_142081_())) {
      str1 = j.name;
      if (str1 == null && str2 == null)
        return; 
      if (str1 != null && str1.equals(str2))
        return; 
      if (str1 != null && !str1.replace("!", "").startsWith("am_") && str2 == null)
        return; 
    } 
    ArrayList<KeyframeAnimationPlayer> arrayList = new ArrayList();
    int i = 0;
    if (paramn != null)
      for (byte b = 0; b < paramn.children.length; b++) {
        n.a a = paramn.children[b];
        KeyframeAnimationPlayer keyframeAnimationPlayer = addAnim(paramPlayer, a, (a.isInstant || paramBoolean1), paramBoolean2);
        int k = (keyframeAnimationPlayer.getData()).endTick;
        if (k > i)
          i = k; 
        arrayList.add(keyframeAnimationPlayer);
      }  
    AnimationStack animationStack = new AnimationStack();
    for (KeyframeAnimationPlayer keyframeAnimationPlayer : arrayList) {
      if (keyframeAnimationPlayer != null)
        animationStack.addAnimLayer(0, (IAnimation)keyframeAnimationPlayer); 
    } 
    ModifierLayer modifierLayer = (ModifierLayer)PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer)paramPlayer).get(new ResourceLocation("am", animationLayers.get(0)));
    j.name = str2;
    bm.debugLog("ANIMATION=" + str2);
    j.unlockAt = (paramPlayer.f_19797_ + i);
    if (paramBoolean2)
      j.needsUnlocking = true; 
    modifierLayer.replaceAnimationWithFade(AbstractFadeModifier.standardFadeIn(arrayList.isEmpty() ? 6 : 3, Ease.INOUTSINE), (IAnimation)animationStack);
  }
  
  private static KeyframeAnimationPlayer addAnim(Player paramPlayer, @Nullable n.a parama, boolean paramBoolean1, boolean paramBoolean2) {
    if (parama == null)
      return null; 
    String str = parama.getName(paramPlayer);
    KeyframeAnimation keyframeAnimation = PlayerAnimationRegistry.getAnimation(new ResourceLocation("am", str));
    if (keyframeAnimation == null) {
      bm.debugLog("Animation not found: " + parama);
      return null;
    } 
    return new KeyframeAnimationPlayer(keyframeAnimation, paramBoolean1 ? (keyframeAnimation.endTick + 1) : keyframeAnimation.beginTick);
  }
  
  public static void applyAnimationFromItems(Player paramPlayer, boolean paramBoolean) {
    LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
    if (paramPlayer instanceof ay) {
      ay ay = (ay)paramPlayer;
      boolean bool = false;
      List<SlotResult> list = bm.getBondages((LivingEntity)paramPlayer);
      for (SlotResult slotResult : list) {
        aC aC = (aC)slotResult.stack().m_41720_();
        if (aC.properties.isBindHands(slotResult.stack())) {
          l l = k.getById(ay.abduction$getAnimationId());
          boolean bool1 = false;
          if (l.parent != null && localPlayer == paramPlayer) {
            bool1 = true;
            aZ.sendToServer(new bc(l.parent.intValue()));
          } 
          playAnimation(paramPlayer, l.definition, paramBoolean, bool1);
          bool = true;
        } 
      } 
      if (!bool)
        playAnimation(paramPlayer, null, paramBoolean, false); 
    } 
  }
  
  public static l getAnimationState(Player paramPlayer) {
    return k.getById(((ay)paramPlayer).abduction$getAnimationId());
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\m.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */