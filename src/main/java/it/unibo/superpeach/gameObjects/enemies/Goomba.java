package it.unibo.superpeach.gameObjects.enemies;

import java.awt.Graphics;

import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;

public class Goomba extends Enemy {

    public Goomba(final int x, final int y, final int width, final int height, final int scale,
            final BlocksHandler blocksHandler) {
        super(x, y, width, height, scale, blocksHandler);
        setSpeed(1 * scale);
        setSprites(getTexturer().getGoombaImg());
        setIsFalling(false);
    }

    @Override
    public void render(final Graphics g) {
        g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        setFalling(true);
        this.collision();
        this.updateCoords();
    }

}
