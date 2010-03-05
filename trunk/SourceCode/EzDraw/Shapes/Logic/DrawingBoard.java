package Logic;

import Shapes.Shape;

import java.util.ArrayList;

/**
 * User: Carl
 * Date: Mar 5, 2010
 * Time: 5:35:19 PM
 */
public abstract class DrawingBoard {
    protected ArrayList<Shape> listOfShapes;
    protected float[] color;


    final public void drawShapes() {
        for (int i = 0; i < listOfShapes.size(); i++) {
            drawShape(listOfShapes.get(i));
        }
    }

    abstract protected void drawShape(Shape s);

}
