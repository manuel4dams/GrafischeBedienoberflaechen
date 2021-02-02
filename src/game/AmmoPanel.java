package game;

import config.Paths;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The AmmoPanel draws the AmmoIcons in the panels
 *
 * @author Manuel Adams
 * @since 2018-06-17
 */
public class AmmoPanel extends JPanel {
    private GameWindow gameWindow;
    private int index;
    private int sidelength;
    private Graphics2D ammoIcob2D;

    /**
     * Constructs the AmmoPanel
     *
     * @param gameWindow includes the necessary methods for the panel
     * @param i          Index for the Panel
     */
    AmmoPanel(GameWindow gameWindow, int i) {
        this.gameWindow = gameWindow;
        index = i;
    }

    /**
     * Paints the 2d graphics in the Panel
     *
     * @param ammoIcon holds the width and height for the calculation of the size
     */
    public void paint(Graphics ammoIcon) {
        int remainingShots = gameWindow.getDionaRapModel().getShootAmount();
        ammoIcob2D = (Graphics2D) ammoIcon;
        ammoIcob2D.setColor(getBackground());
        ammoIcob2D.fillRect(0, 0, getWidth(), getHeight());
        if (getWidth() < getHeight()) {
            sidelength = getWidth();
        } else {
            sidelength = getHeight();
        }
        if (index == 0) {
            if (remainingShots == 1 || remainingShots == 2 || remainingShots == 3) {
                initImage();
            } else if (remainingShots > 3) {
                ammoIcob2D.setColor(Color.black);
                ammoIcob2D.drawString("*" + remainingShots, 10, 10);
            }
        } else if (index == 1) {
            if (remainingShots == 2 || remainingShots == 3 || remainingShots > 3) {
                initImage();
            }
        } else if (index == 2) {
            if (remainingShots == 3 || remainingShots > 3) {
                initImage();
            }
        }
    }

    private void initImage() {
        BufferedImage ammoImage = null;
        try {
            ammoImage = ImageIO.read(new File(Paths.themed(gameWindow.getTheme(), "ammo.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ammoIcob2D.drawImage(ammoImage, 0, 0, sidelength, sidelength, null);
        ammoIcob2D.setColor(Color.black);
        ammoIcob2D.drawRect(0, 0, sidelength - 1, sidelength - 1);
    }
}
