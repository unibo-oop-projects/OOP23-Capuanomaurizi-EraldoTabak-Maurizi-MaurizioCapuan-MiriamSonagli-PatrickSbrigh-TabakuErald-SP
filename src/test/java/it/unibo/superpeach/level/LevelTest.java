
package it.unibo.superpeach.level;

import org.junit.jupiter.api.Test;

import it.unibo.superpeach.gameentities.blocks.BlocksHandler;
import it.unibo.superpeach.gameentities.enemies.EnemiesHandler;

/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
*/
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing the EnemyHandler class.
 * 
 * @author Eraldo Tabaku
 */
class LevelTest {

    private final LevelHandler lH = new LevelHandler(new BlocksHandler(), 1, new EnemiesHandler());

    @Test
    void testParseLevel() {

        assertNotNull(lH);
        // lH.drawLevel("it/unibo/superpeach/resources/level/level_blocks.png");
    }

}
