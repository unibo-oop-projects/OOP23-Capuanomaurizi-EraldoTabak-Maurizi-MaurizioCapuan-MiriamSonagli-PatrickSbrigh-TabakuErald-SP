package it.unibo.superpeach.enemies;

import java.awt.*;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.blocks.Block;
import it.unibo.superpeach.blocks.Block.BlockType;
import it.unibo.superpeach.blocks.BlocksHandler;
import it.unibo.superpeach.enemies.graphics.TexturerEnemies;
import it.unibo.superpeach.game.Game;

public abstract class Enemy {

    private static final int FALL_SPEED = 1;
    private int PADDING_BOUND = 4;

    private int x;
    private int y;
    private Dimension dim;
    private int speed;
    private boolean isFalling;
    private BlocksHandler blocksHandler;
    private int scale;

    private TexturerEnemies texturer = Game.getEnemyTexturer();
    private BufferedImage[] sprites;

    private boolean direction;
    private boolean isAlive;

    public Enemy(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler) {
        this.x = x * scale;
        this.y = y * scale;
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

    public void setIsFalling(boolean fall) {
        this.isFalling = fall;
    }

    public void updateCoords() {
        if (getDirection()) {
            this.x -= this.speed;
        } else {
            this.x += this.speed;
        }

        if (this.isFalling) {
            this.y += FALL_SPEED;
        }
    }

    public Dimension getDimension() {
        return this.dim;
    }

    public void setDimension(int width, int height) {
        this.dim = new Dimension(width, height);
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

    public void setSprites(BufferedImage[] sprites) {
        this.sprites = sprites;
    }

    public TexturerEnemies getTexturer() {
        return texturer;
    }

    public abstract void render(Graphics g);

    public abstract void tick();

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
                    || block.getType() == BlockType.POPPED_LUCKY) {
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
            } else if (block.getType() == BlockType.LUCKY || block.getType() == BlockType.BRICK) {
            } else if (block.getBoundingBox().contains(getBottomBound())) {
                setYCollisionBottom(block);
            } else if (block.getBoundingBox().contains(getLeftBound())) {
                setXCollisionLeft(block);
            } else if (block.getBoundingBox().contains(getRightBound())) {
                setXCollisionRight(block);
            } else if (block.getBoundingBox().intersects(getBottomBound())) {
                setYCollisionBottom(block);
            } else if (block.getBoundingBox().intersects(getLeftBound())) {
                setXCollisionLeft(block);
            } else if (block.getBoundingBox().intersects(getRightBound())) {
                setXCollisionRight(block);
            }

        }
    }

    private void setY(int y) {
        this.y = y * scale;
    }

    private void setX(int x) {
        this.x = x * scale;
    }

    private int getWidth() {
        return this.getDimension().width;
    }

    private int getHeight() {
        return this.getDimension().height;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public static int getFallSpeed() {
        return FALL_SPEED;
    }

    public int getPaddingBound() {
        return PADDING_BOUND;
    }

    public Dimension getDim() {
        return dim;
    }

    public void setDim(Dimension dim) {
        this.dim = dim;
    }

    public int getSpeed() {
        return speed;
    }

    public void setFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }

    public BlocksHandler getBlocksHandler() {
        return blocksHandler;
    }

    public void setBlocksHandler(BlocksHandler blocksHandler) {
        this.blocksHandler = blocksHandler;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setTexturer(TexturerEnemies texturer) {
        this.texturer = texturer;
    }

    public boolean getDirection() {
        return this.direction;
    }

    public void changeDirection() {
        this.direction = !this.direction;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void die() {
        this.isAlive = false;
    }

    private void setYCollisionBottom(Block block) {
        setY(block.getY() / scale - getHeight() / scale);
        setFalling(false);
    }

    private void setXCollisionLeft(Block block) {
        setX((block.getX() + block.getWidth()) / getScale());
        changeDirection();
    }

    private void setXCollisionRight(Block block) {
        setX(block.getX() / block.getScale() - getWidth() / getScale());
        changeDirection();
    }

}