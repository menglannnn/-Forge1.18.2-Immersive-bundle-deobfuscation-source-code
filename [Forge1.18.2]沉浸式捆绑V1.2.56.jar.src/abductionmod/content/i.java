package abductionmod.content;

import java.util.Random;

public enum i {
  LEFT(0),
  UP(1),
  DOWN(2),
  RIGHT(3),
  FORWARD(4),
  BACKWARD(5);
  
  public final int id;
  
  i(int paramInt1) {
    this.id = paramInt1;
  }
  
  public static i random() {
    return values()[(new Random()).nextInt((values()).length)];
  }
  
  public static i randomKeypadDirection() {
    i i1 = values()[(new Random()).nextInt((values()).length)];
    return (i1 == FORWARD || i1 == BACKWARD) ? randomKeypadDirection() : i1;
  }
  
  public static i getById(int paramInt) {
    for (i i1 : values()) {
      if (i1.id == paramInt)
        return i1; 
    } 
    return null;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\i.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */