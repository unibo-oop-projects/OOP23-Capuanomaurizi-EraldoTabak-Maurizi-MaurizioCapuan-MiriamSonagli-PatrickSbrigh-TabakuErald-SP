package it.unibo.superpeach.game;

import org.junit.jupiter.api.Test;

import it.unibo.superpeach.game.Game;
import it.unibo.superpeach.gameentities.blocks.BlockType;
import it.unibo.superpeach.gameentities.blocks.BlocksHandler;
import it.unibo.superpeach.gameentities.blocks.MapFixedBlock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing class of the Game methods.
 * 
 * @author Eraldo Tabaku
 */
public class GameTest {

    Game game = new Game();

    @Test
    public void gameInitTest() {
        game.init();
    }

    @Test
    public void runningTest() {

    }

    @Test
    public void stopTest() {

    }

    @Test
    public void tickTest() {

    }

}