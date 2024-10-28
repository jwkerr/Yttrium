package au.lupine.yttrium.client.object;

import java.util.HashMap;
import java.util.Map;

public interface Tickable {

    Map<Tickable, Integer> TICKS_PASSED = new HashMap<>();

    /**
     * @return The ticks between each time the object is ticked
     */
    default int getInterval() {
        return 0;
    }

    default void tick() {
        int ticksPassed = TICKS_PASSED.getOrDefault(this, 0);

        if (ticksPassed >= getInterval()) {
            performTask();
            TICKS_PASSED.put(this, 0);
            return;
        }

        TICKS_PASSED.put(this, ticksPassed + 1);
    }

    void performTask();
}
