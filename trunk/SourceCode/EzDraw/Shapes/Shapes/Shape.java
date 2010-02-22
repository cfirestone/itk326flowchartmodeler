package Shapes; /**
 * Contains all top level informaiton on
 * how to draw a shape
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */

import java.awt.*;

public abstract class Shape {
    private double m_PositionX;
    private double m_PositionY;
    private double m_DimensionX;
    private double m_DimensionY;
    protected IAttachable m_AttachmentStrategy;
    private Color m_ColorContext;

    /**
     * A stub for all children to implement on a drawing strategy
     */
    public abstract void draw();

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
     * Sets the current color context
     *
     * @param c The Color to be set
     */
    public void setColor(Color c) {
        m_ColorContext = c;
    }

    /**
     * Set the coordinates of the shape
     *
     * @param x The coordinate in the x direction
     * @param y The coordiante in the y direction
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
     * Set the attachment strategy (IAttachable) for the shape
     *
     * @param a The attachment strategy to be used
     */
    protected void setAttachmentStrategy(IAttachable a) {
        m_AttachmentStrategy = a;
    }
}
