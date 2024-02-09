package it.unibo.superpeach.player;

public abstract class Player {
    private int width;
    private int height;
    private int x;
    private int y;
    private int scale;

    public Player(int width, int height, int x, int y, int scale){
        this.width = width*scale;
        this.height = height*scale;
        this.x = x*scale;
        this.y = y*scale;
        this.scale = scale;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    

}
