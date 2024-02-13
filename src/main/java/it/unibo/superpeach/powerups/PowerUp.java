package it.unibo.superpeach.powerups;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class PowerUp {

    //DEVO AGGIUNGERE LE IMMAGINI
    private double x;
    private double y;
    private boolean isFalling;

    public PowerUp(double x, double y) {
        pUpCoords(x, y);
        this.isFalling = false;
    }

    public void pUpCoords(double x, double y) {
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

    // Metodo per controllare la collisione con il personaggio
    public boolean collidesWith(int characterX, int characterY) {
        return x == characterX && y == characterY;
    }

}
