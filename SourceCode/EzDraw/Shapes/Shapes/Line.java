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
        super(p1,p2);
        m_AttachmentStrategy = new AttachNone();
    }

    /**
     * {@inheritDoc}
     */
    public void calculatePoints() {
        if(listOfPoints == null)
            listOfPoints = new LinkedList<Point>();

        listOfPoints.add(startPoint);
        listOfPoints.add(endPoint);
    }
}
