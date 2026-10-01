/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraftforge.client.extensions.common.IClientItemExtensions
 *  software.bernie.geckolib.core.Animatable
 *  software.bernie.geckolib.core.manager.AnimationData
 *  software.bernie.geckolib.core.manager.AnimationFactory
 *  software.bernie.geckolib.util.GeckoLibUtil
 */
package com.dev.am.immersive_restraints.render;

import com.dev.am.immersive_restraints.core.ModCore;
import software.bernie.geckolib.core.object.AnimationState;
import java.util.function.Consumer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.core.Animatable;
import software.bernie.geckolib.core.manager.AnimationData;
import software.bernie.geckolib.core.manager.AnimationFactory;
import software.bernie.geckolib.util.GeckoLibUtil;

public class GeoArmorRenderer
extends Item
implements Animatable {
    public AnimationFactory factory = GeckoLibUtil.createFactory((Animatable)this);

    public GeoArmorRenderer() {
        super(new Item.Properties());
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new AnimationState(this));
    }

    public void registerControllers(AnimationData animationData) {
    }

    public AnimationFactory getFactory() {
        return this.factory;
    }
}