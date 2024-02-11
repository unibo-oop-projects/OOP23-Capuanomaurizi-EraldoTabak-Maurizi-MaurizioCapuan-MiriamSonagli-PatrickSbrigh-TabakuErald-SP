package it.unibo.superpeach.enemies;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Enemy {

    private double x;
    private double y;
    private Dimension dim;
    private double speed;
    private boolean isFalling;
    private BufferedImage img;

    public Enemy(double x, double y, BufferedImage img) {
        setCoords(x, y);
        this.isFalling = false;
        setDimension(img.getWidth(), img.getHeight());
    }

    public void setCoords(double x, double y) {
        setX(x);
        setY(y);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
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

    public BufferedImage getImg() {
        return this.img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public void sprite(Graphics graphic) {
        BufferedImage im = getImg();
        graphic.drawImage(im, (int) x, (int) y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, dim.width, dim.height);
    }

    // completare i metodi bound
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

    // tenere a mente che potrebbe servire implementare metodi getter e setter per
    // la velocità "speed"

}