package abductionmod.content;

public final class a extends Record {
  private final n left;
  
  private final n right;
  
  private final n up;
  
  private final n down;
  
  a(n paramn) {
    this(paramn, paramn, paramn, paramn);
  }
  
  public a(n paramn1, n paramn2, n paramn3, n paramn4) {
    this.left = paramn1;
    this.right = paramn2;
    this.up = paramn3;
    this.down = paramn4;
  }
  
  public final String toString() {
    // Byte code:
    //   0: aload_0
    //   1: <illegal opcode> toString : (Labductionmod/content/k$a;)Ljava/lang/String;
    //   6: areturn
  }
  
  public final int hashCode() {
    // Byte code:
    //   0: aload_0
    //   1: <illegal opcode> hashCode : (Labductionmod/content/k$a;)I
    //   6: ireturn
  }
  
  public final boolean equals(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: <illegal opcode> equals : (Labductionmod/content/k$a;Ljava/lang/Object;)Z
    //   7: ireturn
  }
  
  public n left() {
    return this.left;
  }
  
  public n right() {
    return this.right;
  }
  
  public n up() {
    return this.up;
  }
  
  public n down() {
    return this.down;
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\k$a.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */