package it.unibo.superpeach.gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import it.unibo.superpeach.graphics.Texturer;

public interface GameObject {

    void render(Graphics g);

    void tick();

    Texturer getTexturer();

    Rectangle getBoundingBox();

    int getX();

    int getY();

    int getHeight();

    int getWidth();

    int getScale();

    BufferedImage[] getSprites();

    void setX(int x);

    void setY(int y);

    void setHeight(int height);

    void setWidth(int width);

    void setScale(int scale);

    void setSprites(BufferedImage[] sprites);
}
