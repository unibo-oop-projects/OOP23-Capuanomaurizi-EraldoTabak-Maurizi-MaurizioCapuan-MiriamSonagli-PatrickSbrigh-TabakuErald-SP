package it.unibo.superpeach.keyboard;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameentities.player.PlayerHandler;

public class KeyboardTest{
    Keyboard kb;
    @Test
    void testInit(){
        assertNull(kb);
        kb = new Keyboard(new PlayerHandler(), new Game());
        assertNotNull(kb);
    }
}