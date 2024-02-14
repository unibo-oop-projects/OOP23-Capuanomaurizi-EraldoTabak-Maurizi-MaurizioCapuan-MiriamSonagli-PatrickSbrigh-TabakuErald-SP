package it.unibo.superpeach.enemies;

import java.awt.*;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.blocks.Block;
import it.unibo.superpeach.blocks.Block.BlockType;
import it.unibo.superpeach.blocks.BlocksHandler;
import it.unibo.superpeach.enemies.graphics.TexturerEnemies;
import it.unibo.superpeach.game.Game;

public abstract class Enemy {

    private static final int FALL_SPEED = 3;
    private static final int PADDING_BOUND = 8;

    private int x;
    private int y;
    private Dimension dim;
    private int speed;
    protected boolean isFalling;
    private BlocksHandler blocksHandler;
    private int scale;

    private TexturerEnemies texturer = Game.getEnemyTexturer();
    private BufferedImage[] sprites;

    int moveY;

    public Enemy(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler) {
        this.x = x * scale;
        this.y = y * scale;
        this.isFalling = false;
        this.scale = scale;
        setDimension(width * scale, height * scale);
        this.blocksHandler = blocksHandler;
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
        this.x += this.speed;

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

    public Rectangle getTopBound() {
        return new Rectangle(getX() + getWidth() / 2 - getWidth() / 4, getY(), getWidth() / 2, PADDING_BOUND);
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
        for (int i = 0; i < blocksHandler.getBlocks().size(); i++) {
            Block block = blocksHandler.getBlocks().get(i);
            Rectangle rec = new Rectangle(block.getX(), block.getY(), getWidth(), getHeight());
            if (block.getType() == BlockType.PIPE_LEFT || block.getType() == BlockType.PIPE_RIGHT
                    || block.getType() == BlockType.PIPE_TOP_LEFT || block.getType() == BlockType.PIPE_TOP_RIGHT
                    || block.getType() == BlockType.STONE || block.getType() == BlockType.TERRAIN) {
                if (rec.intersects(getLeftBound())) {
                    setX(block.getX() / block.getScale() + block.getWidth() / block.getScale());
                } else if (rec.intersects(getRightBound())) {
                    setX(block.getX() / block.getScale() - getWidth() / getScale());
                } else if (rec.intersects(getBottomBound())) {
                    setY(block.getY() / block.getScale() - getHeight() / getScale());
                    moveY = 0;
                } else if (rec.intersects(getTopBound())) {
                    setY(block.getY() / block.getScale() + block.getHeight() / block.getScale());
                    moveY = 0;
                }
            } else if (block.getType() == BlockType.LUCKY || block.getType() == BlockType.BRICK) {
                if (rec.intersects(getBottomBound())) {
                    setY(block.getY() / block.getScale() - getHeight() / getScale());
                    moveY = 0;
                } else if (rec.intersects(getTopBound())) {
                    setY(block.getY() / block.getScale() + block.getHeight() / block.getScale());
                    moveY = FALL_SPEED;
                    // chiama metodo per blocchi
                } else if (rec.intersects(getLeftBound())) {
                    setX(block.getX() / block.getScale() + block.getWidth() / block.getScale());
                } else if (rec.intersects(getRightBound())) {
                    setX(block.getX() / block.getScale() - getWidth() / getScale());
                }
            }
        }
    }

    private void setY(int y) {
        this.y = y;
    }

    private void setX(int x) {
        this.x = x;
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

}