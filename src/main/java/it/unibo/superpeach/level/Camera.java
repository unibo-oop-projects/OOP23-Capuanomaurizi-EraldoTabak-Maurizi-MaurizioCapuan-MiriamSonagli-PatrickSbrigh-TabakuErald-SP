package it.unibo.superpeach.level;

import it.unibo.superpeach.player.Player;

public class Camera {

    private final int gameWidth;
    private int cameraX;
    private int cameraY = 0;

    public Camera(int width, int scale){
        gameWidth = width*scale;
    }

    public void tick(Player p){
        cameraX = gameWidth/2 - p.getX();
    }

    public void setCameraX(int x) {
        this.cameraX = x;
    }

    public void setCameraY(int y) {
        this.cameraY = y;
    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }
}
