package it.unibo.superpeach.keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameObjects.player.PlayerHandler;

public class Keyboard extends KeyAdapter {
    private PlayerHandler playHand;
    private static final long MIN_MILLS = 150;
    private long current;
    private final Game game;

    public Keyboard(final PlayerHandler handler, final Game game) {
        this.playHand = handler;
        this.current = System.currentTimeMillis();
        this.game = game;
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        int pressed = e.getKeyCode();

        if (pressed == KeyEvent.VK_SPACE) {
            if (!playHand.getPlayer().hasJumped() && (System.currentTimeMillis() - current) >= MIN_MILLS) {
                current = System.currentTimeMillis();
                playHand.getPlayer().jump();
            }
        }

        if (pressed == KeyEvent.VK_A) {
            playHand.getPlayer().moveLeft();
        }

        if (pressed == KeyEvent.VK_D) {
            playHand.getPlayer().moveRight();
        }

        if (pressed == KeyEvent.VK_ESCAPE) {
            game.restart();
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        int pressed = e.getKeyCode();

        if (pressed == KeyEvent.VK_SPACE) {
            playHand.getPlayer().setHasJumped(false);
        }

        if (pressed == KeyEvent.VK_A || pressed == KeyEvent.VK_D) {
            playHand.getPlayer().setMoveX(0);
        }
    }
}
