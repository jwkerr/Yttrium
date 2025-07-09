package au.lupine.yttrium.client.mixin.hud;

import au.lupine.yttrium.client.config.YttriumConfig;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.util.Colors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(DebugHud.class)
public class DebugHudMixin {

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = -8421377))
    private static int modifyZAxisColour(int value) {
        if (YttriumConfig.getInstance().modifyZAxisColour) return Colors.BLUE;
        return value;
    }
}
