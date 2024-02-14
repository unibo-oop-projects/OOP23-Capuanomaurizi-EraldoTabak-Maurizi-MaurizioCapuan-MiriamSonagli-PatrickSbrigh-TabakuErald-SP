package it.unibo.superpeach.enemies;

import java.awt.*;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.blocks.Block;
import it.unibo.superpeach.blocks.Block.BlockType;
import it.unibo.superpeach.blocks.BlocksHandler;

public abstract class Enemy implements EnemyInt {

    private double x;
    private double y;
    private Dimension dim;
    private double speed;
    private boolean isFalling;
    private BufferedImage img;
    private int scale;

    public Enemy(double x, double y, BufferedImage img, float speed, int scale) {
        setCoords(scale * x, scale * y);
        this.isFalling = false;
        setDimension(img.getWidth(), img.getHeight());
        this.speed = speed;
    }

    @Override
    public void setCoords(double x, double y) {
        setX(x);
        setY(y);
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public boolean getIsFalling() {
        return this.isFalling;
    }

    @Override
    public void setIsFalling(boolean fall) {
        this.isFalling = fall;
    }

    @Override
    public void updateCoords() {
        this.x += this.speed;

        if (this.isFalling) {
            this.y += this.speed;
        }
    }

    @Override
    public Dimension getDimension() {
        return this.dim;
    }

    @Override
    public void setDimension(int width, int height) {
        this.dim = new Dimension(width, height);
    }

    @Override
    public BufferedImage getImg() {
        return this.img;
    }

    @Override
    public void setImg(BufferedImage img) {
        this.img = img;
    }

    @Override
    public void sprite(Graphics graphic) {
        BufferedImage im = getImg();
        graphic.drawImage(im, (int) x, (int) y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, dim.width, dim.height);
    }

    @Override
    public Rectangle getTopBound() {
        return new Rectangle((int) x + dim.width, (int) y, dim.width, dim.height);
    }

    @Override
    public Rectangle getBottomBound() {
        return new Rectangle((int) x + dim.width, (int) y, dim.width, dim.height);
    }

    @Override
    public Rectangle getRightBound() {
        return new Rectangle((int) x, (int) y + dim.height, dim.width, dim.height);
    }

    @Override
    public Rectangle getLeftBound() {
        return new Rectangle((int) x, (int) y + dim.height, dim.width, dim.height);
    }

    @Override
    public abstract void render(Graphics g);

    @Override
    public abstract void tick();

    // tenere a mente che potrebbe servire implementare metodi getter e setter per
    // la velocità "speed"

}