package Shapes;

/**
 * Represents a geometric Shapes.Rectangle
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */
public class Rectangle extends SimpleShape {
    protected String m_Type;

    public Rectangle() {
        m_Type = "Shapes.Rectangle";
        m_AttachmentStrategy = new AttachSides();
    }

    /**
     * {@inheritDoc}
     */
    public void draw() {
        String eol = System.getProperty("line.separator");
        System.out.println("I'm a " + m_Type + " being drawn at X: "
                + getXPosition() + " Y: " + getYPosition()
                + " with Dimensions X: " + getXDimension() + " Y: "
                + getYDimension());
    }
}
