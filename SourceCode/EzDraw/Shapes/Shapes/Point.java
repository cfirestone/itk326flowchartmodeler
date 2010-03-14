package Shapes;

/**
 * User: Carl
 * Date: Mar 7, 2010
 * Time: 11:14:49 AM
 */
public class Point {

    private double x;
    private double y;

    public Point(double y, double x) {
        this.setY(y);
        this.setX(x);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String toString(){
        return "X = "+ x + " Y = " + y;
    }
}
