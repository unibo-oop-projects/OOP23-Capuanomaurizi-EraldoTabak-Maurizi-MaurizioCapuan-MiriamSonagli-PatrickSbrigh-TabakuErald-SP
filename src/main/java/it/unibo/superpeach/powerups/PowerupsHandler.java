package it.unibo.superpeach.powerups;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class PowerupsHandler {

    private List<PowerUp> powerups;

    public PowerupsHandler() {
        powerups = new ArrayList<PowerUp>();
    }

    public void tickPowerups() {
        for (PowerUp powerup : powerups) {
            powerup.tick();
        }
    }

    public void renderPowerups(Graphics g) {
        for (PowerUp powerup : powerups) {
            powerup.render(g);
        }
    }

    public void addPowerUp(PowerUp b) {
        powerups.add(b);
    }

    public void removePowerUp(PowerUp b) { // la cosa del null (?)
        powerups.remove(b);
    }

    public List<PowerUp> getPowerups() {
        return List.copyOf(powerups);
    }

}

