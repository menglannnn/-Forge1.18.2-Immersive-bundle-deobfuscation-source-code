package abductionmod.content;

import java.util.function.Function;
import net.minecraft.world.entity.player.Player;

public class b extends n.a {
  Function<Player, String> function;
  
  public b(Function<Player, String> paramFunction) {
    super((String)null);
    this.function = paramFunction;
  }
  
  public String getName(Player paramPlayer) {
    return this.function.apply(paramPlayer);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\n$b.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */