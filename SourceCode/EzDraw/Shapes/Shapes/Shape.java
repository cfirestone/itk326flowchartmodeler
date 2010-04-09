/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Shapes;
/**
 * Contains all top level information on
 * how to draw a shape
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */

import java.util.LinkedList;

public abstract class Shape {
    protected Point startPoint, endPoint;
    protected LinkedList<Point> listOfPoints;
    protected IAttachable m_AttachmentStrategy;
    protected float rFillColor, gFillColor, bFillColor;
    protected float rBorderColor, gBorderColor, bBorderColor;

    /*TODO Changes must be documented*/
    protected String nestedDiagramURL;

    /**
     * Base Constructor for all other shapes
     *
     * @param p1 The starting point (upper left corner of bounding rectangle)
     * @param p2 The ending point (lower right corner of bounding rectangle)
     */
    public Shape(Point p1, Point p2) {
        startPoint = p1;
        endPoint = p2;

        calculatePoints();
    }

    public abstract Shape clone();


    /** Calculates the points needed to draw the shape */
    public abstract void calculatePoints();


    /**
     * Returns the start point
     *
     * @return The start Point
     */
    public Point getStartPoint() {
        return startPoint;
    }

    /** Returns the end point */
    public Point getEndPoint() {
        return endPoint;
    }

    /**
     * Sets the starting point of the shape
     *
     * @param p The point to set the start point
     */
    public void setStartPoint(Point p) {
        startPoint = p;
    }

    /**
     * Sets the end point
     *
     * @param p The point to set the end point
     */
    public void setEndPoint(Point p) {
        endPoint = p;
    }

    /** Calls the IAttachable to calculate snap points */
    public void drawAttachmentSections() {
        double[] sections =
                m_AttachmentStrategy.calculateAttachmentPoints(startPoint.getX(), startPoint.getY(),
                        Math.abs((endPoint.getX() - startPoint.getX())),
                        Math.abs((endPoint.getY() - startPoint.getY())));

        /*
        * use graphics package to render snap points
        */
        String eol = System.getProperty("line.separator");
        System.out.print(eol + "On hover, here are my snap points");
        for (int i = 0; i < (sections.length); i += 4) {
            System.out.print(eol);
            System.out.println("Rectangle drawn at X: " + sections[i]
                    + " Y: " + sections[i + 1] + eol + "With Dimensions X: "
                    + sections[i + 2] + " Y: " + sections[i + 3]);
        }
        System.out.println("End Snap Points" + eol);
    }

    /**
     * Set the attachment strategy (IAttachable) for the shape
     *
     * @param a The attachment strategy to be used
     */
    protected void setAttachmentStrategy(IAttachable a) {
        m_AttachmentStrategy = a;
    }

    /**
     * retSurns the list of points for drawing the shape
     *
     * @return - The list of points returned
     */
    public LinkedList<Point> getListOfPoints() {
        return listOfPoints;
    }

    abstract public boolean containsPoint(Point p);

    final public void translateBy(double x, double y) {
        startPoint.setX(startPoint.getX() + x);
        startPoint.setY(startPoint.getY() + y);
        endPoint.setX(endPoint.getX() + x);
        endPoint.setY(endPoint.getY() + y);
        calculatePoints();
    }

    /**
     * Gets the string URL for the nested diagram
     *
     * @return The string representing the Path to the nested Diagram
     */
    final public String getNestedDiagramURL() {
        return this.nestedDiagramURL;
    }

    /**
     * Sets the string URL for the nested diagram
     *
     * @param s The string representing the Path to the nested Diagram
     */
    final public void setNestedDiagramURL(String s) {
        this.nestedDiagramURL = s;
    }

    /**
     * Sets the colors of the shapes border
     *
     * @param c the array of colors to set
     */
    final public void setBorderColors(float[] c) {
        rBorderColor = c[0];
        gBorderColor = c[1];
        bBorderColor = c[2];
    }

    /**
     * Sets the colors of the shapes border
     *
     * @param r the red component of colors to set
     * @param g the green component of colors to set
     * @param b the blue component of colors to set
     */
    final public void setBorderColors(float r, float g, float b) {
        rBorderColor = r;
        gBorderColor = g;
        bBorderColor = b;
    }

    /**
     * Gets the colors of the shapes border
     *
     * @return The array containing the shapes border colors
     */
    final public float[] getBorderColors() {
        float[] c = new float[3];
        c[0] = rBorderColor;
        c[1] = gBorderColor;
        c[2] = bBorderColor;
        return c;
    }

    /**
     * Sets the colors of the shapes fill
     *
     * @param c the array of colors to set
     */
    final public void setFillColors(float[] c) {
        rFillColor = c[0];
        gFillColor = c[1];
        bFillColor = c[2];
    }

    /**
     * Sets the colors of the shapes fill
     *
     * @param r the red component of colors to set
     * @param g the green component of colors to set
     * @param b the blue component of colors to set
     */
    final public void setFillColors(float r, float g, float b) {
        rFillColor = r;
        gFillColor = g;
        bFillColor = b;
    }

    /**
     * Gets the colors of the shapes Fill
     *
     * @return The array containing the shapes Fill colors
     */
    final public float[] getFillColors() {
        float[] c = new float[3];
        c[0] = rFillColor;
        c[1] = gFillColor;
        c[2] = bFillColor;
        return c;
    }
}
