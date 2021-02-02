package game;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * PopupMenu lets the player change the theme
 *
 * @author Manuel Adams
 * @since 2018-03-22
 */
public class PopupMenu extends JPopupMenu {
    private GameWindow gameWindow;
    private List<JMenuItem> themeMenuItems = new ArrayList<>();

    /**
     * Constructs the PopupMenu
     *
     * @param gameWindow the owner with the classes to change themes
     */
    PopupMenu(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        String themesPath = System.getProperty("user.dir") + File.separator + "images";
        File themesFolder = new File(themesPath);
        if (!themesFolder.isDirectory()) {
            throw new IllegalArgumentException("Themes path is not a directory!");
        }
        List<String> themeNames = Arrays.asList(Objects.requireNonNull(themesFolder.list()));
        for (String themeName : themeNames) {
            File currentThemeFile = new File(themesFolder, themeName);
            if (!currentThemeFile.isDirectory()) {
                continue;
            }
            JMenuItem themeMenuItem = new JMenuItem(themeName);
            themeMenuItem.addActionListener(e -> onThemeClick(themeMenuItem));
            themeMenuItem.setActionCommand(themeName);
            if (themeName.equals("alien")) {
                themeMenuItem.setBackground(Color.red);
            }
            add(themeMenuItem);
            themeMenuItems.add(themeMenuItem);
        }
    }

    private void onThemeClick(JMenuItem item) {
        gameWindow.setTheme(item.getActionCommand());

        for (JMenuItem menuItem : themeMenuItems) {
            menuItem.setBackground(null);
        }
        item.setBackground(Color.red);
    }
}
