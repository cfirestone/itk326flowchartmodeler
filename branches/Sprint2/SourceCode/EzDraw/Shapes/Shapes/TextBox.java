package Shapes;

/**
 * Describes how to draw a Shapes.TextBox
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */
public class TextBox extends Rectangle {
    private String m_Text;

    public TextBox() {
        m_Type = "Shapes.TextBox";
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
     * {@inheritDoc}
     */
    public void draw() {
        super.draw();
        String eol = System.getProperty("line.separator");
        System.out.println("My Text: " + eol + m_Text);

    }
}
