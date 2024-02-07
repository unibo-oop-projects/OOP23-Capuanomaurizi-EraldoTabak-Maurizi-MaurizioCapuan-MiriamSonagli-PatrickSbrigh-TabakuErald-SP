package it.unibo.superpeach.blocks.graphics;

import java.awt.image.BufferedImage;

public class Texturer {
    
    private static final int BRICKSCASTLEBLOCKS_COUNT = 16;
    private static final int LUCKYBLOCKS_COUNT = 2;
    private static final int PIPEBLOCKS_COUNT = 4;
    private static final int BLOCK_WIDTH = 16;
    private static final int BLOCK_HEIGHT = 16;

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    private BufferedImageLoader loader;

    private BufferedImage blocksSet;

    public BufferedImage[] bricksCastle, lucky, pipes;

    public Texturer(){

        bricksCastle = new BufferedImage[BRICKSCASTLEBLOCKS_COUNT];
        lucky = new BufferedImage[LUCKYBLOCKS_COUNT];
        pipes = new BufferedImage[PIPEBLOCKS_COUNT];

        loader = new BufferedImageLoader();

        try {
            blocksSet = loader.loadImage("it"+FILE_SEPARATOR+"unibo"+FILE_SEPARATOR+"superpeach"+FILE_SEPARATOR+"tiles"+FILE_SEPARATOR+"BlocksTile.png");
            loadBricksCastle();
            loadLucky();
            loadPipes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadBricksCastle(){
        int x = 0;
        int y = 16;
        int index = 0;

        for (int i = 0; i < BRICKSCASTLEBLOCKS_COUNT/8; i++) {
            for (int j = 0; j < BRICKSCASTLEBLOCKS_COUNT/2; j++) {
                bricksCastle[index] = blocksSet.getSubimage(x + j*(BLOCK_WIDTH+1), y + i*(BLOCK_HEIGHT+1), BLOCK_WIDTH, BLOCK_HEIGHT);
                index++;
            }
        }
    }

    public void loadLucky(){
        int x = 298;
        int y = 78;

        for (int i = 0; i < LUCKYBLOCKS_COUNT; i++) {
            lucky[i] = blocksSet.getSubimage(x, y + i*(BLOCK_HEIGHT+1), BLOCK_WIDTH, BLOCK_HEIGHT);
        }
    }

    public void loadPipes(){
        int x = 119;
        int y = 196;
        int index = 0;

        for (int i = 0; i < PIPEBLOCKS_COUNT/2; i++) {
            for (int j = 0; j < PIPEBLOCKS_COUNT/2; j++) {
                pipes[index] = blocksSet.getSubimage(x + j*(BLOCK_WIDTH+1), y + i*(BLOCK_HEIGHT+1), BLOCK_WIDTH, BLOCK_HEIGHT);
                index++;
            }
        }
    }

    public BufferedImage[] getBricksCastle() {
        return bricksCastle;
    }

    public BufferedImage[] getLucky() {
        return lucky;
    }

    public BufferedImage[] getPipes() {
        return pipes;
    }

}
