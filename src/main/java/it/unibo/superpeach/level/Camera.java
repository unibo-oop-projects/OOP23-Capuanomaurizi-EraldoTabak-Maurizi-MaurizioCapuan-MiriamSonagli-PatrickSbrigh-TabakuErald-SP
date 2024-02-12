package it.unibo.superpeach.level;

import it.unibo.superpeach.player.Player;

public class Camera {

    private final int gameWidth;
    //private final int gameHeight;
    private final int gameScale;
    private int cameraX;
    private int cameraY;

    public Camera(int width, int height, int scale){
        gameWidth = width*scale;
        //gameHeight = height*scale;
        gameScale = scale;
    }

    public void tick(Player p){
        cameraX = gameWidth/2 - p.getX()*gameScale;
        cameraY = 0;
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
