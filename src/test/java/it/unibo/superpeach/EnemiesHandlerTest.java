
package it.unibo.superpeach;

import org.junit.jupiter.api.Test;

import it.unibo.superpeach.blocks.BlocksHandler;
import it.unibo.superpeach.enemies.EnemiesHandler;
import it.unibo.superpeach.enemies.FlyingKoopa;
import it.unibo.superpeach.enemies.Goomba;
import it.unibo.superpeach.enemies.KoopaTroopa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemiesHandlerTest {

    private static final int X = 12;
    private static final int Y = 12;
    private static final int WIDTH = 16;
    private static final int KOOPA_HEIGHT = 24;
    private static final int GOOMBA_HEIGHT = 16;
    private static final int SCALE = 1;

    private final EnemiesHandler enemiesHandler = new EnemiesHandler();
    private final BlocksHandler blocksHandler = new BlocksHandler();

    @Test
    void testEnemyHandler() {

        // Check if there's no enemies to manage by the Enemy Handler
        assertNull(enemiesHandler);

        // Adding a new goomba to the Enemies Handler
        enemiesHandler.addEnemy(new Goomba(X, Y, WIDTH, GOOMBA_HEIGHT, SCALE, blocksHandler));
        assertNotNull(blocksHandler);

        // Checking if the enemy got added correctly by checking if the first enemy in
        // enemis<Enemy> has the correct fields values
        assertEquals(X, enemiesHandler.getEnemies().get(0).getX());
        assertNotEquals(Y, enemiesHandler.getEnemies().get(0).getX());
        assertEquals(Y, enemiesHandler.getEnemies().get(0).getY());
        assertNotEquals(X, enemiesHandler.getEnemies().get(0).getY());
        assertEquals(WIDTH, enemiesHandler.getEnemies().get(0).getWidth());
        assertNotEquals(KOOPA_HEIGHT, enemiesHandler.getEnemies().get(0).getWidth());
        assertEquals(GOOMBA_HEIGHT, enemiesHandler.getEnemies().get(0).getHeight());
        assertNotEquals(KOOPA_HEIGHT, enemiesHandler.getEnemies().get(0).getHeight());
        assertEquals(SCALE, enemiesHandler.getEnemies().get(0).getScale());
        assertNotEquals(KOOPA_HEIGHT, enemiesHandler.getEnemies().get(0).getScale());
        assertEquals(blocksHandler, enemiesHandler.getEnemies().get(0).getBlocksHandler());

        // Checking if the enemy is alive
        assertTrue(enemiesHandler.getEnemies().get(0).getIsAlive());

        // Checking if the enemy is dead
        assertFalse(enemiesHandler.getEnemies().get(0).getIsAlive());

        // Removing the previosly added enemy
        enemiesHandler.removeEnemy(enemiesHandler.getEnemies().get(0));

        // Checking if the enemy is actually removed
        assertNull(enemiesHandler);

        // Testing with more enemies
        enemiesHandler.addEnemy(new KoopaTroopa(X, Y, WIDTH, KOOPA_HEIGHT, SCALE, blocksHandler));
        enemiesHandler.addEnemy(new FlyingKoopa(X, Y, WIDTH, KOOPA_HEIGHT, SCALE, blocksHandler));
        enemiesHandler.addEnemy(new Goomba(X, Y, WIDTH, GOOMBA_HEIGHT, SCALE, blocksHandler));
        enemiesHandler.addEnemy(new FlyingKoopa(X, Y, WIDTH, KOOPA_HEIGHT, SCALE, blocksHandler));
        enemiesHandler.addEnemy(new Goomba(X, Y, WIDTH, GOOMBA_HEIGHT, SCALE, blocksHandler));

        // Checking if the number of enemies in List<Enemy> of the EnemyHandler is
        // correct
        assertEquals(5, enemiesHandler.getEnemies().size());
        enemiesHandler.removeEnemy(enemiesHandler.getEnemies().get(1));
        assertEquals(4, enemiesHandler.getEnemies().size());

        enemiesHandler.tickEnemies();

    }
}
