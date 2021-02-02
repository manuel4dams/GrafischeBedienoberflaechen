package game;

import java.util.HashMap;
import java.util.Map;

/**
 * The Settings to store the default settings and the user added settings
 *
 * @author Manuel Adams
 * @since 2018-06-17
 */
public class Settings {
    private static Map<String, String> settingsMap = new HashMap<>();

    /**
     * Constructs the default settings
     */
    public static void setDefaultSettings() {
        settingsMap.put("gridSizeX", "10");
        settingsMap.put("gridSizeY", "10");
        settingsMap.put("opponentCount", "2");
        settingsMap.put("obstacleCount", "3");
        settingsMap.put("multithread", "true");
        settingsMap.put("opponentStartWaitTime", "10000");
        settingsMap.put("opponentWaitTime", "1000");
        settingsMap.put("shotWaitTime", "500");
        settingsMap.put("randomOpponentWaitTime", "false");
        settingsMap.put("avoidCollisionWithObstacles", "true");
        settingsMap.put("avoidCollisionWithOpponent", "false");
    }

    public static boolean getSettingAsBoolean(String key) {
        String value = settingsMap.get(key);
        return value.equals("true");
    }

    public static int getSettingAsInteger(String key) {
        return Integer.parseInt(settingsMap.get(key));
    }

    public static void setSetting(String key, String value) {
        settingsMap.put(key, value);
    }

    public static String getSetting(String key) {
        return settingsMap.get(key);
    }
}
