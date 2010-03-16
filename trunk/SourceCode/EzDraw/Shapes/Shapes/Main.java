/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Shapes;

import java.util.LinkedList;

/**
 * Write a description of class Main here.
 *
 * @author (yourname)
 * @version (aversionnumberoradate)
 */
public class Main {
    public static void main(String args[]) {
        Shape s = new Rectangle(new Point(5, 5), new Point(55, 105));
        LinkedList<Point> pts = s.getListOfPoints();
        for (int i = 0; i < pts.size(); i++) {
            System.out.println(pts.get(i).toString());
        }
        /*Shape a = new Circle();
        a.setCoordinates(200, 50);
        a.setDimensions(70, 70);
        a.draw();
        a.drawAttachmentSections();

        System.out.println("");
        System.out.println("Grouping the rectangle and circle:");
        System.out.println("");

        Shape b = new CompositeShape();
        ((CompositeShape) b).addShape(s);
        ((CompositeShape) b).addShape(a);
        b.draw();
        b.drawAttachmentSections();

        Shape ab = new TextBox();
        ab.setCoordinates(300, 70);
        ab.setDimensions(90, 90);
        ((TextBox) ab).setText("This is a text box");
        ab.draw();

        System.out.println("");
        System.out.println("Grouping the composite shape and text box:");
        System.out.println("");

        Shape d = new CompositeShape();
        ((CompositeShape) d).addShape(b);
        ((CompositeShape) d).addShape(ab);
        d.draw();
        d.drawAttachmentSections();

        Shape l = new Line();
        l.setCoordinates(1, 1);
        l.setDimensions(2, 2);
        l.draw();
        l.drawAttachmentSections();  */
    }
}
