package it.unibo.superpeach.blocks.graphics;

import java.awt.image.BufferedImage;

public class Texturer {
    
    private static final int BRICKSCASTLEBLOCKS_COUNT = 16;
    private static final int LUCKYBLOCKS_COUNT = 2;
    private static final int PIPEBLOCKS_COUNT = 4;
    private static final int CLOUDBLOCKS_COUNT = 6;
    private static final int BUSHBLOCKS_COUNT = 3;
    private static final int HILLBLOCKS_COUNT = 6;

    private static final int TILE_BLOCK_WIDTH = 16;
    private static final int TILE_BLOCK_HEIGHT = 16;

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    private BufferedImageLoader loader;

    private BufferedImage blocksSet;

    public BufferedImage[] terrain, lucky, pipe, cloud, bush, hill;

    public Texturer(){

        terrain = new BufferedImage[BRICKSCASTLEBLOCKS_COUNT];
        lucky = new BufferedImage[LUCKYBLOCKS_COUNT];
        pipe = new BufferedImage[PIPEBLOCKS_COUNT];
        cloud = new BufferedImage[CLOUDBLOCKS_COUNT];
        bush = new BufferedImage[BUSHBLOCKS_COUNT];
        hill = new BufferedImage[HILLBLOCKS_COUNT];

        loader = new BufferedImageLoader();

        try {
            blocksSet = loader.loadImage("it"+FILE_SEPARATOR+"unibo"+FILE_SEPARATOR+"superpeach"+FILE_SEPARATOR+"tiles"+FILE_SEPARATOR+"BlocksTile.png");
            loadTerrain();
            loadLucky();
            loadPipe();
            loadCloud();
            loadBush();
            loadHill();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTerrain(){
        int x = 0;
        int y = 16;
        int index = 0;

        for (int i = 0; i < BRICKSCASTLEBLOCKS_COUNT/8; i++) {
            for (int j = 0; j < BRICKSCASTLEBLOCKS_COUNT/2; j++) {
                terrain[index] = blocksSet.getSubimage(x + j*(TILE_BLOCK_WIDTH+1), y + i*(TILE_BLOCK_HEIGHT+1), TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
                index++;
            }
        }
    }

    private void loadLucky(){
        int x = 298;
        int y = 78;

        for (int i = 0; i < LUCKYBLOCKS_COUNT; i++) {
            lucky[i] = blocksSet.getSubimage(x, y + i*(TILE_BLOCK_HEIGHT+1), TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        }
    }

    private void loadPipe(){
        int x = 119;
        int y = 196;
        int index = 0;

        for (int i = 0; i < PIPEBLOCKS_COUNT/2; i++) {
            for (int j = 0; j < PIPEBLOCKS_COUNT/2; j++) {
                pipe[index] = blocksSet.getSubimage(x + j*(TILE_BLOCK_WIDTH+1), y + i*(TILE_BLOCK_HEIGHT+1), TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
                index++;
            }
        }
    }

    private void loadCloud(){
        int x = 298;
        int y = 16;
        int index = 0;

        for (int i = 0; i < CLOUDBLOCKS_COUNT/3; i++) {
            for (int j = 0; j < CLOUDBLOCKS_COUNT/2; j++) {
                cloud[index] = blocksSet.getSubimage(x + j*(TILE_BLOCK_WIDTH+1), y + i*(TILE_BLOCK_HEIGHT+1), TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
                index++;
            }
        }
    }

    private void loadBush(){
        int x = 0;
        int y = 213;

        for (int i = 0; i < BUSHBLOCKS_COUNT; i++) {
            bush[i] = blocksSet.getSubimage(x + i*(TILE_BLOCK_WIDTH+1), y, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        }
    }

    private void loadHill(){
        hill[0] = blocksSet.getSubimage(34, 230, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);

        int x = 0;
        int y = 247;

        for (int i = 0; i < HILLBLOCKS_COUNT-1; i++) {
            hill[i+1] = blocksSet.getSubimage(x + i*(TILE_BLOCK_WIDTH+1), y, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        }
    }

    public BufferedImage[] getTerrain() {
        return terrain;
    }

    public BufferedImage[] getLucky() {
        return lucky;
    }

    public BufferedImage[] getPipe() {
        return pipe;
    }

    public BufferedImage[] getCloud() {
        return cloud;
    }

    public BufferedImage[] getBush() {
        return bush;
    }

    public BufferedImage[] getHill() {
        return hill;
    }

}
