package it.unibo.superpeach.gameObjects.powerups;

import java.awt.*;

import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;

public class LifeMushroom extends PowerUp {

    public LifeMushroom(final int x, final int y, final int w, final int h, final int s, final BlocksHandler blocksHandler) {
        super(x, y, w, h, s, blocksHandler, PowerUpType.LIFE_MUSHROOM);
        setMovement(2);
        setIsFalling(true);
    }

    @Override
    public void render(final Graphics g) {
        g.drawImage(getSprites()[1],getX(), getY(), getWidth(), getHeight(), null); 
    }

    @Override
    public void tick() {
        setIsFalling(true);
        collisions();
        updateCoords();
    }   
}
