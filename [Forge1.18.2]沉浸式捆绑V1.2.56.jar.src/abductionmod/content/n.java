package abductionmod.content;

import java.util.ArrayList;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;

public class n {
  public a[] children;
  
  public float camYaw;
  
  public float camPitch;
  
  public float camRoll;
  
  public float camX;
  
  public float camY;
  
  public float camZ;
  
  public float heightModifier = 1.0F;
  
  public float jumpHeightModifier = 1.0F;
  
  public n(a... paramVarArgs) {
    this.children = paramVarArgs;
  }
  
  public n(CompoundTag paramCompoundTag) {
    ArrayList<a> arrayList = new ArrayList();
    for (Tag tag : paramCompoundTag.m_128437_("list", 10))
      arrayList.add(new a((CompoundTag)tag)); 
    this.children = arrayList.<a>toArray(new a[0]);
    this.camYaw = paramCompoundTag.m_128457_("camYaw");
    this.camPitch = paramCompoundTag.m_128457_("camPitch");
    this.camRoll = paramCompoundTag.m_128457_("camRoll");
    this.camX = paramCompoundTag.m_128457_("camX");
    this.camY = paramCompoundTag.m_128457_("camY");
    this.camZ = paramCompoundTag.m_128457_("camZ");
    this.jumpHeightModifier = paramCompoundTag.m_128457_("jumpHeightModifier");
    this.heightModifier = paramCompoundTag.m_128457_("heightModifier");
  }
  
  public n withCamYaw(float paramFloat) {
    this.camYaw = paramFloat;
    return this;
  }
  
  public n withCamPitch(float paramFloat) {
    this.camPitch = paramFloat;
    return this;
  }
  
  public n withCamRoll(float paramFloat) {
    this.camRoll = paramFloat;
    return this;
  }
  
  public n withCamX(float paramFloat) {
    this.camX = paramFloat;
    return this;
  }
  
  public n withCamY(float paramFloat) {
    this.camY = paramFloat;
    return this;
  }
  
  public n withHeightModifier(float paramFloat) {
    this.heightModifier = paramFloat;
    return this;
  }
  
  public n withJumpHeightModifier(float paramFloat) {
    this.jumpHeightModifier = paramFloat;
    return this;
  }
  
  public n withCamZ(float paramFloat) {
    this.camZ = paramFloat;
    return this;
  }
  
  public CompoundTag toNbt(Player paramPlayer) {
    CompoundTag compoundTag = new CompoundTag();
    ListTag listTag = new ListTag();
    for (a a1 : this.children)
      listTag.add(a1.toNbt(paramPlayer)); 
    compoundTag.m_128365_("list", (Tag)listTag);
    compoundTag.m_128350_("camYaw", this.camYaw);
    compoundTag.m_128350_("camPitch", this.camPitch);
    compoundTag.m_128350_("camRoll", this.camRoll);
    compoundTag.m_128350_("camX", this.camX);
    compoundTag.m_128350_("camY", this.camY);
    compoundTag.m_128350_("camZ", this.camZ);
    compoundTag.m_128350_("jumpHeightModifier", this.jumpHeightModifier);
    compoundTag.m_128350_("heightModifier", this.heightModifier);
    return compoundTag;
  }
  
  public n copy(int paramInt) {
    return copy((k.getById(paramInt)).definition);
  }
  
  public n copy(n paramn) {
    return withCamPitch(paramn.camPitch).withCamRoll(paramn.camRoll).withCamYaw(paramn.camYaw).withCamX(paramn.camX).withCamY(paramn.camY).withCamZ(paramn.camZ).withJumpHeightModifier(paramn.jumpHeightModifier).withHeightModifier(paramn.heightModifier);
  }
  
  @Nullable
  public String getName(Player paramPlayer) {
    if (this.children.length == 0)
      return null; 
    StringBuilder stringBuilder = new StringBuilder();
    for (a a1 : this.children)
      stringBuilder.append(a1.getName(paramPlayer)).append("&"); 
    return stringBuilder.substring(0, stringBuilder.length() - 1);
  }
  
  public static class a {
    private final String name;
    
    public boolean isInstant;
    
    public a(String param1String) {
      this.name = param1String;
    }
    
    public a(CompoundTag param1CompoundTag) {
      this.name = param1CompoundTag.m_128461_("name");
      this.isInstant = param1CompoundTag.m_128471_("isInstant");
    }
    
    CompoundTag toNbt(Player param1Player) {
      CompoundTag compoundTag = new CompoundTag();
      compoundTag.m_128359_("name", getName(param1Player));
      compoundTag.m_128379_("isInstant", this.isInstant);
      return compoundTag;
    }
    
    public a instant() {
      this.isInstant = true;
      return this;
    }
    
    public String getName(Player param1Player) {
      return this.name;
    }
  }
  
  public static class b extends a {
    Function<Player, String> function;
    
    public b(Function<Player, String> param1Function) {
      super((String)null);
      this.function = param1Function;
    }
    
    public String getName(Player param1Player) {
      return this.function.apply(param1Player);
    }
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\n.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */