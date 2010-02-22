package Shapes;

/**
 * Represents a geometric Shapes.Circle
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */

public class Circle extends SimpleShape {

    public Circle() {
        m_AttachmentStrategy = new AttachSides();
    }

    /**
     * {@inheritDoc}
     */
    public void draw() {
        String eol = System.getProperty("line.separator");
        System.out.println("I'm a circle being drawn at X: "
                + getXPosition() + " Y: " + getYPosition()
                + " with Dimensions X: " + getXDimension() + " Y: "
                + getYDimension());
    }
}
