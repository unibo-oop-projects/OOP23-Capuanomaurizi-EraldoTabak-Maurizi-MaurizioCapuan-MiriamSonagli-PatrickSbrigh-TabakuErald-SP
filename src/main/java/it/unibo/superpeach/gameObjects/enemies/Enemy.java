package it.unibo.superpeach.gameObjects.enemies;

import java.awt.*;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameObjects.GameObject;
import it.unibo.superpeach.gameObjects.blocks.Block;
import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;
import it.unibo.superpeach.gameObjects.blocks.Block.BlockType;
import it.unibo.superpeach.graphics.Texturer;

public abstract class Enemy implements GameObject{

    private static final int FALL_SPEED = 1;
    private int PADDING_BOUND = 4;

    protected int x, y, speed, scale, width, height;
    private Dimension dim;
    private boolean isFalling, direction, isAlive;
    private BlocksHandler blocksHandler;
    private Texturer texturer = Game.getTexturer();
    private BufferedImage[] sprites;

    public Enemy(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler) {
        this.x = x * scale;
        this.y = y * scale;
        this.width = width*scale;
        this.height = height*scale;
        this.scale = scale;
        setDimension(width * scale, height * scale);
        this.blocksHandler = blocksHandler;
        this.direction = false;
        this.isAlive = true;
        this.PADDING_BOUND *= scale;
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
        return new Rectangle(getX() + getWidth() / 2 - getWidth() / 4, getY() + (getHeight() - PADDING_BOUND),
                getWidth() / 2, PADDING_BOUND);
    }

    public Rectangle getLeftBound() {
        return new Rectangle(getX(), getY() + PADDING_BOUND, PADDING_BOUND, getHeight() - 2 * PADDING_BOUND);
    }

    public Rectangle getRightBound() {
        return new Rectangle(getX() + getWidth() - PADDING_BOUND, getY() + PADDING_BOUND, PADDING_BOUND,
                getHeight() - 2 * PADDING_BOUND);
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

    public static int getFallSpeed() {
        return FALL_SPEED;
    }

    public int getPaddingBound() {
        return PADDING_BOUND;
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

    public Rectangle getBoundingBox(){
        return new Rectangle(x, y, width, height);
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setIsFalling(boolean fall) {
        this.isFalling = fall;
    }

    public void setDimension(int width, int height) {
        this.dim = new Dimension(width, height);
    }

    public void setSprites(BufferedImage[] sprites) {
        this.sprites = sprites;
    }

    public void setY(int y) {
        this.y = y * scale;
    }

    public void setX(int x) {
        this.x = x * scale;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDim(Dimension dim) {
        this.dim = dim;
    }

    public void setFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }

    public void setBlocksHandler(BlocksHandler blocksHandler) {
        this.blocksHandler = blocksHandler;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setTexturer(Texturer texturer) {
        this.texturer = texturer;
    }

    public void changeDirection() {
        this.direction = !this.direction;
    }

    public void die() {
        this.isAlive = false;
    }

    private void setYCollisionBottom(Block block) {
        setY((block.getY() - getHeight()) / getScale());
        setFalling(false);
    }

    private void setXCollisionLeft(Block block) {
        setX((block.getX() + block.getWidth()) / getScale());
        changeDirection();
    }

    private void setXCollisionRight(Block block) {
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

    public abstract void render(Graphics g);

    public abstract void tick();

}