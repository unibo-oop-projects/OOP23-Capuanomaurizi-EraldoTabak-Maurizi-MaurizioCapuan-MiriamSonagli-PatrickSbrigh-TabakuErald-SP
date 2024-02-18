package it.unibo.superpeach.gameentities.blocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Rectangle;
import it.unibo.superpeach.game.Game;

public class BlockHandlerTest {

    @BeforeEach
    public void initGameTest() {
        new Game().init();
        BlocksHandler bH = new BlocksHandler();
    }

    @Test
    public void addBlockTest() {

        bH.addFixedBlock();
    }

}
