package abductionmod.content;

public enum a {
  TYING(1),
  BEING_TIED(2),
  UNTYING(3),
  STRUGGLING(4),
  STRUGGLING_OVERCHARGE(5),
  NONE(6);
  
  private final int offset;
  
  a(int paramInt1) {
    this.offset = paramInt1;
  }
  
  public int getOffset() {
    return this.offset;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bi$a.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */