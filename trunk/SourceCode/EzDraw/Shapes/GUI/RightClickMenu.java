/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * User: Carl
 * Date: Mar 15, 2010
 * Time: 11:42:42 AM
 */
class RightClickMenu extends JPopupMenu implements ActionListener, ItemListener {
    JMenuItem menuItem;

    public RightClickMenu() {
        menuItem = new JMenuItem("Delete");
        menuItem.addActionListener(this);
        add(menuItem);
        menuItem = new JMenuItem("Shape Properties");
        menuItem.addActionListener(this);
        add(menuItem);
    }

    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        System.out.println(source.getText());

    }

    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) (e.getSource());
        if (source.getText().equals("Delete")) {
        }

    }
}

