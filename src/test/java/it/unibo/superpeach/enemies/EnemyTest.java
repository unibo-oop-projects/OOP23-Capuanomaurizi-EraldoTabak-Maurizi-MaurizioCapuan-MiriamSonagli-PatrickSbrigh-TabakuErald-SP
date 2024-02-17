<<<<<<< HEAD:src/test/java/it/unibo/superpeach/EnemyTest.java
package it.unibo.superpeach;

import it.unibo.superpeach.blocks.BlocksHandler;
import it.unibo.superpeach.enemies.Enemy;
import it.unibo.superpeach.enemies.FlyingKoopa;
import it.unibo.superpeach.enemies.Goomba;
import it.unibo.superpeach.enemies.KoopaTroopa;
=======
package it.unibo.superpeach.enemies;
>>>>>>> 4db6bd9997b88e95a6b29d632e3b0faa86c33aaf:src/test/java/it/unibo/superpeach/enemies/EnemyTest.java

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

<<<<<<< HEAD:src/test/java/it/unibo/superpeach/EnemyTest.java
import org.junit.jupiter.api.Test;
=======
import it.unibo.superpeach.gameObjects.enemies.FlyingKoopa;
import it.unibo.superpeach.gameObjects.enemies.Goomba;
import it.unibo.superpeach.gameObjects.enemies.KoopaTroopa;
>>>>>>> 4db6bd9997b88e95a6b29d632e3b0faa86c33aaf:src/test/java/it/unibo/superpeach/enemies/EnemyTest.java

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

    @Test
    void testEnemy() {

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
}
