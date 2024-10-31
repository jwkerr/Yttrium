package au.lupine.yttrium.client.mixin.cosmetic;

import au.lupine.yttrium.client.config.YttriumConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.entity.feature.Deadmau5FeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Deadmau5FeatureRenderer.class)
public class Deadmau5FeatureRendererMixin {

    @Redirect(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/network/AbstractClientPlayerEntity;FFFFFF)V",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z")
    )
    private boolean shouldRenderDeadmau5Ears(String instance, Object object) {
        if (!(object instanceof String name)) return false;

        if (name.equals("deadmau5")) return true;

        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return false;

        YttriumConfig config = YttriumConfig.getInstance();
        boolean earsOnSelf = config.earsOnSelf;
        boolean earsOnOthers = config.earsOnOthers;

        String playerName = player.getName().getString();

        if (name.equals(playerName)) {
            return earsOnSelf;
        } else {
            return earsOnOthers;
        }
    }
}
