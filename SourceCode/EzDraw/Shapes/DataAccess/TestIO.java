/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package DataAccess;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: justinblakley
 * Date: Apr 7, 2010
 * Time: 4:17:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestIO {
    public static void main(String args[]){
        XMLDataIO a = new XMLDataIO("Test.xml");
        a.getData();


    }
}
