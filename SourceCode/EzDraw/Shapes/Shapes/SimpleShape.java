/*
 * Copyright (c) 2010. Justin Blakley and Carl Firestone
 */

package Shapes;

/**
 * Designed for abstracting the simple shapes
 *
 * @author Justin Blakley & Carl Firestone
 * @version 1.0.0.0
 */
public abstract class SimpleShape extends Shape {
    /**
     * Constructor for Simple Shape
     * @param p1 The starting point of the shape
     * @param p2 The ending point of the shape
     */
    public SimpleShape(Point p1, Point p2) {
        super(p1, p2);
    }
}
