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

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
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

    public double getDistance(Point other) {
        return Math.sqrt(Math.pow((x - other.getX()), 2) + Math.pow((y - other.getY()), 2));
    }

    public String toString() {
        return "X = " + x + " Y = " + y;
    }
}
