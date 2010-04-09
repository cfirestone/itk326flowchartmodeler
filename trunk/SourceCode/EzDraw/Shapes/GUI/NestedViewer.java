/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package GUI;

import Logic.DrawingBoardJava2D;

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

    public NestedViewer(String url){
        canvas = new Canvas();
        this.setLayout(new BorderLayout());
        this.url = url;

        this.add(canvas);
    }
    public void open(){
        canvas.open(url);
    }
}
