package com.dev.am.mixin.client_mixins;

import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = {Camera.class}, remap = false)
public abstract class CameraMix {}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\com\dev\am\mixin\client_mixins\CameraMix.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */