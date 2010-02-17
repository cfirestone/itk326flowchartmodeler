package GUI;


/**
 * Created by IntelliJ IDEA.
 * User: Carl
 * Date: Feb 17, 2010
 * Time: 4:07:55 PM
 * To change this template use File | Settings | File Templates.
 */

import javax.swing.*;

public class GUI extends JFrame {
    private JMenuBar menuBar = new JMenuBar(); // Window menu bar
    private JMenuItem newItem, openItem, closeItem, saveItem, saveAsItem, printItem, exitItem, lineItem, rectItem, circleItem, textboxItem;

    public GUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenu drawMenu = new JMenu("Draw");
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
        drawMenu.add(lineItem = new JMenuItem("Line"));
        drawMenu.add(rectItem = new JMenuItem("Rectangle"));
        drawMenu.add(circleItem = new JMenuItem("Circle"));
        drawMenu.add(textboxItem = new JMenuItem("Textbox"));
        menuBar.add(fileMenu);
        menuBar.add(drawMenu);
    }

    public static void main(String[] a) {
        GUI window = new GUI();
        window.setBounds(30, 30, 300, 300); // Size
        window.setVisible(true);
    }
}
