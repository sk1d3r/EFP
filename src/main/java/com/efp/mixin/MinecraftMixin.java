package com.efp.mixin;

import com.efp.EFPClient;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow
    private int rightClickDelay;

    @Inject(method = "tick", at = @At("HEAD"))
    private void efp$resetRightClickDelay(CallbackInfo ci) {
        if (EFPClient.isEnabled()) {
            this.rightClickDelay = 0;
        }
    }
}
