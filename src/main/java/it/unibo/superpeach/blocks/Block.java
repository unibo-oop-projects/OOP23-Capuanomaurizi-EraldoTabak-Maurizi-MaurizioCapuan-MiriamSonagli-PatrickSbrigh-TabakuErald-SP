package it.unibo.superpeach.blocks;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.blocks.graphics.Texturer;
import it.unibo.superpeach.game.Game;

public abstract class Block {
    public enum BlockType {BRICK, TERRAIN, LUCKY, PIPE, BUSH, CLOUD, HILL};

    private int x;
    private int y;
    private int width;
    private int height;
    private int scale;

    private Texturer texturer = Game.getBlocksTexturer();
    private BufferedImage[] sprites;

    private BlockType type;

    public Block(int x, int y, int w, int h, int s){
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
        return new Rectangle(x, y, width, 0);
    }

    public Rectangle getBotBound(){
        return new Rectangle(x, y+height, width, 0);
    }

    public Rectangle getLeftBound(){
        return new Rectangle(x, y, 0, height);
    }

    public Rectangle getRightBound(){
        return new Rectangle(x+width, y, 0, height);
    }

    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }

    public int getScale() {
        return scale;
    }

    public int getX() {
        return x;
    }

    public int getY() {
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

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}   
