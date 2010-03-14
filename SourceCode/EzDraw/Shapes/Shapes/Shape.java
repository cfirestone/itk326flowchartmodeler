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
    protected double m_PositionX;
    protected double m_PositionY;
    protected double m_DimensionX;
    protected double m_DimensionY;
    //Changed private to protected
    protected LinkedList<Point> listOfPoints;
    protected IAttachable m_AttachmentStrategy;

    /**
     * Base Constructor for all other shapes
     * @param - The starting point (upper left corner of bounding rectangle)
     * @param - The ending point (lower right corner of bounding rectangle)
     */
    public Shape(Point p1, Point p2){
        m_PositionX = p1.getX();
        m_PositionY = p1.getY();
        m_DimensionX = Math.abs(m_PositionX - p2.getX());
        m_DimensionY = Math.abs(m_PositionY - p2.getY());

        calculatePoints();
    }
    /**
     * Calculates the points needed to draw the shape
     */
    public abstract void calculatePoints();

    /**
     * Gets the dimension in the x direction
     *
     * @return The value of the shape's dimension in the x direction
     */
    protected double getXDimension() {
        return m_DimensionX;
    }

    /**
     * Gets the dimension in the y direction
     *
     * @return The value of the shape's dimension in the y direction
     */
    protected double getYDimension() {
        return m_DimensionY;
    }

    /**
     * Gets the position in the x axis
     *
     * @return The value of the x coordinate of the shape
     */
    protected double getXPosition() {
        return m_PositionX;
    }

    /**
     * Get the position in the y axis
     *
     * @return The value of the y coordiante of the shape
     */
    protected double getYPosition() {
        return m_PositionY;
    }

    /**
     * Calls the IAttachable to calculate snap points
     */
    public void drawAttachmentSections() {
        double[] sections =
                m_AttachmentStrategy.calculateAttachmentPoints(m_PositionX, m_PositionY, m_DimensionX, m_DimensionY);

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
     * Set the coordinates of the shape
     *
     * @param x The coordinate in the x direction
     * @param y The coordinate in the y direction
     */
    public void setCoordinates(double x, double y) {
        m_PositionX = x;
        m_PositionY = y;
    }

    /**
     * Set the Dimensions of the shape
     *
     * @param x The dimension in the x direction
     * @param y The dimension in the y direction
     */
    public void setDimensions(double x, double y) {
        m_DimensionX = x;
        m_DimensionY = y;
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
     * @return - The list of points returned
     */
    public LinkedList<Point> getListOfPoints() {
        return listOfPoints;
    }
}
