package au.lupine.yttrium.client.mixin.hud;

import au.lupine.yttrium.client.config.YttriumConfig;
import net.kyori.adventure.platform.fabric.FabricClientAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerListHud.class)
public class PlayerListHudMixin {

    @Shadow @Final private MinecraftClient client;

    @Shadow @Nullable private Text header;

    @Shadow @Nullable private Text footer;

    @ModifyConstant(constant = @Constant(longValue = 80L), method = "collectPlayerEntries")
    private long modifyCount(long maxEntries) {
        long configuredMaxEntries = YttriumConfig.getInstance().maxPlayerListEntries;

        ClientPlayNetworkHandler nh = client.getNetworkHandler();
        if (nh == null) return configuredMaxEntries;

        return configuredMaxEntries <= 0 ? nh.getListedPlayerListEntries().size() : configuredMaxEntries;
    }

    @ModifyConstant(constant = @Constant(intValue = 20), method = "render")
    private int modifyMaxRows(int maxRows) {
        return YttriumConfig.getInstance().maxPlayerListRows;
    }

    @Inject(method = "setHeader", at = @At("TAIL"))
    public void setHeader(Text header, CallbackInfo ci) {
        String headerString = YttriumConfig.getInstance().playerListHeader;
        if (headerString.isEmpty()) return;

        Component component = MiniMessage.miniMessage().deserialize(headerString);
        this.header = FabricClientAudiences.of().toNative(component);
    }

    @Inject(method = "setFooter", at = @At("TAIL"))
    public void setFooter(Text footer, CallbackInfo ci) {
        String footerString = YttriumConfig.getInstance().playerListFooter;
        if (footerString.isEmpty()) return;

        Component component = MiniMessage.miniMessage().deserialize(footerString);
        this.footer = FabricClientAudiences.of().toNative(component);
    }
}
