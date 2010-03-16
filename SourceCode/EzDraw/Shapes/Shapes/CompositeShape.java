/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

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
     * Describes how to draw a composite shape
     *
     * @author Justin Blakley & Carl Firestone
     * @version 1.0.0.0
     */
    public CompositeShape(Point p1, Point p2) {
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

    /** Calculates and sets the dimension base on its children */
    public void setDimensions() {
        double largex = -1, largey = -1;
        double smallx = -1, smally = -1;

        for (int i = 0; i < m_ChildShapes.size(); i++) {

            double startX = m_ChildShapes.get(i).getStartPoint().getX();
            double endX = m_ChildShapes.get(i).getEndPoint().getX();
            double startY = m_ChildShapes.get(i).getStartPoint().getY();
            double endY = m_ChildShapes.get(i).getEndPoint().getY();

            if (startX < smallx || smallx < 0)
                smallx = startX;
            if (endX < smallx || smallx < 0)
                smallx = endX;
            if (startY < smally || smally < 0)
                smally = startY;
            if (endY < smally || smally < 0)
                smally = startY;
            if (endX > largex || largex < 0)
                largex = endX;
            if (startX > largex || largex < 0)
                largex = startX;
            if (endY > largey || largey < 0)
                largey = endY;
            if (startY > largey || largey < 0)
                largey = startY;
        }

        //offset by .5 to make a "boarder"
        startPoint = new Point((smally - .5), (smallx - .5));
        endPoint = new Point(largey + .5, largex + .5);

        calculatePoints();
    }

    /** {@inheritDoc} */
    public void calculatePoints() {
        if (listOfPoints == null)
            listOfPoints = new LinkedList<Point>();

        listOfPoints.clear();

        Point topRight = new Point(startPoint.getY(), endPoint.getX());
        Point bottomLeft = new Point(endPoint.getY(), startPoint.getX());

        listOfPoints.add(startPoint);
        listOfPoints.add(topRight);
        listOfPoints.add(endPoint);
        listOfPoints.add(bottomLeft);

        for (int i = 0; i < m_ChildShapes.size(); i++) {
            m_ChildShapes.get(i).calculatePoints();
        }
    }

    public boolean containsPoint(Point point) {
        //TODO
        return false;
    }

    public LinkedList<Shape> getListOfShapes() {
        return m_ChildShapes;
    }
}
