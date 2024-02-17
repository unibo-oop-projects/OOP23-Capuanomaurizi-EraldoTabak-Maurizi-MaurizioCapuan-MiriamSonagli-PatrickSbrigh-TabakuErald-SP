package it.unibo.superpeach.gameObjects.powerups;

import java.awt.*;
import java.awt.image.BufferedImage;
import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameObjects.GameObject;
import it.unibo.superpeach.gameObjects.blocks.Block;
import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;
import it.unibo.superpeach.gameObjects.blocks.Block.BlockType;
import it.unibo.superpeach.graphics.Texturer;

public abstract class PowerUp implements GameObject {

    public enum PowerUpType { RED_MUSHROOM, STAR, LIFE_MUSHROOM, COIN};

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

    private int PADDING_BOUND;
    private Texturer texturer = Game.getTexturer();
    private BufferedImage[] image = texturer.getPowerups();
    
    public PowerUp(int x, int y, int w, int h, int s, BlocksHandler blocksHandler, PowerUpType type) {
        this.x = x*s;
        this.y = y*s;
        this.width = w*s;
        this.height = h*s;
        this.scale = s;
        this.isFalling = false;
        this.isAlive = true;
        this.direction = false;
        this.powerUpType = type;
        this.blocksHandler = blocksHandler;
        this.PADDING_BOUND = 4*scale;
    }

    public abstract void render(Graphics g);

    public abstract void tick();

    public Texturer getTexturer() {
        return texturer;
    }

    public void setTextures(Texturer texturer) {
        this.texturer = texturer;
    }

    public BufferedImage[] getSprites() {
        return image;
    }
    
    public void setSprites(BufferedImage[] image) {
        this.image = image;
    }

    public void setX(int x) {
        this.x = x * scale;
    }

    public void setY(int y) {
        this.y = y * scale;
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public void setHeight(int h) {
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

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getMovement() {
        return this.movement;
    }

    public void setIsFalling(boolean fall) {
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
            this.y += FALL_SPEED;
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
        return new Rectangle(getX() + getWidth() / 2 - getWidth() / 4, getY() + (getHeight() - PADDING_BOUND),
                getWidth() / 2, PADDING_BOUND);
    }

    public Rectangle getLeftBound() {
        return new Rectangle(getX(), getY() + PADDING_BOUND, PADDING_BOUND, getHeight() - 2 * PADDING_BOUND);
    }

    public Rectangle getRightBound() {
        return new Rectangle(getX() + getWidth() - PADDING_BOUND, getY() + PADDING_BOUND, PADDING_BOUND, getHeight() - 2 * PADDING_BOUND);
    }

    public int getPaddingBound() {
        return PADDING_BOUND;
    }

    public BlocksHandler getBlocksHandler() {
        return blocksHandler;
    }

    public void setBlocksHandler(BlocksHandler blocksHandler) {
        this.blocksHandler = blocksHandler;
    }

    private void setYCollisionBottom(Block block) {
        setY(block.getY() / scale - getHeight() / scale);
        setIsFalling(false);
    }

    private void setXCollisionLeft(Block block) {
        setX((block.getX() + block.getWidth()) / getScale());
        changeDirection();
    }

    private void setXCollisionRight(Block block) {
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
                    || block.getType() == BlockType.POPPED_LUCKY || block.getType() == BlockType.LUCKY || block.getType() == BlockType.BRICK) {
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
