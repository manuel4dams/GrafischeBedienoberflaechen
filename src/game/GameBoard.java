package game;

import config.Paths;
import de.fhwgt.dionarap.model.objects.AbstractPawn;
import de.fhwgt.dionarap.model.objects.Ammo;
import de.fhwgt.dionarap.model.objects.Destruction;
import de.fhwgt.dionarap.model.objects.Obstacle;
import de.fhwgt.dionarap.model.objects.Opponent;
import de.fhwgt.dionarap.model.objects.Player;
import de.fhwgt.dionarap.model.objects.Vortex;
import navigation.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * GameBoard drwa the chess pattern and pawns to the board
 *
 * @author Manuel Adams
 * @since 2017-10-25
 */
public class GameBoard extends JPanel {
    private static final int FIELD_WIDTH = 50;
    private static final int FIELD_HEIGHT = 50;
    private int width;
    private int height;
    private final GameWindow gameWindow;
    private JLabel[][] fields;

    /**
     * Constructs the GameBoard
     *
     * @param gameWindow the owner of the board
     * @param width      the number of horizontal Labels
     * @param height     the number of vertical Labels
     */
    GameBoard(GameWindow gameWindow, int width, int height) {
        this.width = width;
        this.height = height;
        this.gameWindow = gameWindow;
        fields = new JLabel[height][width];
        setLayout(new GridLayout(height, width));
        initField();
        setVisible(true);
    }

    private void initField() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                fields[y][x] = new JLabel();
                fields[y][x].setOpaque(true);
                fields[y][x].setBackground(Color.black);
                fields[y][x].setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
                fields[y][x].addMouseListener(new MouseListener(gameWindow, x, y));
                fields[y][x].setBackground(((x + y) % 2 == 0) ? Color.white : Color.black);
                add(fields[y][x]);
            }
        }
    }

    /**
     * redraw the field
     */
    public void drawField() {
        emptyField();

        for (AbstractPawn abstractPawn : gameWindow.getDionaRapModel().getAllPawns()) {
            int posX = abstractPawn.getY();
            int posY = abstractPawn.getX();

            if (abstractPawn instanceof Player) {
                int playerDirection = ((Player) abstractPawn).getViewDirection();
                setPawnIcon(posX, posY, gameWindow.getTheme(), "player" + playerDirection + ".gif");
            }
            if (abstractPawn instanceof Obstacle) {
                setPawnIcon(posX, posY, gameWindow.getTheme(), "obstacle.gif");
            }
            if (abstractPawn instanceof Opponent) {
                setPawnIcon(posX, posY, gameWindow.getTheme(), "opponent.gif");
            }
            if (abstractPawn instanceof Vortex) {
                setPawnIcon(posX, posY, gameWindow.getTheme(), "vortex.gif");
            }
            if (abstractPawn instanceof Destruction) {
                setPawnIcon(posX, posY, gameWindow.getTheme(), "destruction.gif");
            }
            if (abstractPawn instanceof Ammo) {
                setPawnIcon(posX, posY, gameWindow.getTheme(), "ammo.png");
            }
        }
    }

    private void emptyField() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                fields[i][j].setIcon(null);
            }
        }
    }

    private void setPawnIcon(int posX, int posY, String theme, String filename) {
        ImageIcon icon = new ImageIcon(Paths.themed(theme, filename));
        fields[posX][posY].setIcon(icon);
    }
}
