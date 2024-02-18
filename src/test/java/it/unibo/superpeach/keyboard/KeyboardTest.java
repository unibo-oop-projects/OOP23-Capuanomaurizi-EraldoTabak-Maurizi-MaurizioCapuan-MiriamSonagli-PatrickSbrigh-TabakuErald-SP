package it.unibo.superpeach.keyboard;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameentities.player.PlayerHandler;

/**
 * Keyboard testing class.
 * 
 * @author Patrick Sbrighi
 */
public class KeyboardTest {
    private Keyboard kb;

    @Test
    void testInit() {
        assertNull(kb);
        kb = new Keyboard(new PlayerHandler(), new Game());
        assertNotNull(kb);
    }
}
