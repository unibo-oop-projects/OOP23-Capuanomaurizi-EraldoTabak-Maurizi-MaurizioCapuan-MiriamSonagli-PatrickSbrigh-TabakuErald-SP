package it.unibo.superpeach.powerups;

import java.awt.Graphics;

public class Star extends PowerUp{

    public Star (int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getImage()[2],getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {

    }
    
}
