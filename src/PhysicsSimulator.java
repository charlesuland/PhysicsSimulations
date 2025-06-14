import javax.swing.*;
import java.awt.*;

public class PhysicsSimulator extends JPanel {
    public static void main(String[] args) throws InterruptedException {
        createGUI();
    }

    public static void createGUI() throws InterruptedException {
        JFrame frame = new JFrame("Physics Sim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel physicsSim = new PhysicsSimulator();
        frame.add(physicsSim);

        frame.setLocation(500, 100);
        frame.pack();
        frame.setVisible(true);
    }

    public PhysicsSimulator() throws InterruptedException {
        super(null);
        setPreferredSize(new Dimension(800, 650));
        setBackground(Color.black);
        add(new MotionPanel());

    }
}
