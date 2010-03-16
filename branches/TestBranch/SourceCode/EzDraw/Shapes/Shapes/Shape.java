package Shapes;
/**
 * Contains all top level information on
 * how to draw a shape
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */

import java.util.LinkedList;

public abstract class Shape {
    protected Point startPoint, endPoint;
    protected LinkedList<Point> listOfPoints;
    protected IAttachable m_AttachmentStrategy;

    /**
     * Base Constructor for all other shapes
     *
     * @param p1 The starting point (upper left corner of bounding rectangle)
     * @param p2 The ending point (lower right corner of bounding rectangle)
     */
    public Shape(Point p1, Point p2) {
        startPoint = p1;
        endPoint = p2;

        calculatePoints();
    }

    /**
     * Calculates the points needed to draw the shape
     */
    public abstract void calculatePoints();

    /**
     * Returns the start point
     *
     * @return The start Point
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /**
     * Returns the end point
     */
    public Point getEndPoint() {
        return endPoint;
    }

    /**
     * Sets the starting point of the shape
     *
     * @param p The point to set the start point
     */
    public void setStartPoint(Point p) {
        startPoint = p;
    }

    /**
     * Sets the end point
     *
     * @param p The point to set the end point
     */
    public void setEndPoint(Point p) {
        endPoint = p;
    }

    /**
     * Calls the IAttachable to calculate snap points
     */
    public void drawAttachmentSections() {
        double[] sections =
                m_AttachmentStrategy.calculateAttachmentPoints(startPoint.getX(), startPoint.getY(),
                        Math.abs((endPoint.getX() - startPoint.getX())),
                        Math.abs((endPoint.getY() - startPoint.getY())));

        /*
        * use graphics package to render snap points
        */
        String eol = System.getProperty("line.separator");
        System.out.print(eol + "On hover, here are my snap points");
        for (int i = 0; i < (sections.length); i += 4) {
            System.out.print(eol);
            System.out.println("Rectangle drawn at X: " + sections[i]
                    + " Y: " + sections[i + 1] + eol + "With Dimensions X: "
                    + sections[i + 2] + " Y: " + sections[i + 3]);
        }
        System.out.println("End Snap Points" + eol);
    }

    /**
     * Set the attachment strategy (IAttachable) for the shape
     *
     * @param a The attachment strategy to be used
     */
    protected void setAttachmentStrategy(IAttachable a) {
        m_AttachmentStrategy = a;
    }

    /**
     * retSurns the list of points for drawing the shape
     *
     * @return - The list of points returned
     */
    public LinkedList<Point> getListOfPoints() {
        return listOfPoints;
    }

    abstract public boolean containsPoint(Point p);

}
