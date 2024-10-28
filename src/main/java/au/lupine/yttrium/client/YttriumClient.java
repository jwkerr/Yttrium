package au.lupine.yttrium.client;

import au.lupine.yttrium.client.config.YttriumConfig;
import au.lupine.yttrium.client.manager.ClientTickManager;
import net.fabricmc.api.ClientModInitializer;

public class YttriumClient implements ClientModInitializer {

    public static final String MOD_ID = "yttrium";

    @Override
    public void onInitializeClient() {
        YttriumConfig.HANDLER.load();

        ClientTickManager.getInstance().initialise();
    }
}
