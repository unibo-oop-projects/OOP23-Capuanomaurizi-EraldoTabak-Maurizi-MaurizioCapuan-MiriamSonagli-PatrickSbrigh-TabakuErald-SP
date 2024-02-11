package it.unibo.superpeach.keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import it.unibo.superpeach.player.PlayerHandler;

public class Keyboard extends KeyAdapter {
    private PlayerHandler playHand;

    public Keyboard(PlayerHandler handler){
        this.playHand = handler;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int pressed = e.getKeyCode();
    
        if(pressed == KeyEvent.VK_SPACE){
            playHand.getPlayer().jump();
        }
        else if(pressed == KeyEvent.VK_A){
            playHand.getPlayer().moveLeft();
        }
        else if(pressed == KeyEvent.VK_D){
            playHand.getPlayer().moveRight();
        }
        else if(pressed == KeyEvent.VK_ESCAPE){

        }
    }
}