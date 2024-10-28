package au.lupine.yttrium.client.mixin.cosmetic;

import au.lupine.yttrium.client.config.YttriumConfig;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @ModifyConstant(method = "renderStars(Lnet/minecraft/client/render/BufferBuilder;)Lnet/minecraft/client/render/BufferBuilder$BuiltBuffer;", constant = @Constant(longValue = 10842L))
    private long modifyStarSeed(long seed) {
        return YttriumConfig.getInstance().starSeed;
    }

    @ModifyConstant(method = "renderStars(Lnet/minecraft/client/render/BufferBuilder;)Lnet/minecraft/client/render/BufferBuilder$BuiltBuffer;", constant = @Constant(intValue = 1500))
    private int modifyStarCount(int count) {
        return YttriumConfig.getInstance().starCount;
    }
}
