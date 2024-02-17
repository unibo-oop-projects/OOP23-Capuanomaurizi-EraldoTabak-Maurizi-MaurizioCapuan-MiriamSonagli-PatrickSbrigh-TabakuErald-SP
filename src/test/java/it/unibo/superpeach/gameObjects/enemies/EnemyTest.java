
package it.unibo.superpeach.gameObjects.enemies;

import org.junit.jupiter.api.Test;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameObjects.blocks.BlocksHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyTest {

    private static final int X1 = 12;
    private static final int Y1 = 12;
    private static final int X2 = 22;
    private static final int Y2 = 22;
    private static final int X3 = 32;
    private static final int Y3 = 32;
    private static final int WIDTH = 16;
    private static final int KOOPA_HEIGHT = 24;
    private static final int GOOMBA_HEIGHT = 16;
    private static final int SCALE1 = 1;
    private static final int SCALE2 = 2;

    private static final BlocksHandler BLOCKS_HANDLER = new BlocksHandler();
    private Game game = new Game();

    @Test
    void testEnemy() {

        game.init();

        // Creating an enemy for each type
        Enemy koopa = new KoopaTroopa(X1, Y1, WIDTH, KOOPA_HEIGHT, SCALE1, BLOCKS_HANDLER);
        Enemy goomba = new Goomba(X2, Y2, WIDTH, GOOMBA_HEIGHT, SCALE2, BLOCKS_HANDLER);
        Enemy fkoopa = new FlyingKoopa(X3, Y3, WIDTH, KOOPA_HEIGHT, SCALE2, BLOCKS_HANDLER);

        // Checking if the enemies got correctly created
        assertNotNull(koopa);
        assertNotNull(goomba);
        assertNotNull(fkoopa);

        // Checking the field values of the enemies that have been created
        assertEquals(X1, koopa.getX());
        assertEquals(Y1, koopa.getY());
        assertEquals(WIDTH, koopa.getWidth());
        assertEquals(KOOPA_HEIGHT, koopa.getHeight());
        assertEquals(SCALE1, koopa.getScale());
        assertEquals(BLOCKS_HANDLER, koopa.getBlocksHandler());

        // Checking if the scales operations on the coordinates are done correcly
        assertNotEquals(X2, goomba.getX());
        assertEquals(X2 * SCALE2, goomba.getX());
        assertNotEquals(X3, fkoopa.getX());
        assertEquals(X3 * SCALE2, fkoopa.getX());

        // Checking direction changes
        assertFalse(fkoopa.getDirection());
        fkoopa.changeDirection();
        assertTrue(fkoopa.getDirection());

        // Checking enemy "alive" status
        assertTrue(goomba.getIsAlive());
        goomba.die();
        assertFalse(goomba.getIsAlive());

    }

    @Test
    void testQualcosa() {
        game.init();
        Enemy koopa = new KoopaTroopa(X1, Y1, WIDTH, KOOPA_HEIGHT, SCALE1, BLOCKS_HANDLER);

    }
}