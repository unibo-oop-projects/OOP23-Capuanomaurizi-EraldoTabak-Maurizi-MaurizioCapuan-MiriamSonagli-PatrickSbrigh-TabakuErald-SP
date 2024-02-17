package it.unibo.superpeach.gameObjects.powerups;

import java.awt.Graphics;

import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;

public class Star extends PowerUp {

    public Star(final int x, final int y, final int w, final int h, final int s, final BlocksHandler blocksHandler) {
        super(x, y, w, h, s, blocksHandler, PowerUpType.STAR);
        setMovement(2);
        setIsFalling(false);
    }

    @Override
    public void render(final Graphics g) {
        g.drawImage(getSprites()[2], getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        setIsFalling(true);
        collisions();
        updateCoords();
    }
}
