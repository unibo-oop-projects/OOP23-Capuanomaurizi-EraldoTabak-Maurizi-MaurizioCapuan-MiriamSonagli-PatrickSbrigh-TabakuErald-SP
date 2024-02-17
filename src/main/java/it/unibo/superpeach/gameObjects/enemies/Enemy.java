package it.unibo.superpeach.gameObjects.enemies;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameObjects.GameObject;
import it.unibo.superpeach.gameObjects.blocks.Block;
import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;
import it.unibo.superpeach.gameObjects.blocks.BlockType;
import it.unibo.superpeach.graphics.Texturer;

/**
 * Enemies abstract class.
 * 
 * @author Eraldo Tabaku
 */

public abstract class Enemy implements GameObject {

    private static final int FALL_SPEED = 1;
    private int paddingBOUND = 4;

    private int x;
    private int y;
    private int speed;
    private int scale;
    private int width;
    private int height;
    private Dimension dim;
    private boolean isFalling, direction, isAlive;
    private BlocksHandler blocksHandler;
    private Texturer texturer = Game.getTexturer();
    private BufferedImage[] sprites;

    /**
     * @param x             coordinate of the enemy.
     * @param y             coordinate of the enemy.
     * @param width         of the enemy in game.
     * @param height        of the enemy in game.
     * @param scale         of the game.
     * @param blocksHandler the blockHandler used by the enemy.
     */
    public Enemy(final int x, final int y, final int width, final int height, final int scale,
            final BlocksHandler blocksHandler) {
        this.x = x * scale;
        this.y = y * scale;
        this.width = width * scale;
        this.height = height * scale;
        this.scale = scale;
        this.width = width * scale;
        this.height = height * scale;
        this.blocksHandler = blocksHandler;
        this.direction = false;
        this.isAlive = true;
        this.paddingBOUND *= scale;
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
    public int getScale() {
        return scale;
    }

    /**
     * @return a boolean for the enemy falling or not status
     */
    public boolean getIsFalling() {
        return this.isFalling;
    }

    /**
     * this method returns the bottom bound rectangle of the enemy.
     * 
     * @return
     */
    public Rectangle getBottomBound() {
        return new Rectangle(getX() + getWidth() / 2 - getWidth() / 4, getY() + (getHeight() - paddingBOUND),
                getWidth() / 2, paddingBOUND);
    }

    /**
     * this method returns the left bound rectangle of the enemy.
     * 
     * @return
     */
    public Rectangle getLeftBound() {
        return new Rectangle(getX(), getY() + paddingBOUND, paddingBOUND, getHeight() - 2 * paddingBOUND);
    }

    /**
     * this method returns the right bound of the enemy.
     * 
     * @return
     */
    public Rectangle getRightBound() {
        return new Rectangle(getX() + getWidth() - paddingBOUND, getY() + paddingBOUND, paddingBOUND,
                getHeight() - 2 * paddingBOUND);
    }

    @Override
    public BufferedImage[] getSprites() {
        return this.sprites;
    }

    @Override
    public Texturer getTexturer() {
        return texturer;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     * @return BlockHandler used by the enemy.
     */
    public BlocksHandler getBlocksHandler() {
        return this.blocksHandler;
    }

    /**
     * @return enemy's speed.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @return enemy's direction.
     */
    public boolean getDirection() {
        return this.direction;
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * @return boolean indicating enemies isAlive status.
     */
    public boolean getIsAlive() {
        return this.isAlive;
    }

    /**
     * @param fall to change enemy's status from falling to not falling.
     */
    public void setIsFalling(final boolean fall) {
        this.isFalling = fall;
    }

    @Override
    public void setSprites(final BufferedImage[] sprites) {
        this.sprites = sprites;
    }

    @Override
    public void setY(final int y) {
        this.y = y * scale;
    }

    @Override
    public void setX(final int x) {
        this.x = x * scale;
    }

    @Override
    public void setWidth(final int width) {
        this.width = width;
    }

    @Override
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * @param speed the speed to give to the enemy.
     */
    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    /**
     * @param isFalling to change enemy's falling status.
     */
    public void setFalling(final boolean isFalling) {
        this.isFalling = isFalling;
    }

    /**
     * @param blocksHandler to set the BlockHandler used by the enemy.
     */
    public void setBlocksHandler(final BlocksHandler blocksHandler) {
        this.blocksHandler = blocksHandler;
    }

    /**
     * sets the scale of the enemy based on the game scale.
     * 
     * @param scale used by the game.
     */
    public void setScale(final int scale) {
        this.scale = scale;
    }

    /**
     * @param texturer used by the enemy for the sprite creation.
     */
    public void setTexturer(final Texturer texturer) {
        this.texturer = texturer;
    }

    /**
     * method used to change enemy's direction.
     */
    public void changeDirection() {
        this.direction = !this.direction;
    }

    /**
     * this method puts the enemy in the dead status.
     */
    public void die() {
        this.isAlive = false;
    }

    private void setYCollisionBottom(final Block block) {
        setY((block.getY() - getHeight()) / getScale());
        setFalling(false);
    }

    private void setXCollisionLeft(final Block block) {
        setX((block.getX() + block.getWidth()) / getScale());
        changeDirection();
    }

    private void setXCollisionRight(final Block block) {
        setX((block.getX() - getWidth()) / getScale());
        changeDirection();
    }

    /**
     * this method changes the enemy coordinates.
     */
    protected void updateCoords() {
        if (getDirection()) {
            this.x -= this.speed;
        } else {
            this.x += this.speed;
        }

        if (this.isFalling) {
            this.y += FALL_SPEED;
        }
    }

    /**
     * The collision method checks what type of block has contact with the enemies
     * in order to define their behavior.
     */
    public void collision() {
        for (Block block : blocksHandler.getBlocks()) {
            if (block.getType() == BlockType.DEATH_BLOCK) {
                if (block.getBoundingBox().intersects(getBottomBound())) {
                    die();
                }
            }
            if (block.getType() == BlockType.PIPE_LEFT || block.getType() == BlockType.PIPE_RIGHT
                    || block.getType() == BlockType.PIPE_TOP_LEFT || block.getType() == BlockType.PIPE_TOP_RIGHT
                    || block.getType() == BlockType.STONE || block.getType() == BlockType.TERRAIN
                    || block.getType() == BlockType.POPPED_LUCKY || block.getType() == BlockType.ALT_BLOCK
                    || block.getType() == BlockType.LUCKY || block.getType() == BlockType.BRICK) {
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

    /**
     * abstract method for image rendering.
     * 
     * @param g Graphics object used for the rendering.
     */
    public abstract void render(Graphics g);

    /**
     * abstract method for the enemy tick.
     */
    public abstract void tick();

}
