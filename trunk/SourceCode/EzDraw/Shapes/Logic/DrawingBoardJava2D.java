package Logic;

import Shapes.CompositeShape;
import Shapes.Shape;
import Shapes.TextBox;

import java.awt.*;

/**
 * User: Carl
 * Date: Mar 5, 2010
 * Time: 5:41:09 PM
 */
public class DrawingBoardJava2D extends DrawingBoard {

    private Graphics graphicsObj;

    protected void drawShape(Shape s) {
        Graphics2D graphics2dObj = (Graphics2D) graphicsObj;
        if (s instanceof CompositeShape) {

        }

        if (s instanceof TextBox) {

        }
    }

}
