import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * Contains code for an arc element
 * @author  Milo Bowles
 * @version 6/15/2022
 */
public class Arc implements FractalElement{

    /** radius of arc */
    private int radius;
    /** x coordinate of arc, using cartesian system */
    private int xCoord;
    /** y coordinate of arc, using cartesian system */
    private int yCoord;

    /**
     * Constructor for an arc
     * @param radius    radius of arc
     * @param xCoord    x coordinate of arc, using cartesian system
     * @param yCoord    y coordinate of arc, using cartesian system
     */
    public Arc(double radius, double xCoord, double yCoord) {
        this.radius = (int)radius;
        this.xCoord = (int)xCoord;
        this.yCoord = (int)yCoord;
    }

    /**
     * Draws this arc
     * @param g             the graphics reference used to draw
     * @param displaySize   the dimensions of the panel to be drawn on
     */
    @Override
    public void draw(Graphics g, Dimension displaySize) {

        //translate cartesian to graphics coordinates
        int graphicsXCoord = (displaySize.width / 2)  + xCoord;
        int graphicsYCoord = (displaySize.height / 2) - yCoord;

        //make sure to draw the circles at origin not left-corner

        ((Graphics2D) g).setStroke(new BasicStroke(radius / 10));
        g.setColor(Color.LIGHT_GRAY);
        g.drawArc(graphicsXCoord - radius, graphicsYCoord - radius,
                radius * 2, radius * 2, 40, 10);
    }

}
