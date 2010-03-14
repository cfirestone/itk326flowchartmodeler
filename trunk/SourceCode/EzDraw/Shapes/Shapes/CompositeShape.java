package Shapes;
/**
 * Defines a shape of shapes
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */

import java.util.Collection;
import java.util.LinkedList;

public class CompositeShape extends Shape {
    private LinkedList<Shape> m_ChildShapes;

    /**
     * Default constructor
     */
    public CompositeShape(Point p1, Point p2)
    {
        super(p1, p2);
        m_ChildShapes = new LinkedList();
        m_AttachmentStrategy = new AttachSides();
    }

    /**
     * Adds all elements of a collection to the children
     *
     * @param c The collection of shapes to add
     */
    public void addShapes(Collection<Shape> c) {
        m_ChildShapes.addAll(c);
        setDimensions();
    }

    /**
     * Adds an element to the children
     *
     * @param s The shape to be added
     */
    public void addShape(Shape s) {
        m_ChildShapes.add(s);
        setDimensions();
    }

    /**
     * Calculates and sets the dimension base on its children
     */                                                                                                          S
    public void setDimensions() {
        double largex = -1, largey = -1;
        double smallx = -1, smally = -1;

        for (int i = 0; i < m_ChildShapes.size(); i++) {
            if (m_ChildShapes.get(i).getXPosition() < smallx || smallx < 0)
                smallx = m_ChildShapes.get(i).getXPosition();
            if (m_ChildShapes.get(i).getYPosition() < smally || smally < 0)
                smally = m_ChildShapes.get(i).getYPosition();
            if (m_ChildShapes.get(i).getXDimension() + m_ChildShapes.get(i).getXPosition() > largex || largex < 0)
                largex = m_ChildShapes.get(i).getXDimension() + m_ChildShapes.get(i).getXPosition();
            if (m_ChildShapes.get(i).getYDimension() + m_ChildShapes.get(i).getYPosition() > largey || largey < 0)
                largey = m_ChildShapes.get(i).getYDimension() + m_ChildShapes.get(i).getYPosition();
        }

        //offset by .5 to make a "boarder"
        setCoordinates(smallx - .5, smally - .5);
        setDimensions((largex - smallx) + 1.0, (largey - smally) + 1.0);

        calculatePoints();
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
      
        for(int i = 0; i < m_ChildShapes.size(); i++)
        {
            m_ChildShapes.get(i).calculatePoints();
        }
    }

    public LinkedList<Shape> getListOfShapes() {
        return m_ChildShapes;
    }
}
