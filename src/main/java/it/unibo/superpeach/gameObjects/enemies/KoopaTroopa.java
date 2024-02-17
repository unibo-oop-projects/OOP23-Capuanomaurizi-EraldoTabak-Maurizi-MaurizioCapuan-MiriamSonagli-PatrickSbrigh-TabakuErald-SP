package it.unibo.superpeach.gameObjects.enemies;

import java.awt.Graphics;

import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;

/**
 * Enemy Koopa Class.
 * 
 * @author Eraldo Tabaku
 */
public class KoopaTroopa extends Enemy {

    public KoopaTroopa(final int x, final int y, final int width, final int height, final int scale,
            final BlocksHandler blocksHandler) {
        super(x, y, width, height, scale, blocksHandler);
        setSprites(getTexturer().getKoopaImg());
        setSpeed(2 * scale);
        setFalling(true);
    }

    @Override
    public void render(final Graphics g) {
        if (getDirection()) {
            g.drawImage(getSprites()[0], getX(), getY(), getDimension().width, getDimension().height,
                    null);
        } else {
            g.drawImage(getSprites()[1], getX(), getY(), getDimension().width, getDimension().height,
                    null);
        }
    }

    @Override
    public void tick() {
        setFalling(true);
        this.collision();
        this.updateCoords();
    }
}
