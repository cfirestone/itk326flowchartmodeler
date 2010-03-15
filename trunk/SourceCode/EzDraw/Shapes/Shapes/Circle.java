package Shapes;

import java.util.LinkedList;

/**
 * Represents a geometric Circle
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */

public class Circle extends SimpleShape {
    private final double THETA_INCR = .3;

    public Circle(Point p1, Point p2) {
        super(p1, p2);
        m_AttachmentStrategy = new AttachSides();
    }

    /**
     * {@inheritDoc}
     */
    public void calculatePoints() {
        if (listOfPoints == null) {
            listOfPoints = new LinkedList<Point>();
        }

        double radius = startPoint.getDistance(endPoint);
        listOfPoints.add(endPoint);
        for (double theta = 0; theta < 2 * Math.PI; theta += THETA_INCR) {
            double newX = startPoint.getX() + (radius * Math.cos(theta));
            double newY = startPoint.getY() + (radius * Math.sin(theta));
            listOfPoints.add(new Point(newX, newY));
        }
        listOfPoints.add(endPoint);

    }


}
