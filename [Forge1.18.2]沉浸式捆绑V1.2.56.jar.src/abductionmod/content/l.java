package abductionmod.content;

import javax.annotation.Nullable;

public class l {
  public n definition;
  
  @Nullable
  public Integer left;
  
  @Nullable
  public Integer right;
  
  @Nullable
  public Integer up;
  
  @Nullable
  public Integer down;
  
  @Nullable
  public Integer forward;
  
  @Nullable
  public Integer backward;
  
  @Nullable
  public Integer parent;
  
  @Nullable
  public k.a struggle;
  
  public l(n paramn) {
    this.definition = paramn;
  }
  
  public l withStruggle(k.a parama) {
    this.struggle = parama;
    return this;
  }
  
  public l withLeftRight(Integer paramInteger) {
    this.left = paramInteger;
    this.right = paramInteger;
    return this;
  }
  
  public l withLeft(Integer paramInteger) {
    this.left = paramInteger;
    return this;
  }
  
  public l withForward(Integer paramInteger) {
    this.forward = paramInteger;
    return this;
  }
  
  public l withBackward(Integer paramInteger) {
    this.backward = paramInteger;
    return this;
  }
  
  public l withRight(Integer paramInteger) {
    this.right = paramInteger;
    return this;
  }
  
  public l withUp(Integer paramInteger) {
    this.up = paramInteger;
    return this;
  }
  
  public l withDown(Integer paramInteger) {
    this.down = paramInteger;
    return this;
  }
  
  public l returnTo(Integer paramInteger) {
    this.parent = paramInteger;
    return this;
  }
  
  public l copy(Integer paramInteger) {
    l l1 = k.getById(paramInteger.intValue());
    this.definition.copy(l1.definition);
    return withUp(l1.up).withDown(l1.down).withRight(l1.right).withLeft(l1.left).withForward(l1.forward).withBackward(l1.backward).withStruggle(l1.struggle);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\l.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */