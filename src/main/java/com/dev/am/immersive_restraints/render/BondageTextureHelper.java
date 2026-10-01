/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  dev.kosmx.playerAnim.impl.IUpperPartHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.HumanoidModel
 *  net.minecraft.client.model.Model
 *  net.minecraft.client.model.PlayerModel
 *  net.minecraft.client.model.geom.ModelLayerLocation
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.RenderType
 *  net.minecraft.client.renderer.entity.EntityRenderer
 *  net.minecraft.client.renderer.entity.ItemRenderer
 *  net.minecraft.client.renderer.entity.RenderLayerParent
 *  net.minecraft.client.renderer.entity.layers.RenderLayer
 *  net.minecraft.client.renderer.entity.player.PlayerRenderer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.HumanoidArm
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  net.minecraftforge.client.ForgeHooksClient
 *  org.jetbrains.annotations.NotNull
 *  top.theillusivec4.curios.api.SlotResult
 */
package com.dev.am.immersive_restraints.render;

import com.dev.am.immersive_restraints.config.TextureConfig;
import com.dev.am.immersive_restraints.item.PartVisibilityEnum;
import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.curios.CurioSlotType;
import com.dev.am.immersive_restraints.util.ModUtil;
import com.dev.am.immersive_restraints.item.BondageItem;
import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.network.StateSyncPacket;
import com.dev.am.immersive_restraints.data.SkinProvider;
import com.dev.am.immersive_restraints.event.PlayerEventHandler;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.item.PartVisibility;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.kosmx.playerAnim.impl.IUpperPartHelper;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.SlotResult;

/*
 * Renamed from abductionmod.content.w
 */
@OnlyIn(value=Dist.CLIENT)
public class BondageTextureHelper<ClothesProtectionCommand extends LivingEntity, ConfigBase extends HumanoidModel<ClothesProtectionCommand>, ModCore extends HumanoidModel<ClothesProtectionCommand>>
extends RenderLayer<ClothesProtectionCommand, ConfigBase> {
    public static ModelLayerLocation BONDAGE_INNER_ARMOR;
    public static ModelLayerLocation BONDAGE_INNER_ARMOR_SLIM;
    public static HumanoidModel<?> modelBeingRendered;
    private final ModCore innerModel;
    public boolean renderingHand;
    public HumanoidArm renderingArmSide;

    public BondageTextureHelper(RenderLayerParent<ClothesProtectionCommand, ConfigBase> renderLayerParent, ModCore a2) {
        super(renderLayerParent);
        this.innerModel = a2;
        ((IUpperPartHelper)this).setUpperPart(false);
    }

    @NotNull
    public static ResourceLocation getTextureLocation(ItemStack itemStack, LivingEntity livingEntity) {
        EntityRenderer entityRenderer;
        EntityRenderer entityRenderer2;
        ResourceLocation resourceLocation = SkinProvider.getResourceLocationFromTextureTag(itemStack, PlayerEventHandler.DYNAMIC_TEXTURE_TAG, SkinProvider.NO_SKIN_OTHER);
        if (resourceLocation != null) {
            return resourceLocation;
        }
        Object object = ((BondageItem)itemStack.getItem()).name;
        BondageItem aK2 = (BondageItem)itemStack.getItem();
        if (aK2.properties.hasSeparateSlimTexture(itemStack) && (entityRenderer2 = (entityRenderer = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer((Entity)livingEntity))) instanceof PlayerRenderer) {
            PlayerRenderer playerRenderer = (PlayerRenderer)entityRenderer2;
            if (((PlayerModel)playerRenderer.getModel()).slim) {
                object = (String)object + "_slim";
            }
        }
        return new ResourceLocation("immersive_restraints", "textures/models/bondage/" + (String)object + ".png");
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int n2, @NotNull ClothesProtectionCommand ClothesProtectionCommand, float f, float f2, float f3, float f4, float f5, float f6) {
        List<SlotResult> list = EntityEventHandler.getBondagesForRendering(ClothesProtectionCommand);
        for (SlotResult slotResult : list) {
            ItemStack itemStack = slotResult.stack();
            float f7 = 0.0f;
            if (ClothesProtectionCommand instanceof CurioSlotType) {
                Float f8;
                CurioSlotType aG2 = (CurioSlotType)ClothesProtectionCommand;
                String string = "";
                String string2 = itemStack.getItem().getDescriptionId();
                if (string2.endsWith("_gag")) {
                    string = "gag";
                } else if (string2.endsWith("_blindfold")) {
                    string = "blindfold";
                }
                f7 = 0.0f;
                if (!string.isEmpty()) {
                    f7 = aG2.abduction$getAdjustmentOffsetsMap().getFloat(string);
                }
                if ((f8 = PlayerEventHandler.getCustomTagFloat(itemStack, PlayerEventHandler.DATA_AM_ADJUSTMENT_MAP_TAG)) != null && f8.floatValue() != 0.0f) {
                    f7 = f8.floatValue();
                }
            }
            this.renderBondagePiece(poseStack, multiBufferSource, ClothesProtectionCommand, f7, EquipmentSlot.LEGS, n2, this.innerModel, BondageTextureHelper.getTextureLocation(itemStack, ClothesProtectionCommand), itemStack);
        }
    }

    private void renderBondagePiece(PoseStack poseStack, MultiBufferSource multiBufferSource, ClothesProtectionCommand ClothesProtectionCommand, float f, EquipmentSlot equipmentSlot, int n2, ModCore a2, ResourceLocation resourceLocation, ItemStack itemStack) {
        ((HumanoidModel)this.getArmorModelForPlacement()).setAllVisible(a2);
        this.setPartVisibility(a2, ClothesProtectionCommand, itemStack, equipmentSlot);
        HumanoidModel humanoidModel = (HumanoidModel)this.getArmorModelHook(ClothesProtectionCommand, ClothesProtectionCommand.getArmorTexture(equipmentSlot), equipmentSlot, a2);
        humanoidModel.crouching = false;
        this.renderModel(poseStack, multiBufferSource, ClothesProtectionCommand, f, n2, false, (Model)humanoidModel, 1.0f, 1.0f, 1.0f, resourceLocation, itemStack);
    }

    protected void setPartVisibility(ModCore a2, ClothesProtectionCommand ClothesProtectionCommand, ItemStack itemStack, EquipmentSlot equipmentSlot) {
        if (this.renderingHand) {
            a2.setAllVisible(false);
            if (this.renderingArmSide == HumanoidArm.LEFT) {
                ((HumanoidModel)a2).leftArm.visible = true;
            } else {
                ((HumanoidModel)a2).rightArm.visible = true;
            }
            return;
        }
        a2.setAllVisible(true);
        CurioSlotType aG2 = (CurioSlotType)ClothesProtectionCommand;
        if (!(itemStack.getItem() instanceof StateSyncPacket) && aG2.abduction$getLegsUntied()) {
            ((HumanoidModel)a2).leftArm.visible = false;
            ((HumanoidModel)a2).rightArm.visible = false;
        }
    }

    private void renderModel(PoseStack poseStack, MultiBufferSource multiBufferSource, ClothesProtectionCommand ClothesProtectionCommand, float f, int n2, boolean bl2, Model model, float f2, float f3, float f4, ResourceLocation resourceLocation, ItemStack itemStack) {
        VertexConsumer vertexConsumer = ItemRenderer.getArmorFoilBuffer((MultiBufferSource)multiBufferSource, (RenderType)RenderType.armorCutoutNoCull((ResourceLocation)resourceLocation), (boolean)false, (boolean)bl2);
        if (model instanceof HumanoidModel) {
            HumanoidModel humanoidModel;
            modelBeingRendered = humanoidModel = (HumanoidModel)model;
            this.applyHeadOffsetAdjustments(f);
            PartVisibilityEnum PartVisibilityEnum = new PartVisibilityEnum(humanoidModel);
            Object object = itemStack.getItem();
            if (object instanceof BondageItem) {
                BondageItem aK2 = (BondageItem)object;
                object = new PartVisibilityEnum(humanoidModel);
                aK2.properties.getModelVisibilityConsumer(itemStack).accept((PartVisibility)object);
                List<SlotResult> list = EntityEventHandler.getBondagesForRendering(ClothesProtectionCommand);
                for (SlotResult slotResult : list) {
                    Item item = slotResult.stack().getItem();
                    if (!(item instanceof BondageItem)) continue;
                    BondageItem aK3 = (BondageItem)item;
                    aK3.hideBodyParts.accept((PartVisibility)object);
                }
                ((PartVisibilityEnum)object).unloadInto(humanoidModel);
            }
            model.renderToBuffer(poseStack, vertexConsumer, n2, OverlayTexture.NO_OVERLAY, f2, f3, f4, 1.0f);
            PartVisibilityEnum.unloadInto(humanoidModel);
            modelBeingRendered = null;
        }
    }

    private void applyHeadOffsetAdjustments(float f) {
        if (f != 0.0f) {
            float f2 = ((HumanoidModel)this.innerModel).body.yRot;
            float f3 = ((HumanoidModel)this.innerModel).body.xRot;
            float f4 = ((HumanoidModel)this.innerModel).body.xScale;
            float f5 = ((HumanoidModel)this.innerModel).body.yScale;
            ((HumanoidModel)this.innerModel).body.yRot = 0.0f;
            ((HumanoidModel)this.innerModel).body.xRot = 0.0f;
            ((HumanoidModel)this.innerModel).body.xScale = 0.0f;
            ((HumanoidModel)this.innerModel).body.yScale -= f;
            this.rotateAroundPivot(((HumanoidModel)this.innerModel).body, ((HumanoidModel)this.innerModel).body.zRot, f5, ((HumanoidModel)this.innerModel).body.xRot, f2, f3, f4);
        }
    }

    public void rotateAroundPivot(ModelPart modelPart, float f, float f2, float f3, float f4, float f5, float f6) {
        modelPart.zRot -= f;
        modelPart.yScale -= f2;
        modelPart.xRot -= f3;
        float f7 = (float)((double)modelPart.yScale * Math.cos(f4) - (double)modelPart.xRot * Math.sin(f4));
        float f8 = (float)((double)modelPart.yScale * Math.sin(f4) + (double)modelPart.xRot * Math.cos(f4));
        modelPart.yScale = f7;
        modelPart.xRot = f8;
        float f9 = (float)((double)modelPart.xRot * Math.sin(f5) + (double)modelPart.zRot * Math.cos(f5));
        f8 = (float)((double)modelPart.xRot * Math.cos(f5) - (double)modelPart.zRot * Math.sin(f5));
        modelPart.zRot = f9;
        modelPart.xRot = f8;
        f9 = (float)((double)modelPart.zRot * Math.cos(f6) - (double)modelPart.yScale * Math.sin(f6));
        f7 = (float)((double)modelPart.zRot * Math.sin(f6) + (double)modelPart.yScale * Math.cos(f6));
        modelPart.zRot = f9;
        modelPart.yScale = f7;
        modelPart.zRot += f;
        modelPart.yScale += f2;
        modelPart.xRot += f3;
        modelPart.yRot += f4;
        modelPart.xRot += f5;
        modelPart.xScale += f6;
    }

    protected Model getArmorModelHook(ClothesProtectionCommand ClothesProtectionCommand, ItemStack itemStack, EquipmentSlot equipmentSlot, ModCore a2) {
        return ForgeHooksClient.getArmorModel(ClothesProtectionCommand, (ItemStack)itemStack, (EquipmentSlot)equipmentSlot, a2);
    }

    public /* synthetic */ void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int n2, @NotNull Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
        this.render(poseStack, multiBufferSource, n2, (LivingEntity)entity, f, f2, f3, f4, f5, f6);
    }

    static {
        modelBeingRendered = null;
    }
}

