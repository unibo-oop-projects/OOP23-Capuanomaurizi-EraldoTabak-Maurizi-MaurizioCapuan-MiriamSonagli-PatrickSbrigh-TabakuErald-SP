package it.unibo.superpeach.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Player {
    private int width;
    private int height;
    private int x;
    private int y;
    private int scale;
    private Rectangle rectangle;

    public Player(int x, int y, int width, int height, int scale){
        this.width = width*scale;
        this.height = height*scale;
        this.x = x*scale;
        this.y = y*scale;
        this.scale = scale;
        this.rectangle = new Rectangle(this.x, this.y, this.width, this.height);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public Rectangle getRectangle(){
        return this.rectangle;
    }

    public void setX(int x){
        this.x = x*this.scale;
    }

    public void setY(int y){
        this.y = y*this.scale;
    }

    public void setScale(int scale){
        this.scale = scale;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void updateRectangle(){
        this.rectangle = new Rectangle(x, y, width, height);
    }

    public void showRectangle(Graphics g){
        Graphics2D graph = (Graphics2D)g;
        graph.draw(rectangle);
        graph.setColor(Color.BLACK);
    }

    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void jump();
    public abstract void render(Graphics g);
    public abstract void tick();
}
