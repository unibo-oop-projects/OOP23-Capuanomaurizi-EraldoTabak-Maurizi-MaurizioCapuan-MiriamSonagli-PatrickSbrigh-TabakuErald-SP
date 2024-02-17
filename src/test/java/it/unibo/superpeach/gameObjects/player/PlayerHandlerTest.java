package it.unibo.superpeach.gameObjects.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameentities.player.Peach;
import it.unibo.superpeach.gameentities.player.Player;
import it.unibo.superpeach.gameentities.player.PlayerHandler;

public class PlayerHandlerTest {
    private PlayerHandler pHandler;
    private Player peach;
    private Game game = new Game();
    @Test
    void testInit(){
        assertNull(pHandler);
        pHandler = new PlayerHandler();
        assertNotNull(pHandler);
        assertNull(pHandler.getPlayer());
    }

    @Test
    void testSetPlayer(){
        game.init();
        pHandler = new PlayerHandler();
        peach = new Peach(0, 0, 0, 0, 0, null, null, null, null);
        assertEquals(0, pHandler.takePlayer(peach));
        assertNotNull(pHandler.getPlayer());
    }

    @Test
    void testRemovePlayer(){
        game.init();
        pHandler = new PlayerHandler();
        peach = new Peach(0, 0, 0, 0, 0, null, null, null, null);
        pHandler.takePlayer(peach);
        assertEquals(0, pHandler.removePlayer());
        assertEquals(null, pHandler.getPlayer());
    }
}
