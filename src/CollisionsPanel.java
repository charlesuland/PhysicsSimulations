import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CollisionsPanel extends JPanel {
    int rectangleWidth = 500;
    int rectangleHeight = 600;
    static final int rowCap = 500;

    ArrayList<Ball> balls;

    public CollisionsPanel() {
        super(null);
        setBounds(100, 25, 600, 600);
        setBackground(Color.GRAY);

        balls = new ArrayList<>();
        int renderingTimerDelay = 16; // roughly 60 Hz
        ActionListener renderingPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 4; i++) {
                    updateBalls(renderingTimerDelay / 4.0 / 100.0);
                }
                repaint();
            }
        };
        Timer renderingTimer = new Timer(renderingTimerDelay, renderingPerformer);
        renderingTimer.start();

        int addBallTimerDelay = 60; // 2/s
        ActionListener addBallPerformer = new ActionListener() {
            int rowCounter = 0;
            public void actionPerformed(ActionEvent e) {

                    Color rgb = new Color(Color.HSBtoRGB( rowCounter/360.0f *30, 1, 1));
                    for(int j = 0; j < 7; j++) {
                        Ball b = new Ball(50 + Ball.radius, Ball.radius + Ball.radius * 2 * j, 50 + Ball.radius * 3 / 4, Ball.radius + Ball.radius * 2 * j, rgb);
                        balls.add(b);
                    }
                    rowCounter++;
                if (rowCounter > rowCap) {
                    Timer timer = (Timer) e.getSource();
                    timer.stop();
                }
            }
        };
        Timer addBallTimer = new Timer(addBallTimerDelay, addBallPerformer);
        addBallTimer.start();


    }

    public void updateBalls(double dt) {
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
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

            } else if (b.y - b.radius < 0) {
                double velocity = b.y - b.oldY;
                b.y = 0 + b.radius;
                b.oldY = b.y + velocity;

            }
            for(int j = 0; j < balls.size(); j++) {
                Ball c = balls.get(j);
                if (c == b) continue;
                double dist = Math.sqrt((b.x - c.x) * (b.x - c.x) + (b.y - c.y) * (b.y - c.y));
                if (dist < b.radius + c.radius) {
                    //move them to where the dont collide
                    double overlap = b.radius + c.radius - dist;

                    //more physically correct solution
                    /*double magVelB = Math.sqrt((b.x - b.oldX) * (b.x - b.oldX) + (b.y-b.oldY) * (b.y-b.oldY)) * .8;
                    double dirVelBX = (b.x - b.oldX) / magVelB;
                    double dirVelBY = (b.y - b.oldY) / magVelB;
                    double magVelC = Math.sqrt((c.x - c.oldX) * (c.x - c.oldX) + (c.y-c.oldY) * (c.y-c.oldY)) * .8;
                    double dirVelCX = (c.x - c.oldX) / magVelC;
                    double dirVelCY = (c.y - c.oldY) / magVelC;*/


                    //better looking solution
                    double collisionalBtoCX = c.x - b.x;
                    double collisionalBtoCY = c.y - b.y;
                    double magCollisional = Math.sqrt(collisionalBtoCY * collisionalBtoCY + collisionalBtoCX * collisionalBtoCX);
                    double normX = collisionalBtoCX / magCollisional;
                    double normY = collisionalBtoCY / magCollisional;

                    double collisionFactor = 1;
                    //SEPARATE BASED ON COLLISIONAL AXIS
                    b.x -= overlap / 2 * normX * collisionFactor;
                    b.y -= overlap / 2 * normY * collisionFactor;
                    c.x += overlap / 2 * normX * collisionFactor;
                    c.y += overlap / 2 * normY * collisionFactor;
                    //adjust there old positions such that they swap their velocities

                    /*b.oldX = b.x - magVelC * dirVelCX;
                    b.oldY = b.y - magVelC * dirVelCY;
                    c.oldX = c.x - magVelB * dirVelBX;
                    c.oldY = c.y - magVelB * dirVelBY;*/
                }
            }

        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        g.fillRect(50, 0, rectangleWidth, rectangleHeight);

        for (Ball b : balls) {
            g.setColor(b.rgb);
            g.fillOval((int) b.x - b.radius, (int) b.y - b.radius, b.radius * 2, b.radius * 2);
        }
    }

}



