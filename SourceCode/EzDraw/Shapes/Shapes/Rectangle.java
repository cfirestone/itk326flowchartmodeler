/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Shapes;

import java.util.LinkedList;

/**
 * Represents a geometric Rectangle
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */
public class Rectangle extends SimpleShape {
    protected String m_Type;

    public Rectangle(Point p1, Point p2) {
        super(p1, p2);
        m_Type = "Rectangle";
        m_AttachmentStrategy = new AttachSides();
    }

    public Rectangle clone() {
        Rectangle clone = new Rectangle(new Point(startPoint.getX(), startPoint.getY()), new Point(endPoint.getX(), endPoint.getY()));
        clone.setBorderColors(this.getBorderColors());
        clone.setFillColors(this.getFillColors());
        return clone;
    }

    /** {@inheritDoc} */
    public void calculatePoints() {
        if (listOfPoints == null)
            listOfPoints = new LinkedList<Point>();

        listOfPoints.clear();

        Point topRight = new Point(endPoint.getX(), startPoint.getY());
        Point bottomLeft = new Point(startPoint.getX(), endPoint.getY());

        listOfPoints.add(startPoint);
        listOfPoints.add(topRight);
        listOfPoints.add(endPoint);
        listOfPoints.add(bottomLeft);
    }

    /**
     * For Rectangle, a point is within the rectangle if
     * the point's x value is between startpoint and endpoint's x value
     * and the point's y value is between startpoint and endpoint's y value
     */
    public boolean containsPoint(Point p) {
        boolean bool = false;
        boolean xBounds = false;
        boolean yBounds = false;

        if ((p.getX() <= endPoint.getX() && p.getX() >= startPoint.getX()) ||
                (p.getX() <= startPoint.getX() && p.getX() >= endPoint.getX())) {
            xBounds = true;
        }
        if ((p.getY() <= endPoint.getY() && p.getY() >= startPoint.getY()) ||
                (p.getY() <= startPoint.getY() && p.getY() >= endPoint.getY())) {
            yBounds = true;
        }
        if (xBounds && yBounds) {
            bool = true;
        }
        return bool;
    }
}
