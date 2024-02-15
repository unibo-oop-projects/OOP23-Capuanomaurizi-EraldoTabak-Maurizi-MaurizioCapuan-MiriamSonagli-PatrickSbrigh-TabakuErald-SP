package it.unibo.superpeach.powerups;

import java.awt.*;

import it.unibo.superpeach.blocks.BlocksHandler;

public class RedMushroom extends PowerUp {
    
    public RedMushroom (int x, int y, int w, int h, int s, BlocksHandler blocksHandler) {
        super(x, y, w, h, s, blocksHandler);
        setMovement(1);
        setIsFalling(false);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getImage()[0],getX(), getY(), getWidth(), getHeight(), null);
    }

    @Override
    public void tick() {
        setIsFalling(true);
        collisions();
        updateCoords();
    }

}
