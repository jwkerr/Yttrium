package au.lupine.yttrium.client.manager;

import au.lupine.yttrium.client.object.Tickable;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;

public class ClientTickManager {

    private static ClientTickManager instance;

    private static final List<Tickable> TICKABLES = new ArrayList<>();

    private ClientTickManager() {}

    public static ClientTickManager getInstance() {
        if (instance == null) instance = new ClientTickManager();
        return instance;
    }

    public void initialise() {
        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
    }

    private void tick(MinecraftClient client) {
        for (Tickable tickable : TICKABLES) {
            tickable.tick();
        }
    }
}
