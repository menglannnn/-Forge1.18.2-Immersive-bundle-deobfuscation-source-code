package abductionmod.content;

import javax.annotation.Nullable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public interface aw {
  @Nullable
  Entity abduction$getLeashHolder();
  
  void abduction$setLeashedTo(Entity paramEntity, boolean paramBoolean);
  
  boolean abduction$canBeLeashedInstantly(Player paramPlayer);
  
  void abduction$dropLeash(boolean paramBoolean1, boolean paramBoolean2);
  
  void abduction$setDelayedLeashHolderId(int paramInt);
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\aw.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */