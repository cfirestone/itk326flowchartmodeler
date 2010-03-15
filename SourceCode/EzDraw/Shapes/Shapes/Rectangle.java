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
        super(p1,p2);
        m_Type = "Rectangle";
        m_AttachmentStrategy = new AttachSides();
    }

    /**
     * {@inheritDoc}
     */
    public void calculatePoints() {
        if(listOfPoints == null)
            listOfPoints = new LinkedList<Point>();

        Point topRight = new Point(startPoint.getY(), endPoint.getX());
        Point bottomLeft = new Point(endPoint.getY(), startPoint.getX());

        listOfPoints.add(startPoint);
        listOfPoints.add(topRight);
        listOfPoints.add(endPoint);
        listOfPoints.add(bottomLeft);
    }
}
