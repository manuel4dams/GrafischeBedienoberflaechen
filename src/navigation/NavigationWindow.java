package navigation;

import game.GameWindow;

import javax.swing.*;
import java.awt.*;

/**
 * NavigationWindow includes the KeyboardPanel
 *
 * @author Manuel Adams
 * @since 2017-10-25
 */
public class NavigationWindow extends JWindow {

    /**
     * Constructs a new navigation window
     *
     * @param owner Frame that owns the navigation window and the navigation window is attached to
     */
    public NavigationWindow(GameWindow owner) {
        super(owner);
        Container pane = this.getContentPane();
        KeyboardPanel keyboardPanel = new KeyboardPanel((GameWindow) this.getParent());
        pane.add(keyboardPanel, BorderLayout.CENTER);
        setShape(new NavigationWindowShape());
        pack();
        setVisible(true);
    }
}
