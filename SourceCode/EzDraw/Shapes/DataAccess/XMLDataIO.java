/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package DataAccess;

import Shapes.Shape;

import java.util.LinkedList;

/**
 * XmlBased File IO operations
 *
 * @author Justin Blakley & Carl Firestonr
 * @version 1.0.0.0
 */
public class XMLDataIO extends DataIO{

    public XMLDataIO(String connData){
        connectionString = connData;
    }
    @Override
    public LinkedList<Shape> getData() {
        return null;
    }

    @Override
    public boolean saveData(LinkedList<Shape> shapes) {
        return false;
    }
}
