package abductionmod.content;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class a {
  private final String name;
  
  public boolean isInstant;
  
  public a(String paramString) {
    this.name = paramString;
  }
  
  public a(CompoundTag paramCompoundTag) {
    this.name = paramCompoundTag.m_128461_("name");
    this.isInstant = paramCompoundTag.m_128471_("isInstant");
  }
  
  CompoundTag toNbt(Player paramPlayer) {
    CompoundTag compoundTag = new CompoundTag();
    compoundTag.m_128359_("name", getName(paramPlayer));
    compoundTag.m_128379_("isInstant", this.isInstant);
    return compoundTag;
  }
  
  public a instant() {
    this.isInstant = true;
    return this;
  }
  
  public String getName(Player paramPlayer) {
    return this.name;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\n$a.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */