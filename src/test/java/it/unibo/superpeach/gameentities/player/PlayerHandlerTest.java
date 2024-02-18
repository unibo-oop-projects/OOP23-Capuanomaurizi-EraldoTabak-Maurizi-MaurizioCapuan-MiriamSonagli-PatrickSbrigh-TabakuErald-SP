package it.unibo.superpeach.gameentities.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.game.Scoreboard;
import it.unibo.superpeach.gameentities.blocks.BlocksHandler;
import it.unibo.superpeach.gameentities.enemies.EnemiesHandler;
import it.unibo.superpeach.gameentities.powerups.PowerupsHandler;

public class PlayerHandlerTest {
    private PlayerHandler pHandler;
    private Player peach;
    private final static int SCALE = 2;
    private Game game = new Game();

    @Test
    void testInit() {
        assertNull(pHandler);
        pHandler = new PlayerHandler();
        assertNotNull(pHandler);
    }

    @Test
    void testSetPlayer() {
        game.init();
        pHandler = new PlayerHandler();
        peach = new Peach(1, 1, 16, 16, SCALE, new BlocksHandler(), new EnemiesHandler(), new PowerupsHandler(),
                new Scoreboard(3, 7, SCALE));
        assertEquals(0, pHandler.takePlayer(peach));
        assertNotNull(pHandler.getPlayer());
    }

    @Test
    void testRemovePlayer() {
        game.init();
        pHandler = new PlayerHandler();
        peach = new Peach(1, 1, 16, 32, SCALE, new BlocksHandler(), new EnemiesHandler(), new PowerupsHandler(),
                new Scoreboard(1, 7, SCALE));
        pHandler.takePlayer(peach);
        assertNotNull(pHandler.getPlayer());
        pHandler.removePlayer();
        // assertEquals(-1, pHandler.takePlayer(peach));
    }
}
