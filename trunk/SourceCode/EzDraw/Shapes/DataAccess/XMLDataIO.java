/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package DataAccess;

import Shapes.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
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
        LinkedList<Shape> data = new LinkedList<Shape>();

        loadXMLFile();
        try {
            data.addAll(getCirclesFromDocument());
            data.addAll(getRectanglesFromDocument());
            data.addAll(getLinesFromDocument());
        }
        catch (XPathExpressionException e) {
            System.out.println("Could not read parse XML Document");
            e.printStackTrace();  
        }
        return data;
    }

    @Override
    public boolean saveData(LinkedList<Shape> shapes) {
        return false;
    }

    private void loadXMLFile(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            document = docBuilder.parse(connectionString);            
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LinkedList<Circle> getCirclesFromDocument() throws XPathExpressionException {
        LinkedList<Circle> circles = new LinkedList<Circle>();

        NodeList nodes = getNodeList("//circle");
        Circle circle;
        for(int i = 0; i < nodes.getLength(); i++)
        {
            int x = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("cx").getNodeValue());
            int y = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("cy").getNodeValue());
            int r = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("r").getNodeValue());

            circle = new Circle(new Point((double)x,(double)y),new Point((double)(x+r),(double)(y+r)));
            String uri = getNestedDiagram(nodes.item(i));

            if(uri != null)
                circle.setNestedDiagramURL(uri);
            
            //String style = nodes.item(i).getAttributes().getNamedItem("style").getNodeValue();

            circles.add(circle);
        }

        return circles;
    }

    private LinkedList<Rectangle> getRectanglesFromDocument() throws XPathExpressionException {
        LinkedList<Rectangle> rects = new LinkedList<Rectangle>();

        NodeList nodes = getNodeList("//rect");
        Rectangle rect;
        for(int i = 0; i < nodes.getLength(); i++)
        {
            int x = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("x").getNodeValue());
            int y = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("y").getNodeValue());
            int width = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("width").getNodeValue());
            int height = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("height").getNodeValue());

            if(nodes.item(i).getAttributes().getNamedItem("text") != null)
            {
                String text = nodes.item(i).getAttributes().getNamedItem("text").getNodeValue();
                rect = new TextBox(new Point((double)x, (double)y),new Point((double)x+width, (double)y+height));
                ((TextBox)rect).setText(text);
            }
            else {
                rect = new Rectangle(new Point((double) x, (double) y), new Point((double) x + width, (double) y + height));
            }

            String uri = getNestedDiagram(nodes.item(i));

            if(uri != null)
                rect.setNestedDiagramURL(uri);

            String style = nodes.item(i).getAttributes().getNamedItem("style").getNodeValue();

            rects.add(rect);
        }

        return rects;
    }

    private LinkedList<Line> getLinesFromDocument() throws XPathExpressionException {
        LinkedList<Line> lines = new LinkedList<Line>();

        NodeList nodes = getNodeList("//line");
        Line line;
        for(int i = 0; i < nodes.getLength(); i++)
        {
            //TODO check the actual query string for line
            int sx = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("x1").getNodeValue());
            int sy = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("y1").getNodeValue());
            int ex = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("x2").getNodeValue());
            int ey = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("x2").getNodeValue());

            line = new Line(new Point((double)sx,(double)sy), new Point((double)ex,(double)ey));
            String uri = getNestedDiagram(nodes.item(i));

            if(uri != null)
                line.setNestedDiagramURL(uri);

            String style = nodes.item(i).getAttributes().getNamedItem("style").getNodeValue();

            lines.add(line);
        }

        return lines;
    }

    private String getNestedDiagram(Node n){
        if(n.hasChildNodes())
         return n.getFirstChild().getNodeValue();
        return null;
    }

    private NodeList getNodeList(String expression) throws XPathExpressionException {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr = xpath.compile(expression);
        Object result = expr.evaluate(document, XPathConstants.NODESET);
        return (NodeList) result;
    }

    private float[] parseFillStyle(String s){return null;}
    private float[] parseBorderStyle(String s){return null;}
}
