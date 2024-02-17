package it.unibo.superpeach.gameObjects.blocks;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameObjects.GameObject;
import it.unibo.superpeach.graphics.Texturer;

/**
 * World block.
 * @author  Maurizio Capuano
 */
public abstract class Block implements GameObject {

    private int x;
    private int y;
    private int width;
    private int height;
    private int scale;

    private Texturer texturer = Game.getTexturer();
    private BufferedImage[] sprites;

    private BlockType type;

    /**
     * Constructor method for a Block object.
     * 
     * @param x x position of the block on the tile
     * @param y y position of the block on the tile
     * @param w block width
     * @param h block height
     * @param s game scale for enlarging the item if needed
     */
    public Block(final int x, final int y, final int w, final int h, final int s) {
        this.x = x * s;
        this.y = y * s;
        this.width = w * s;
        this.height = h * s;
        this.scale = s;
    }

    public Texturer getTexturer() {
        return texturer;
    }

    public Rectangle getBoundingBox() {
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

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public void setScale(final int scale) {
        this.scale = scale;
    }

    public void setSprites(final BufferedImage[] sprites) {
        this.sprites = sprites;
    }

    public void setType(final BlockType type) {
        this.type = type;
    }

}
