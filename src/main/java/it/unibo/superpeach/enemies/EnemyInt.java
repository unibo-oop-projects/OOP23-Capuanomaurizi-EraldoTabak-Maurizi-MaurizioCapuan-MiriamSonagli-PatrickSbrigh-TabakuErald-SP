package it.unibo.superpeach.enemies;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public interface EnemyInt {

    void setCoords(double x, double y);

    void setX(double x);

    void setY(double y);

    double getX();

    double getY();

    boolean getIsFalling();

    void setIsFalling(boolean fall);

    void updateCoords();

    Dimension getDimension();

    void setDimension(int width, int height);

    BufferedImage getImg();

    void setImg(BufferedImage img);

    void sprite(Graphics graphic);

    Rectangle getBounds();

    Rectangle getTopBound();

    Rectangle getBottomBound();

    Rectangle getRightBound();

    Rectangle getLeftBound();

    void render(Graphics g);

    void tick();

}