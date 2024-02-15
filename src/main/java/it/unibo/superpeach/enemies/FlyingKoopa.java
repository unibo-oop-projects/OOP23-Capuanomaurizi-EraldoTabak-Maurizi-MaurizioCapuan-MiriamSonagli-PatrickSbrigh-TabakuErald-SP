package it.unibo.superpeach.enemies;

import java.awt.*;

import it.unibo.superpeach.blocks.BlocksHandler;

public class FlyingKoopa extends Enemy {

    public int nMoves;

    public FlyingKoopa(int x, int y, int width, int height, int scale, BlocksHandler blocksHandler) {
        super(x, y, width, height, scale, blocksHandler);
        setSprites(getTexturer().getFlyingKoopa());
        setSpeed(1 * scale);
        setFalling(false);
        this.nMoves = 0;

    }

    @Override
    public void updateCoords() {
        if (this.nMoves < 50) {
            if (getDirection()) {
                this.x -= this.speed;
            } else {
                this.x += this.speed;
            }
            this.nMoves++;
        }
        if (this.nMoves == 50) {
            changeDirection();
            this.nMoves = 0;
        }
    }

    @Override
    public void render(Graphics g) {
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
        this.updateCoords();
    }
}
