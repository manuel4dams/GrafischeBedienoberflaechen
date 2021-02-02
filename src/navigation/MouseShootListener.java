package navigation;

import game.GameWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MouseShootListener that reacts to the mouse input of the player
 *
 * @author Manuel Adams
 * @since 2017-11-03
 */
class MouseShootListener implements ActionListener {

    /**
     * handles click event for the shot
     *
     * @param e event for the click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton t5 = (JButton) e.getSource();
        NavigationWindow navigationWindow = (NavigationWindow) t5.getTopLevelAncestor();
        ((GameWindow) navigationWindow.getOwner()).shoot();
        navigationWindow.getOwner().requestFocus();
    }
}
