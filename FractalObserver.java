/**
 * Contains the methods a fractal observer must contain
 * @author  Milo Bowles
 * @version 6/15/2022
 */
public interface FractalObserver {
    /**
     * causes the observer to get and use its subject's data, using a pull model;
     */
    public void update();
}
