/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package DataAccess;

import Shapes.*;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.FileWriter;
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
    public LinkedList<Shape> getData() throws Exception {
        LinkedList<Shape> data = new LinkedList<Shape>();

        loadXMLFile();

        data.addAll(getCirclesFromDocument());
        data.addAll(getRectanglesFromDocument());
        data.addAll(getLinesFromDocument());
        data.addAll(getTextFromDocument());

        return data;
    }

    @Override
    public boolean saveData(LinkedList<Shape> shapes)throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;

        docBuilder = factory.newDocumentBuilder();

        document = docBuilder.newDocument();

        Element root = buildRoot();
        
        for(int i = 0; i < shapes.size(); i++){
            Shape shape = shapes.get(i);

            if(shape instanceof Circle){
                root.appendChild(setCircleElement(shape));
            }
            else if(shape instanceof Line){
                root.appendChild(setLineElement(shape));
            }
            else if(shape instanceof TextBox){
                root.appendChild(setTextElement(shape));
            }
            else if(shape instanceof Rectangle){
                root.appendChild(setRectangleElement(shape));
            }
        }

        document.appendChild(root);
        
        XMLSerializer serializer = new XMLSerializer();

        serializer.setOutputCharStream(new FileWriter(connectionString));
        serializer.serialize(document);

        return true;
    }

    private Element setTextElement(Shape s){
        int sX = (int)s.getStartPoint().getX();
        int sY = (int)s.getStartPoint().getY();
        int eY = (int)s.getEndPoint().getY();
        int eX = (int)s.getEndPoint().getX();

        Element textElement = document.createElement("text");
        textElement.setAttribute("x",""+ sX);
        textElement.setAttribute("y",""+ sY);
        textElement.setAttribute("width",""+(eX-sX));
        textElement.setAttribute("height",""+(eY-sY));
        textElement.setAttribute("font-family","Verdana");
        textElement.setAttribute("font-size","12");
        textElement.setAttribute("fill",""+rgbToHex(s.getFillColors()));

        textElement.appendChild(document.createTextNode(((TextBox)s).getText()));

        return textElement;
    }       

    private Element setLineElement(Shape s){
        int sx = (int)s.getStartPoint().getX();
        int sy = (int)s.getStartPoint().getY();
        int ex = (int)s.getEndPoint().getX();
        int ey = (int)s.getEndPoint().getY();

        Element lineElement = document.createElement("line");
        lineElement.setAttribute("x1",""+sx);
        lineElement.setAttribute("x2",""+ex);
        lineElement.setAttribute("y1",""+sy);
        lineElement.setAttribute("y2",""+ey);

        lineElement.setAttribute("fill",""+rgbToHex(s.getFillColors()));
        lineElement.setAttribute("stroke",""+rgbToHex(s.getBorderColors()));

        return lineElement;
    }

    private Element setCircleElement(Shape s){
        int sX = (int)s.getStartPoint().getX();
        int sY = (int)s.getStartPoint().getY();
        int r = (int)(s.getStartPoint().getDistance(s.getEndPoint())+.5);

        Element circleElement = document.createElement("circle");
        circleElement.setAttribute("cx",""+sX);
        circleElement.setAttribute("cy",""+sY);
        circleElement.setAttribute("r",""+ r);

        circleElement.setAttribute("fill",""+rgbToHex(s.getFillColors()));
        circleElement.setAttribute("stroke",""+rgbToHex(s.getBorderColors()));

        if(s.getNestedDiagramURL() != null)
            circleElement.setAttribute("nestURL",s.getNestedDiagramURL());

        return circleElement;
    }

    private Element setRectangleElement(Shape s){
        int sX = (int)s.getStartPoint().getX();
        int sY = (int)s.getStartPoint().getY();
        int eX = (int)s.getEndPoint().getX();
        int eY = (int)s.getEndPoint().getY();

        Element rectElement = document.createElement("rect");
        rectElement.setAttribute("x",""+sX);
        rectElement.setAttribute("y",""+sY);
        rectElement.setAttribute("width",""+(eX-sX));
        rectElement.setAttribute("height",""+(eY-sY));
        rectElement.setAttribute("fill",""+rgbToHex(s.getFillColors()));
        rectElement.setAttribute("stroke",""+rgbToHex(s.getBorderColors()));

        if(s.getNestedDiagramURL() != null)
            rectElement.setAttribute("nestURL",s.getNestedDiagramURL());

        return rectElement;
    }

    private Element buildRoot() {
        Element root = document.createElement("svg");
        root.setAttribute("width","100%");
        root.setAttribute("height","100%");
        root.setAttribute("version","1.1");
        root.setAttribute("xmlns","http://www.w3.org/2000/svg");

        return root;
    }

    private void loadXMLFile() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        document = docBuilder.parse(connectionString);
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
            String uri = getNestedDiagram(nodes.item(i).getAttributes().getNamedItem("nestURL"));

            float[] fillArray = convertHexToFloat(fill);
            float[] strokeArray = convertHexToFloat(stroke);

            circle = new Circle(new Point((double)x,(double)y),new Point((double)(x+r),(double)(y+r)));

            if(uri != null)               
                circle.setNestedDiagramURL(uri);

            circle.setBorderColors(strokeArray);
            circle.setFillColors(fillArray);

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
            String uri = getNestedDiagram(nodes.item(i).getAttributes().getNamedItem("nestURL"));

            float[] fillArray = convertHexToFloat(fill);
            float[] strokeArray = convertHexToFloat(stroke);

            rect = new Rectangle(new Point((double) x, (double) y), new Point((double) x + width, (double) y + height));
            
            rect.setBorderColors(strokeArray);
            rect.setFillColors(fillArray);

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
            int sx = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("x1").getNodeValue());
            int sy = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("y1").getNodeValue());
            int ex = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("x2").getNodeValue());
            int ey = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("x2").getNodeValue());
            String fill = nodes.item(i).getAttributes().getNamedItem("fill").getNodeValue();
            String stroke = nodes.item(i).getAttributes().getNamedItem("stroke").getNodeValue();

            float[] fillArray = convertHexToFloat(fill);
            float[] strokeArray = convertHexToFloat(stroke);

            line = new Line(new Point((double)sx,(double)sy), new Point((double)ex,(double)ey));

            line.setBorderColors(strokeArray);
            line.setFillColors(fillArray);

            lines.add(line);
        }

        return lines;
    }

    private LinkedList<TextBox> getTextFromDocument() throws XPathExpressionException{
        LinkedList<TextBox> text = new LinkedList<TextBox>();

        NodeList nodes = getNodeList("//text");
        TextBox rect;
        for(int i = 0; i < nodes.getLength(); i++)
        {
            int x = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("x").getNodeValue());
            int y = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("y").getNodeValue());
            int width = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("width").getNodeValue());
            int height = Integer.parseInt(nodes.item(i).getAttributes().getNamedItem("height").getNodeValue());

            String fill = nodes.item(i).getAttributes().getNamedItem("fill").getNodeValue();

            float[] fillArray = convertHexToFloat(fill);

            rect = new TextBox(new Point((double) x, (double) y), new Point((double) x+width , (double) y+height));

            rect.setBorderColors(fillArray);

            text.add(rect);
        }

        return text;
    }

    private String getNestedDiagram(Node n){
        if(n != null)
         return n.getNodeValue();
        return null;
    }

    private NodeList getNodeList(String expression) throws XPathExpressionException {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        XPathExpression expr = xpath.compile(expression);
        Object result = expr.evaluate(document, XPathConstants.NODESET);
        return (NodeList) result;
    }

    private static float[] convertHexToFloat(String hex){
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
    
    private static int convertToInt(char c1, char c2){
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

    private String rgbToHex(float [] c){
        int rI = (int)(c[0] * 255);
        int gI = (int)(c[1] * 255);
        int bI = (int)(c[2] * 255);

        String rS = Integer.toHexString(rI);
        String gS = Integer.toHexString(gI);
        String bS = Integer.toHexString(bI);

        if(rS.length() == 1)
            rS = "0"+rS;
        if(gS.length() == 1)
            gS = "0"+gS;
        if(bS.length() == 1)
            bS = "0"+bS;

        return "#"+rS+gS+bS;
    }
}