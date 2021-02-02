package game;

import javax.swing.BorderFactory;
import java.awt.Color;

/**
 * The BlinkingThread show blinking AmmoBar when the player tries to shot without ammo
 *
 * @author Manuel Adams
 * @since 2018-06-22
 */
public class BlinkThread extends Thread {
    private GameWindow gameWindow;

    /**
     * Constructs the thread
     *
     * @param gameWindow includes the necessary methods for the thread
     */
    BlinkThread(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    /**
     * handles the blinking AmmoBar
     */
    public void run() {
        try {
            for (int interval = 0; interval <= 5; interval++) {
                if (gameWindow.getDionaRapModel().getShootAmount() != 0) {
                    break;
                }
                Thread.sleep(200);
                gameWindow.getToolbar().getAmmoPanel().setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.red, 3, false), "Munition"));
                Thread.sleep(200);
                gameWindow.getToolbar().getAmmoPanel().setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 3, false), "Munition"));
            }
            gameWindow.getToolbar().getAmmoPanel().setBorder(BorderFactory.createTitledBorder("Munition"));
            gameWindow.setThreatState(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
