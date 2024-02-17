package it.unibo.superpeach.gameObjects.blocks;

import java.awt.Graphics;

import it.unibo.superpeach.gameObjects.powerups.Coin;
import it.unibo.superpeach.gameObjects.powerups.LifeMushroom;
import it.unibo.superpeach.gameObjects.powerups.PowerupsHandler;
import it.unibo.superpeach.gameObjects.powerups.RedMushroom;
import it.unibo.superpeach.gameObjects.powerups.Star;
import it.unibo.superpeach.gameObjects.powerups.PowerUp.PowerUpType;

/**
 * Blocks class that inherits from FixedBlock and represents lucky blocks
 * containing code to identify which powerup is inside.
 * 
 * @author  Maurizio Capuano
 */
public final class LuckyBlock extends MapFixedBlock {

    private PowerUpType containedPowerUp;

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @param scale
     * @param type
     * @param powerUp powerup type
     */
    public LuckyBlock(final int x, final int y, final int width, final int height, final int scale,
            final BlockType type, final PowerUpType powerUp) {
        super(x, y, width, height, scale, type);
        containedPowerUp = powerUp;
    }

    @Override
    public void render(final Graphics g) {
        if (getType() == BlockType.POPPED_LUCKY) {
            g.drawImage(getSprites()[1], getX(), getY(), getWidth(), getHeight(), null);
        } else {
            g.drawImage(getSprites()[0], getX(), getY(), getWidth(), getHeight(), null);
        }
    }

    /**
     * @return which powerup is contained inside the lucky block
     */
    public PowerUpType getContainedPowerUp() {
        return containedPowerUp;
    }

    @Override
    public void popLuckyBlock(final PowerupsHandler powerupsHandler, final BlocksHandler blocksHandler) {
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
                default:
                    break;
            }
            setType(BlockType.POPPED_LUCKY);
        }
    }

}
