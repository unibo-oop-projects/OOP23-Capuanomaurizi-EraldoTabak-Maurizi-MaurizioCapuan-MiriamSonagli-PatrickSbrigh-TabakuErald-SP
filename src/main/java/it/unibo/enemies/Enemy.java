package main.java.it.unibo.enemies;

import java.awt.*;

public abstract class Enemy {

    private double x;
    private double y;
    private Dimension dim;
    private double gravity;
    private boolean isFalling;

    public Enemy(double x, double y) {
        setCoords(x, y);
        this.isFalling = false;
    }

    public void nextMove() {

    }

    public setCoords(double x, double y){
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
}