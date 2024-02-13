package it.unibo.superpeach.enemies;

import java.awt.*;
import java.awt.image.BufferedImage;

public class KoopaTroopa extends Enemy {

    public KoopaTroopa(double x, double y, BufferedImage img, float speed) {
        super(x, y, img, speed);
    }

    @Override
    public void render(Graphics g) {
        sprite(g);
    }

    @Override
    public void tick() {

    }

}
