/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.phys.Vec3
 */
package com.dev.am.immersive_restraints.render;

import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.mechanic.StruggleMinigame;
import com.dev.am.immersive_restraints.input.KeyBindings;
import com.dev.am.immersive_restraints.config.CommonConfig;
import java.util.HashMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

/*
 * Renamed from abductionmod.content.x
 */
public class RenderHelper {
    public static ItemStack hoveredStack;
    public static HashMap<Integer, a> tyingBars;
    public static Vec3 savedPosition;

    public static void reset() {
        tyingBars.clear();
        hoveredStack = null;
        savedPosition = null;
        StruggleMinigame.reset();
    }

    static {
        tyingBars = new HashMap();
    }

    public static final class a {
        public int tyingBarDisappearTime;
        public float tyingBarProgress;
        public String entityName;
        public CommonConfig.a tyingType;

        public a(int n2, float f, CommonConfig.a a2, String string) {
            this.tyingBarDisappearTime = n2;
            this.tyingBarProgress = f;
            this.tyingType = a2;
            this.entityName = string;
        }
    }
}

