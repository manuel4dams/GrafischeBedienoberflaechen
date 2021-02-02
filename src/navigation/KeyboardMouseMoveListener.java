package navigation;

import game.GameWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * KeyboardMouseMoveListener handles mouse clicks in the NavigationWindow
 *
 * @author Manuel Adams
 * @since 2017-11-03
 */
class KeyboardMouseMoveListener implements ActionListener {
    private GameWindow gameWindow;

    /**
     * Constructs the listener
     *
     * @param gameWindow with the necessary methods for handling the click events
     */
    KeyboardMouseMoveListener(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    /**
     * handles mouse clicks
     *
     * @param e Action event for mouse clicks
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gameWindow.getDionaRapController().movePlayer(Integer.parseInt(e.getActionCommand()));
        gameWindow.requestFocus();
    }

}
