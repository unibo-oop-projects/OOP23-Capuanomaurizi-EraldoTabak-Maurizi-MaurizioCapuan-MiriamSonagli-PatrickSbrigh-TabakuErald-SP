package it.unibo.superpeach.powerups;

import java.awt.*;

public class Coin extends PowerUp{

    public Coin (int x, int y, int w, int h, int s, int m) {
        super(x, y, w, h, s, m);
        setMovement(0);
        setIsFalling(false);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getImage()[3],getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {}

}
