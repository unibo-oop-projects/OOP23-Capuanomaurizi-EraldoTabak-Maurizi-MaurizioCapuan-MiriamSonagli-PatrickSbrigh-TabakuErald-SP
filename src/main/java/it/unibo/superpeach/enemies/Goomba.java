package it.unibo.superpeach.enemies;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Goomba extends Enemy {

    public Goomba(double x, double y, BufferedImage img, float speed, int scale) {
        super(x, y, img, speed, scale);
    }

    @Override
    public void render(Graphics g) {
        sprite(g);
    }

    @Override
    public void tick() {

    }

}
