package Logic;

import Shapes.Shape;

import java.util.LinkedList;

/**
 * User: Carl
 * Date: Mar 5, 2010
 * Time: 5:35:19 PM
 */
public abstract class DrawingBoard {
    protected LinkedList<Shape> listOfShapes;
    protected float[] color;


    protected DrawingBoard() {
        listOfShapes = new LinkedList<Shape>();
    }

    public void drawShapes() {
        if (listOfShapes != null) {
            for (int i = 0; i < listOfShapes.size(); i++) {
                drawShape(listOfShapes.get(i));
            }
        }
    }

    abstract protected void drawShape(Shape s);

    final public void addShape(Shape s) {

        listOfShapes.add(s);
        drawShapes();
    }

    final public void removeShape(Shape s) {
        listOfShapes.remove(s);
        drawShapes();
    }

    final public void clearAllShapes() {
        listOfShapes.clear();
    }

    abstract public void drawTempShape(Shape shape);

}
