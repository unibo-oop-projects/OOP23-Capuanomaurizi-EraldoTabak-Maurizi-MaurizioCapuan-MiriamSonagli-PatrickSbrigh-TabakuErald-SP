package it.unibo.superpeach.blocks;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class BlocksHandler {

    private List<Block> fixedBlocks;
    private List<Block> backgroundBlocks;

    public BlocksHandler(){
        fixedBlocks = new ArrayList<Block>();
        backgroundBlocks = new ArrayList<Block>();
    }

    public void renderBlocks(Graphics g){
        for (Block block : fixedBlocks) {
            block.render(g);
        }
        for (Block block : backgroundBlocks) {
            block.render(g);
        }
    }

    public void addFixedBlock(Block b){
        fixedBlocks.add(b);
    }

    public void addBackgroundBlock(Block b){
        backgroundBlocks.add(b);
    }

    public List<Block> getBlocks() {
        return List.copyOf(fixedBlocks);
    }

}
