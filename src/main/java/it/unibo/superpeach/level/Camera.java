package it.unibo.superpeach.level;

import it.unibo.superpeach.gameObjects.player.Player;

public class Camera {

    private final int gameWidth;
    private int cameraX;

    public Camera(final int width, final int scale) {
        gameWidth = width * scale;
    }

    public void tick(final Player p) {
        cameraX = gameWidth / 2 - p.getX();
    }

    public void setCameraX(final int x) {
        this.cameraX = x;
    }

    public int getCameraX() {
        return cameraX;
    }

}
