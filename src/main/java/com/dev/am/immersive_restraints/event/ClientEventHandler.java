/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.components.Button
 *  net.minecraft.client.gui.components.events.GuiEventListener
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.gui.screens.inventory.InventoryScreen
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.TextComponent
 *  net.minecraft.network.chat.TranslatableComponent
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  top.theillusivec4.curios.api.SlotResult
 */
package com.dev.am.immersive_restraints.event;

import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.block.StocksBlock;
import com.dev.am.immersive_restraints.util.ConfigBase;
import com.dev.am.immersive_restraints.config.ModConfig;
import com.dev.am.immersive_restraints.config.ServerConfig;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.ImmersiveRestraints;
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
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotResult;

@OnlyIn(value=Dist.CLIENT)
public class ClientEventHandler
extends Screen {
    private final List<Button> buttons = Lists.newArrayList();
    private LivingEntity entity;
    int oldItemCount = 0;
    Boolean oldLegUnTied = false;

    public ClientEventHandler(LivingEntity livingEntity) {
        super(Component.empty());
        this.entity = livingEntity;
    }

    protected void init() {
        String string;
        int n2;
        this.children.clear();
        this.buttons.clear();
        int n3 = 120;
        ArrayList<String> arrayList = Abduction.slotNames;
        CurioSlotType aG2 = (CurioSlotType)this.entity;
        this.oldLegUnTied = aG2.abduction$getLegsUntied();
        int n4 = 85;
        for (n2 = 0; n2 < arrayList.size(); ++n2) {
            string = arrayList.get(n2);
            boolean bl2 = true;
            ItemStack itemStack = EntityEventHandler.getBondageInSlot(this.entity, string);
            if (itemStack == null || itemStack.isEmpty() || !EntityEventHandler.canBeTakenOffFreely(itemStack)) {
                bl2 = false;
            }
            if (aG2.abduction$getProtectedClothes() && string.equals("clothes")) {
                bl2 = false;
            }
            Button button2 = new Button(this.width / 2 - n3 / 2 + 27, this.height / 2 - n4 + n2 * 22, n3, 20, (Component)Component.translatable("ui.am.take_off", new Object[]{string}), button -> StocksBlock.sendToServer(new ModConfig(this.entity.getId(), string)));
            button2.active = bl2;
            this.buttons.add((Button)this.addRenderableWidget((GuiEventListener)button2));
        }
        string = new Button(this.width / 2 - n3 / 2 + 27, this.height / 2 - n4 + n2++ * 22, n3, 20, (Component)Component.translatable(this.oldLegUnTied != false ? "ui.am.tie_legs" : "ui.am.untie_legs"), button -> StocksBlock.sendToServer(new ServerConfig(this.entity.getId(), this.oldLegUnTied == false)));
        ((Button)string).active = EntityEventHandler.hasLegBinds(this.entity);
        this.buttons.add((Button)this.addRenderableWidget((GuiEventListener)string));
    }

    public void render(PoseStack poseStack, int n2, int n3, float f) {
        this.renderBackground(poseStack);
        int n4 = 60;
        int n5 = this.width / 2 - 90;
        int n6 = this.height / 2 + 60;
        InventoryScreen.renderEntityInInventory((int)n5, (int)n6, (int)n4, (float)((float)n5 - (float)n2), (float)((float)(n6 - 50) - (float)n3), (LivingEntity)this.entity);
        super.render(poseStack, n2, n3, f);
    }

    public boolean isPauseScreen() {
        return false;
    }

    public void onClose() {
        super.onClose();
        if (this.entity == null || !this.entity.isAlive()) {
            this.clearWidgets();
            return;
        }
        if (this.entity.distanceTo((Entity)Minecraft.getInstance().player) > 8.0f) {
            this.clearWidgets();
            return;
        }
        CurioSlotType aG2 = (CurioSlotType)this.entity;
        List<SlotResult> list = EntityEventHandler.getBondages(this.entity);
        if (aG2.abduction$getLegsUntied() != this.oldLegUnTied.booleanValue()) {
            this.oldLegUnTied = aG2.abduction$getLegsUntied();
            this.init();
        }
        if (this.oldItemCount != list.size()) {
            this.oldItemCount = list.size();
            this.init();
        }
    }
}