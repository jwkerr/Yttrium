package au.lupine.yttrium.client.mixin.misc;

import au.lupine.yttrium.client.config.YttriumConfig;
import net.minecraft.client.ClientBrandRetriever;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientBrandRetriever.class)
public class ClientBrandRetrieverMixin {

    @Inject(method = "getClientModName", at = @At("RETURN"), cancellable = true, remap = false)
    private static void modifyClientBrand(CallbackInfoReturnable<String> cir) {
        String clientBrand = YttriumConfig.getInstance().clientBrand;
        if (!clientBrand.isEmpty()) cir.setReturnValue(clientBrand);
    }
}
