package navigation;

import game.GameWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * MouseListener handles the events for clicking into the GameBoard
 *
 * @author Manuel Adams
 * @since 2017-22-03
 */
public class MouseListener extends MouseAdapter implements ActionListener {
    private GameWindow gameWindow;
    private int clickedY;
    private int clickedX;

    /**
     * @param gameWindow the gameWindow with the necessary methods for the click events
     * @param x          the x coordinate for the clicked Panel
     * @param y          the y coordinate for the clicked Panel
     */
    public MouseListener(GameWindow gameWindow, int x, int y) {
        this.gameWindow = gameWindow;
        clickedX = x;
        clickedY = y;
    }

    /**
     * handles the player movement when mouse clicking into the GameBoard
     *
     * @param e event for the click
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel gameLabel = (JLabel) e.getSource();

        if (e.getButton() == MouseEvent.BUTTON1) {
            int playerX = gameWindow.getDionaRapModel().getPlayer().getX();
            int playerY = gameWindow.getDionaRapModel().getPlayer().getY();

            if (playerX - clickedX == 1 && playerY - clickedY == -1) {
                gameWindow.getDionaRapController().movePlayer(1);
            } else if (playerX - clickedX == 0 && playerY - clickedY == -1) {
                gameWindow.getDionaRapController().movePlayer(2);
            } else if (playerX - clickedX == -1 && playerY - clickedY == -1) {
                gameWindow.getDionaRapController().movePlayer(3);
            } else if (playerX - clickedX == 1 && playerY - clickedY == 0) {
                gameWindow.getDionaRapController().movePlayer(4);
            } else if (playerX - clickedX == 0 && playerY - clickedY == 0) {
                gameWindow.getDionaRapController().shoot();
            } else if (playerX - clickedX == -1 && playerY - clickedY == 0) {
                gameWindow.getDionaRapController().movePlayer(6);
            } else if (playerX - clickedX == 1 && playerY - clickedY == 1) {
                gameWindow.getDionaRapController().movePlayer(7);
            } else if (playerX - clickedX == 0 && playerY - clickedY == 1) {
                gameWindow.getDionaRapController().movePlayer(8);
            } else if (playerX - clickedX == -1 && playerY - clickedY == 1) {
                gameWindow.getDionaRapController().movePlayer(9);
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            gameWindow.add(gameWindow.getPopupMenu());
            gameWindow.getPopupMenu().show(gameLabel, e.getX(), e.getY());
        }
    }

    /**
     * empty generated method
     *
     * @param e empty event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
