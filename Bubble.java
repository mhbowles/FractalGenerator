import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * Contains code for a bubble element
 * @author  Milo Bowles
 * @version 6/15/2022
 */
public class Bubble implements FractalElement{

    /** radius of bubble */
    private int radius;
    /** x coordinate of bubble, using cartesian system */
    private int xCoord;
    /** y coordinate of bubble, using cartesian system */
    private int yCoord;
    /** color of bubble */
    private Color color;

    /**
     * Constructor of bubble
     * @param radius            radius of bubble
     * @param xCoord            x coordinate of bubble, using cartesian system
     * @param yCoord            y coordinate of bubble, using cartesian system
     * @param chosenColor       color selected for the bubble
     * @param hasRandomPastels  if true bubble color is randomly chosen from a set,
     *                          if false bubble color is the original one passed
     */
    public Bubble(double radius, double xCoord, double yCoord, Color chosenColor, boolean hasRandomPastels) {
        this.radius = (int)radius;
        this.xCoord = (int)xCoord;
        this.yCoord = (int)yCoord;
        if (hasRandomPastels) {
            this.color = returnRandomPastel();
        } else {
            this.color = chosenColor;
        }
    }

    /**
     * Draws this bubble
     * @param g             the graphics reference used to draw
     * @param displaySize   the dimensions of the panel to be drawn on
     */
    @Override
    public void draw(Graphics g, Dimension displaySize) {

        //translate cartesian to graphics coordinates
        int graphicsXCoord = (displaySize.width / 2)  + xCoord;
        int graphicsYCoord = (displaySize.height / 2) - yCoord;

        ((Graphics2D) g).setStroke(new BasicStroke());
        g.setColor(color);
        //make sure to draw the circles at origin not left-corner
        g.drawOval(graphicsXCoord - radius, graphicsYCoord - radius,
                radius * 2, radius * 2);
        g.setColor(new Color(color.getRed() / 255f,
                             color.getGreen() / 255f,
                             color.getBlue() / 255f,
                             .4f));
        g.fillOval(graphicsXCoord - radius, graphicsYCoord - radius,
                radius * 2, radius * 2);
    }

    /**
     * Retrieves a random color from a set of 5 pastels
     * @return  a random color from a set of 5 pastels
     */
    private Color returnRandomPastel() {
        Color yellow = new Color(255, 255, 176);
        Color blue = new Color(148, 168, 208);
        Color purple = new Color(221, 212, 240);
        Color pink = new Color(251, 182, 209);
        Color orange = new Color(255, 223, 211);

        Random random = new Random();
        Color pastelColor;
        switch (random.nextInt(6)){
            case(1) -> pastelColor = yellow;
            case(2) -> pastelColor = blue;
            case(3) -> pastelColor = purple;
            case(4) -> pastelColor = pink;
            case(5) -> pastelColor = orange;
            default -> pastelColor = orange;
        }
        return pastelColor;
    }
}
