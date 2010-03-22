/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package DataAccess;

import Shapes.Shape;

import java.util.LinkedList;

/**
 * Top level abstraction for data import and export
 *
 * @author Justin Blakley & Carl Firestonr
 * @version 1.0.0.0
 */
public abstract class DataIO {
    protected String connectionString;

    public void changeConnectionString(String newConnString){
        connectionString = newConnString;
    }
    
    public abstract LinkedList<Shape> getData();
    public abstract boolean saveData(LinkedList<Shape> shapes);
}
