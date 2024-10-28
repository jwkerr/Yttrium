package au.lupine.yttrium.client.mixin.hud;

import au.lupine.yttrium.client.config.YttriumConfig;
import au.lupine.yttrium.client.util.ExperienceUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "shouldRenderSpectatorCrosshair", at = @At("RETURN"), cancellable = true)
    private void renderSpectatorCrosshair(HitResult hitResult, CallbackInfoReturnable<Boolean> cir) {
        if (YttriumConfig.getInstance().renderSpectatorCrosshair) cir.setReturnValue(true);
    }

    @ModifyVariable(method = "renderExperienceBar", at = @At("STORE"), ordinal = 0)
    private String renderTotalExperience(String experienceString) {
        if (!YttriumConfig.getInstance().renderTotalExperience) return experienceString;

        ClientPlayerEntity player = client.player;
        if (player == null) return experienceString;

        return experienceString + " (" + ExperienceUtil.getTotalXP(player) + ")";
    }
}
