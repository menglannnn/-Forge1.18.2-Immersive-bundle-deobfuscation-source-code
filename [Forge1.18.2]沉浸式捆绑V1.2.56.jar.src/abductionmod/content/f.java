package abductionmod.content;

import com.dev.am.mod.gamerule.GameruleRegistry;
import com.dev.am.mod.gamerule.LimitedItemPickupForTiedPlayers;
import java.util.Objects;
import net.minecraft.ChatFormatting;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
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

public class f {
  @SubscribeEvent
  public void entitySize(EntityEvent.Size paramSize) {
    Entity entity = paramSize.getEntity();
    if (!entity.isAddedToWorld())
      return; 
    if (entity instanceof Player) {
      Player player = (Player)entity;
      if (bm.isTied((LivingEntity)player)) {
        int i = ((ay)player).abduction$getAnimationId();
        float f1 = (paramSize.getOldSize()).f_20378_;
        float f2 = (k.getById(i)).definition.heightModifier;
        Pose pose = player.m_20089_();
        paramSize.setNewSize(entity.m_6972_(pose).m_20390_(1.0F, f2), false);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onPlayerLogIn(PlayerEvent.PlayerLoggedInEvent paramPlayerLoggedInEvent) {
    Player player = paramPlayerLoggedInEvent.getPlayer();
    bm.syncGamerules(Objects.<MinecraftServer>requireNonNull(player.m_20194_()), paramPlayer2 -> (paramPlayer2 == paramPlayer1));
    if (!player.f_19853_.f_46443_) {
      if (player instanceof av) {
        av av = (av)player;
        av.abduction$setCurrentWardrobeItem(av.abduction$getCurrentWardrobeItem());
      } 
      if (player instanceof ax) {
        ax ax = (ax)player;
        int i = ax.abduction$getTalkArea();
        TranslatableComponent translatableComponent = new TranslatableComponent("command.talkarea.your_value", new Object[] { Integer.valueOf(i) });
        if (i < 0)
          translatableComponent = new TranslatableComponent("command.talkarea.unlimited"); 
        player.m_5661_((Component)translatableComponent.m_130940_(ChatFormatting.GOLD), false);
      } 
    } 
  }
  
  @SubscribeEvent
  public void itemPickUp(EntityItemPickupEvent paramEntityItemPickupEvent) {
    Player player = paramEntityItemPickupEvent.getPlayer();
    if (GameruleRegistry.isGameruleEnabled(player.m_183503_(), LimitedItemPickupForTiedPlayers.ID) && bm.areHandsTied((LivingEntity)player)) {
      Item item = paramEntityItemPickupEvent.getItem().m_32055_().m_41720_();
      if (item instanceof az) {
        paramEntityItemPickupEvent.setResult(Event.Result.DENY);
        paramEntityItemPickupEvent.setCanceled(true);
      } 
    } 
  }
  
  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent paramServerStartingEvent) {
    for (RegistryObject<Item> registryObject : aJ.wearableItems)
      DispenserBlock.m_52672_((ItemLike)registryObject.get(), (DispenseItemBehavior)d.bondageDispenserBehavior); 
    bs.initGagFiles(paramServerStartingEvent.getServer().m_177941_());
    bs.initToolPropertiesFiles(paramServerStartingEvent.getServer().m_177941_());
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\f.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */