/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package GUI;

import Shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: justinblakley
 * Date: Apr 25, 2010
 * Time: 1:48:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class ShapePropertyDialog extends JDialog implements ActionListener {
    JPanel content, lowerButton, lower, upperleft, upperright, nestedContent, nestedPanel;
    JColorChooser fillColorChooser, strokeColorChooser;
    JButton ok, cancel, fileChooser;
    JLabel strokeColor, fillColor, path;
    JTextField pathField;
    JTabbedPane tabs;

    Shape shapeToChange;

    protected float[] fillRGB;
    protected float[] strokeRGB;

    public ShapePropertyDialog(Shape s, Frame owner) {
        super(owner, true);
        setTitle("Shape Options");
        shapeToChange = s;

        fillRGB = new float[3];
        strokeRGB = new float[3];

        content = new JPanel(new BorderLayout());
        lowerButton = new JPanel();
        lower = new JPanel(new BorderLayout());
        upperleft = new JPanel(new BorderLayout());
        upperright = new JPanel(new BorderLayout());
        nestedPanel = new JPanel(new BorderLayout());
        nestedContent = new JPanel(new BorderLayout());

        fileChooser = new JButton("Choose Path");
        fileChooser.addActionListener(this);
        path = new JLabel("Path:");
        pathField = new JTextField();
        nestedPanel.add(path, BorderLayout.NORTH);
        nestedPanel.add(pathField, BorderLayout.CENTER);
        nestedPanel.add(fileChooser, BorderLayout.EAST);
        nestedContent.add(nestedPanel, BorderLayout.NORTH);

        fillColorChooser = new JColorChooser();
        fillColorChooser.setColor(new Color(shapeToChange.getFillColors()[0],
                shapeToChange.getFillColors()[1],
                shapeToChange.getFillColors()[2]));
        fillColorChooser.removeChooserPanel(fillColorChooser.getChooserPanels()[0]);
        fillColorChooser.removeChooserPanel(fillColorChooser.getChooserPanels()[0]);

        strokeColorChooser = new JColorChooser();
        strokeColorChooser.setColor(new Color(shapeToChange.getBorderColors()[0],
                shapeToChange.getBorderColors()[1],
                shapeToChange.getBorderColors()[2]));
        strokeColorChooser.removeChooserPanel(strokeColorChooser.getChooserPanels()[0]);
        strokeColorChooser.removeChooserPanel(strokeColorChooser.getChooserPanels()[0]);

        ok = new JButton("Ok");
        ok.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);

        strokeColor = new JLabel("Stroke:");
        fillColor = new JLabel("Fill:");

        lowerButton.add(ok);
        lowerButton.add(cancel);

        lower.add(lowerButton, BorderLayout.LINE_END);

        upperleft.add(strokeColor, BorderLayout.NORTH);
        upperleft.add(strokeColorChooser, BorderLayout.SOUTH);

        upperright.add(fillColor, BorderLayout.NORTH);
        upperright.add(fillColorChooser, BorderLayout.SOUTH);

        tabs = new JTabbedPane();
        tabs.addTab("Fill Color", upperright);
        tabs.addTab("Stroke Color", upperleft);
        tabs.addTab("Nested Diagram", nestedContent);

        content.add(tabs, BorderLayout.PAGE_START);
        content.add(lower, BorderLayout.PAGE_END);

        this.setBounds(200, 200, 500, 455);

        this.setContentPane(content);
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source == ok) {
            float[] a = new float[3];
            float[] b = new float[3];
            fillColorChooser.getColor().getRGBColorComponents(fillRGB);
            strokeColorChooser.getColor().getRGBColorComponents(strokeRGB);

            /*
            Shape changedShape = shapeToChange.clone();
            changedShape.setFillColors(fillRGB);
            changedShape.setBorderColors(strokeRGB);
            changedShape.setNestedDiagramURL(pathField.getText());
            shapeToChange = changedShape;
            */

            System.out.println("" + a[0] + " " + a[1] + " " + a[2]);
            System.out.println("" + b[0] + " " + b[1] + " " + b[2]);
            this.setVisible(false);

        } else if (source == cancel) {
            this.setVisible(false);
        } else if (source == fileChooser) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            //fc.setFileFilter(new SVGFileFilter());
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String path = fc.getSelectedFile().getAbsolutePath();
                pathField.setText(path);
            }
        }
    }

    public float[] getFillRGB() {
        return fillRGB;
    }

    public float[] getStrokeRGB() {
        return strokeRGB;
    }

    public String getNestedPath() {
        return pathField.getText();
    }
}
