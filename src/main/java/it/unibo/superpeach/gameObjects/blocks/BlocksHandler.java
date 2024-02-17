package it.unibo.superpeach.gameObjects.blocks;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class BlocksHandler {

    private List<MapFixedBlock> fixedBlocks;
    private List<Block> backgroundBlocks;

    public BlocksHandler() {
        fixedBlocks = new ArrayList<MapFixedBlock>();
        backgroundBlocks = new ArrayList<Block>();
    }

    public void renderBlocks(final Graphics g) {
        for (MapFixedBlock block : fixedBlocks) {
            block.render(g);
        }
        for (Block block : backgroundBlocks) {
            block.render(g);
        }
    }

    public void addFixedBlock(final MapFixedBlock b) {
        fixedBlocks.add(b);
    }

    public void addBackgroundBlock(final Block b) {
        backgroundBlocks.add(b);
    }

    public void removeFixedBlock(final Block b) {
        fixedBlocks.remove(b);
    }

    public List<MapFixedBlock> getBlocks() {
        return List.copyOf(fixedBlocks);
    }

}
