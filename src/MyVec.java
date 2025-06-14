public class MyVec {
    private double x, y;

    public MyVec(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double mag() {
        return Math.sqrt(x*x + y*y);
    }

    public void add(MyVec other) {
        this.x += other.x;
        this.y += other.y;
    }

    public void subtract(MyVec other) {
        this.x -= other.x;
        this.y -= other.y;
    }

    public static MyVec add(MyVec vec1, MyVec vec2) {
        return new MyVec(vec1.x + vec2.x, vec1.y + vec2.y);
    }

    public static MyVec subtract(MyVec vec1, MyVec vec2) {
        return new MyVec(vec1.x - vec2.x, vec1.y - vec2.y);
    }
    public static double dotProduct(MyVec vec1, MyVec vec2) {
        return vec1.x * vec2.x + vec1.y * vec2.y;
    }

    public MyVec unitVec() {
        return new MyVec(x/mag(), y/mag());
    }

    public MyVec negate() {
        return new MyVec(-x, -y);
    }

//    public void scale(double scalar) {
//        this.x *= scalar;
//        this.y *= scalar;
//    }
    public MyVec scale(double scalar) {
        return new MyVec(x*scalar, y*scalar);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
