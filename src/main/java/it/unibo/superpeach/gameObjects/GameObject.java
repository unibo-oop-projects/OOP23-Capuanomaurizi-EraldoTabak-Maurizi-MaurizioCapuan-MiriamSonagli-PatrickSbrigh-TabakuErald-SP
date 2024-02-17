package it.unibo.superpeach.gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.graphics.Texturer;

public interface GameObject {

    public abstract void render(Graphics g);

    public abstract void tick();

    public Texturer getTexturer();

    public Rectangle getBoundingBox();

    public int getX();

    public int getY();

    public int getHeight();
    
    public int getWidth();

    public int getScale();

    public BufferedImage[] getSprites();

    public void setX(int x);

    public void setY(int y);

    public void setHeight(int height);

    public void setWidth(int width);

    public void setScale(int scale);

    public void setSprites(BufferedImage[] sprites);
}
