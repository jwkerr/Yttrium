package au.lupine.yttrium.client.mixin.hud;

import au.lupine.yttrium.client.config.YttriumConfig;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Consumer;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @ModifyArg(
            method = "onKey",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/ScreenshotRecorder;saveScreenshot(Ljava/io/File;Lnet/minecraft/client/gl/Framebuffer;Ljava/util/function/Consumer;)V"),
            index = 2
    )
    public Consumer<Text> onScreenshot(Consumer<Text> messageReceiver) {
        YttriumConfig config = YttriumConfig.getInstance();

        if (config.playSoundOnScreenshot) {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null) player.playSound(SoundEvents.BLOCK_LEVER_CLICK, 1.0F, 1.0F);
        }

        if (!config.cancelScreenshotMessage) return messageReceiver;

        return message -> {};
    }
}
