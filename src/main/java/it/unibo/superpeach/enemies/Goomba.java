package it.unibo.superpeach.enemies;

import java.awt.*;

import it.unibo.superpeach.blocks.BlocksHandler;

public class Goomba extends Enemy {

    public Goomba(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler) {
        super(x, y, width, height, scale, blocksHandler);
        setSpeed(1);
        setSprites(getTexturer().getGoombaImg());

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getSprites()[0], getX(), getY(), getDimension().width, getDimension().height,
                null);
    }

    @Override
    public void tick() {
        this.updateCoords();
        this.collision();
        // System.err.println(getX());
    }

}
