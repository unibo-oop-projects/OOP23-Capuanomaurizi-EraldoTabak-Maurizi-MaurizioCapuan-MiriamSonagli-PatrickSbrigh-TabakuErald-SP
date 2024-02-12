package it.unibo.superpeach.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import it.unibo.superpeach.blocks.BlocksHandler;

public abstract class Player {
    private int width;
    private int height;
    private int x;
    private int y;
    private int scale;
    private Rectangle rectangle;
    private boolean jumped;
    private BlocksHandler blocksHandler;

    public Player(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler){
        this.width = width*scale;
        this.height = height*scale;
        this.x = x*scale;
        this.y = y*scale;
        this.scale = scale;
        this.jumped = false;
        this.rectangle = new Rectangle(this.x, this.y, this.width, this.height);
        this.blocksHandler = blocksHandler;
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

    public int getScale(){
        return this.scale;
    }

    public boolean hasJumped(){
        return this.jumped;
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

    public void setHasJumped(boolean jumped){
        this.jumped = jumped;
    }

    public void updateRectangle(){
        this.rectangle = new Rectangle(x, y, width, height);
    }

    public void showRectangle(Graphics g){
        Graphics2D graph = (Graphics2D)g;
        graph.setColor(Color.BLACK);
        graph.draw(rectangle);
    }

    public Rectangle getTopBound(){
        return new Rectangle(getX(), getY(), getWidth(), 0);
    }

    public Rectangle getBottomBound(){
        return new Rectangle(getX(), getY()+getHeight(), getWidth(), 0);
    }

    public Rectangle getLeftBound(){
        return new Rectangle(getX(), getY(), 0, getHeight());
    }

    public Rectangle getRightBound(){
        return new Rectangle(getX()+getWidth(), getY(), 0 ,getHeight());
    }

    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void jump();
    public abstract void fall();
    public abstract void render(Graphics g);
    public abstract void tick();
}
