package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyControlListener lets the player move and shoot with the numPad buttons
 *
 * @author Manuel Adams
 * @since 2017-11-03
 */
public class KeyControlListener implements KeyListener {

    private GameWindow gameWindow;

    KeyControlListener(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    /**
     * empty generated method
     *
     * @param e empty event
     */
    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * empty generated method
     *
     * @param e empty event
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * reacts to the numPad of the Player
     *
     * @param e action for the numPad key
     */
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '5') {
            gameWindow.shoot();
        } else if (e.getKeyChar() >= '1' && e.getKeyChar() <= '9') {
            gameWindow.getDionaRapController().movePlayer(Character.getNumericValue(e.getKeyChar()));
        }
    }
}
