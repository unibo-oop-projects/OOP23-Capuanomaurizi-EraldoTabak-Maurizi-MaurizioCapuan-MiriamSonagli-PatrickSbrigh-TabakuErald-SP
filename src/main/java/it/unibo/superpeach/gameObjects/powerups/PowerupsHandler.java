package it.unibo.superpeach.gameObjects.powerups;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Power up handler class implementation.
 * @author  Miriam Sonaglia
 */
public class PowerupsHandler {

    private List<PowerUp> powerups;

    /**
     * initializes a new list of power ups.
     */
    public PowerupsHandler() {
        powerups = new ArrayList<PowerUp>();
    }

    /**
     * iterates through all the powerups alive,
     * if they die they are removed.
     */
    public void tickPowerups() {
        Set<PowerUp> powerupsToRemove = new HashSet<>();
        for (PowerUp powerUp : powerups) {
            powerUp.tick();
            if (!powerUp.getIsAlive()) {
                powerupsToRemove.add(powerUp);
            }
        }

        for (PowerUp powerUp : powerupsToRemove) {
            powerups.remove(powerUp);
        }
    }

    /**
     * Method that draws all the power ups in the list.
     * @param g
     */
    public void renderPowerups(final Graphics g) {
        for (PowerUp powerup : powerups) {
            powerup.render(g);
        }
    }

    /**
     * adds a power up to the power ups handler.
     * @param p
     */
    public void addPowerUp(final PowerUp p) {
        powerups.add(p);
    }

    /**
     * removes a power up from the power ups handler.
     * @param p
     */
    public void removePowerUp(final PowerUp p) {
        powerups.remove(p);
    }

    /**
     * @return a copy of the power up list.
     */
    public List<PowerUp> getPowerups() {
        return List.copyOf(powerups);
    }

}

