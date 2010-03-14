package Shapes;

/**
 * Describes how to draw a TextBox
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */
public class TextBox extends Rectangle {
    private String m_Text;

    public TextBox(Point p1, Point p2) {
        super(p1,p2);
        m_Type = "TextBox";
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
}
