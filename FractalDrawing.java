import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

/**
 * Contains the code a FractalDrawing JFrame;
 *      draws a fractal on a panel when updated, based on data pulled from subject
 *      this is the observer in the subject/observer model
 * @author Milo Bowles
 * @version 6/15/2022
 */
public class FractalDrawing extends JFrame implements FractalObserver{

    /** dimensions of the FractalDrawing JFrame */
    private static final Dimension WINDOW_DIMENSION = new Dimension(600, 600);

    /** toolKit of the FractalDrawing JFrame */
    private Toolkit toolKit;
    /** subject of the FractalDrawing, whose data will be used in drawing */
    private FractalSubject subject;
    /** the panel the fractal will be drawn on */
    private DrawingArea drawPanel;
    /** a list of fractalElements to be used to draw a fractal */
    private ArrayList<FractalElement> elements;

    /**
     * Constructor of FractalDrawing
     * @param subject   the subject FractalDrawing will be attached to,
     *                      pulling data from when triggered
     */
    public FractalDrawing(FractalSubject subject) {
        this.subject = subject;
        subject.attach(this);
        elements = new ArrayList<>(0);

        //sets up the JFrame
        setTitle("Bubbles and Bedlam Fractal");
        setUndecorated(true);
        setResizable(false);
        setSize(WINDOW_DIMENSION.width, WINDOW_DIMENSION.height);
        toolKit = getToolkit();
        Dimension screenSize = toolKit.getScreenSize();
        setLocation(screenSize.width / 2 ,
                    (screenSize.height - getHeight()) / 2);

        //DrawArea
        drawPanel = new DrawingArea();
        drawPanel.setLayout(null);
        drawPanel.setBackground(Color.BLACK);
        getContentPane().add(drawPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * @inheritdoc
     */
    @Override
    public void update() {
        elements = subject.getData();
        drawPanel.repaint();
    }

    /**
     * Contains code for a DrawingArea JPanel,
     *      paintComponent draws each fractal element based off elements list
     */
    private class DrawingArea extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (FractalElement element : elements) {
                element.draw(g, WINDOW_DIMENSION);
            }
        }
    }
}
