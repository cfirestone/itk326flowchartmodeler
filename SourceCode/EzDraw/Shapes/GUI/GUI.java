package GUI;


/**
 * Created by IntelliJ IDEA.
 * User: Carl
 * Date: Feb 17, 2010
 * Time: 4:07:55 PM
 * To change this template use File | Settings | File Templates.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GUI extends JFrame implements ActionListener, ItemListener {
    private JMenuBar menuBar = new JMenuBar(); // Window menu bar
    private JMenuItem newItem, openItem, closeItem, saveItem, saveAsItem, printItem, exitItem,
            lineItem, rectItem, circleItem, textboxItem, selectItem, clearItem;
    private Canvas canvas;

    public GUI() {
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenu toolMenu = new JMenu("Tool");
        newItem = fileMenu.add("New");
        openItem = fileMenu.add("Open");
        closeItem = fileMenu.add("Close");
        fileMenu.addSeparator();
        saveItem = fileMenu.add("Save");
        saveAsItem = fileMenu.add("Save As...");
        fileMenu.addSeparator();
        printItem = fileMenu.add("Print");
        fileMenu.addSeparator();
        exitItem = fileMenu.add("Exit");
        toolMenu.add(lineItem = new JMenuItem("Draw Line"));
        toolMenu.add(rectItem = new JMenuItem("Draw Rectangle"));
        toolMenu.add(circleItem = new JMenuItem("Draw Circle"));
        toolMenu.add(textboxItem = new JMenuItem("Draw Textbox"));
        toolMenu.add(selectItem = new JMenuItem("Select Tool"));
        toolMenu.add(clearItem = new JMenuItem("Clear All"));
        menuBar.add(fileMenu);
        menuBar.add(toolMenu);

        canvas = new Canvas();
        this.add(canvas);

        lineItem.addActionListener(this);
        rectItem.addActionListener(this);
        circleItem.addActionListener(this);
        textboxItem.addActionListener(this);
        selectItem.addActionListener(this);
        clearItem.addActionListener(this);

    }

    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        System.out.println(source.getText());

    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        if (source.getText().equals("Draw Line")) {
            canvas.setCurrentTool(ToolType.DRAW_LINE);
        } else if (source.getText().equals("Draw Textbox")) {
            canvas.setCurrentTool(ToolType.DRAW_TEXTBOX);
        } else if (source.getText().equals("Draw Circle")) {
            canvas.setCurrentTool(ToolType.DRAW_CIRCLE);
        } else if (source.getText().equals("Draw Rectangle")) {
            canvas.setCurrentTool(ToolType.DRAW_RECTANGLE);
        } else if (source.getText().equals("Select Tool")) {
            canvas.setCurrentTool(ToolType.SELECT);
        } else if (source.getText().equals("Clear All")) {
            canvas.clearAllShapes();
            canvas.repaint();
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public static void main(String[] a) {
        GUI window = new GUI();
        window.setBounds(30, 30, 300, 300); // Size
        window.setTitle("EzDraw 0.1");
        window.setVisible(true);
    }
}

