
package it.unibo.superpeach.level;

import org.junit.jupiter.api.Test;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameentities.blocks.BlockType;
import it.unibo.superpeach.gameentities.blocks.BlocksHandler;
import it.unibo.superpeach.gameentities.blocks.MapFixedBlock;
import it.unibo.superpeach.gameentities.enemies.EnemiesHandler;
import it.unibo.superpeach.graphics.BufferedImageLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing the EnemyHandler class.
 * 
 * @author Eraldo Tabaku
 */
public class LevelTest {

    LevelHandler lH = new LevelHandler(new BlocksHandler(), 1, new EnemiesHandler());

    @Test
    public void testParseLevel() {

        assertNotNull(lH);
        // lH.drawLevel("it/unibo/superpeach/resources/level/level_blocks.png");
    }

}