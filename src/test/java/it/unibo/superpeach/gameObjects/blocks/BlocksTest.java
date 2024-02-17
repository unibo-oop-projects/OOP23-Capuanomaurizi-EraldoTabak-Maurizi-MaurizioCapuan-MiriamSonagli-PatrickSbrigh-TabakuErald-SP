package it.unibo.superpeach.gameObjects.blocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameentities.blocks.BlockType;
import it.unibo.superpeach.gameentities.blocks.MapFixedBlock;

/**
 * Test class for Blocks
 */
final class BlocksTest {

    private final Game game = new Game();

    private MapFixedBlock terrainBlock = null;

    @BeforeEach
    void initGameTest() {
        game.init();
        terrainBlock = new MapFixedBlock(0, 0, 32, 32, 1, BlockType.TERRAIN);
    }

    @Test
    void testCreation() {
        assertNotNull(terrainBlock);
    }

}