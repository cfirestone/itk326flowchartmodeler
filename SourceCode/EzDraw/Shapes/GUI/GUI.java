/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package GUI;


/**
 * Created by IntelliJ IDEA.
 * User: Carl
 * Date: Feb 17, 2010
 * Time: 4:07:55 PM
 * To change this template use File | Settings | File Templates.
 */

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GUI extends JFrame implements ActionListener, ItemListener, KeyListener {
    private Canvas canvas;
    private String lastSavedPath;
    private boolean saveEnabled = false;

    public GUI() {
        JMenuBar menuBar = new JMenuBar();
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenu toolMenu = new JMenu("Tool");
        JMenu stateMenu = new JMenu("State");
        JMenuItem newItem = fileMenu.add("New");
        JMenuItem openItem = fileMenu.add("Open");
        fileMenu.addSeparator();
        JMenuItem saveItem = fileMenu.add("Save");
        saveItem.setEnabled(false);
        JMenuItem saveAsItem = fileMenu.add("Save As");
        fileMenu.addSeparator();
        JMenuItem exitItem = fileMenu.add("Exit");
        JMenuItem lineItem;
        toolMenu.add(lineItem = new JMenuItem("Draw Line"));
        JMenuItem rectItem;
        toolMenu.add(rectItem = new JMenuItem("Draw Rectangle"));
        JMenuItem circleItem;
        toolMenu.add(circleItem = new JMenuItem("Draw Circle"));
        JMenuItem textboxItem;
        toolMenu.add(textboxItem = new JMenuItem("Draw Textbox"));
        JMenuItem selectItem;
        toolMenu.add(selectItem = new JMenuItem("Select Tool"));
        JMenuItem clearItem;
        toolMenu.add(clearItem = new JMenuItem("Clear All"));

        JMenuItem undoItem, redoItem;
        stateMenu.add(undoItem = new JMenuItem("Undo"));
        stateMenu.add(redoItem = new JMenuItem("Redo"));

        menuBar.add(fileMenu);
        menuBar.add(toolMenu);
        menuBar.add(stateMenu);

        canvas = new Canvas();
        this.add(canvas);
        this.addKeyListener(this);

        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        saveAsItem.addActionListener(this);
        exitItem.addActionListener(this);

        lineItem.addActionListener(this);
        rectItem.addActionListener(this);
        circleItem.addActionListener(this);
        textboxItem.addActionListener(this);
        selectItem.addActionListener(this);
        clearItem.addActionListener(this);

        undoItem.addActionListener(this);
        redoItem.addActionListener(this);

    }

    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        System.out.println(source.getText());

    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        if (source.getText().equals("New")) {
            if (!canvas.isSaved()) {
                int n = JOptionPane.showConfirmDialog(null, "Do you want to save?", "Unsaved work", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    if (saveEnabled)
                        saveCurrent(lastSavedPath);
                    else
                        saveCurrentAs();
                }
            }
            Canvas tempCanvas = new Canvas();
            tempCanvas.paintComponent(canvas.getGraphics());
            tempCanvas.setCurrentTool(canvas.getCurrentTool());
            this.remove(canvas);
            this.add(tempCanvas);
            this.validate();
            canvas = tempCanvas;

            setSaveEnabled(false);
        } else if (source.getText().equals("Open")) {
            if (!canvas.isSaved()) {
                int n = JOptionPane.showConfirmDialog(null, "Do you want to save?", "Unsaved work", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    if (saveEnabled)
                        saveCurrent(lastSavedPath);
                    else
                        saveCurrentAs();
                }
            }
            open();
            setSaveEnabled(true);
        } else if (source.getText().equals("Save As")) {
            saveCurrentAs();
            setSaveEnabled(true);
        } else if (source.getText().equals("Save")) {
            saveCurrent(lastSavedPath);
        } else if (source.getText().equals("Exit")) {
            processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (source.getText().equals("Draw Line")) {
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
        } else if (source.getText().equals("Redo")) {
            if (!canvas.goForward()) {
                showMessageBox("Error", "Cannot Redo.");
            }
        } else if (source.getText().equals("Undo")) {
            if (!canvas.goBackward()) {
                showMessageBox("Error", "Cannot Undo.");
            }
        }
    }

    private void setSaveEnabled(boolean enabled) {
        JMenuBar menuBar = this.getJMenuBar();
        JMenu menu = null;
        Component[] c = menuBar.getComponents();
        for (int i = 0; menu == null; i++) {
            if (c[i] instanceof JMenu) {
                menu = (JMenu) c[i];
            }
        }
        c = menu.getMenuComponents();
        for (int i = 0; i < c.length; i++) {
            if (c[i] instanceof JMenuItem) {
                if (((JMenuItem) c[i]).getText().equals("Save")) {
                    ((JMenuItem) c[i]).setEnabled(enabled);
                    break;
                }
            }
        }
    }

    void menuSelected(MouseEvent e) {
        String blah = "";
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void showMessageBox(String title, String message) {
        JOptionPane pane = new JOptionPane(message);
        JDialog dialog = pane.createDialog(new JFrame(), title);
        dialog.setVisible(true);
        dialog = null;
        pane = null;
    }

    private void saveCurrentAs() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setFileFilter(new SVGFileFilter());
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = lastSavedPath = fc.getSelectedFile().getAbsolutePath() + ".svg";
            saveCurrent(path);
        }
    }

    private void saveCurrent(String path) {
        try {
            canvas.save(path);
        }
        catch (Exception e) {
            this.showMessageBox("Data Error", "Cannot save data");
        }
    }

    private void open() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setFileFilter(new SVGFileFilter());
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = lastSavedPath = fc.getSelectedFile().getAbsolutePath();
            try {
                canvas.open(path);
            }
            catch (Exception e) {
                this.showMessageBox("Data Error", "Cannot read data");
            }
            System.out.println("Invoked open: " + path);
        }
    }

    public static void main(String[] a) {
        GUI window = new GUI();
        window.setBounds(30, 30, 500, 500); // Size
        window.setTitle("EzDraw 0.1");
        window.setVisible(true);
    }

    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            if (!canvas.isSaved()) {
                int n = JOptionPane.showConfirmDialog(null, "Do you want to save?", "Unsaved work", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    if (saveEnabled)
                        saveCurrentAs();
                }
            }
            System.exit(0);
        }
    }

    public void keyTyped(KeyEvent e) {
        if (e.getModifiers() == KeyEvent.CTRL_MASK) {
            char key = e.getKeyChar();
            if (key == '\u001A') { //z
                if (!canvas.goBackward()) {
                    showMessageBox("Error", "Cannot Undo.");
                }
            }
            if (key == '\u0019') { // y
                if (!canvas.goForward()) {
                    showMessageBox("Error", "Cannot Redo.");
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
    }

    protected class SVGFileFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            boolean accepted = false;
            String path = f.getAbsolutePath();
            int dotLoc = path.lastIndexOf('.');

            if (dotLoc < 0 && f.isDirectory()) {
                accepted = true;
            } else {
                String ext = path.substring(path.lastIndexOf('.'));
                if (ext.equalsIgnoreCase(".svg")) {
                    accepted = true;
                }
                if (ext.equalsIgnoreCase(".xml")) {
                    accepted = true;
                }
            }
            return accepted;
        }

        @Override
        public String getDescription() {
            return "SVG/XML Files (*.svg,*.xml)";
        }
    }
}

