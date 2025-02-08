package au.lupine.yttrium.client.mixin.misc;

import au.lupine.yttrium.client.config.YttriumConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingBobberEntityRenderer.class)
public class FishingBobberEntityRendererMixin {

    @Inject(method = "shouldRender(Lnet/minecraft/entity/projectile/FishingBobberEntity;Lnet/minecraft/client/render/Frustum;DDD)Z", at = @At("HEAD"), cancellable = true)
    public void cancelBobberRenderIfInHead(FishingBobberEntity fishingBobberEntity, Frustum frustum, double d, double e, double f, CallbackInfoReturnable<Boolean> cir) {
        if (!YttriumConfig.getInstance().removeFishingBobbersObstructingVision) return;

        Entity hooked = fishingBobberEntity.getHookedEntity();
        if (hooked == null) return;

        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        if (hooked.equals(player)) cir.setReturnValue(false);
    }
}
