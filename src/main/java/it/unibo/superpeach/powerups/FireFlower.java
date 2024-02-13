package it.unibo.superpeach.powerups;

public class FireFlower extends PowerUp{

    //DEVO FARE IO LA PALLINA DI FUOCO?
 
    public FireFlower (double x, double y) {
        super(x, y);
    }

    public void move(int movement){
        this.setX(this.getX() + movement);
    }

    public void isFalling () {
        
    }

}
