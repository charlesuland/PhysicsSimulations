import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CollisionsOptimizationPanel extends JPanel {

    int rectangleWidth = 500;
    int rectangleHeight = 600;
    static final int rowCap = 500;

    ArrayList<Ball> balls;

    public CollisionsOptimizationPanel() {
        super(null);
        setBounds(100, 25, 600, 600);
        setBackground(Color.GRAY);


    }
}
