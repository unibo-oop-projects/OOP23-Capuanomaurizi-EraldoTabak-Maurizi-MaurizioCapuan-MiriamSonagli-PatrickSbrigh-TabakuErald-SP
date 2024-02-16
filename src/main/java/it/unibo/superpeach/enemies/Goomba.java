package it.unibo.superpeach.enemies;

import java.awt.*;

import it.unibo.superpeach.blocks.BlocksHandler;

public class Goomba extends Enemy {

    public Goomba(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler) {
        super(x, y, width, height, scale, blocksHandler);
        setSpeed(1 * scale);
        setSprites(getTexturer().getGoombaImg());
        setIsFalling(false);
    }

    @Override
    protected void render(Graphics g) {
        g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    protected void tick() {
        setFalling(true);
        this.collision();
        this.updateCoords();
    }

}
