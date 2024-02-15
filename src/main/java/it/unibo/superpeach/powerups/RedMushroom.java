package it.unibo.superpeach.powerups;

import java.awt.*;

public class RedMushroom extends PowerUp {
    
    public RedMushroom (int x, int y, int w, int h, int s, int m) {
        super(x, y, w, h, s, m);
        setMovement(1);
        setIsFalling(false);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getImage()[0],getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        updateCoords();
        collisions();
    }

}
