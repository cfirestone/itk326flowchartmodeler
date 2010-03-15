package GUI;

import Logic.DrawingBoard;
import Logic.DrawingBoardJava2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * User: Carl
 * Date: Mar 7, 2010
 * Time: 10:08:12 PM
 */
public class Canvas extends JPanel implements MouseListener, MouseMotionListener {

    private DrawingBoard db;

    public Canvas() {
        setBackground(Color.white);
        addMouseMotionListener(this);
        addMouseListener(this);
        db = new DrawingBoardJava2D();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Dimension dim = getSize();
        int w = (int) dim.getWidth();
        int h = (int) dim.getHeight();

        // clear canvas
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, w, h);

        //drawShapes
        ((DrawingBoardJava2D) db).setGraphics(g);
        db.drawShapes();
    }

}
