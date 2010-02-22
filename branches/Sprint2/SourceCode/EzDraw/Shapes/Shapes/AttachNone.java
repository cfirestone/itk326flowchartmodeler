package Shapes;

/**
 * Discribes no attachability
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */
public class AttachNone implements IAttachable {
    /**
     * {@inheritDoc}
     */
    public double[] calculateAttachmentPoints(double x, double y, double dimX, double dimY) {
        return new double[0];
    }
}
