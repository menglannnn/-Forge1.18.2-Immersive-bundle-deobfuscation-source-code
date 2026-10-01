package abductionmod.content;

import java.util.function.Consumer;

public class h {
  public boolean showHead;
  
  public boolean showBody;
  
  public boolean showLeftArm;
  
  public boolean showRightArm;
  
  public boolean showLeftLeg;
  
  public boolean showRightLeg;
  
  public boolean showHat;
  
  public boolean showJacket;
  
  public boolean showLeftSleeve;
  
  public boolean showRightSleeve;
  
  public boolean showLeftPants;
  
  public boolean showRightPants;
  
  public static final Consumer<h> modelHeadOnly;
  
  public static final Consumer<h> modelArmsOnly;
  
  public static final Consumer<h> torsoOnly;
  
  public static final Consumer<h> bodyOnly;
  
  public static final Consumer<h> hideArms;
  
  public h copy() {
    h h1 = new h();
    h1.showHead = this.showHead;
    h1.showBody = this.showBody;
    h1.showLeftArm = this.showLeftArm;
    h1.showRightArm = this.showRightArm;
    h1.showLeftLeg = this.showLeftLeg;
    h1.showRightLeg = this.showRightLeg;
    h1.showHat = this.showHat;
    h1.showJacket = this.showJacket;
    h1.showLeftSleeve = this.showLeftSleeve;
    h1.showRightSleeve = this.showRightSleeve;
    h1.showLeftPants = this.showLeftPants;
    h1.showRightPants = this.showRightPants;
    return h1;
  }
  
  static {
    modelHeadOnly = (paramh -> {
        paramh.showBody = false;
        paramh.showLeftArm = false;
        paramh.showRightArm = false;
        paramh.showLeftLeg = false;
        paramh.showRightLeg = false;
      });
    modelArmsOnly = (paramh -> {
        paramh.showHead = false;
        paramh.showHat = false;
        paramh.showBody = false;
        paramh.showLeftLeg = false;
        paramh.showRightLeg = false;
      });
    torsoOnly = (paramh -> {
        paramh.showHead = false;
        paramh.showHat = false;
        paramh.showLeftArm = false;
        paramh.showRightArm = false;
        paramh.showLeftLeg = false;
        paramh.showRightLeg = false;
      });
    bodyOnly = (paramh -> {
        paramh.showHead = false;
        paramh.showHat = false;
      });
    hideArms = (paramh -> {
        paramh.showLeftArm = false;
        paramh.showRightArm = false;
        paramh.showLeftSleeve = false;
        paramh.showRightSleeve = false;
      });
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\h.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */