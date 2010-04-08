/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Logic;

import Shapes.Shape;

import java.util.LinkedList;

/**
 * User: Carl
 * Date: Apr 7, 2010
 * Time: 6:58:54 PM
 */
public class StateManager {

    LinkedList<State> states;
    int currentIndex;

    public StateManager() {
        states = new LinkedList<State>();
        states.add(new State(new LinkedList<Shape>()));
        currentIndex = 0;
    }

    public StateManager(LinkedList<State> newListOfStates) {
        if (newListOfStates.size() > 0) {
            states = newListOfStates;
        } else {
            states = new LinkedList<State>();
            states.add(new State(new LinkedList<Shape>()));
        }
        currentIndex = 0;
    }

    public State getCurrentState() {
        return states.get(currentIndex);
    }

    public boolean canGoForward() {
        return (currentIndex < states.size() - 1) ? true : false;
    }

    public boolean canGoBackward() {
        return (currentIndex >= 1) ? true : false;
    }

    public boolean goForward() {
        if (canGoForward()) {
            currentIndex++;
            return true;
        } else {
            return false;
        }
    }

    public boolean goBackward() {
        if (canGoBackward()) {
            currentIndex--;
            return true;
        } else {
            return false;
        }
    }

    public void newState(LinkedList<Shape> newShapes) {
        State newState = new State(newShapes);
        while (states.size() > currentIndex + 1) {
            states.remove(currentIndex + 1);
        }
        states.add(newState);
        currentIndex++;
    }
}
