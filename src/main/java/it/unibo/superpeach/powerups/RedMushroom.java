package it.unibo.superpeach.powerups;

import java.awt.*;

public class RedMushroom extends PowerUp {
    
    public RedMushroom (int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getImage()[0],getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {

    }

}
