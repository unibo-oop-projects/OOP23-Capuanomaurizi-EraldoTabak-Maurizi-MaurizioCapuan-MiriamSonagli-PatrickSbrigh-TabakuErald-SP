package it.unibo.superpeach.gameObjects.blocks;

import java.awt.Graphics;

import it.unibo.superpeach.gameObjects.powerups.*;
import it.unibo.superpeach.gameObjects.powerups.PowerUp.PowerUpType;

public class LuckyBlock extends MapFixedBlock {

    private PowerUpType containedPowerUp;

    public LuckyBlock(int x, int y, int width, int height, int scale, BlockType type, PowerUpType powerUp) {
        super(x, y, width, height, scale, type);
        containedPowerUp = powerUp;
    }

    @Override
    public void render(Graphics g) {
        if (getType() == BlockType.POPPED_LUCKY) {
            g.drawImage(getSprites()[1], getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
        }
    }

    public PowerUpType getContainedPowerUp() {
        return containedPowerUp;
    }

    @Override
    public void popLuckyBlock(PowerupsHandler powerupsHandler, BlocksHandler blocksHandler) {
        if (getType() == BlockType.LUCKY) {
            switch (containedPowerUp) {
                case RED_MUSHROOM:
                    powerupsHandler.addPowerUp(new RedMushroom(getX() / getScale(), getY() / getScale() - 16, 16, 16,
                            getScale(), blocksHandler));
                    break;
                case STAR:
                    powerupsHandler.addPowerUp(
                            new Star(getX() / getScale(), getY() / getScale() - 16, 16, 16, getScale(), blocksHandler));
                    break;
                case LIFE_MUSHROOM:
                    powerupsHandler.addPowerUp(new LifeMushroom(getX() / getScale(), getY() / getScale() - 16, 16, 16,
                            getScale(), blocksHandler));
                    break;
                case COIN:
                    powerupsHandler.addPowerUp(
                            new Coin(getX() / getScale(), getY() / getScale() - 16, 16, 16, getScale(), blocksHandler));
                    break;
            }
            setType(BlockType.POPPED_LUCKY);
        }
    }

}
