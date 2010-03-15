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

    protected DrawingBoard db;
    protected Shapes.Point startPoint, endPoint, currPoint;
    protected boolean mouseDown;
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
        if ((e.getModifiers() & InputEvent.BUTTON3_MASK)
                == InputEvent.BUTTON3_MASK) {
            System.out.println("Right Click Fired");

            Shapes.Shape selectedShape = ((DrawingBoardJava2D) db).getShape(new Shapes.Point(e.getX(), e.getY()));
            if (selectedShape != null) {
                System.out.println("Bring up Context Menu for " + selectedShape.getClass().getName() +
                        " at location: (" + selectedShape.getStartPoint().getX() + "," +
                        selectedShape.getStartPoint().getY() + ")");
                popup.show(e.getComponent(), e.getX(), e.getY());
            } else {
                System.out.println("No shape at location");
            }

        }

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
        if (mouseDown && ((e.getModifiers() & InputEvent.BUTTON1_MASK)
                == InputEvent.BUTTON1_MASK)) {
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
            this.paintAll(this.getGraphics());
        }
        mouseDown = false;
    }

    public void mousePressed(MouseEvent e) {
        if ((e.getModifiers() & InputEvent.BUTTON1_MASK)
                == InputEvent.BUTTON1_MASK) {
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

    public DrawingBoard getDb() {
        return db;
    }

    public void setDb(DrawingBoard db) {
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
}
