package it.unibo.superpeach.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Block {
    private float x;
    private float y;
    private float width;
    private float height;
    private int scale;

    public Block(float x, float y, float w, float h, int s){
        this.x = x*scale;
        this.y = y*scale;
        this.width = width*scale;
        this.height = height*scale;
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
        g2d.setColor(Color.red);
        g2d.draw(getBotBound());
        g2d.draw(getTopBound());
        g2d.draw(getRightBound());
        g2d.draw(getLeftBound());
    }

    public float getHeight() {
        return height;
    }
    
    public float getWidth() {
        return width;
    }

    public int getScale() {
        return scale;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    
}   
