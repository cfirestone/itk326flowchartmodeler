package Shapes;

/**
 * Describes how to draw a line
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */
public class Line extends SimpleShape {
    public Line() {
        m_AttachmentStrategy = new AttachNone();
    }

    /**
     * {@inheritDoc}
     */
    public void draw() {
        String eol = System.getProperty("line.separator");
        System.out.println("I'm a line being drawn from X: "
                + getXPosition() + "Y: " + getYPosition()
                + eol + "to X: " + (getXDimension() + getXPosition()) + " Y: "
                + (getYDimension() + getYPosition()));
    }
}
