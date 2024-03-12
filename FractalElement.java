import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Contains the methods a fractal element must contain
 * @author  Milo Bowles
 * @version 6/15/2022
 */
public interface FractalElement {
    /**
     * Draws the fractal element using graphics reference
     * @param g             graphics reference to be used to draw
     * @param displaySize   dimensions of panel to be drawn on
     */
    public void draw(Graphics g, Dimension displaySize);
}
