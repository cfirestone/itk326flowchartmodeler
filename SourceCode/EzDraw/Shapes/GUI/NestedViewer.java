/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: justinblakley
 * Date: Apr 9, 2010
 * Time: 11:19:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class NestedViewer extends JFrame {
    private Canvas canvas;
    private String url;

    public NestedViewer(String url) {
        canvas = new NestViewerCanvas();
        this.setLayout(new BorderLayout());
        this.url = url;

        this.add(canvas);
    }

    public void open() {
        try{
            canvas.open(url);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Unable to load shape data");
        }
    }
}
