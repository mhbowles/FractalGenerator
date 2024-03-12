/**
 * Contains code for the main class to set off the whole thing
 * @author  Milo Bowles
 * @version 6/15/2022
 */
public class Main {

    /**
     * Setups up subject/observer model and initial handshakes between
     * @param args      NO IDEA WHAT TO PUT HERE
     */
    public static void main(String[] args) {
        FractalGenerator fg = new FractalGenerator();
        FractalDrawing fd = new FractalDrawing(fg);
        FractalGui gui = new FractalGui(fg);
    }

}
