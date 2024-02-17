package it.unibo.superpeach.gameObjects.powerups;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameObjects.GameObject;
import it.unibo.superpeach.gameObjects.blocks.Block;
import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;
import it.unibo.superpeach.gameObjects.blocks.Block.BlockType;
import it.unibo.superpeach.graphics.Texturer;

public abstract class PowerUp implements GameObject {

    public enum PowerUpType { RED_MUSHROOM, STAR, LIFE_MUSHROOM, COIN };

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

    public abstract void render(Graphics g);

    public abstract void tick();

    public Texturer getTexturer() {
        return texturer;
    }

    public void setTextures(final Texturer texturer) {
        this.texturer = texturer;
    }

    public BufferedImage[] getSprites() {
        return image;
    }

    public void setSprites(final BufferedImage[] image) {
        this.image = image;
    }

    public void setX(final int x) {
        this.x = x * scale;
    }

    public void setY(final int y) {
        this.y = y * scale;
    }

    public void setWidth(final int w) {
        this.width = w;
    }

    public void setHeight(final int h) {
        this.height = h;
    }

    public PowerUpType getPowerUpType() {
        return powerUpType;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getScale() {
        return this.scale;
    }

    public void setScale(final int scale) {
        this.scale = scale;
    }

    public void setMovement(final int movement) {
        this.movement = movement * scale;
    }

    public int getMovement() {
        return this.movement;
    }

    public void setIsFalling(final boolean fall) {
        this.isFalling = fall;
    }

    public boolean getIsFalling() {
        return this.isFalling;
    }

    public int getFallSpeed() {
       return movement;
    }

    public boolean getDirection() {
        return this.direction;
    }

    public void changeDirection() {
        this.direction = !this.direction;
    }

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

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void die() {
        this.isAlive = false;
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
    }

    public Rectangle getBottomBound() {
        return new Rectangle(getX() + getWidth() / 2 - getWidth() / 4, 
                getY() + (getHeight() - paddingBound),
                getWidth() / 2, paddingBound);
    }

    public Rectangle getLeftBound() {
        return new Rectangle(getX(), getY() + paddingBound, paddingBound, getHeight() - 2 * paddingBound);
    }

    public Rectangle getRightBound() {
        return new Rectangle(getX() + getWidth() - paddingBound, getY() + paddingBound, paddingBound, 
                    getHeight() - 2 * paddingBound);
    }

    public int getPaddingBound() {
        return paddingBound;
    }

    public BlocksHandler getBlocksHandler() {
        return blocksHandler;
    }

    public void setBlocksHandler(final BlocksHandler blocksHandler) {
        this.blocksHandler = blocksHandler;
    }

    private void setYCollisionBottom(final Block block) {
        setY(block.getY() / scale - getHeight() / scale);
        setIsFalling(false);
    }

    private void setXCollisionLeft(final Block block) {
        setX((block.getX() + block.getWidth()) / getScale());
        changeDirection();
    }

    private void setXCollisionRight(final Block block) {
        setX((block.getX() - getWidth()) / getScale());
        changeDirection();
    }

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
