import java.awt.Color;
import java.util.ArrayList;

/**
 * Contains methods that must be present in a FractalSubject
 * @author  Milo Bowles
 * @version 6/15/2022
 */
public interface FractalSubject {

    /**
     * adds the given observer to the subjects list of observers
     * @param observer  the observer to be added
     */
    public void attach(FractalObserver observer);

    /**
     * removes the given observer from subjects list of observers
     * @param observer  the observer to be removed
     */
    public void detach(FractalObserver observer);

    /**
     * notifies (or updates) all observers of subject that data is ready
     */
    public void notifyObservers();

    /**
     * generates and returns a list of fractal elements, recursed based on subject data
     * @return  a list of fractal elements
     */
    public ArrayList<FractalElement> getData();

    /**
     * Sets the instance variables (data) of the fractal subject
     * @param recurseDepth      the number of recursions the fractal will do
     * @param childRatio        the ratio of a child to its parent
     * @param childCount        the number of children a parent element has
     * @param bedlamLevel       the level of deviation a bubble will have
     * @param color             the color a bubble will be set to by default,
     *                              if color is not randomized
     * @param hasRandomPastels  if true bubble will have random pastel colors,
     *                              if false bubble will have the default color chosen
     */
    public void setParams(int recurseDepth, int childRatio, int childCount, int bedlamLevel,
                          Color color, boolean hasRandomPastels);

}
