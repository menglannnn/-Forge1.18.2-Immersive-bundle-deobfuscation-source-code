package abductionmod.content;

import java.util.HashMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class w {
  public static ItemStack hoveredStack;
  
  public static HashMap<Integer, a> tyingBars = new HashMap<>();
  
  public static Vec3 savedPosition;
  
  public static void reset() {
    tyingBars.clear();
    hoveredStack = null;
    savedPosition = null;
    N.reset();
  }
  
  public static final class a {
    public int tyingBarDisappearTime;
    
    public float tyingBarProgress;
    
    public String entityName;
    
    public bi.a tyingType;
    
    public a(int param1Int, float param1Float, bi.a param1a, String param1String) {
      this.tyingBarDisappearTime = param1Int;
      this.tyingBarProgress = param1Float;
      this.tyingType = param1a;
      this.entityName = param1String;
    }
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\w.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */