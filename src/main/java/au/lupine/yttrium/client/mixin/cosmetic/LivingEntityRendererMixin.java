package au.lupine.yttrium.client.mixin.cosmetic;

import au.lupine.yttrium.client.config.YttriumConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

    @Inject(method = "shouldFlipUpsideDown", at = @At("RETURN"), cancellable = true)
    private static void shouldFlipUpsideDown(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        YttriumConfig config = YttriumConfig.getInstance();
        boolean flipSelf = config.flipSelf;
        boolean flipOthers = config.flipOthers;
        boolean flipMobs = config.flipMobs;

        UUID playerUUID = player.getUuid();
        if (entity instanceof PlayerEntity) {
            if (playerUUID.equals(entity.getUuid())) {
                if (flipSelf) cir.setReturnValue(true);
            } else {
                if (flipOthers) cir.setReturnValue(true);
            }
        } else {
            if (flipMobs) cir.setReturnValue(true);
        }
    }
}
