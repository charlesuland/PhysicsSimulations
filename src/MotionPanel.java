import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MotionPanel extends JPanel {
    int rectangleWidth = 500;
    int rectangleHeight = 600;

    ArrayList<Ball> balls;

    public MotionPanel() throws InterruptedException {
        super(null);
        setBounds(100, 25, 600, 600);
        setBackground(Color.GRAY);


        int renderingTimerDelay = 16; // roughly 60 Hz
        ActionListener renderingPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < 4; i++) {
                    updateBalls(renderingTimerDelay /4.0 / 100.0);
                }
                repaint();
            }
        };
        Timer renderingTimer = new Timer(renderingTimerDelay, renderingPerformer);
        renderingTimer.start();
/*        int addBallTimerDelay = 500; // 2/s
        ActionListener addBallPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBall();
            }
        };
        Timer addBallTimer = new Timer(addBallTimerDelay, addBallPerformer);*/

        balls = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            if(i % 2 == 1) {
                balls.add(new Ball(50 + 20 * 2* (i) + 20, 100));
            } else{
                balls.add(new Ball(50 + 20 * 2* (i) + 20, 100));
            }
            balls.get(i).setColor(new Color(Color.HSBtoRGB( i/360.0f *30, 1, 1)));
            Thread.sleep(200);
        }



    }

    public void updateBalls(double dt) {
        for(Ball b : balls) {
            b.update(dt);

            if (b.x + b.radius > 50 + rectangleWidth) {
                double velocity = b.x - b.oldX;
                b.x = 50 + rectangleWidth - b.radius;
                b.oldX = b.x + velocity;

            } else if (b.x - b.radius < 50) {
                double velocity = b.x - b.oldX;
                b.x = 50 + b.radius;
                b.oldX = b.x + velocity;

            } else if (b.y + b.radius > rectangleHeight) {
                double velocity = b.y - b.oldY;
                b.y = rectangleHeight - b.radius;
                b.oldY = b.y + velocity;

            } else if(b.y - b.radius < 0) {
                double velocity = b.y - b.oldY;
                b.y = 0 + b.radius;
                b.oldY = b.y + velocity;

            }

        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        g.fillRect(50, 0, rectangleWidth, rectangleHeight);

        for(Ball b : balls) {
            g.setColor(b.rgb);
            g.fillOval((int) b.x - b.radius, (int) b.y - b.radius, b.radius * 2, b.radius*2);
        }
    }

}

class Ball{

    double x;
    double y;
    double oldX;
    double oldY;
    double accelerationX;
    double accelerationY = 9.8;
    int radius = 20;
    Color rgb;




    public Ball(int x, int y) {
        this.x = oldX = x;
        this.y = oldY = y;

    }
    public void setColor(Color c) {
        rgb = c;
    }

    public void update(double dt) {
        double velocityX = x - oldX;
        double velocityY = y - oldY;

        oldX = x;
        oldY = y;

        x = x + velocityX + accelerationX * dt * dt;
        y = y + velocityY + accelerationY * dt * dt;

    }


}