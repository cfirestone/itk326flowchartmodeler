/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Logic;

import DataAccess.XMLDataIO;
import Shapes.Point;
import Shapes.Shape;

import java.io.File;
import java.util.LinkedList;

/**
 * User: Carl
 * Date: Mar 5, 2010
 * Time: 5:35:19 PM
 */
public abstract class DrawingBoard {
    protected LinkedList<Shape> listOfShapes;
    StateManager stateManager;
    protected float[] color;


    protected DrawingBoard() {
        listOfShapes = new LinkedList<Shape>();
        stateManager = new StateManager();
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
        updateStateManager();
        drawShapes();
    }

    final public void removeShape(Shape s) {
        listOfShapes.remove(s);
        updateStateManager();
        drawShapes();
    }

    final public void clearAllShapes() {
        listOfShapes.clear();
        updateStateManager();
    }

    abstract public void drawTempShape(Shape shape);

    abstract public Shape getShape(Point point);

    public void open(File f)
    {

    }

    public void open(String path)
    {
        XMLDataIO dataIO = new XMLDataIO(path);
        listOfShapes = dataIO.getData();        
        updateStateManager();
        drawShapes();
    }

    protected void updateStateManager() {
        stateManager.newState((LinkedList<Shape>) listOfShapes.clone());
    }

    public boolean goForward() {
        boolean success = stateManager.goForward();
        if (success) {
            listOfShapes = (LinkedList<Shape>) (stateManager.getCurrentState().getListOfShapes().clone());
        }
        return success;
    }

    public boolean goBackward() {
        boolean success = stateManager.goBackward();
        if (success) {
            listOfShapes = (LinkedList<Shape>) (stateManager.getCurrentState().getListOfShapes().clone());
        }
        return success;
    }

}
