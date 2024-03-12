import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contains code for a functional FractalGui JFrame,
 *      allowing a user interact with and generate a fractal
 * @author  Milo Bowles
 * @version 6/15/2022
 */
public class FractalGui extends JFrame implements ActionListener, ChangeListener{

    /** the title of the JFrame */
    public static final String title = "Bubbles and Bedlam Fractal Settings";
    /** the default color selected for fractal */
    public static final Color DEFAULT_COLOR = Color.RED;

    /** the toolKit of the JFrame */
    private Toolkit toolkit;
    /** the subject the gui/JFrame will send its data to */
    private FractalSubject subject;

    //buttons and sliders whose data needs to be available globally,
    //  e.g. the parameters needing to be send to subject
    /** slider to select recursion depth */
    private JSlider     recurseDepthSlider;
    /** slider to select child ratio */
    private JSlider     ratioSlider;
    /** slider to select child count */
    private JSlider     childCountSlider;
    /** slider to select bedlam level */
    private JSlider     bedlamSlider;
    /** label to display (and contain) a selected color */
    private JLabel      colorDisplay;
    /** checkbox to select random pastel colors */
    private JCheckBox   randPast;

    /**
     * Constructor for the gui/JFrame
     * @param subject   the subject the gui/JFrame will send its data to
     */
    public FractalGui(FractalSubject subject) {

        this.subject = subject;

        setTitle(title);
        setResizable(false);
        setSize(400, 600);
        toolkit = getToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() - 50,
                    (screenSize.height - getHeight()) / 2);

        //------------------------------------------------------------------------------
        //              sliders, buttons, labels, etc.
        //------------------------------------------------------------------------------

        JPanel contentPane = new JPanel();
        getContentPane().add(contentPane);
        contentPane.setLayout(null);

        //creating sliders and their labels

        recurseDepthSlider = createRecurseSlider();
        ratioSlider = createRatioSlider();
        childCountSlider = createChildCountSlider();
        bedlamSlider = createBedlamSlider();

        contentPane.add(recurseDepthSlider);
        contentPane.add(createSliderLabel("Recursion Depth", 70, 30));
        contentPane.add(ratioSlider);
        contentPane.add(createSliderLabel("Child to Parent Ratio", 70, 130));
        contentPane.add(childCountSlider);
        contentPane.add(createSliderLabel("Child Count", 70, 230));
        contentPane.add(bedlamSlider);
        contentPane.add(createSliderLabel("Bedlam Level", 70, 330));

        //creating buttons (and labels for some of them)
        colorDisplay = new JLabel();
        colorDisplay.setBackground(DEFAULT_COLOR);
        colorDisplay.setOpaque(true);
        colorDisplay.setBounds(200, 420, 100, 20);

        JButton fractalColor = new JButton("Fractal color...");
        fractalColor.setBounds(70, 420, 130, 30);

        randPast = new JCheckBox();
        randPast.setBounds(90, 470, 20, 20);

        JLabel randPastLabel = new JLabel("Random Pastels");
        randPastLabel.setBounds(115, 470, 130, 20);

        contentPane.add(fractalColor);
        contentPane.add(colorDisplay);
        contentPane.add(randPast);
        contentPane.add(randPastLabel);

        //adding the listeners to each button
        recurseDepthSlider.addChangeListener(this);
        ratioSlider.addChangeListener(this);
        childCountSlider.addChangeListener(this);
        bedlamSlider.addChangeListener(this);
        fractalColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color colorChosen = JColorChooser.showDialog(null, "Pick a Color...", Color.RED);
                colorDisplay.setBackground(colorChosen);
                randPast.setSelected(false);
                subject.setParams(recurseDepthSlider.getValue(), ratioSlider.getValue(), childCountSlider.getValue(),
                        bedlamSlider.getValue(), colorChosen, randPast.isSelected());
            }
        });
        randPast.addActionListener(this);

        subject.setParams(recurseDepthSlider.getValue(), ratioSlider.getValue(), childCountSlider.getValue(),
                        bedlamSlider.getValue(), colorDisplay.getBackground(), randPast.isSelected());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * The action performed when a button that affects the gui is pressed (or box checked)
     * @param e the event triggering this action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        subject.setParams(recurseDepthSlider.getValue(), ratioSlider.getValue(), childCountSlider.getValue(),
                bedlamSlider.getValue(), colorDisplay.getBackground(), randPast.isSelected());
    }

    /**
     * The action performed when a slider that affects the gui is changed
     * @param e the even triggering this action
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        subject.setParams(recurseDepthSlider.getValue(), ratioSlider.getValue(), childCountSlider.getValue(),
                bedlamSlider.getValue(), colorDisplay.getBackground(), randPast.isSelected());
    }

    /**
     * Creates and returns a label for a JSlider, with title and bounds set
     * @param title     the title of the label
     * @param xCoord    the x bounds of the label
     * @param yCoord    the y bounds of the label
     * @return  a label for a JSlider, with title and bounds set
     */
    private JLabel createSliderLabel(String title, int xCoord, int yCoord) {
        JLabel label = new JLabel(title);
        label.setBounds(xCoord, yCoord, 130, 20);
        return label;
    }

    /**
     * Creates and returns the recursion depth slider for the gui
     * @return  the recursion depth slider for the gui
     */
    private JSlider createRecurseSlider() {
        JSlider recurseDepthSlider = new JSlider(2, 8, 2);
        recurseDepthSlider.setBounds(50, 50, 300, 50);
        recurseDepthSlider.setMajorTickSpacing(1);
        recurseDepthSlider.setPaintTicks(true);
        recurseDepthSlider.setPaintLabels(true);
        recurseDepthSlider.setSnapToTicks(true);
        return recurseDepthSlider;
    }

    /**
     * Creates and returns the child ratio slider for the gui
     * @return  the child ratio slider for the gui
     */
    private JSlider createRatioSlider() {
        JSlider ratioSlider = new JSlider(20, 70, 20);
        ratioSlider.setBounds(50, 150, 300, 50);
        ratioSlider.setMajorTickSpacing(10);
        ratioSlider.setMinorTickSpacing(5);
        ratioSlider.setPaintTicks(true);
        ratioSlider.setPaintLabels(true);
        ratioSlider.setSnapToTicks(true);
        return ratioSlider;
    }

    /**
     * Creates and returns the child count slider for the gui
     * @return  the child count slider for the gui
     */
    private JSlider createChildCountSlider() {
        JSlider childCountSlider = new JSlider(1, 11, 1);
        childCountSlider.setBounds(50, 250, 300, 50);
        childCountSlider.setMajorTickSpacing(2);
        childCountSlider.setMinorTickSpacing(1);
        childCountSlider.setPaintTicks(true);
        childCountSlider.setPaintLabels(true);
        childCountSlider.setSnapToTicks(true);
        return childCountSlider;
    }

    /**
     * Creates and returns the bedlam level slider for the gui
     * @return  the bedlam level slider for the gui
     */
    private JSlider createBedlamSlider() {
        JSlider bedlamSlider = new JSlider(0, 4, 0);
        bedlamSlider.setBounds(50, 350, 300, 50);
        bedlamSlider.setMajorTickSpacing(1);
        bedlamSlider.setPaintTicks(true);
        bedlamSlider.setPaintLabels(true);
        bedlamSlider.setSnapToTicks(true);
        return bedlamSlider;
    }
}
