package abductionmod.content;

import dev.kosmx.playerAnim.core.util.MathHelper;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityLeaveWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "am", bus = Mod.EventBusSubscriber.Bus.FORGE, value = {Dist.CLIENT})
public class x {
  private static float targetYaw = 0.0F;
  
  private static float targetPitch = 0.0F;
  
  private static float targetRoll = 0.0F;
  
  private static float currentYaw = 0.0F;
  
  private static float currentPitch = 0.0F;
  
  private static float currentRoll = 0.0F;
  
  @SubscribeEvent
  public static void playerJoin(EntityJoinWorldEvent paramEntityJoinWorldEvent) {
    Entity entity = paramEntityJoinWorldEvent.getEntity();
    if (entity.f_19853_.f_46443_ && entity instanceof Player) {
      Player player = (Player)entity;
      m.playAnimation(player, null, false, false);
    } 
  }
  
  @SubscribeEvent
  public static void cameraEvent(EntityViewRenderEvent.CameraSetup paramCameraSetup) {
    Minecraft minecraft = Minecraft.m_91087_();
    if (minecraft.f_91066_.m_92176_().m_90612_()) {
      n n = m.currentLocalAnimation;
      if (n != null) {
        targetPitch = n.camPitch;
        targetYaw = n.camYaw;
        targetRoll = n.camRoll;
      } else {
        targetYaw = 0.0F;
        targetPitch = 0.0F;
        targetRoll = 0.0F;
      } 
      float f1 = Minecraft.m_91087_().m_91297_();
      float f2 = f1 / 5.0F;
      if (f2 == 0.0F)
        f2 = 0.0022857143F; 
      currentPitch = MathHelper.lerp(f2, currentPitch, targetPitch);
      currentYaw = MathHelper.lerp(f2, currentYaw, targetYaw);
      currentRoll = MathHelper.lerp(f2, currentRoll, targetRoll);
      paramCameraSetup.setPitch(paramCameraSetup.getPitch() + currentPitch);
      paramCameraSetup.setYaw(paramCameraSetup.getYaw() + currentYaw);
      paramCameraSetup.setRoll(paramCameraSetup.getRoll() + currentRoll);
    } 
  }
  
  @SubscribeEvent
  public static void playerLeft(EntityLeaveWorldEvent paramEntityLeaveWorldEvent) {
    Entity entity = paramEntityLeaveWorldEvent.getEntity();
    if (entity.f_19853_.f_46443_ && entity instanceof Player) {
      Player player = (Player)entity;
      m.playAnimation(player, null, false, false);
    } 
  }
  
  @SubscribeEvent
  public static void onScroll(InputEvent.MouseScrollEvent paramMouseScrollEvent) {
    LocalPlayer localPlayer = (Minecraft.m_91087_()).f_91074_;
    if (localPlayer == null)
      return; 
    ay ay = (ay)localPlayer;
    if (ay.abduction$isHoldingChangePositionKey()) {
      double d = paramMouseScrollEvent.getScrollDelta();
      List list = localPlayer.f_19853_.m_45933_((Entity)localPlayer, localPlayer.m_142469_().m_82400_(60.0D));
      for (Entity entity : list) {
        if (entity instanceof aw) {
          aw aw = (aw)entity;
          if (aw.abduction$getLeashHolder() == localPlayer) {
            aZ.sendToServer(new aS((float)d / 2.0F));
            paramMouseScrollEvent.setCanceled(true);
            return;
          } 
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\x.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */