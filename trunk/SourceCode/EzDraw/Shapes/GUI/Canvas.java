/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package GUI;

import Logic.DrawingBoard;
import Logic.DrawingBoardJava2D;
import Shapes.Circle;
import Shapes.Line;
import Shapes.TextBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
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
    private Shapes.Point startPoint;
    private Shapes.Point endPoint;
    protected Shapes.Point currPoint;
    private boolean mouseDown;
    private ToolType currentTool;
    private RightClickMenu popup;


    public Canvas() {
        setBackground(Color.white);
        addMouseMotionListener(this);
        addMouseListener(this);
        setDb(new DrawingBoardJava2D());
        currentTool = ToolType.SELECT;

        popup = new RightClickMenu();
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
        if (isRButtonDown(e)) {
            Shapes.Shape selectedShape = ((DrawingBoardJava2D) db).getShape(new Shapes.Point(e.getX(), e.getY()));
            if (selectedShape != null) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        endPoint = new Shapes.Point(e.getX(), e.getY());
        if (isLButtonDown(e)) {
            switch (currentTool) {
                case SELECT:
                    break;
                case DRAW_TEXTBOX:
                    db.drawTempShape(new TextBox(startPoint, endPoint));
                    break;
                case DRAW_LINE:
                    db.drawTempShape(new Line(startPoint, endPoint));
                    break;
                case DRAW_RECTANGLE:
                    db.drawTempShape(new Shapes.Rectangle(startPoint, endPoint));
                    break;
                case DRAW_CIRCLE:
                    db.drawTempShape(new Circle(startPoint, endPoint));
                    break;
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        endPoint = new Shapes.Point(e.getX(), e.getY());
        if (isLButtonDown(e)) {
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
            repaint();
        }
        mouseDown = false;
    }

    public void mousePressed(MouseEvent e) {
        if (isLButtonDown(e)) {
            mouseDown = true;
            startPoint = new Shapes.Point(e.getX(), e.getY());
        }

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
        return (e.getModifiers() & InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK;
    }

    private boolean isMButtonDown(MouseEvent e) {
        return (e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK;
    }

}
