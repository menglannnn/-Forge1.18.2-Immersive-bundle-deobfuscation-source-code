package abductionmod.content;

import com.dev.am.Abduction;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotResult;

@OnlyIn(Dist.CLIENT)
public class bk extends Screen {
  private final List<Button> buttons = Lists.newArrayList();
  
  private LivingEntity entity;
  
  int oldItemCount = 0;
  
  Boolean oldLegUnTied = Boolean.valueOf(false);
  
  public bk(LivingEntity paramLivingEntity) {
    super(TextComponent.f_131282_);
    this.entity = paramLivingEntity;
  }
  
  protected void m_7856_() {
    this.f_169369_.clear();
    this.buttons.clear();
    byte b1 = 120;
    ArrayList<String> arrayList = Abduction.slotNames;
    byte b2 = 0;
    ay ay = (ay)this.entity;
    this.oldLegUnTied = Boolean.valueOf(ay.abduction$getLegsUntied());
    byte b3 = 85;
    while (b2 < arrayList.size()) {
      String str = arrayList.get(b2);
      boolean bool = true;
      ItemStack itemStack = bm.getBondageInSlot(this.entity, str);
      if (itemStack == null || itemStack.m_41619_() || !bm.canBeTakenOffFreely(itemStack))
        bool = false; 
      if (ay.abduction$getProtectedClothes() && str.equals("clothes"))
        bool = false; 
      Button button1 = new Button(this.f_96543_ / 2 - b1 / 2 + 27, this.f_96544_ / 2 - b3 + b2 * 22, b1, 20, (Component)new TranslatableComponent("ui.am.take_off", new Object[] { str }), paramButton -> aZ.sendToServer(new bg(this.entity.m_142081_(), paramString)));
      button1.f_93623_ = bool;
      this.buttons.add((Button)m_142416_((GuiEventListener)button1));
      b2++;
    } 
    Button button = new Button(this.f_96543_ / 2 - b1 / 2 + 27, this.f_96544_ / 2 - b3 + b2++ * 22, b1, 20, (Component)new TranslatableComponent(this.oldLegUnTied.booleanValue() ? "ui.am.tie_legs" : "ui.am.untie_legs"), paramButton -> aZ.sendToServer(new bj(this.entity.m_142081_(), !this.oldLegUnTied.booleanValue())));
    button.f_93623_ = bm.hasLegBinds(this.entity);
    this.buttons.add((Button)m_142416_((GuiEventListener)button));
  }
  
  public void m_6305_(PoseStack paramPoseStack, int paramInt1, int paramInt2, float paramFloat) {
    m_7333_(paramPoseStack);
    byte b = 60;
    int i = this.f_96543_ / 2 - 90;
    int j = this.f_96544_ / 2 + 60;
    InventoryScreen.m_98850_(i, j, b, i - paramInt1, (j - 50) - paramInt2, this.entity);
    super.m_6305_(paramPoseStack, paramInt1, paramInt2, paramFloat);
  }
  
  public boolean m_7043_() {
    return false;
  }
  
  public void m_96624_() {
    super.m_96624_();
    if (this.entity == null || !this.entity.m_6084_()) {
      m_7379_();
      return;
    } 
    if (this.entity.m_20270_((Entity)(Minecraft.m_91087_()).f_91074_) > 8.0F) {
      m_7379_();
      return;
    } 
    ay ay = (ay)this.entity;
    List<SlotResult> list = bm.getBondages(this.entity);
    if (ay.abduction$getLegsUntied() != this.oldLegUnTied.booleanValue()) {
      this.oldLegUnTied = Boolean.valueOf(ay.abduction$getLegsUntied());
      m_7856_();
    } 
    if (this.oldItemCount != list.size()) {
      this.oldItemCount = list.size();
      m_7856_();
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bk.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */