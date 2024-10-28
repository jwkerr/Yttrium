package au.lupine.yttrium.client.util;

import net.minecraft.client.network.ClientPlayerEntity;

public class ExperienceUtil {

    /**
     * This returns a roughly approximated (but mostly accurate) version of the player's current XP
     * There is some trickery to get the amount gained so far towards the next level involving rounding
     *
     * @param player Player to get the total XP of
     * @return An int representing the player's total XP including their progress towards the next
     */
    public static int getTotalXP(ClientPlayerEntity player) {
        int level = player.experienceLevel;
        float progress = player.experienceProgress;

        int currentLevelXP = getXPOfLevel(level); // 1395
        int gainedXP = getXPGainedTowardsNextLevel(level, progress);

        return currentLevelXP + gainedXP;
    }

    /**
     * @param level Level you want to check
     * @return The amount of XP required to get this level
     */
    public static int getXPOfLevel(int level) {
        if (level >= 32) return (int) (4.5 * level * level - 162.5 * level + 2220);
        if (level >= 17) return (int) (2.5 * level * level - 40.5 * level + 360);

        return level * level + 6 * level;
    }

    /**
     * @param level Current player level
     * @param progress Progress towards next level between 0-1
     * @return A rounded amount of XP gained towards the next level
     */
    public static int getXPGainedTowardsNextLevel(int level, float progress) {
        if (level >= 31) return Math.round(progress * (9 * level - 158)); // i.e. xp gained = 0.05 * 112
        if (level >= 16) return Math.round(progress * (5 * level - 38));

        return Math.round(progress * (2 * level + 7));
    }
}
