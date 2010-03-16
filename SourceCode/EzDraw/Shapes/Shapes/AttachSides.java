/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Shapes;

/**
 * Determines attach points for sides
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */
public class AttachSides implements IAttachable {
    /** {@inheritDoc} */
    public double[] calculateAttachmentPoints(double x, double y, double dimX, double dimY) {
        double[] positions = new double[16];

        positions[0] = ((dimX / 2) + x) - 1;
        positions[1] = (y - 1);
        positions[2] = 2;
        positions[3] = 2;

        positions[4] = (x + dimX) - 1;
        positions[5] = ((dimY / 2) + y) - 1;
        positions[6] = 2;
        positions[7] = 2;

        positions[8] = ((dimX / 2) + x) - 1;
        positions[9] = (y + dimY) - 1;
        positions[10] = 2;
        positions[11] = 2;

        positions[12] = (x - 1);
        positions[13] = ((dimY / 2) + y) - 1;
        positions[14] = 2;
        positions[15] = 2;

        return positions;
    }
}
