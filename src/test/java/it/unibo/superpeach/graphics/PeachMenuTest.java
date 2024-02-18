package it.unibo.superpeach.graphics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import it.unibo.superpeach.game.Game;

/**
 * PeachMenu testing class.
 * 
 * @author Miriam Sonaglia
 */
public class PeachMenuTest {

    private static final String TITLE = "TITLE";
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final int SCALE = 1;
    private Game game = new Game();

    @Test
    void menuTest() {

        // CREO IL MIO MENU'
        PeachMenu menu = new PeachMenu(TITLE, WIDTH, HEIGHT, SCALE, game);

        // VERIFICO SE IL MENU E' STATO CREATO CORRETTAMENTE
        assertNotNull(menu);

    }

}
