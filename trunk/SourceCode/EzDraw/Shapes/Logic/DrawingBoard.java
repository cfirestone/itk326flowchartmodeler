/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Logic;

import DataAccess.DataIO;
import DataAccess.XMLDataIO;
import Shapes.Point;
import Shapes.Shape;
import Shapes.TextBox;

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
    protected DataIO dataIO;
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
        // this check is so you cant clear all on an empty list,
        // and still be able to undo the clear all
        if (listOfShapes.size() > 0) {
            listOfShapes.clear();
            updateStateManager();
            drawShapes();
        }
    }

    abstract public void drawTempShape(Shape shape);

    abstract public Shape getShape(Point point);

    public void open(File f) throws Exception {
        open(f.getAbsolutePath());
    }

    public void open(String path) throws Exception {
        dataIO = new XMLDataIO(path);
        listOfShapes = dataIO.getData();
        updateStateManager();
        drawShapes();
        isSaved = true;
    }

    public void save(File f) throws Exception {
        String path = null;
        if (f != null)
            path = f.getAbsolutePath();
        save(path);
    }

    public void save(String path) throws Exception {
        if (dataIO == null)
            dataIO = new XMLDataIO(path);
        if (dataIO.saveData(listOfShapes)) {

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

    public void changeShapeProperties(Shape s, float[] fill, float[] stroke, String path) {
        int index = listOfShapes.indexOf(s);
        Shape changedShape = s.clone();
        changedShape.setBorderColors(stroke);
        changedShape.setFillColors(fill);
        changedShape.setNestedDiagramURL(path);
        listOfShapes.set(index, changedShape);
        updateStateManager();
        drawShapes();
    }

    public void changeTextBox(TextBox tb, String newText) {
        int index = listOfShapes.indexOf(tb);
        TextBox changedTextBox = tb.clone();
        changedTextBox.setText(newText);
        listOfShapes.set(index, changedTextBox);
        updateStateManager();
        drawShapes();
    }

    public Shape translateShape(Shape s, double deltaX, double deltaY) {
        int index = listOfShapes.indexOf(s);
        Shape clone = s.clone();
        Point cloneStart, cloneEnd;
        cloneStart = new Point(clone.getStartPoint().getX() + deltaX, clone.getStartPoint().getY() + deltaY);
        cloneEnd = new Point(clone.getEndPoint().getX() + deltaX, clone.getEndPoint().getY() + deltaY);
        clone.setStartPoint(cloneStart);
        clone.setEndPoint(cloneEnd);
        listOfShapes.set(index, clone);
        updateStateManager();
        drawShapes();
        return clone;
    }

    public Shape dragShape(Shape s, double deltaX, double deltaY) {
        int index = listOfShapes.indexOf(s);
        Shape clone = s.clone();
        Point cloneStart, cloneEnd;
        cloneStart = new Point(clone.getStartPoint().getX() + deltaX, clone.getStartPoint().getY() + deltaY);
        cloneEnd = new Point(clone.getEndPoint().getX() + deltaX, clone.getEndPoint().getY() + deltaY);
        clone.setStartPoint(cloneStart);
        clone.setEndPoint(cloneEnd);
        listOfShapes.set(index, clone);
        //updateStateManager();
        drawShapes();
        return clone;
    }


}
