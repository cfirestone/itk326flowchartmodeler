/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Shapes;

/**
 * User: Carl
 * Date: Mar 7, 2010
 * Time: 11:14:49 AM
 */
public class Point {

    private double x;
    private double y;

    /**
     * The constructor for Point
     * @param x The X representation of the cartesian point
     * @param y The Y representation of the cartesian point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x val of this point
     * @return The x value
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x val of this point
     * @param x The x value to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y val of this point
     * @return the y value of this point
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y value of this point
     * @param y the y value to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Traditional geometric distance formula SQRT((x2-x1)^2 + (y2-y1)^2)
     * @param other the other point to measure distance
     * @return the value representing the distance between the points
     */
    public double getDistance(Point other) {
        return Math.sqrt(Math.pow((x - other.getX()), 2) + Math.pow((y - other.getY()), 2));
    }

    public String toString() {
        return "X = " + x + " Y = " + y;
    }
}
