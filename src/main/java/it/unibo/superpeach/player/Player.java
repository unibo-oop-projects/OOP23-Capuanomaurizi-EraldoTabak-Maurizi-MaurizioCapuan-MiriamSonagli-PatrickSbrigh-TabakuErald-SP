package it.unibo.superpeach.player;

public abstract class Player {
    private int width;
    private int height;
    private int x;
    private int y;
    private int scale;

    public Player(int width, int height, int x, int y, int scale){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    


}
