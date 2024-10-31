package au.lupine.yttrium.client.config;

import au.lupine.yttrium.client.YttriumClient;
import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.*;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Identifier;

public class YttriumConfig {

    public static final ConfigClassHandler<YttriumConfig> HANDLER = ConfigClassHandler.createBuilder(YttriumConfig.class)
            .id(Identifier.of(YttriumClient.MOD_ID, "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("yttrium.json5"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
                    .setJson5(true)
                    .build())
            .build();

    private static final String COSMETIC = "cosmetic";
    private static final String FLIP_ENTITIES = "flipEntities";
    private static final String DEADMAU5_EARS = "deadmau5Ears";
    private static final String MODIFY_STARS = "modifyStars";

    private static final String HUD = "hud";
    private static final String PLAYER_LIST = "playerList";
    private static final String CROSSHAIR = "crosshair";
    private static final String HOTBAR = "hotbar";

    private static final String MISC = "misc";

    public static YttriumConfig getInstance() {
        return HANDLER.instance();
    }

    public Screen getScreen(Screen parent) {
        return HANDLER.generateGui().generateScreen(parent);
    }

    // Cosmetic

    @SerialEntry
    @AutoGen(category = COSMETIC, group = FLIP_ENTITIES)
    @Boolean(colored = true)
    public boolean flipSelf = false;

    @SerialEntry
    @AutoGen(category = COSMETIC, group = FLIP_ENTITIES)
    @Boolean(colored = true)
    public boolean flipOthers = false;

    @SerialEntry
    @AutoGen(category = COSMETIC, group = FLIP_ENTITIES)
    @Boolean(colored = true)
    public boolean flipMobs = false;

    @SerialEntry
    @AutoGen(category = COSMETIC, group = DEADMAU5_EARS)
    @Boolean(colored = true)
    public boolean earsOnSelf = false;

    @SerialEntry
    @AutoGen(category = COSMETIC, group = DEADMAU5_EARS)
    @Boolean(colored = true)
    public boolean earsOnOthers = false;

    @SerialEntry
    @AutoGen(category = COSMETIC, group = MODIFY_STARS)
    @LongField
    public Long starSeed = 10842L;

    @SerialEntry
    @AutoGen(category = COSMETIC, group = MODIFY_STARS)
    @IntField(min = 0)
    public int starCount = 1500;

    // HUD

    @SerialEntry
    @AutoGen(category = HUD, group = PLAYER_LIST)
    @LongField
    public long maxPlayerListEntries = 0L;

    @SerialEntry
    @AutoGen(category = HUD, group = PLAYER_LIST)
    @IntField
    public int maxPlayerListRows = 40;

    @SerialEntry
    @AutoGen(category = HUD, group = PLAYER_LIST)
    @StringField
    public String playerListHeader = "";

    @SerialEntry
    @AutoGen(category = HUD, group = PLAYER_LIST)
    @StringField
    public String playerListFooter = "";

    @SerialEntry
    @AutoGen(category = HUD, group = CROSSHAIR)
    @Boolean(colored = true)
    public boolean renderSpectatorCrosshair = true;

    @SerialEntry
    @AutoGen(category = HUD, group = CROSSHAIR)
    @Boolean(colored = true)
    public boolean modifyZAxisColour = true;

    @SerialEntry
    @AutoGen(category = HUD, group = HOTBAR)
    @Boolean(colored = true)
    public boolean renderTotalExperience = true;

    // Misc

    @SerialEntry
    @AutoGen(category = MISC)
    @StringField
    public String clientBrand = "";

    @SerialEntry
    @AutoGen(category = MISC)
    @Boolean(colored = true)
    public boolean removeFishingBobbersObstructingVision = false;

    @SerialEntry
    @AutoGen(category = MISC)
    @Boolean(colored = true)
    public boolean nullMovement = true;
}
