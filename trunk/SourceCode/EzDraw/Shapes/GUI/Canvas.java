/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package GUI;

import Logic.DrawingBoard;
import Logic.DrawingBoardJava2D;
import Shapes.Circle;
import Shapes.Line;
import Shapes.Shape;
import Shapes.TextBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * User: Carl
 * Date: Mar 7, 2010
 * Time: 10:08:12 PM
 */
public class Canvas extends JPanel implements MouseListener, MouseMotionListener, ActionListener, ItemListener {

    private DrawingBoard db;
    private Shapes.Point startPoint;
    private Shapes.Point endPoint;
    protected Shapes.Point currPoint;
    private boolean mouseDown;
    private ToolType currentTool;
    private JPopupMenu popup;
    private Shape selectedShape;

    public Canvas() {
        setBackground(Color.white);
        addMouseMotionListener(this);
        addMouseListener(this);
        setDb(new DrawingBoardJava2D());
        currentTool = ToolType.SELECT;

        popup = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Delete");
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Shape Properties");
        menuItem.addActionListener(this);
        popup.add(menuItem);

        this.addMouseListener(this);
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
        ((DrawingBoardJava2D) getDb()).setGraphics(g);
        getDb().drawShapes();
    }

    DrawingBoard getDb() {
        return db;
    }

    void setDb(DrawingBoard db) {
        this.db = db;
    }

    public ToolType getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(ToolType tool) {
        currentTool = tool;
        System.out.println("Current tool is now: " + tool.toString());
    }

    void clearAllShapes() {
        db.clearAllShapes();
    }

    private boolean isLButtonDown(MouseEvent e) {
        return (e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK;
    }

    private boolean isRButtonDown(MouseEvent e) {
        return (e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK;
    }

    private boolean isMButtonDown(MouseEvent e) {
        return (e.getModifiers() & InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK;
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
        endPoint = new Shapes.Point(e.getX(), e.getY());
        if (isLButtonDown(e) && mouseDown) {
            switch (currentTool) {
                case SELECT:
                    break;
                case DRAW_TEXTBOX:
                    db.addShape(new TextBox(startPoint, endPoint));
                    break;
                case DRAW_LINE:
                    db.addShape(new Line(startPoint, endPoint));
                    break;
                case DRAW_RECTANGLE:
                    db.addShape(new Shapes.Rectangle(startPoint, endPoint));
                    break;
                case DRAW_CIRCLE:
                    db.addShape(new Circle(startPoint, endPoint));
                    break;
            }
            update(getGraphics());
        }
        mouseDown = false;
    }

    public void mousePressed(MouseEvent e) {
        if (isLButtonDown(e)) {
            if (!mouseDown) {
                startPoint = new Shapes.Point(e.getX(), e.getY());
            }
            mouseDown = true;
        }
        if (isRButtonDown(e)) {
            selectedShape = db.getShape(new Shapes.Point(e.getX(), e.getY()));
            if (selectedShape != null) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }

    }


    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        System.out.println(source.getText());

    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        if (source.getText().equals("Delete")) {
            db.removeShape(selectedShape);
            repaint();
            update(getGraphics());
        }
    }

    public boolean goForward() {
        boolean flag = db.goForward();
        if (flag) {
            update(getGraphics());
        }
        return flag;
    }

    public boolean goBackward() {
        boolean flag = db.goBackward();
        if (flag) {
            update(getGraphics());
        }
        return flag;
    }
}
