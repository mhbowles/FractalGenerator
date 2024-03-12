import java.awt.Color;
import java.util.ArrayList;

/**
 * Contains the code for a fractal generator,
 *      in which fractal elements are calculated and generated;
 *      the subject in the subject/observer model
 * @author  Milo Bowles
 * @version 6/15/2022
 */
public class FractalGenerator implements FractalSubject{

    /** the radius of the center circle in the fractal */
    private static final int STARTING_RADIUS = 100;

    //------------------------------------------------------------------------
    //                      Instance Variables
    //------------------------------------------------------------------------

    /** a list of the subject's observers */
    private ArrayList<FractalObserver> observers;
    /** the number of times the fractal will recurse */
    private int recurseDepth;
    /** the ratio of a child to its parent */
    private int childRatio;
    /** the number of children a parent will have */
    private int childCount;
    /** the level of deviation of each bubble */
    private int bedlamLevel;
    /** the default color of every bubble */
    private Color color;
    /**
     *  if true each color will have random pastel colors,
     *      if false each color will have the default color
     */
    private boolean hasRandomPastels;


    //------------------------------------------------------------------------
    //                      Constructor
    //------------------------------------------------------------------------

    /**
     * Contructor for a fractal generator;
     *      sets all instance variables to 0 equivalents;
     */
    public FractalGenerator() {
        observers = new ArrayList<FractalObserver>();
        recurseDepth = 0;
        childRatio = 0;
        childCount = 0;
        bedlamLevel = 0;
        color = null;
        hasRandomPastels = false;
    }

    //------------------------------------------------------------------------
    //                      Instance Methods
    //------------------------------------------------------------------------

    /**
     * @inheritdoc
     */
    @Override
    public void setParams(int recurseDepth, int childRatio, int childCount, int bedlamLevel,
                          Color color, boolean hasRandomPastels) {
        this.recurseDepth = recurseDepth;
        this.childRatio = childRatio;
        this.childCount = childCount;
        this.bedlamLevel = bedlamLevel;
        this.color = color;
        this.hasRandomPastels = hasRandomPastels;
        notifyObservers();
    }

    /**
     * @inheritdoc
     */
    @Override
    public void attach(FractalObserver observer) {
        observers.add(observer);
    }

    /**
     * @inheritdoc
     */
    @Override
    public void detach(FractalObserver observer) {
        observers.remove(observer);
    }

    /**
     * @inheritdoc
     */
    @Override
    public void notifyObservers() {
        for (FractalObserver oneObserver : observers) {
            oneObserver.update();
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    public ArrayList<FractalElement> getData() {
        ArrayList<FractalElement> dataArrayList = new ArrayList<>();
        getData(dataArrayList, 0, 0, recurseDepth, STARTING_RADIUS);
        return dataArrayList;
    }

    /**
     * Private recursive method to getData;
     *      generates an element and recurses based of instance data
     *      adds each element to a list
     * @param dataArrayList     the list of fractal elements to be added to
     * @param centerX           the center x coordinate of the fractal element to be generated
     * @param centerY           the center y coordinate of the fractal element to be generated
     * @param recurseCounter    the number of recursions left for the fractal to do;
     *                              if 0 or less the element won't be generated
     * @param initialRadius     the radius of the element to be generated
     */
    private void getData(ArrayList<FractalElement> dataArrayList, double centerX, double centerY,
                         int recurseCounter, double initialRadius) {

        if (recurseCounter > 0) {
            //"places" a circle and arc at center coordinates
            dataArrayList.add(new Bubble(initialRadius, centerX, centerY, color, hasRandomPastels));
            dataArrayList.add(new Arc(initialRadius * .75, centerX, centerY));
            recurseCounter--;

            //recurses for each child around original circle
            double newRadius = initialRadius * ((double)childRatio / 100);
            for (int child = 0; child < childCount; child++) {
                double theta = (Math.PI / 2) + child * ((2 * Math.PI) / childCount);
                double bubbleX = Math.cos(theta) * (initialRadius + newRadius) + centerX;
                double bubbleY = Math.sin(theta) * (initialRadius + newRadius) + centerY;
                getData(dataArrayList, bubbleX, bubbleY, recurseCounter, newRadius);
            }
        }
    }

}
