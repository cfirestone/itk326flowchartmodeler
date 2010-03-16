/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Shapes;

import java.util.LinkedList;

/**
 * Represents a geometric Circle
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */

public class Circle extends SimpleShape {
    private final double THETA_INCR = .1;

    public Circle(Point p1, Point p2) {
        super(p1, p2);
        m_AttachmentStrategy = new AttachSides();
    }

    /** {@inheritDoc} */
    public void calculatePoints() {
        if (listOfPoints == null)
            listOfPoints = new LinkedList<Point>();

        listOfPoints.clear();

        double radius = startPoint.getDistance(endPoint);

        for (double theta = 0; theta < 2 * Math.PI; theta += THETA_INCR) {
            double newX = startPoint.getX() + (radius * Math.cos(theta));
            double newY = startPoint.getY() + (radius * Math.sin(theta));
            listOfPoints.add(new Point(newX, newY));
        }

        //This is so the dimensions of the actual shape are correct
        /*double newStartX = startPoint.getX() - radius;
        double newStartY = startPoint.getY() - radius;
        double newEndX = startPoint.getX() + radius;
        double newEndY = startPoint.getY() + radius;

        startPoint.setX(newStartX);
        startPoint.setY(newStartY);
        endPoint.setX(newEndX);
        endPoint.setY(newEndY);
        */
    }

    /**
     * For circle, a point is within the circle if the distance
     * from startpoint to p is less than startpoint to endpoint
     */
    public boolean containsPoint(Point p) {
        boolean bool = false;
        if (startPoint.getDistance(p) < startPoint.getDistance(endPoint)) {
            bool = true;
        }
        return bool;
    }


}
