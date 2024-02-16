package it.unibo.superpeach.level;

import it.unibo.superpeach.player.Player;

public class Camera {

    private final int gameWidth;
    private int cameraX;

    public Camera(int width, int scale){
        gameWidth = width*scale;
    }

    public void tick(Player p){
        cameraX = gameWidth/2 - p.getX();
    }

    public void setCameraX(int x) {
        this.cameraX = x;
    }

    public int getCameraX() {
        return cameraX;
    }

}
