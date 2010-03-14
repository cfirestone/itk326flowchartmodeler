package Shapes;

/**
 * Represents a geometric Circle
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */

public class Circle extends SimpleShape {

    public Circle(Point p1, Point p2) {
        super(p1,p2);
        m_AttachmentStrategy = new AttachSides();
    }

    /**
     * {@inheritDoc}
     */
    public void calculatePoints() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
