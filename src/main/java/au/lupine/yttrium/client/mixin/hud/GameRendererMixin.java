package au.lupine.yttrium.client.mixin.hud;

import au.lupine.yttrium.client.config.YttriumConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Shadow @Final MinecraftClient client;

    @Inject(method = "shouldRenderBlockOutline", at = @At("HEAD"), cancellable = true)
    private void renderBlockOutlineIfInSpectator(CallbackInfoReturnable<Boolean> cir) {
        if (!YttriumConfig.getInstance().renderSpectatorCrosshair) return;

        ClientPlayerInteractionManager im = client.interactionManager;
        if (im == null) return;

        ClientWorld world = client.world;
        if (world == null) return;

        ClientPlayerEntity player = client.player;
        if (player == null) return;

        if (!im.getCurrentGameMode().equals(GameMode.SPECTATOR) || client.options.hudHidden) return;

        if (world.getBlockState(BlockPos.ofFloored(player.getEyePos())).isAir()) cir.setReturnValue(true);
    }
}
