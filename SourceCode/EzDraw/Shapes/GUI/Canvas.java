package GUI;

/**
 * Created by IntelliJ IDEA.
 * User: Carl
 * Date: Feb 17, 2010
 * Time: 10:49:26 PM
 * To change this template use File | Settings | File Templates.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Canvas extends JPanel {

    public boolean isOpaque() {
        return true;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double rect = new Rectangle2D.Double(10, 10, 100, 100);


        g2d.setColor(new Color(255, 255, 0));

        g2d.draw(rect);
    }
}
