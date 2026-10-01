package abductionmod.content;

import com.dev.am.mod.gamerule.GameruleRegistry;
import com.dev.am.mod.gamerule.StruggleFailStrengthPercent;
import com.dev.am.mod.gamerule.StruggleSuccessStrengthPercent;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

public class be {
  private final boolean isSuccess;
  
  private final i direction;
  
  public be(FriendlyByteBuf paramFriendlyByteBuf) {
    this.isSuccess = paramFriendlyByteBuf.readBoolean();
    this.direction = i.getById(paramFriendlyByteBuf.readInt());
  }
  
  public be(boolean paramBoolean, i parami) {
    this.isSuccess = paramBoolean;
    this.direction = parami;
  }
  
  public static void encode(be parambe, FriendlyByteBuf paramFriendlyByteBuf) {
    paramFriendlyByteBuf.writeBoolean(parambe.isSuccess);
    paramFriendlyByteBuf.writeInt(parambe.direction.id);
  }
  
  public static void handle(be parambe, Supplier<NetworkEvent.Context> paramSupplier) {
    NetworkEvent.Context context = paramSupplier.get();
    context.enqueueWork(() -> {
          ServerPlayer serverPlayer = ((NetworkEvent.Context)paramSupplier.get()).getSender();
          if (serverPlayer instanceof ay) {
            k.a a;
            ay ay = (ay)serverPlayer;
            ItemStack itemStack = bm.getFirstBondageWithBaseResistance((LivingEntity)serverPlayer, bm.struggleStackPredicate);
            if (itemStack == null)
              return; 
            float f = parambe.isSuccess ? GameruleRegistry.getGameruleValue(serverPlayer.f_19853_, StruggleSuccessStrengthPercent.ID).intValue() : -GameruleRegistry.getGameruleValue(serverPlayer.f_19853_, StruggleFailStrengthPercent.ID).intValue();
            f /= 100.0F;
            aC aC = (aC)itemStack.m_41720_();
            if (parambe.isSuccess) {
              float f1 = bm.getUntyingPower(itemStack, serverPlayer.m_21205_());
              if (f1 > 1.0F) {
                if (serverPlayer.m_21205_().m_41763_())
                  serverPlayer.m_21205_().m_41622_(1, (LivingEntity)serverPlayer, ()); 
                f = (int)(f * f1);
              } 
              ay.abduction$setUntieClicks(ay.abduction$getUntieClicks() + 1);
            } 
            if (ay.abduction$getUntieClicks() % 3 == 0 || !parambe.isSuccess) {
              SoundEvent soundEvent = aC.properties.getSound(itemStack);
              bm.playSound((LivingEntity)serverPlayer, soundEvent);
            } 
            int j = aC.properties.getResistance(itemStack);
            int k = aC.properties.getBaseResistance(itemStack);
            Integer integer = bl.getCustomTagInt(itemStack, "struggleAddedResistance");
            if (integer == null)
              integer = Integer.valueOf(0); 
            integer = Integer.valueOf(integer.intValue() - (int)f);
            j -= (int)f;
            aZ.sendToClient(serverPlayer, new aX((int)f));
            boolean bool = false;
            if ((integer.intValue() + k) > k * 1.2F) {
              integer = Integer.valueOf((int)(k * 0.2F));
              bool = true;
            } 
            bl.setCustomTag(itemStack, integer, "struggleAddedResistance");
            if (!bool) {
              if (j <= 0) {
                bm.takeOffBondage((LivingEntity)serverPlayer, itemStack, null, SoundEvents.f_12018_, ());
                aZ.sendToClient(serverPlayer, new bd());
              } 
              aZ.sendToClient(serverPlayer, new bi(bi.a.STRUGGLING, Mth.m_14036_(j / k, 0.0F, 2.0F), serverPlayer.m_142049_(), serverPlayer.m_7755_().getString()));
            } 
            if (aC.properties.getStruggleAnimation(itemStack) != null) {
              a = aC.properties.getStruggleAnimation(itemStack);
            } else {
              a = (k.getById(ay.abduction$getAnimationId())).struggle;
            } 
            if (a != null) {
              n n = null;
              switch (bf.$SwitchMap$com$dev$am$minified$MoveDirection[parambe.direction.ordinal()]) {
                case 1:
                  n = a.up();
                  break;
                case 2:
                  n = a.left();
                  break;
                case 3:
                  n = a.right();
                  break;
                case 4:
                  n = a.down();
                  break;
              } 
              if (n != null)
                for (Player player : serverPlayer.f_19853_.m_6907_())
                  aZ.sendToClient((ServerPlayer)player, new ba(n.toNbt((Player)serverPlayer), serverPlayer.m_142081_()));  
            } 
          } 
        });
    context.setPacketHandled(true);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\be.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */