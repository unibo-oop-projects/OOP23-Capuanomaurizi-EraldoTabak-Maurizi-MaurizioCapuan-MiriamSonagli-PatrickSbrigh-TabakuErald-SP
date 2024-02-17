package it.unibo.superpeach.gameobjects.powerups;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameobjects.GameObject;
import it.unibo.superpeach.gameobjects.blocks.Block;
import it.unibo.superpeach.gameobjects.blocks.BlockType;
import it.unibo.superpeach.gameobjects.blocks.BlocksHandler;
import it.unibo.superpeach.graphics.Texturer;

/**
 * Power up class implementation.
 * @author  Miriam Sonaglia
 */
public abstract class PowerUp implements GameObject {
    
    public enum PowerUpType { 
        /** 
         * Power up RED MUSHROOM
         */
        RED_MUSHROOM, 
        /**
         * Power up STAR
         */
        STAR, 
        /**
         * Power up LIFE MUSHROOM
         */
        LIFE_MUSHROOM, 
        /**
         * Power up COIN
         */
        COIN };

    private static final int FALL_SPEED = 1;

    private int x;
    private int y;
    private int width;
    private int height;
    private int scale;
    private int movement;
    private boolean isFalling;
    private boolean isAlive;
    private boolean direction;
    private PowerUpType powerUpType;
    private BlocksHandler blocksHandler;

    private int paddingBound;
    private Texturer texturer = Game.getTexturer();
    private BufferedImage[] image = texturer.getPowerups();

    /**
     * Power ups constructor.
     * @param x
     * @param y
     * @param w
     * @param h
     * @param s
     * @param blocksHandler
     * @param type
     */
    public PowerUp(final int x, final int y, final int w, final int h, final int s, 
                    final BlocksHandler blocksHandler, final PowerUpType type) {
        this.x = x * s;
        this.y = y * s;
        this.width = w * s;
        this.height = h * s;
        this.scale = s;
        this.isFalling = false;
        this.isAlive = true;
        this.direction = false;
        this.powerUpType = type;
        this.blocksHandler = blocksHandler;
        this.paddingBound = 4 * scale;
    }

    @Override
    public abstract void render(Graphics g);

    @Override
    public abstract void tick();

    @Override
    public Texturer getTexturer() {
        return texturer;
    }

    public void setTextures(final Texturer texturer) {
        this.texturer = texturer;
    }

    @Override
    public BufferedImage[] getSprites() {
        return image;
    }

    @Override
    public void setSprites(final BufferedImage[] image) {
        this.image = image;
    }

    @Override
    public void setX(final int x) {
        this.x = x * scale;
    }

    @Override
    public void setY(final int y) {
        this.y = y * scale;
    }

    @Override
    public void setWidth(final int w) {
        this.width = w;
    }

    @Override
    public void setHeight(final int h) {
        this.height = h;
    }

    /**
     * @return power up type between:RED MUSHROOM, LIFE MUSHROOM, STAR and COIN.
     */
    public PowerUpType getPowerUpType() {
        return powerUpType;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getScale() {
        return this.scale;
    }

    @Override
    public void setScale(final int scale) {
        this.scale = scale;
    }

    /**
     * Sets the speed according to the GUI scale selected.
     * @param movement
     */
    public void setMovement(final int movement) {
        this.movement = movement * scale;
    }

    /**
     * @return speed of the power up
     */
    public int getMovement() {
        return this.movement;
    }

    /**
     * Sets if the power up is falling off a block or not.
     * @param fall
     */
    public void setIsFalling(final boolean fall) {
        this.isFalling = fall;
    }

    /**
     * @return if the power up is falling or not.
     */
    public boolean getIsFalling() {
        return this.isFalling;
    }

    /**
     * @return speed of the falling speed of the power up
     */
    public int getFallSpeed() {
       return movement;
    }

    /**
     * @return the direction in which the power up is moving
     */
    public boolean getDirection() {
        return this.direction;
    }

    /**
     * Changes the direction of the power up.
     */
    public void changeDirection() {
        this.direction = !this.direction;
    }

    /**
     * Updates the coordinates of the power up 
     * according to the direction and the state.
     */
    public void updateCoords() {
        if (getDirection()) {
            this.x -= this.movement;
        } else {
            this.x += this.movement;
        }

        if (this.isFalling) {
            this.y += FALL_SPEED * scale;
        }
    }

    /**
     * @return if the power up is alive or not
     */
    public boolean getIsAlive() {
        return this.isAlive;
    }

    /**
     * the power up dies.
     */
    public void die() {
        this.isAlive = false;
    }

    /**
     * @return the coordinates and the dimentions of the power up
     */
    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * @return the botton bound of the power up
     */
    public Rectangle getBottomBound() {
        return new Rectangle(getX() + getWidth() / 2 - getWidth() / 4, 
                getY() + (getHeight() - paddingBound),
                getWidth() / 2, paddingBound);
    }

    /**
     * @return the left bound of the power up
     */
    public Rectangle getLeftBound() {
        return new Rectangle(getX(), getY() + paddingBound, paddingBound, getHeight() - 2 * paddingBound);
    }

    /**
     * @return the right bound of the power up
     */
    public Rectangle getRightBound() {
        return new Rectangle(getX() + getWidth() - paddingBound, getY() + paddingBound, paddingBound, 
                    getHeight() - 2 * paddingBound);
    }

    /**
     * @return the padding bound of the power up
     */
    public int getPaddingBound() {
        return paddingBound;
    }

    /**
     * @return the blocks handler
     */
    public BlocksHandler getBlocksHandler() {
        return blocksHandler;
    }

    /**
     * sets the blocks handler.
     * @param blocksHandler
     */
    public void setBlocksHandler(final BlocksHandler blocksHandler) {
        this.blocksHandler = blocksHandler;
    }

    /**
     * When the power ups collides with something on the bottom bound,
     * it doesn't fall
     * @param block
     */
    private void setYCollisionBottom(final Block block) {
        setY(block.getY() / scale - getHeight() / scale);
        setIsFalling(false);
    }

    /**
     * When the power ups collides with something on the left bound,
     * it changes direction.
     * @param block
     */
    private void setXCollisionLeft(final Block block) {
        setX((block.getX() + block.getWidth()) / getScale());
        changeDirection();
    }

    /**
     * When the power ups collides with something on the right bound,
     * it changes direction.
     * @param block
     */
    private void setXCollisionRight(final Block block) {
        setX((block.getX() - getWidth()) / getScale());
        changeDirection();
    }

    /**
     * It handles the collisions between the power up and the blocks.
     */
    public void collisions() {
        for (Block block : blocksHandler.getBlocks()) {
            if (block.getType() == BlockType.DEATH_BLOCK) {
                if (block.getBoundingBox().intersects(getBottomBound())) {
                    die();
                }
            }
            if (block.getType() == BlockType.PIPE_LEFT || block.getType() == BlockType.PIPE_RIGHT
                    || block.getType() == BlockType.PIPE_TOP_LEFT || block.getType() == BlockType.PIPE_TOP_RIGHT
                    || block.getType() == BlockType.STONE || block.getType() == BlockType.TERRAIN
                    || block.getType() == BlockType.POPPED_LUCKY || block.getType() == BlockType.LUCKY 
                    || block.getType() == BlockType.BRICK) {
                if (block.getBoundingBox().contains(getLeftBound())) {
                    setXCollisionLeft(block);
                } else if (block.getBoundingBox().contains(getRightBound())) {
                    setXCollisionRight(block);
                } else if (block.getBoundingBox().contains(getBottomBound())) {
                    setYCollisionBottom(block);
                } else if (block.getBoundingBox().intersects(getLeftBound())) {
                    setXCollisionLeft(block);
                } else if (block.getBoundingBox().intersects(getRightBound())) {
                    setXCollisionRight(block);
                } else if (block.getBoundingBox().intersects(getBottomBound())) {
                    setYCollisionBottom(block);
                }
            } 
        }
    }

}
