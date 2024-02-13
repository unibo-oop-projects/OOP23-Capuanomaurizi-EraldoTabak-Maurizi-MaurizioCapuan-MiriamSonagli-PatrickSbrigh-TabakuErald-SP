package it.unibo.superpeach.powerups;
import javax.swing.*;
import java.awt.*;

public class RedMushroom extends PowerUp {
    
    public RedMushroom (double x, double y) {
        super(x, y);
    }

    public void move(int movement){
        this.setX(this.getX() + movement);
    }

    public void isFalling () {
        
    }

}
