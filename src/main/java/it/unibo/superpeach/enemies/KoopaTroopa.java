package it.unibo.superpeach.enemies;

import java.awt.*;

import it.unibo.superpeach.blocks.BlocksHandler;

public class KoopaTroopa extends Enemy {

    public KoopaTroopa(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler) {
        super(x, y, width, height, scale, blocksHandler);
        setSprites(getTexturer().getKoopaImg());
        setSpeed(2 * scale);
        setFalling(true);
    }

    @Override
    protected void render(Graphics g) {
        if (getDirection()) {
            g.drawImage(getSprites()[0], getX(), getY(), getDimension().width, getDimension().height,
                    null);
        } else {
            g.drawImage(getSprites()[1], getX(), getY(), getDimension().width, getDimension().height,
                    null);
        }
    }

    @Override
    protected void tick() {
        setFalling(true);
        this.collision();
        this.updateCoords();
    }
}
