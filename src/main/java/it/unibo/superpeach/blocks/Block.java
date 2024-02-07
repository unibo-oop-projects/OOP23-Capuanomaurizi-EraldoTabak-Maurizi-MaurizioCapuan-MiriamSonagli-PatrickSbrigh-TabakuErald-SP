package it.unibo.superpeach.blocks;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.blocks.graphics.Texturer;
import it.unibo.superpeach.game.Game;

public abstract class Block {
    public enum BlockType {LUCKY, BRICK, TERRAIN};

    private double x;
    private double y;
    private double width;
    private double height;
    private int scale;

    private Texturer texturer = Game.getBlocksTexturer();
    private BufferedImage[] sprites;

    private BlockType type;

    public Block(double x, double y, double w, double h, int s){
        this.x = x*s;
        this.y = y*s;
        this.width = w*s;
        this.height = h*s;
        this.scale = s;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public Texturer getTexturer() {
        return texturer;
    }

    public BlockType getType() {
        return type;
    }

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

    public BufferedImage[] getSprites() {
        return sprites;
    }

    public void setSprites(BufferedImage[] sprites) {
        this.sprites = sprites;
    }

    public void setType(BlockType type) {
        this.type = type;
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
