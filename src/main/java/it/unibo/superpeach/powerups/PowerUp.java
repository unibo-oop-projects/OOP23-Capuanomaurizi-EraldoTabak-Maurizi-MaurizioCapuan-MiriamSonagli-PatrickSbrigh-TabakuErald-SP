package it.unibo.superpeach.powerups;

import java.awt.*;
import java.awt.image.BufferedImage;
import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.powerups.graphics.Textures;


public abstract class PowerUp {

    public enum PowerUpType { RED_MUSHROOM, STAR, LIFE_MUSHROOM, COIN};

    private int x;
    private int y;
    private int width;
    private int height;
    private int scale;
    private double movement;
    private boolean isFalling;
    private Textures textures = Game.getPowerupsTextures();
    private BufferedImage[] image = textures.getPowerups();

    
    public PowerUp(int x, int y, int w, int h, int s) {
        this.x = x*s;
        this.y = y*s;
        this.width = w*s;
        this.height = h*s;
        this.scale = s;
        this.isFalling = false;
    }

    public abstract void render(Graphics g);

    public abstract void tick();

    public BufferedImage[] getImage() {
        return image;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public void setHeight(int h) {
        this.height = h;
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
        return scale;
    }

    public void setMovement(double movement) {
        this.movement = movement;
    }

    public double getMovement() {
        return this.movement;
    }

    public void setIsFalling(boolean fall) {
        this.isFalling = fall;
    }
    
    public boolean getIsFalling() {
        return this.isFalling;
    }

    public void movesRight(int movement){
        this.setX(this.getX() + movement);
    }

    public void movesLeft(int movement) {
        this.setX(this.getX() - movement);
    }

    public void fallingDown() {
        if (this.isFalling) {
            this.y += this.movement;
        }
    }

    // Metodo per controllare la collisione con il personaggio
    public boolean collidesWith(int characterX, int characterY) {
        return x == characterX && y == characterY;
    }

}
