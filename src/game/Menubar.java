package game;

import javax.swing.*;

/**
 * Menubar includes Toolbar position, show/hide navigationWindow and Options for LookAndFeel
 *
 * @author Manuel Adams
 * @since 2017-01-18
 */
class Menubar extends JMenuBar {
    private GameWindow gameWindow;
    private JRadioButtonMenuItem viewToolbarPositionTop;
    private JRadioButtonMenuItem viewToolbarPositionBottom;
    private JCheckBoxMenuItem viewNavigationCheckbox;

    /**
     * Constructs the Menubar
     *
     * @param gameWindow includes the methods for the Toolbar to work properly
     */
    Menubar(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        init();
    }

    private void init() {
        JMenu menuView = new JMenu("Ansicht");
        viewToolbarPositionTop = new JRadioButtonMenuItem("Toolbar Position oben");
        viewToolbarPositionTop.setSelected(true);
        viewToolbarPositionTop.addActionListener(e -> {
                    viewToolbarPositionTop.setSelected(true);
                    viewToolbarPositionBottom.setSelected(false);
                    gameWindow.setToolbar("north");
                }
        );
        menuView.add(viewToolbarPositionTop);
        viewToolbarPositionBottom = new JRadioButtonMenuItem("Toolbar Position unten");
        viewToolbarPositionBottom.addActionListener(e -> {
            viewToolbarPositionBottom.setSelected(true);
            viewToolbarPositionTop.setSelected(false);
            gameWindow.setToolbar("south");
        });
        menuView.add(viewToolbarPositionBottom);
        menuView.addSeparator();
        viewNavigationCheckbox = new JCheckBoxMenuItem("Navigator anzeigen");
        viewNavigationCheckbox.setSelected(true);
        viewNavigationCheckbox.addActionListener(e -> {
            if (!viewNavigationCheckbox.isSelected()) {
                gameWindow.getNavigationWindow().setVisible(false);
            } else {
                gameWindow.getNavigationWindow().setVisible(true);
            }
        });
        menuView.add(viewNavigationCheckbox);
        menuView.addSeparator();
        JMenu viewLookAndFeel = new JMenu("Look and Feel");
        menuView.add(viewLookAndFeel);
        UIManager.LookAndFeelInfo lookAndFeels[] = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo lookAndFeel : lookAndFeels) {
            JMenuItem lookAndFeelItem = new JMenuItem(lookAndFeel.getName());
            String lookAndFeelName = lookAndFeel.getClassName();
            viewLookAndFeel.add(lookAndFeelItem);
            lookAndFeelItem.addActionListener(e -> {
                gameWindow.setGameLookAndFeel(lookAndFeelName);
            });
        }
        this.add(menuView);
    }
}
