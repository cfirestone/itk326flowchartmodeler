/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Logic;

import Shapes.Shape;

import java.util.LinkedList;

/**
 * User: Carl
 * Date: Mar 22, 2010
 * Time: 3:33:53 PM
 */
public class State {

    LinkedList<Shape> listOfShapes;

    public State() {
        listOfShapes = new LinkedList<Shape>();
    }

    public State(LinkedList<Shape> newList) {
        listOfShapes = newList;
    }

    public LinkedList<Shape> getListOfShapes() {
        return listOfShapes;
    }
}
