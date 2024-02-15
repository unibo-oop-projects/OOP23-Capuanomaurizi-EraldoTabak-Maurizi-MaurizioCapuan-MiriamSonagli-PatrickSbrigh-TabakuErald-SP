package it.unibo.superpeach.blocks;

import java.awt.Graphics;

import it.unibo.superpeach.powerups.PowerUp.PowerUpType;

public class LuckyBlock extends MapFixedBlock{

    private PowerUpType containedPowerUp;

    public LuckyBlock(int x, int y, int width, int height, int scale, BlockType type, PowerUpType powerUp) {
        super(x, y, width, height, scale, type);
        containedPowerUp = powerUp;
    }

    @Override
    public void render(Graphics g) {
        if(getType() == BlockType.POPPED_LUCKY){
            g.drawImage(getSprites()[1], getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
        }
    }

    public PowerUpType getContainedPowerUp() {
        return containedPowerUp;
    }

    @Override
    public void popLuckyBlock() {
        if(getType() == BlockType.LUCKY){
            setType(BlockType.POPPED_LUCKY);
        }
    }

}
