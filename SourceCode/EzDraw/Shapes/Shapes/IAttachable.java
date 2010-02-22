package Shapes;

/**
 * An interface for encapsulating all strategies for
 * calculating snap points of shapes
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */

public interface IAttachable {
    /**
     * Calculates the snap points of a shape
     *
     * @param x    The x-Coordinate
     * @param y    The y-Coordinate
     * @param dimX The x-based dimension
     * @param dimY The y-based dimension
     * @return The base 4 indexed array of data used to create the snap points
     */
    double[] calculateAttachmentPoints(double x, double y, double dimX, double dimY);
}
