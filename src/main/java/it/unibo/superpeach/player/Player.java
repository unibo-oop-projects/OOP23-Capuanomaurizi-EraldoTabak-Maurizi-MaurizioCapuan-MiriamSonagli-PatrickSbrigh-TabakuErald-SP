package it.unibo.superpeach.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.player.graphics.PlayerTexture;

public abstract class Player {
    private int width;
    private int height;
    private int x;
    private int y;
    private int scale;
    private Rectangle rectangle;
    private boolean jumped;
    private PlayerTexture texture;
    private BufferedImage[] sprite;

    public Player(int x, int y, int width, int height, int scale){
        this.width = width*scale;
        this.height = height*scale;
        this.x = x*scale;
        this.y = y*scale;
        this.scale = scale;
        this.jumped = false;
        this.rectangle = new Rectangle(this.x, this.y, this.width, this.height);
        this.texture = Game.getPlayerTexturer();
        this.sprite = texture.getPlayerImage();
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

    public BufferedImage[] getSprites(){
        return this.sprite;
    }

    public PlayerTexture getPlayerTexture(){
        return this.texture;
    }

    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void jump();
    public abstract void fall();
    public abstract void render(Graphics g);
    public abstract void tick();
}
