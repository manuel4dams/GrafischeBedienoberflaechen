package navigation;

import java.awt.*;

/**
 * The shape sets the octagon shape of the NavigationWindow
 * Polygon shape for the navigation window (octagon)
 *
 * @author Manuel Adams
 * @since 2018-01-15
 */
class NavigationWindowShape extends Polygon {

    /**
     * Constructs a new shape
     */
    NavigationWindowShape() {
        this.addPoint(0, 50);
        this.addPoint(0, 100);
        this.addPoint(50, 150);
        this.addPoint(100, 150);
        this.addPoint(150, 100);
        this.addPoint(150, 50);
        this.addPoint(100, 0);
        this.addPoint(50, 0);
    }
}
