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

        Point start = new Point(m_PositionY, m_PositionX);
        Point end = new Point((m_PositionY + m_DimensionY),(m_PositionX + m_DimensionX));

        listOfPoints.add(start);
        listOfPoints.add(end);
    }
}
