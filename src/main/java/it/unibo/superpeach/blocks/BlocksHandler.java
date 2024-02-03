package it.unibo.superpeach.blocks;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class BlocksHandler {

    private List<Block> blocks;

    public BlocksHandler(){
        blocks = new ArrayList<Block>();
    }

    public void tickBlocks(){
        for (Block block : blocks) {
            block.tick();
        }
    }

    public void renderBlocks(Graphics g){
        for (Block block : blocks) {
            block.render(g);
        }
    }

    public void addBlock(Block b){
        blocks.add(b);
    }

    public void removeBlock(Block b){
        blocks.remove(b);
    }

    public List<Block> getBlocks() {
        return List.copyOf(blocks);
    }

}
