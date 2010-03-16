/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Shapes;

import java.util.LinkedList;

/**
 * Describes how to draw a line
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */
public class Line extends SimpleShape {
    public Line(Point p1, Point p2) {
        super(p1, p2);
        m_AttachmentStrategy = new AttachNone();
    }

    /** {@inheritDoc} */
    public void calculatePoints() {
        if (listOfPoints == null)
            listOfPoints = new LinkedList<Point>();

        listOfPoints.clear();

        listOfPoints.add(startPoint);
        listOfPoints.add(endPoint);
    }

    /**
     * For line, containsPoint compares the slope of the line between
     * P1 and P2 with the slope of the line between P1 and the supplied p.
     * if the slopes are the same, and p is within the bounds of p1 and p2,
     * then p is on the line P1-P2.
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

        double actualSlope = (startPoint.getY() - endPoint.getY()) / (startPoint.getX() - endPoint.getX());
        double proposedSlope = (startPoint.getY() - p.getY()) / (startPoint.getX() - p.getX());
        if (proposedSlope == actualSlope && yBounds && xBounds) {
            bool = true;
        }

        return bool;
    }
}
