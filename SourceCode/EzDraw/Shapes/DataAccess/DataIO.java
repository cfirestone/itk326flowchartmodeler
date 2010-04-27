/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package DataAccess;

import Shapes.Shape;

import javax.xml.xpath.XPathExpressionException;
import java.util.LinkedList;

/**
 * Top level abstraction for data import and export
 *
 * @author Justin Blakley & Carl Firestonr
 * @version 1.0.0.0
 */
public abstract class DataIO {
    protected String connectionString;

    /**
     * Constructor for the DataIO class
     * @param newConnString The connection string for this DataIO Object specific to the implementation
     */
    public void changeConnectionString(String newConnString){
        connectionString = newConnString;
    }

    /**
     * The method responsible for getting shape data from the implementations data source
     * @return The list of shapes that are present in the data source
     * @throws Exception A data read error
     */
    public abstract LinkedList<Shape> getData() throws Exception;

    /**
     * The method responsible for saving shape data to the implementations data source
     * @param shapes The list of shapes to save
     * @return true if successful, false if not successful
     * @throws Exception A data save error
     */
    public abstract boolean saveData(LinkedList<Shape> shapes) throws Exception;
}
