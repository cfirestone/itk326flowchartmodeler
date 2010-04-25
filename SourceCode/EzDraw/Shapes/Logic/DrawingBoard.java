/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Logic;

import DataAccess.XMLDataIO;
import Shapes.Point;
import Shapes.Shape;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * User: Carl
 * Date: Mar 5, 2010
 * Time: 5:35:19 PM
 */
public abstract class DrawingBoard {
    protected LinkedList<Shape> listOfShapes;
    protected StateManager stateManager;
    protected boolean isSaved;
    protected float[] color;


    protected DrawingBoard() {
        listOfShapes = new LinkedList<Shape>();
        stateManager = new StateManager();

        isSaved = false;
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

    public void open(File f) throws Exception {

    }

    public void open(String path) throws Exception {
        XMLDataIO dataIO = new XMLDataIO(path);
        listOfShapes = dataIO.getData();
        updateStateManager();
        drawShapes();
    }

    public void save(File f) throws Exception{
        isSaved = true;
    }

    public void save(String path) throws Exception {
        XMLDataIO dataIO = new XMLDataIO(path);
        if(dataIO.saveData(listOfShapes))
        {
            
        }

        isSaved = true;
    }

    protected void updateStateManager() {
        stateManager.newState(deepCopy(listOfShapes));
        isSaved = false;
    }

    public boolean goForward() {
        boolean success = stateManager.goForward();
        if (success) {
            listOfShapes = deepCopy(stateManager.getCurrentState().getListOfShapes());
            isSaved = false;
        }
        return success;
    }

    public boolean goBackward() {
        boolean success = stateManager.goBackward();
        if (success) {
            listOfShapes = deepCopy(stateManager.getCurrentState().getListOfShapes());
            isSaved = false;
        }
        return success;
    }

    private LinkedList<Shape> deepCopy(LinkedList<Shape> source) {
        LinkedList<Shape> target = new LinkedList<Shape>();
        Iterator<Shape> sourceItr = source.iterator();
        while (sourceItr.hasNext()) {
            target.add(sourceItr.next().clone());
        }
        return target;
    }

    public boolean isSaved() {
        return isSaved;
    }
}
