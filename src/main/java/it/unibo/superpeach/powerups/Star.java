package it.unibo.superpeach.powerups;

public class Star extends PowerUp {
    
    public Star (double x, double y) {
        super(x, y);
    }

    public void move(int movement){
        this.setX(this.getX() + movement);
    }

    public void isFalling () {
        
    }

}
