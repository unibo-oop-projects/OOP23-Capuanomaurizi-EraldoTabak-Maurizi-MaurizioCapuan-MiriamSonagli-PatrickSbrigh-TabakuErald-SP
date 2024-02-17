package it.unibo.superpeach.gameObjects.enemies;

import java.awt.Graphics;

import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;

/**
 * Flying Koopa class.
 * 
 * @author Eraldo Tabaku
 */
public class FlyingKoopa extends Enemy {

    private static final int NUMBER_OF_MOVES = 50;

    private int nMoves;

    public FlyingKoopa(final int x, final int y, final int width, final int height, final int scale,
            final BlocksHandler blocksHandler) {
        super(x, y, width, height, scale, blocksHandler);
        setSprites(getTexturer().getFlyingKoopa());
        setSpeed(scale);
        setFalling(false);
        this.nMoves = 0;
    }

    /**
     * this method updates the flying koopa coordinates.
     */
    @Override
    protected void updateCoords() {
        if (this.nMoves < NUMBER_OF_MOVES) {
            if (getDirection()) {
                setX((getX() - getSpeed()) / getScale());
            } else {
                setX((getX() + getSpeed()) / getScale());
            }
            this.nMoves++;
        }
        if (this.nMoves == NUMBER_OF_MOVES) {
            changeDirection();
            this.nMoves = 0;
        }
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
        this.updateCoords();
    }
}
