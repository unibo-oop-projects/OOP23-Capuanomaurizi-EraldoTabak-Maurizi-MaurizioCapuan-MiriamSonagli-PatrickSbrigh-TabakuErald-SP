package it.unibo.superpeach.blocks;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.blocks.graphics.Texturer;
import it.unibo.superpeach.game.Game;

public abstract class Block {
    public enum BlockType {
        BRICK,
        TERRAIN,
        LUCKY, POPPED_LUCKY,
        PIPE_LEFT, PIPE_RIGHT, PIPE_TOP_LEFT, PIPE_TOP_RIGHT,
        STONE,
        BUSH_LEFT, BUSH_MIDDLE, BUSH_RIGHT,
        CLOUD_TOP_LEFT, CLOUD_TOP_MIDDLE, CLOUD_TOP_RIGHT, CLOUD_BOT_LEFT, CLOUD_BOT_MIDDLE, CLOUD_BOT_RIGHT,
        HILL_UP, HILL_BLANK, HILL_SPOTS1, HILL_SPOTS2, HILL_TOP, HILL_DOWN,
        FLAG_TIP, FLAG_POLE, FLAG_LEFT, FLAG_RIGHT,
        CASTLE_BRICK, CASTLE_BALCONY1, CASTLE_BALCONY2, CASTLE_WINDOW_LEFT, CASTLE_WINDOW_RIGHT, CASTLE_DOOR_BOT, CASTLE_DOOR_TOP,
        DEATH_BLOCK, ALT_BLOCK
    };

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

    public abstract void render(Graphics g);

    public Texturer getTexturer() {
        return texturer;
    }

    public BlockType getType() {
        return type;
    }

    public Rectangle getTopBound(){
        return new Rectangle(x, y, width, 5*scale);
    }

    public Rectangle getBotBound(){
        return new Rectangle(x, y+height, width, 5*scale);
    }

    public Rectangle getLeftBound(){
        return new Rectangle(x, y, 5*scale, height);
    }

    public Rectangle getRightBound(){
        return new Rectangle(x+width, y, 5*scale, height);
    }
    
    public Rectangle getBoundingBox(){
        return new Rectangle(x, y, width, height);
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
