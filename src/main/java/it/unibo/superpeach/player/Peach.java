package it.unibo.superpeach.player;

import java.awt.Graphics;

public class Peach extends Player{
    private static final int SPEED_X = 10;
    private static final int JUMP = 50;

    public Peach(int x, int y, int width, int height, int scale){
        super(x, y, width, height, scale);
    }

    @Override
    public void moveLeft() {
        setX((getX()/getScale())-SPEED_X);
    }

    @Override
    public void moveRight() {
        setX((getX()/getScale())+SPEED_X);
    }

    @Override
    public void jump() {
        setY((getY()/getScale())-JUMP);
        setHasJumped(true);
    }
    
    @Override
    public void fall() {
        setY((getY()/getScale())+JUMP);
        setHasJumped(false);
    }

    @Override
    public void render(Graphics g) {
        showRectangle(g);

        g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        updateRectangle();
    }
}
