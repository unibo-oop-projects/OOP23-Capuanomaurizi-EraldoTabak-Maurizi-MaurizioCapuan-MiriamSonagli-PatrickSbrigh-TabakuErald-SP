package it.unibo.superpeach.level;

public class Camera {

    private final int gameWidth;
    private final int gameHeight;
    private int cameraX;
    private int cameraY;

    public Camera(int width, int height){
        gameWidth = width;
        gameHeight = height;
        System.out.println(width+ " "+height);
    }

    public void tick(/*player reference */){
        cameraX = gameWidth/2 /* - player x */;
        cameraY = gameHeight/2 /* - player y */;
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
