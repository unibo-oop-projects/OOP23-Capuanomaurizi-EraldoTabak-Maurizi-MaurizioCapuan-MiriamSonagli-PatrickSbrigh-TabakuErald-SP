package it.unibo.superpeach.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Block {
    private double x;
    private double y;
    private double width;
    private double height;
    private int scale;

    public Block(double x, double y, double w, double h, int s){
        this.x = x*s;
        this.y = y*s;
        this.width = w*s;
        this.height = h*s;
        this.scale = s;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public Rectangle getTopBound(){
        return new Rectangle((int)x, (int)y, (int)width, 0);
    }

    public Rectangle getBotBound(){
        return new Rectangle((int)x, (int)(y+height), (int)width, 0);
    }

    public Rectangle getLeftBound(){
        return new Rectangle((int)x, (int)y, 0, (int)height);
    }

    public Rectangle getRightBound(){
        return new Rectangle((int)(x+width), (int)y, 0, (int)height);
    }

    public void showBorders(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.black);
        g2d.draw(getTopBound());
        g2d.draw(getBotBound());
        g2d.draw(getLeftBound());
        g2d.draw(getRightBound());
    }

    public double getHeight() {
        return height;
    }
    
    public double getWidth() {
        return width;
    }

    public int getScale() {
        return scale;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
}   
