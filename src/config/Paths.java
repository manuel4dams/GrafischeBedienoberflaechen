package config;

/**
 * Paths returns the paths to the Icons and gif
 *
 * @author Manuel Adams
 * @since 2018-16-01
 */
public class Paths {

    /**
     * returns the path for the navigationIcons
     *
     * @param keyNumber the number for the key where the Icon shall be placed
     * @return navigationIconPaths
     */
    public static String navigationKey(int keyNumber) {
        return System.getProperty("user.dir") + "\\navigator\\taste" + keyNumber + ".gif";
    }

    /**
     * return the IconPaths with the right theme
     *
     * @param theme    the theme the Icon shall have
     * @param filename the name of the Icon or Image
     * @return The path to the Icon or Image
     */
    public static String themed(String theme, String filename) {
        return System.getProperty("user.dir") + "\\images\\" + theme + "\\" + filename;
    }
}
