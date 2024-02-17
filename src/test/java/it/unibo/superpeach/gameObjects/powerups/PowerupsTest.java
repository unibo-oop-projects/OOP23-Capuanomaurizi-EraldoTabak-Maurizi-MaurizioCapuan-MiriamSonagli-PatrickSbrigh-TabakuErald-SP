package it.unibo.superpeach.gameObjects.powerups;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;
import it.unibo.superpeach.gameObjects.powerups.RedMushroom;
import it.unibo.superpeach.gameObjects.powerups.LifeMushroom;
import it.unibo.superpeach.gameObjects.powerups.Star;
import it.unibo.superpeach.gameObjects.powerups.Coin;

public class PowerupsTest {

    private static final int X1 = 10;
    private static final int Y1 = 10;
    private static final int X2 = 12;
    private static final int Y2 = 12;
    private static final int X3 = 16;
    private static final int Y3 = 16;
    private static final int X4 = 24;
    private static final int Y4 = 24;

    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;

    private static final int SCALE1 = 1;
    private static final int SCALE2 = 2;
    private static final int SCALE3 = 3;
    private static final int SCALE4 = 4;

    private static final BlocksHandler BLOCKSHANDLER = new BlocksHandler();

     Game game = new Game();
    
    @Test
    void powerupsTest() {

        game.init();

        // CREO UN POWERUP PER OGNI TIPO
        PowerUp redMushroom = new RedMushroom(X1, Y1, WIDTH, HEIGHT, SCALE1, BLOCKSHANDLER);
        PowerUp lifeMushroom = new LifeMushroom(X2, Y2, WIDTH, HEIGHT, SCALE2, BLOCKSHANDLER);
        PowerUp star = new Star(X3, Y3, WIDTH, HEIGHT, SCALE3, BLOCKSHANDLER);
        PowerUp coin = new RedMushroom(X4, Y4, WIDTH, HEIGHT, SCALE4, BLOCKSHANDLER);

        // VERIFICO SE I POWERUPS SONO STATI CREATI CORRETTAMENTE
        assertNotNull(redMushroom);
        assertNotNull(lifeMushroom);
        assertNotNull(star);
        assertNotNull(coin);

        // VERIFICO SE LE DIMENSIONI SONO GIUSTE
        assertEquals(X1, redMushroom.getX());
        assertEquals(Y1, redMushroom.getY());
        assertEquals(X2 * SCALE2, lifeMushroom.getX());
        assertEquals(Y2 * SCALE2, lifeMushroom.getY());
        assertEquals(X3 * SCALE3, star.getX());
        assertEquals(Y3 * SCALE3, star.getY());
        assertEquals(X4 * SCALE4, coin.getX());
        assertEquals(Y4 * SCALE4, coin.getY());

        assertEquals(WIDTH, redMushroom.getWidth());
        assertEquals(HEIGHT, redMushroom.getHeight());
        assertEquals(WIDTH * SCALE2, lifeMushroom.getWidth());
        assertEquals(HEIGHT * SCALE2, lifeMushroom.getHeight());
        assertEquals(WIDTH * SCALE3, star.getWidth());
        assertEquals(HEIGHT * SCALE3, star.getHeight());
        assertEquals(WIDTH * SCALE4, coin.getWidth());
        assertEquals(HEIGHT * SCALE4, coin.getHeight());

        assertEquals(SCALE1, redMushroom.getScale());
        assertEquals(SCALE2, lifeMushroom.getScale());
        assertEquals(SCALE3, star.getScale());
        assertEquals(SCALE4, coin.getScale());

        // VERIFICO SE IL CAMBIO DIREZIONE VIENE GESTITO CORRETTAMENTE
        assertFalse(redMushroom.getDirection());
        redMushroom.changeDirection();
        assertTrue(redMushroom.getDirection());
        assertFalse(lifeMushroom.getDirection());
        lifeMushroom.changeDirection();
        assertTrue(lifeMushroom.getDirection());
        assertFalse(star.getDirection());
        star.changeDirection();
        assertTrue(star.getDirection());
        
        // VERIFICO CHE IL POWERUP MUOIA CORRETTAMENTE
        assertTrue(redMushroom.getIsAlive());
        redMushroom.die();
        assertFalse(redMushroom.getIsAlive());
        assertTrue(lifeMushroom.getIsAlive());
        lifeMushroom.die();
        assertFalse(lifeMushroom.getIsAlive());
        assertTrue(star.getIsAlive());
        star.die();
        assertFalse(star.getIsAlive());

        // VERIFICO SE LE COLLISIONI VENGONO GESTITE CORRETTAMENTE








    }
    






}