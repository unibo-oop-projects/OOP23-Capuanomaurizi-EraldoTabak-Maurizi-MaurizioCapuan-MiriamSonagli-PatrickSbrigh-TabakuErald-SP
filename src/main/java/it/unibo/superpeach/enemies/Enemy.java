package it.unibo.superpeach.enemies;

import java.awt.*;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.blocks.BlocksHandler;
import it.unibo.superpeach.enemies.graphics.TexturerEnemies;
import it.unibo.superpeach.game.Game;

public abstract class Enemy {

    private static final int FALL_SPEED = 3;

    private int x;
    private int y;
    private Dimension dim;
    private int speed;
    private boolean isFalling;
    private BlocksHandler blocksHandler;
    private int scale;

    private TexturerEnemies texturer = Game.getEnemyTexturer();
    private BufferedImage[] sprites;

    public Enemy(int x, int y, int width, int height, int scale) {
        this.x = x * scale;
        this.y = y * scale;
        this.isFalling = false;
        this.scale = scale;
        setDimension(width * scale, height * scale);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
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
            this.y += this.speed;
        }
    }

    public Dimension getDimension() {
        return this.dim;
    }

    public void setDimension(int width, int height) {
        this.dim = new Dimension(width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, dim.width, dim.height);
    }

    public Rectangle getTopBound() {
        return new Rectangle((int) x + dim.width, (int) y, dim.width, dim.height);
    }

    public Rectangle getBottomBound() {
        return new Rectangle((int) x + dim.width, (int) y, dim.width, dim.height);
    }

    public Rectangle getRightBound() {
        return new Rectangle((int) x, (int) y + dim.height, dim.width, dim.height);
    }

    public Rectangle getLeftBound() {
        return new Rectangle((int) x, (int) y + dim.height, dim.width, dim.height);
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

    // tenere a mente che potrebbe servire implementare metodi getter e setter per
    // la velocità "speed"

}