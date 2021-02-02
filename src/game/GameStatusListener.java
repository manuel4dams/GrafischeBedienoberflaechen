package game;

import config.Paths;
import de.fhwgt.dionarap.model.events.DionaRapChangedEvent;
import de.fhwgt.dionarap.model.events.GameStatusEvent;
import de.fhwgt.dionarap.model.listener.DionaRapListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * GameStatusListener reacts to changes of the game status and GameBoard changes
 *
 * @author Manuel Adams
 * @since 2017-11-03
 */
public class GameStatusListener implements DionaRapListener {

    private GameWindow gameWindow;

    /**
     * Constructs the listener
     *
     * @param gameWindow includes the necessary methods
     */
    GameStatusListener(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    /**
     * if anything on the board has changed redraw it
     *
     * @param e action event for changes on the board
     */
    @Override
    public void modelChanged(DionaRapChangedEvent e) {
        gameWindow.getGameBoard().drawField();
    }

    /**
     * if the player lost or won show the Dialog
     *
     * @param e action event for game status changes
     */
    @Override
    public void statusChanged(GameStatusEvent e) {
        Object[] opt = {"Neues Spiel", "Abbrechen"};
        ImageIcon image = new ImageIcon(Paths.themed(gameWindow.getTheme(), e.isGameOver() ? "loss.gif" : "win.gif"));
        int dialog = JOptionPane.showOptionDialog(gameWindow, null, e.isGameOver() ? "Gameover" : "Victory", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, (Icon) image, opt, opt[0]);
        if (dialog == 0) {
            gameWindow.startGame();
        }
    }
}
