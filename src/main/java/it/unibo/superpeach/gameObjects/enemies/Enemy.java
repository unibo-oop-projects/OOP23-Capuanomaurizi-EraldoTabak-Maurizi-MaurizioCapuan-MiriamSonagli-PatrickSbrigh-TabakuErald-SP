package it.unibo.superpeach.gameObjects.enemies;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameObjects.GameObject;
import it.unibo.superpeach.gameObjects.blocks.Block;
import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;
import it.unibo.superpeach.gameObjects.blocks.Block.BlockType;
import it.unibo.superpeach.graphics.Texturer;

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

    public Enemy(final int x, final int y, final int width, final int height, final int scale,
            final BlocksHandler blocksHandler) {
        this.x = x * scale;
        this.y = y * scale;
        this.width = width * scale;
        this.height = height * scale;
        this.scale = scale;
        setDimension(width * scale, height * scale);
        this.blocksHandler = blocksHandler;
        this.direction = false;
        this.isAlive = true;
        this.paddingBOUND *= scale;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getScale() {
        return scale;
    }

    public boolean getIsFalling() {
        return this.isFalling;
    }

    public Dimension getDimension() {
        return this.dim;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, dim.width, dim.height);
    }

    public Rectangle getBottomBound() {
        return new Rectangle(getX() + getWidth() / 2 - getWidth() / 4, getY() + (getHeight() - paddingBOUND),
                getWidth() / 2, paddingBOUND);
    }

    public Rectangle getLeftBound() {
        return new Rectangle(getX(), getY() + paddingBOUND, paddingBOUND, getHeight() - 2 * paddingBOUND);
    }

    public Rectangle getRightBound() {
        return new Rectangle(getX() + getWidth() - paddingBOUND, getY() + paddingBOUND, paddingBOUND,
                getHeight() - 2 * paddingBOUND);
    }

    public BufferedImage[] getSprites() {
        return this.sprites;
    }

    public Texturer getTexturer() {
        return texturer;
    }

    public int getWidth() {
        return this.getDimension().width;
    }

    public int getHeight() {
        return this.getDimension().height;
    }

    public BlocksHandler getBlocksHandler() {
        return this.blocksHandler;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean getDirection() {
        return this.direction;
    }

    public Dimension getDim() {
        return dim;
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setIsFalling(final boolean fall) {
        this.isFalling = fall;
    }

    public void setDimension(final int width, final int height) {
        this.dim = new Dimension(width, height);
    }

    public void setSprites(final BufferedImage[] sprites) {
        this.sprites = sprites;
    }

    public void setY(final int y) {
        this.y = y * scale;
    }

    public void setX(final int x) {
        this.x = x * scale;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    public void setDim(final Dimension dim) {
        this.dim = dim;
    }

    public void setFalling(final boolean isFalling) {
        this.isFalling = isFalling;
    }

    public void setBlocksHandler(final BlocksHandler blocksHandler) {
        this.blocksHandler = blocksHandler;
    }

    public void setScale(final int scale) {
        this.scale = scale;
    }

    public void setTexturer(final Texturer texturer) {
        this.texturer = texturer;
    }

    public void changeDirection() {
        this.direction = !this.direction;
    }

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
                    || block.getType() == BlockType.POPPED_LUCKY || block.getType() == BlockType.ALT_BLOCK ||
                    block.getType() == BlockType.LUCKY || block.getType() == BlockType.BRICK) {
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

    public abstract void render(final Graphics g);

    public abstract void tick();

}
