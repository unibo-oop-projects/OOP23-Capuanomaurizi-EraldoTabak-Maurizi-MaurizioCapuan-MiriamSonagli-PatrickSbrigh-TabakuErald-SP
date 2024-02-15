package it.unibo.superpeach.powerups;

import java.awt.*;

public class LifeMushroom extends PowerUp{

    public LifeMushroom (int x, int y, int w, int h, int s, int m) {
        super(x, y, w, h, s, m);
        setMovement(2);
        setIsFalling(false);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getImage()[1],getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        updateCoords();
        collisions();
    }
    
}
