package it.unibo.superpeach.blocks.graphics;

import java.awt.image.BufferedImage;

public class Texturer {
    
    private static final int BRICKSCASTLEBLOCKS_COUNT = 16;
    private static final int LUCKYBLOCKS_COUNT = 2;
    private static final int PIPEBLOCKS_COUNT = 4;
    private static final int BLOCK_WIDTH = 16;
    private static final int BLOCK_HEIGHT = 16;

    private BufferedImageLoader loader;

    private BufferedImage blocksSet;

    public BufferedImage[] bricksCastle, lucky, pipes;

    public Texturer(){

        bricksCastle = new BufferedImage[BRICKSCASTLEBLOCKS_COUNT];
        lucky = new BufferedImage[LUCKYBLOCKS_COUNT];
        pipes = new BufferedImage[PIPEBLOCKS_COUNT];

        loader = new BufferedImageLoader();

        try {
            blocksSet = loader.loadImage("/BlocksTile.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        getBricksCastle();
        getLucky();
        getPipes();
    }

    public BufferedImage[] getBricksCastle(){
        int x = 0;
        int y = 16;

        for (int i = 0; i < BRICKSCASTLEBLOCKS_COUNT/8; i++) {
            for (int j = 0; j < BRICKSCASTLEBLOCKS_COUNT/2; j++) {
                bricksCastle[0] = blocksSet.getSubimage(x + j*(BLOCK_WIDTH+1), y + i*(BLOCK_HEIGHT+1), BLOCK_WIDTH, BLOCK_HEIGHT);
            }
        }

        return bricksCastle;
    }

    public BufferedImage[] getLucky(){
        int x = 298;
        int y = 78;

        for (int i = 0; i < LUCKYBLOCKS_COUNT; i++) {
            lucky[0] = blocksSet.getSubimage(x, y + i*(BLOCK_HEIGHT+1), BLOCK_WIDTH, BLOCK_HEIGHT);
        }

        return lucky;
    }

    public BufferedImage[] getPipes(){
        int x = 119;
        int y = 196;

        for (int i = 0; i < PIPEBLOCKS_COUNT/2; i++) {
            for (int j = 0; j < PIPEBLOCKS_COUNT/2; j++) {
                pipes[0] = blocksSet.getSubimage(x + j*(BLOCK_WIDTH+1), y + i*(BLOCK_HEIGHT+1), BLOCK_WIDTH, BLOCK_HEIGHT);
            }
        }

        return pipes;
    }

}
