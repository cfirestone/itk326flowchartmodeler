/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Shapes;

/**
 * Describes how to draw a TextBox
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */
public class TextBox extends Rectangle {
    private String m_Text;

    /**
     * Constructor for TextBod
     * @param p1 The starting point
     * @param p2 The ending point
     */
    public TextBox(Point p1, Point p2) {
        super(p1, p2);
        m_Type = "TextBox";
        m_Text = "(default text)";

        rFillColor = gFillColor = bFillColor = 1.0f;
        rBorderColor = gBorderColor = bBorderColor = 0.0f;
    }

    /**
     * The constructor for TextBox
     * @param rect The rectangle object to make this text box out of
     */
    public TextBox(Rectangle rect) {
        super(rect.startPoint, rect.endPoint);
        m_Type = "TextBox";
        m_Text = "(default text)";

        rFillColor = gFillColor = bFillColor = 1.0f;
        rBorderColor = gBorderColor = bBorderColor = 0.0f;

    }

    public TextBox clone() {
        TextBox clone = new TextBox(super.clone());
        clone.setText(this.getText());
        return clone;
    }

    /**
     * Sets the text in the text box
     *
     * @param text The text to be displayed
     */
    public void setText(String text) {
        m_Text = text;
    }

    /**
     * Returns the text to be displayed
     *
     * @returns The text to be displayed;
     */
    public String getText() {
        return m_Text;
    }

    /**
     * Sets the string URL for the nested diagram
     *
     * @param s The string representing the Path to the nested Diagram
     */
    public void setNestedDiagramURL(String s) {
    }

    /**
     * Sets the colors of the shapes border
     *
     * @param c the array of colors to set
     */
    public void setBorderColors(float[] c) {

    }

    /**
     * Sets the colors of the shapes border
     *
     * @param r the red component of colors to set
     * @param g the green component of colors to set
     * @param b the blue component of colors to set
     */
    public void setBorderColors(float r, float g, float b) {

    }


    /**
     * Sets the colors of the shapes fill
     *
     * @param c the array of colors to set
     */
    public void setFillColors(float[] c) {

    }

    /**
     * Sets the colors of the shapes fill
     *
     * @param r the red component of colors to set
     * @param g the green component of colors to set
     * @param b the blue component of colors to set
     */
    public void setFillColors(float r, float g, float b) {

    }
}
