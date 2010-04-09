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
            System.out.println("Could not parse XML Document");
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
            String fill = nodes.item(i).getAttributes().getNamedItem("fill").getNodeValue();
            String stroke = nodes.item(i).getAttributes().getNamedItem("stroke").getNodeValue();

            float[] fillArray = convertHexToFloat(fill);
            float[] strokeArray = convertHexToFloat(stroke);

            circle = new Circle(new Point((double)x,(double)y),new Point((double)(x+r),(double)(y+r)));

            circle.setBorderColors(fillArray);
            circle.setFillColors(strokeArray);

            String uri = getNestedDiagram(nodes.item(i));

            if(uri != null)
                circle.setNestedDiagramURL(uri);

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
            String fill = nodes.item(i).getAttributes().getNamedItem("fill").getNodeValue();
            String stroke = nodes.item(i).getAttributes().getNamedItem("stroke").getNodeValue();

            float[] fillArray = convertHexToFloat(fill);
            float[] strokeArray = convertHexToFloat(stroke);

            if(nodes.item(i).getAttributes().getNamedItem("text") != null)
            {
                String text = nodes.item(i).getAttributes().getNamedItem("text").getNodeValue();
                rect = new TextBox(new Point((double)x, (double)y),new Point((double)x+width, (double)y+height));
                ((TextBox)rect).setText(text);
            }
            else {
                rect = new Rectangle(new Point((double) x, (double) y), new Point((double) x + width, (double) y + height));
            }
            
            rect.setBorderColors(fillArray);
            rect.setFillColors(strokeArray);
            String uri = getNestedDiagram(nodes.item(i));

            if(uri != null)
                rect.setNestedDiagramURL(uri);
            
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
            String fill = nodes.item(i).getAttributes().getNamedItem("fill").getNodeValue();
            String stroke = nodes.item(i).getAttributes().getNamedItem("stroke").getNodeValue();

            float[] fillArray = convertHexToFloat(fill);
            float[] strokeArray = convertHexToFloat(stroke);

            line = new Line(new Point((double)sx,(double)sy), new Point((double)ex,(double)ey));

            line.setBorderColors(fillArray);
            line.setFillColors(strokeArray);

            String uri = getNestedDiagram(nodes.item(i));

            if(uri != null)
                line.setNestedDiagramURL(uri);

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

    private static float[] convertHexToFloat(String hex)
    {
        float[] returnVal = new float[3];
        if(hex.charAt(0)== '#')
        {
            hex = hex.substring(1);
        }
        int i = 0;
        while(hex.length() > 0){
            int digit = convertToInt(hex.charAt(0),hex.charAt(1));
            returnVal[i] = (float)digit/255;
            i++;
            hex = hex.substring(2);
        }
        return returnVal;
    }
    private static int convertToInt(char c1, char c2)
    {
        int one, two;
        if(c1 == 'a' || c1 == 'A'){one = 10;}
        else if(c1 == 'b' || c1 == 'B'){one = 11;}
        else if(c1 == 'c' || c1 == 'C'){one = 12;}
        else if(c1 == 'd' || c1 == 'D'){one = 13;}
        else if(c1 == 'e' || c1 == 'E'){one = 14;}
        else if(c1 == 'f' || c1 == 'F'){one = 15;}
        else{one = Integer.parseInt(""+c1);}

        if(c2 == 'a' || c2 == 'A'){two = 10;}
        else if(c2 == 'b' || c2 == 'B'){two = 11;}
        else if(c2 == 'c' || c2 == 'C'){two = 12;}
        else if(c2 == 'd' || c2 == 'D'){two = 13;}
        else if(c2 == 'e' || c2 == 'E'){two = 14;}
        else if(c2 == 'f' || c2 == 'F'){two = 15;}
        else{two = Integer.parseInt(""+c2);}

        return (((one + 1)*(two + 1))-1);
    }

    public static void main(String args[]){
        convertHexToFloat("#00fF00");
    }
}
