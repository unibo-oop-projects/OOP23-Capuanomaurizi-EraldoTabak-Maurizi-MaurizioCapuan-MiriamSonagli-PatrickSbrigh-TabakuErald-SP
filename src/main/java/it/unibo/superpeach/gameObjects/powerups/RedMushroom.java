package it.unibo.superpeach.gameObjects.powerups;

import java.awt.Graphics;
import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;

public class RedMushroom extends PowerUp {
    
    public RedMushroom(final int x, final int y, final int w, final int h, final int s, final BlocksHandler blocksHandler) {
        super(x, y, w, h, s, blocksHandler, PowerUpType.RED_MUSHROOM);
        setMovement(1);
        setIsFalling(false);
    }

    @Override
    public void render(final Graphics g) {
        g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        setIsFalling(true);
        collisions();
        updateCoords();
    }

}
