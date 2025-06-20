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
                if (ballsCollide(b, c)) {
                    updateBasedOnCollision(b, c);
                    //move them to where the dont collide



                }
            }

        }

    }

    public boolean ballsCollide(Ball ball1, Ball ball2) {
        return Math.sqrt((ball1.x-ball2.x)*(ball1.x-ball2.x)+(ball1.y-ball2.y)*(ball1.y-ball2.y)) < ball1.radius + ball2.radius;
    }
    public void updateBasedOnCollision(Ball ball1, Ball ball2) {
        double dist = Math.sqrt((ball1.x-ball2.x)*(ball1.x-ball2.x)+(ball1.y-ball2.y)*(ball1.y-ball2.y));
        double overlap = ball1.radius + ball2.radius - dist;


        double collisionalBtoCX = ball2.x - ball1.x;
        double collisionalBtoCY = ball2.y - ball1.y;
        double magCollisional = Math.sqrt(collisionalBtoCY * collisionalBtoCY + collisionalBtoCX * collisionalBtoCX);
        double normX = collisionalBtoCX / magCollisional;
        double normY = collisionalBtoCY / magCollisional;

        double collisionFactor = 1;
        //SEPARATE BASED ON COLLISIONAL AXIS
        ball1.x -= overlap / 2 * normX * collisionFactor;
        ball1.y -= overlap / 2 * normY * collisionFactor;
        ball2.x += overlap / 2 * normX * collisionFactor;
        ball2.y += overlap / 2 * normY * collisionFactor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        g.fillRect(50, 0, rectangleWidth, rectangleHeight);

        for (Ball b : balls) {
            g.setColor(b.rgb);
            g.fillOval((int) b.x - Ball.radius, (int) b.y - Ball.radius, Ball.radius * 2, Ball.radius * 2);
        }
    }
}
