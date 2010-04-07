/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package DataAccess;

import Shapes.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import java.io.IOException;
import java.util.LinkedList;

/**
 * XmlBased File IO operations
 *
 * @author Justin Blakley & Carl Firestonr
 * @version 1.0.0.0
 */
public class XMLDataIO extends DataIO{
    private Document document;   

    public XMLDataIO(String connData){
        connectionString = connData;
    }
    @Override
    public LinkedList<Shape> getData() {
        loadXMLFile();
        return null;
    }

    @Override
    public boolean saveData(LinkedList<Shape> shapes) {
        return false;
    }

    private void loadXMLFile(){
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = docBuilder.parse(connectionString);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LinkedList<Circle> getCirclesFromDocument(){
        //use XPath to get the Circle elements in the document
        return null;
    }

    private LinkedList<Rectangle> getRectanglesFromDocument(){
        //use XPath to get the Rectangle elements in the document
        //check the tag metadata to see if this rectangle is a text box
        return null;
    }

    private LinkedList<Line> getLinesFromDocument(){
        //use XPath to get the Line elements in the document
        return null;
    }

    private String getNestedDiagram(){
        //check the current element for metadata which points to the nested diagram
        return "";
    }
}
