package it.unibo.superpeach.enemies;

import java.awt.*;

public class Goomba extends Enemy {

    public Goomba(int x, int y, int width, int height, int scale) {
        super(x, y, width, height, scale);
        setSprites(getTexturer().getGoombaImg());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getSprites()[0], (int) getX(), (int) getY(), getDimension().width, getDimension().height,
                null);
    }

    @Override
    public void tick() {

    }

}
