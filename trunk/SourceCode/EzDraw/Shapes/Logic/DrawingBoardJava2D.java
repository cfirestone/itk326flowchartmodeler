package Logic;

import Shapes.*;
import Shapes.Point;
import Shapes.Shape;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.LinkedList;

/**
 * User: Carl
 * Date: Mar 5, 2010
 * Time: 5:41:09 PM
 */
public class DrawingBoardJava2D extends DrawingBoard {

    private Graphics2D graphics2dObj;


    // RENDER_MODE = 0 --> No Fill
    // RENDER_MODE = 1 --> Fill
    private final int RENDER_MODE = 1;

    public DrawingBoardJava2D() {
    }

    public void setGraphics(Graphics g) {
        graphics2dObj = (Graphics2D) g;
    }

    /**
     * Draws a shape on the Java2D board
     *
     * @param s The Shape to draw
     */
    protected void drawShape(Shape s) {

        LinkedList<Point> listOfPoints = s.getListOfPoints();

        // check for line, because line is NOT a polygon
        if (s instanceof Line) {
            if (listOfPoints.size() != 2) {
                //TODO error catching
            } else {
                drawLine(listOfPoints.get(0), listOfPoints.get(1));
            }
        } else {
            Polygon poly = new Polygon();
            for (int i = 0; i < listOfPoints.size(); i++) {
                Point p = listOfPoints.get(i);
                poly.addPoint((int) p.getX(), (int) p.getY());
            }
            graphics2dObj.draw(poly);

            if (s instanceof TextBox) {
                TextBox t = (TextBox) s;
                String text = t.getText();
                double heightMidPoint = ((listOfPoints.get(0).getY()) + (listOfPoints.get(listOfPoints.size() - 1)).getY()) / 2.0;
                graphics2dObj.drawString(text, (int) (listOfPoints.get(0).getX()), (int) heightMidPoint);
            }
            if (s instanceof CompositeShape) {
                LinkedList<Shape> listOfShapes = ((CompositeShape) s).getListOfShapes();
                for (int i = 0; i < listOfShapes.size(); i++) {
                    drawShape(listOfShapes.get(i));
                }
            }
        }
    }

    /**
     * Draws a line from point a to point b
     *
     * @param a The starting point of the line
     * @param b The ending point of the line
     */
    private void drawLine(Point a, Point b) {
        Line2D.Double graphicsLine = new Line2D.Double(a.getX(), a.getY(), a.getX(), b.getY());
        graphics2dObj.draw(graphicsLine);
    }


}
