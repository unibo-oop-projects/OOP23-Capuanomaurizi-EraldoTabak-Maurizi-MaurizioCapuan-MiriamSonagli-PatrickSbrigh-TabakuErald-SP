package it.unibo.superpeach.blocks;

import java.awt.Graphics;
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
    public abstract Rectangle getBoundingBox();

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
