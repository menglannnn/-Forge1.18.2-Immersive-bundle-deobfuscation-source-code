/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.world.entity.player.Player
 */
package com.dev.am.immersive_restraints.mechanic;

import com.dev.am.immersive_restraints.command.ArmorVisibilityCommand;
import com.dev.am.immersive_restraints.animation.StruggleAnimations;
import java.util.ArrayList;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;

/*
 * Renamed from abductionmod.content.n
 */
public class MinigameCamera {
    public a[] children;
    public float camYaw;
    public float camPitch;
    public float camRoll;
    public float camX;
    public float camY;
    public float camZ;
    public float heightModifier = 1.0f;
    public float jumpHeightModifier = 1.0f;

    public MinigameCamera(a ... aArray) {
        this.children = aArray;
    }

    public MinigameCamera(CompoundTag compoundTag) {
        ArrayList<a> arrayList = new ArrayList<a>();
        for (Tag tag : compoundTag.getList("list", 10)) {
            arrayList.add(new a((CompoundTag)tag));
        }
        this.children = arrayList.toArray(new a[0]);
        this.camYaw = compoundTag.getFloat("camYaw");
        this.camPitch = compoundTag.getFloat("camPitch");
        this.camRoll = compoundTag.getFloat("camRoll");
        this.camX = compoundTag.getFloat("camX");
        this.camY = compoundTag.getFloat("camY");
        this.camZ = compoundTag.getFloat("camZ");
        this.jumpHeightModifier = compoundTag.getFloat("jumpHeightModifier");
        this.heightModifier = compoundTag.getFloat("heightModifier");
    }

    public MinigameCamera withCamYaw(float f) {
        this.camYaw = f;
        return this;
    }

    public MinigameCamera withCamPitch(float f) {
        this.camPitch = f;
        return this;
    }

    public MinigameCamera withCamRoll(float f) {
        this.camRoll = f;
        return this;
    }

    public MinigameCamera withCamX(float f) {
        this.camX = f;
        return this;
    }

    public MinigameCamera withCamY(float f) {
        this.camY = f;
        return this;
    }

    public MinigameCamera withHeightModifier(float f) {
        this.heightModifier = f;
        return this;
    }

    public MinigameCamera withJumpHeightModifier(float f) {
        this.jumpHeightModifier = f;
        return this;
    }

    public MinigameCamera withCamZ(float f) {
        this.camZ = f;
        return this;
    }

    public CompoundTag toNbt(Player player) {
        CompoundTag compoundTag = new CompoundTag();
        ListTag listTag = new ListTag();
        for (a a2 : this.children) {
            listTag.add((Object)a2.toNbt(player));
        }
        compoundTag.put("list", (Tag)listTag);
        compoundTag.putFloat("camYaw", this.camYaw);
        compoundTag.putFloat("camPitch", this.camPitch);
        compoundTag.putFloat("camRoll", this.camRoll);
        compoundTag.putFloat("camX", this.camX);
        compoundTag.putFloat("camY", this.camY);
        compoundTag.putFloat("camZ", this.camZ);
        compoundTag.putFloat("jumpHeightModifier", this.jumpHeightModifier);
        compoundTag.putFloat("heightModifier", this.heightModifier);
        return compoundTag;
    }

    public MinigameCamera copy(int n2) {
        return this.copy(StruggleAnimations.getById((int)n2).definition);
    }

    public MinigameCamera copy(MinigameCamera n_02) {
        return this.withCamPitch(n_02.camPitch).withCamRoll(n_02.camRoll).withCamYaw(n_02.camYaw).withCamX(n_02.camX).withCamY(n_02.camY).withCamZ(n_02.camZ).withJumpHeightModifier(n_02.jumpHeightModifier).withHeightModifier(n_02.heightModifier);
    }

    @Nullable
    public String getName(Player player) {
        if (this.children.length == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (a a2 : this.children) {
            stringBuilder.append(a2.getName(player)).append("&");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    public static class a {
        private final String name;
        public boolean isInstant;

        public a(String string) {
            this.name = string;
        }

        public a(CompoundTag compoundTag) {
            this.name = compoundTag.getString("name");
            this.isInstant = compoundTag.getBoolean("isInstant");
        }

        CompoundTag toNbt(Player player) {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putString("name", this.getName(player));
            compoundTag.putBoolean("isInstant", this.isInstant);
            return compoundTag;
        }

        public a instant() {
            this.isInstant = true;
            return this;
        }

        public String getName(Player player) {
            return this.name;
        }
    }

    public static class b
    extends a {
        Function<Player, String> function;

        public b(Function<Player, String> function) {
            super((String)null);
            this.function = function;
        }

        @Override
        public String getName(Player player) {
            return this.function.apply(player);
        }
    }
}

