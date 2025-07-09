package au.lupine.yttrium.client.mixin.misc;

import au.lupine.yttrium.client.config.YttriumConfig;
import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.math.Vec2f;
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
    private void afterTick(CallbackInfo ci) {
        if (!YttriumConfig.getInstance().nullMovement) return;

        PlayerInput input = playerInput;
        boolean forward = input.forward();
        boolean backward = input.backward();
        boolean left = input.left();
        boolean right = input.right();

        if (forward ^ backward) lastForward = forward;
        if (left ^ right) lastSideways = left;

        float forwardValue = movementVector.y;
        float sidewaysValue = movementVector.x;

        if (forward && backward) forwardValue = getMultiplier(lastForward);

        if (left && right) sidewaysValue = getMultiplier(lastSideways);

        this.movementVector = new Vec2f(sidewaysValue, forwardValue);
    }

    @Unique
    public float getMultiplier(boolean lastPressedWasPositive) {
        return lastPressedWasPositive ? -1.0F : 1.0F;
    }
}
