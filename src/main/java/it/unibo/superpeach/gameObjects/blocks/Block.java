package it.unibo.superpeach.gameobjects.blocks;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameobjects.GameObject;
import it.unibo.superpeach.graphics.Texturer;

/**
 * World block.
 * 
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

    @Override
    public Texturer getTexturer() {
        return texturer;
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getScale() {
        return scale;
    }

    @Override
    public BufferedImage[] getSprites() {
        return sprites;
    }

    /**
     * @return block type
     */
    public BlockType getType() {
        return type;
    }

    @Override
    public void setX(final int x) {
        this.x = x;
    }

    @Override
    public void setY(final int y) {
        this.y = y;
    }

    @Override
    public void setHeight(final int height) {
        this.height = height;
    }

    @Override
    public void setWidth(final int width) {
        this.width = width;
    }

    @Override
    public void setScale(final int scale) {
        this.scale = scale;
    }

    @Override
    public void setSprites(final BufferedImage[] sprites) {
        this.sprites = sprites;
    }

    /**
     * @param type new block type
     */
    public void setType(final BlockType type) {
        this.type = type;
    }

}
