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
            if(!playHand.getPlayer().hasJumped()){
                playHand.getPlayer().jump();
            }
        }
        
        if(pressed == KeyEvent.VK_A){
            playHand.getPlayer().moveLeft();
        }
        
        if(pressed == KeyEvent.VK_D){
            playHand.getPlayer().moveRight();
        }
        
        if(pressed == KeyEvent.VK_ESCAPE){

        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int pressed = e.getKeyCode();
    
        if(pressed == KeyEvent.VK_SPACE){
            playHand.getPlayer().setHasJumped(false);
        }
        
        if(pressed == KeyEvent.VK_A || pressed == KeyEvent.VK_D){
            playHand.getPlayer().setMoveX(0);;
        }
    }
}