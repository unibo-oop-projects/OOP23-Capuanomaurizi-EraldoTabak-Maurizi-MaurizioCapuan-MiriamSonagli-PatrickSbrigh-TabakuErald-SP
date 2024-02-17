package it.unibo.superpeach.gameObjects.powerups;

import java.awt.Graphics;
import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;

/**
 * Implementation of the STAR power up.
 * @author  Miriam Sonaglia
 */
public class Star extends PowerUp {

    /**
     * Powerup STAR
     * @param x
     * @param y
     * @param w
     * @param h
     * @param s
     * @param blocksHandler
     */
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
