package au.lupine.yttrium.client.mixin.misc;

import au.lupine.yttrium.client.config.YttriumConfig;
import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin extends Input {

    @Unique private boolean lastForward;
    @Unique private boolean lastSideways;

    @Inject(method = "tick", at = @At("TAIL"))
    private void afterTick(boolean slowDown, float slowDownFactor, CallbackInfo ci) {
        if (!YttriumConfig.getInstance().nullMovement) return;

        boolean forward = pressingForward;
        boolean backward = pressingBack;
        boolean left = pressingLeft;
        boolean right = pressingRight;

        if (forward && !backward) {
            lastForward = true;
        } else if (!forward && backward) {
            lastForward = false;
        }

        if (left && !right) {
            lastSideways = true;
        } else if (!left && right) {
            lastSideways = false;
        }

        if (forward && backward) {
            movementForward = getMultiplier(lastForward);
            if (slowDown) movementForward *= slowDownFactor;
        }

        if (left && right) {
            movementSideways = getMultiplier(lastSideways);
            if (slowDown) movementSideways *= slowDownFactor;
        }
    }

    @Unique
    public float getMultiplier(boolean lastPressedWasPositive) {
        return lastPressedWasPositive ? -1.0f : 1.0f;
    }
}
