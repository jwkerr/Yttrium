package au.lupine.yttrium.client.mixin.cosmetic;

import au.lupine.yttrium.client.config.YttriumConfig;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @ModifyConstant(method = "buildStarsBuffer", constant = @Constant(longValue = 10842L))
    private long modifyStarSeed(long seed) {
        return YttriumConfig.getInstance().starSeed;
    }

    @ModifyConstant(method = "buildStarsBuffer", constant = @Constant(intValue = 1500))
    private int modifyStarCount(int count) {
        return YttriumConfig.getInstance().starCount;
    }
}
