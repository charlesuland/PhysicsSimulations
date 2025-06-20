import java.awt.*;

public class Ball {

    double x;
    double y;
    double oldX;
    double oldY;
    double accelerationX;
    double accelerationY = 20;
    static int radius = 5;
    Color rgb;


    public Ball(int x, int y) {
        this.x = oldX = x;
        this.y = oldY = y;

    }

    public Ball(int x, int y, int oldX, int oldY, Color c) {
        this.x = x;
        this.y = y;
        this.oldX = oldX;
        this.oldY = oldY;
        this.rgb = c;
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