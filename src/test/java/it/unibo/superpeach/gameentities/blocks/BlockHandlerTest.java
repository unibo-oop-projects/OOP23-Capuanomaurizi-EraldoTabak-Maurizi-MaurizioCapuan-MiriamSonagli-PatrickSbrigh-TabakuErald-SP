package it.unibo.superpeach.gameentities.blocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.superpeach.game.Game;

public class BlockHandlerTest {

    private static final int X = 1;
    private static final int Y = 1;
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final int SCALE1 = 1;
    private static final int SCALE2 = 2;

    @BeforeEach
    public void initGameTest() {
        new Game().init();
    }

    @Test
    public void addBlockTest() {

        BlocksHandler bH = new BlocksHandler();

        bH.addFixedBlock(new MapFixedBlock(X, Y, WIDTH, HEIGHT, SCALE2, BlockType.BRICK));
        bH.addFixedBlock(new MapFixedBlock(X, Y, WIDTH, HEIGHT, SCALE1, BlockType.BRICK));

        assertEquals(2, bH.getBlocks().size());

        assertNotEquals(bH.getBlocks().get(0), bH.getBlocks().get(1));

        assertNotNull(bH.getBlocks().get(1));
    }

    @Test
    public void removeBlockTest() {

        BlocksHandler bH1 = new BlocksHandler();

        bH1.addFixedBlock(new MapFixedBlock(X, Y, WIDTH, HEIGHT, SCALE2, BlockType.BRICK));
        bH1.addFixedBlock(new MapFixedBlock(X, Y, WIDTH, HEIGHT, SCALE1, BlockType.BRICK));

        assertEquals(2, bH1.getBlocks().size());

        bH1.removeFixedBlock(bH1.getBlocks().get(1));
        assertEquals(1, bH1.getBlocks().size());

        assertNotEquals(new MapFixedBlock(X, Y, WIDTH, HEIGHT, SCALE1, BlockType.BRICK), bH1.getBlocks().get(0));

        bH1.removeFixedBlock(bH1.getBlocks().get(0));
        assertEquals(0, bH1.getBlocks().size());
    }

}
