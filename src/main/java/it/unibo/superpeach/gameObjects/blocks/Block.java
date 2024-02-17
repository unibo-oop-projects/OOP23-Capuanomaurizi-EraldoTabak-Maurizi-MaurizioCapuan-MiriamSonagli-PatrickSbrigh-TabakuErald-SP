package it.unibo.superpeach.gameObjects.blocks;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameObjects.GameObject;
import it.unibo.superpeach.graphics.Texturer;

public abstract class Block implements GameObject{
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

    private Texturer texturer = Game.getTexturer();
    private BufferedImage[] sprites;

    private BlockType type;

    public Block(int x, int y, int w, int h, int s){
        this.x = x*s;
        this.y = y*s;
        this.width = w*s;
        this.height = h*s;
        this.scale = s;
    }

    public Texturer getTexturer() {
        return texturer;
    }

    public Rectangle getBoundingBox(){
        return new Rectangle(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public BufferedImage[] getSprites() {
        return sprites;
    }

    public BlockType getType() {
        return type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    public void setSprites(BufferedImage[] sprites) {
        this.sprites = sprites;
    }

    public void setType(BlockType type) {
        this.type = type;
    }
    
}   
