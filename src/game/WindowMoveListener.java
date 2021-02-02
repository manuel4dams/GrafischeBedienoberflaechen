package game;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * WindowMoveListener sticks the NavigationWindow to the GameWindow
 *
 * @author Manuel Adams
 * @since 2017-11-03
 */
public class WindowMoveListener implements ComponentListener {

    /**
     * empty generated method
     *
     * @param e empty event
     */
    @Override
    public void componentResized(ComponentEvent e) {
    }

    /**
     * gets the position of the GameWindow in order to stick the NavigationWindow to it
     *
     * @param e action event gets the GameWindow
     */
    @Override
    public void componentMoved(ComponentEvent e) {
        GameWindow gameWindow = (GameWindow) e.getSource();
        int posX = (int) gameWindow.getLocation().getX();
        int posY = (int) gameWindow.getLocation().getY();
        int width = gameWindow.getWidth();
        gameWindow.getNavigationWindow().setLocation(posX + width, posY);
    }

    /**
     * empty generated method
     *
     * @param e empty event
     */
    @Override
    public void componentShown(ComponentEvent e) {
    }

    /**
     * empty generated method
     *
     * @param e empty event
     */
    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
