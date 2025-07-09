package au.lupine.yttrium.client.mixin.hud;

import au.lupine.yttrium.client.config.YttriumConfig;
import au.lupine.yttrium.client.util.ExperienceUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.bar.Bar;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Bar.class)
public interface BarMixin {

    @Redirect(
            method = "drawExperienceLevel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/text/Text;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/text/MutableText;"
            )
    )
    private static MutableText drawTotalExperience(String key, Object[] args, DrawContext ctx, TextRenderer tr, int level) {
        if (!YttriumConfig.getInstance().renderTotalExperience) return Text.translatable("gui.experience.level", level);

        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return Text.translatable("gui.experience.level", level);

        return Text.literal(level + " (" + ExperienceUtil.getTotalXP(player) + ")");
    }
}
