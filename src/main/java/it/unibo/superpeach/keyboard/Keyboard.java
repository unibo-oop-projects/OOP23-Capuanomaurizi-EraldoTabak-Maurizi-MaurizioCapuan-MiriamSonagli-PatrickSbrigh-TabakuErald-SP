package it.unibo.superpeach.keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.player.PlayerHandler;

public class Keyboard extends KeyAdapter {
    private PlayerHandler playHand;
    private static final long MIN_MILLS = 150;
    private long current;

    public Keyboard(PlayerHandler handler){
        this.playHand = handler;
        this.current = System.currentTimeMillis();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int pressed = e.getKeyCode();
    
        if(pressed == KeyEvent.VK_SPACE){
            if(!playHand.getPlayer().hasJumped() && (System.currentTimeMillis() - current)>=MIN_MILLS){
                current = System.currentTimeMillis();
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
            Game.restart();
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