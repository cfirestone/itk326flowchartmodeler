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

        Point topLeft = new Point(m_PositionY, m_PositionX);
        Point topRight = new Point(m_PositionY,(m_PositionX + m_DimensionX));
        Point bottomRight = new Point((m_PositionY + m_DimensionY),(m_PositionX + m_DimensionX));
        Point bottomLeft = new Point((m_PositionY + m_DimensionY), m_PositionX);

        listOfPoints.add(topLeft);
        listOfPoints.add(topRight);
        listOfPoints.add(bottomRight);
        listOfPoints.add(bottomLeft);
    }
}
