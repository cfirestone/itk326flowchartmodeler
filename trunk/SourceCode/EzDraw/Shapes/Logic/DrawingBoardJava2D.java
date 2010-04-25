/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

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

    private Graphics2D g2;


    public DrawingBoardJava2D() {
        super();
    }

    public void setGraphics(Graphics g) {
        g2 = (Graphics2D) g;
        g.setColor(Color.black);
    }

    /**
     * Draws a shape on the Java2D board
     *
     * @param s The Shape to draw
     */
    protected void drawShape(Shape s) {
        System.out.println("Drawing Shape: " + s.getClass().getName());

        LinkedList<Point> listOfPoints = s.getListOfPoints();

        float[] fcolors = s.getFillColors();
        float[] scolors = s.getBorderColors();
        g2.setColor(new Color(fcolors[0],fcolors[1],fcolors[2]));
        g2.setColor(new Color(scolors[0],scolors[1],scolors[2]));

        // check for line, because line is NOT a polygon
        if (s instanceof Line) {
            if (listOfPoints.size() != 2) {
                //TODO error catching
            } else {
                drawLine(listOfPoints.get(0), listOfPoints.get(1));
            }
        } else {
            Polygon poly = new Polygon();
            for (Point p : listOfPoints) {
                poly.addPoint((int) p.getX(), (int) p.getY());
            }

            g2.fill(poly);
            g2.draw(poly);

            if (s instanceof TextBox) {
                TextBox t = (TextBox) s;
                String text = t.getText();
                double heightMidPoint = ((listOfPoints.get(0).getY()) + (listOfPoints.get(listOfPoints.size() - 1)).getY()) / 2.0;
                g2.drawString(text, (int) (listOfPoints.get(0).getX()), (int) heightMidPoint);
            }
            if (s instanceof CompositeShape) {
                LinkedList<Shape> listOfShapes = ((CompositeShape) s).getListOfShapes();
                for (Shape listOfShape : listOfShapes) {
                    drawShape(listOfShape);
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
        Line2D.Double graphicsLine = new Line2D.Double(a.getX(), a.getY(), b.getX(), b.getY());
        g2.draw(graphicsLine);
    }

    public Shape getShape(Shapes.Point point) {
        boolean foundTopShape = false;
        Shape foundShape = null;
        for (int i = 0; i < listOfShapes.size(); i++) {
            if (!foundTopShape) {
                if (listOfShapes.get(listOfShapes.size() - 1 - i).containsPoint(point)) {
                    foundShape = listOfShapes.get(listOfShapes.size() - 1 - i);
                    foundTopShape = true;
                }
            }
        }
        return foundShape;
    }

    /**
     * Draws a shape on the DB, but will not add a shape to the list
     * so this shape will not be re-drawn when the whole canvas updates.
     */
    public void drawTempShape(Shape shape) {
        if (shape != null) {
            drawShape(shape);
        }
    }


}
