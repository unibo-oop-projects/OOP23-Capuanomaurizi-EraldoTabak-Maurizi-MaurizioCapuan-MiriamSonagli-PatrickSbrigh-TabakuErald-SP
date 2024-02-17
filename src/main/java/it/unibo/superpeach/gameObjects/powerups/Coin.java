package it.unibo.superpeach.gameObjects.powerups;

import java.awt.Graphics;
import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;

public class Coin extends PowerUp {

    public Coin(final int x, final int y, final int w, final int h, final int s, final BlocksHandler blocksHandler) {
        super(x, y, w, h, s, blocksHandler, PowerUpType.COIN);
        setMovement(0);
        setIsFalling(false);
    }

    @Override
    public void render(final Graphics g) {
        g.drawImage(getSprites()[3], getX(), getY(), getWidth(), getHeight(), null); 
    }

    @Override
    public void tick() { }

}
