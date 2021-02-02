package navigation;

import config.Paths;
import game.GameWindow;

import javax.swing.*;
import java.awt.*;

/**
 * KeyboardPanel with the navigationButtons
 *
 * @author Manuel Adams
 * @since 2017-11-03
 */
class KeyboardPanel extends JPanel {

    /**
     * Constructs the KeyboardPanel
     *
     * @param gameWindow super class of the keyboardPanel
     */
    KeyboardPanel(GameWindow gameWindow) {
        super(new GridLayout(3, 3));

        setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JButton[] button = new JButton[10];
        for (int i = 9; i > 0; i--) {
            button[i] = new JButton();
            button[i].setIcon(new ImageIcon(Paths.navigationKey(i)));
            button[i].setPreferredSize(new Dimension(50, 50));
            button[i].setMargin(new Insets(0, 0, 0, 0));
            button[i].setActionCommand(String.valueOf(i));
            if (i == 5) {
                button[i].addActionListener(new MouseShootListener());
            } else {
                button[i].addActionListener(new KeyboardMouseMoveListener(gameWindow));
            }
            add(button[i]);
        }
    }
}
