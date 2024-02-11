package it.unibo.superpeach.player;

import java.awt.Graphics;

public class Peach extends Player{
    private static final int SPEED_X = 10;

    public Peach(int x, int y, int width, int height, int scale){
        super(x, y, width, height, scale);
    }

    @Override
    public void moveLeft() {
        setX(getX()-SPEED_X);
    }

    @Override
    public void moveRight() {
        setX(getX()+SPEED_X);
    }

    @Override
    public void jump() {
        
    }

    @Override
    public void render(Graphics g) {
        showRectangle(g);
    }

    @Override
    public void tick() {
        updateRectangle();
    }
    
}
