import javax.swing.*;
import java.awt.*;


public class Frame extends JFrame {

    public Frame() {
        super("Piano");

        //Get Screen Size
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();


        // make sure frame is maximized state
        setExtendedState(Frame.MAXIMIZED_VERT);


        //Set default close operation on the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set Frames Dimensions
        //setSize(dim.width, dim.height);
        setSize(1400,800);

        //Set frame's visibility
        setVisible(true);

        // Processing panel for visual output.
        final Processing cimg = new Processing();
        /*important to call this whenever embedding a PApplet.
         It ensures that the animation thread is started and
         that other internal variables are properly set*/
        cimg.init();
        /*se auto to shmeio prosthete to component pou sxediase h PAppelt sto Frame toy JFrame*/
        this.add(cimg);

        Between.getInstance().addObserver(cimg);


    }

    public static void main(String[] args) {
       Frame wf = new Frame();
    }
}