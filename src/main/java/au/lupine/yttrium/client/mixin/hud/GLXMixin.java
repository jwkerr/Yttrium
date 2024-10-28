package au.lupine.yttrium.client.mixin.hud;

import au.lupine.yttrium.client.config.YttriumConfig;
import com.mojang.blaze3d.platform.GLX;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GLX.class)
public class GLXMixin {

    @ModifyConstant(method = "_renderCrosshair", constant = @Constant(intValue = 127))
    private static int modifyZAxisColour(int value) {
        if (YttriumConfig.getInstance().modifyZAxisColour) return 0;
        return value;
    }
}
