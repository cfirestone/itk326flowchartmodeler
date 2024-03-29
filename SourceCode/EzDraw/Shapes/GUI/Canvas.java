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
import java.io.File;


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
    private double mouseDownX, mouseDownY, mouseCurrX, mouseCurrY, mouseUpX, mouseUpY;


    public Canvas() {
        setBackground(Color.white);
        addMouseMotionListener(this);
        addMouseListener(this);
        setDb(new DrawingBoardJava2D());
        currentTool = ToolType.SELECT;


        mouseDownX = mouseDownY = mouseCurrX = mouseCurrY = mouseUpX = mouseUpY = 0;

        popup = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Delete");
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Shape Properties");
        menuItem.addActionListener(this);
        popup.add(menuItem);
        menuItem = new JMenuItem("Open Nested Diagram");
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

    public void open(File f) throws Exception {
        db.open(f);
        update(getGraphics());
    }

    public void open(String path) throws Exception {
        db.open(path);
        update(getGraphics());
    }

    public void save(File f) throws Exception {
        db.save(f);
    }

    public void save(String path) throws Exception {
        db.save(path);
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
        double mcx, mcy;
        mcx = (mouseCurrX == 0) ? mouseDownX : mouseCurrX;
        mcy = (mouseCurrY == 0) ? mouseDownY : mouseCurrY;


        mouseCurrX = e.getX();
        mouseCurrY = e.getY();
        if (isLButtonDown(e) && mouseDown) {
            switch (currentTool) {
                case SELECT:
                    if (selectedShape != null) {
                        double deltaX = mouseCurrX - mcx;
                        double deltaY = mouseCurrY - mcy;
                        selectedShape = db.dragShape(selectedShape, deltaX, deltaY);
                    }
                    break;
            }
        }
        update(getGraphics());
    }

    public void mouseMoved(MouseEvent e) {


    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        mouseUpX = e.getX();
        mouseUpY = e.getY();
        endPoint = new Shapes.Point(e.getX(), e.getY());
        if (isLButtonDown(e) && mouseDown) {
            switch (currentTool) {
                case SELECT:
                    if (selectedShape != null) {
                        double deltaX = mouseUpX - mouseDownX;
                        double deltaY = mouseUpY - mouseDownY;
                        // comit the change to the statemanager, but only slightly move this time
                        db.translateShape(selectedShape, .000000001, .000000001);
                    }
                    break;
                case DRAW_TEXTBOX:
                    TextBox tb = new TextBox(startPoint, endPoint);
                    String text = (String) JOptionPane.showInputDialog(
                            this,
                            "Enter the text for this TextBox:\n",
                            "TextBox Text Entry");
                    tb.setText(text);
                    db.addShape(tb);
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
        mouseCurrX = mouseCurrY = 0;
    }

    public void mousePressed(MouseEvent e) {
        mouseDownX = e.getX();
        mouseDownY = e.getY();
        if (isLButtonDown(e)) {
            if (currentTool == ToolType.SELECT) {
                selectedShape = db.getShape(new Shapes.Point(e.getX(), e.getY()));
            }
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
        } else if (source.getText().equals("Open Nested Diagram")) {
            if (selectedShape.getNestedDiagramURL() != null) {
                NestedViewer n = new NestedViewer(selectedShape.getNestedDiagramURL());

                n.setBounds(30, 30, 500, 500); // Size
                n.setLocation((int) this.getParent().getLocation().getX() + 50, (int) this.getParent().getLocation().getY());
                this.getParent().getLocation().getX();
                n.setTitle("NestedViewer");
                n.setVisible(true);
                n.paint(this.getGraphics());
                n.open();
                /*n.repaint();*/
                this.repaint();
            } else {
                System.out.println("Cannot open the diagram");
            }
        } else if (source.getText().equals("Shape Properties")) {
            if (!(selectedShape instanceof TextBox)) {
                Component c = this.getParent();
                while (!(c instanceof Frame) && c != null) {
                    c = c.getParent();
                }
                ShapePropertyDialog shapeProperty = new ShapePropertyDialog(selectedShape, (Frame) c);

                shapeProperty.setVisible(true);

                float[] fillRGB = shapeProperty.getFillRGB();
                float[] strokeRGB = shapeProperty.getStrokeRGB();
                String pathURL = shapeProperty.getNestedPath();

                shapeProperty.dispose();
                shapeProperty = null;

                db.changeShapeProperties(selectedShape, fillRGB, strokeRGB, pathURL);

                update(getGraphics());
                this.repaint();
            } else {
                //JOptionPane.showMessageDialog(this,"Cannot change properties of a TextBox","Error",JOptionPane.ERROR_MESSAGE);

                String newText = JOptionPane.showInputDialog(this, "Edit text", ((TextBox) selectedShape).getText());

                if (newText != null) {
                    db.changeTextBox((TextBox) selectedShape, newText);
                    update(getGraphics());
                    this.repaint();
                }
            }
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

    public boolean isSaved() {
        return db.isSaved();
    }


}
