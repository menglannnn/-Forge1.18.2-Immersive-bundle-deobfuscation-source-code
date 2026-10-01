/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraftforge.client.extensions.common.IClientItemExtensions
 *  software.bernie.geckolib.core.Animatable
 *  software.bernie.geckolib.core.PlayState
 *  software.bernie.geckolib.core.controller.AnimationController
 *  software.bernie.geckolib.core.event.predicate.AnimationEvent
 *  software.bernie.geckolib.core.manager.AnimationData
 *  software.bernie.geckolib.core.manager.AnimationFactory
 *  software.bernie.geckolib.util.GeckoLibUtil
 */
package com.dev.am.immersive_restraints.render;

import com.dev.am.immersive_restraints.core.ModCore;
import software.bernie.geckolib.core.controller.AnimationController;
import java.util.function.Consumer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.core.Animatable;
import software.bernie.geckolib.core.PlayState;
import software.bernie.geckolib.core.event.predicate.AnimationEvent;
import software.bernie.geckolib.core.manager.AnimationData;
import software.bernie.geckolib.core.manager.AnimationFactory;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BondageRenderer
extends Item
implements Animatable {
    public AnimationFactory factory = GeckoLibUtil.createFactory((Animatable)this);

    public BondageRenderer(Item.Properties properties) {
        super(properties);
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new AnimationController(this));
    }

    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController((Animatable)this, "controller", 0.0f, this::predicate));
    }

    private <AnimationId extends Animatable> PlayState predicate(AnimationEvent<AnimationId> animationEvent) {
        return PlayState.CONTINUE;
    }

    public AnimationFactory getFactory() {
        return this.factory;
    }
}