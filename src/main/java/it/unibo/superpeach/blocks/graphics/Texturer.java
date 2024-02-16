package it.unibo.superpeach.blocks.graphics;

import java.awt.image.BufferedImage;

public class Texturer {

    private static final int BRICKSCASTLEBLOCKS_COUNT = 16;
    private static final int LUCKYBLOCKS_COUNT = 2;
    private static final int SCOREBOARD_COUNT = 4;
    private static final int PIPEBLOCKS_COUNT = 4;
    private static final int CLOUDBLOCKS_COUNT = 6;
    private static final int BUSHBLOCKS_COUNT = 3;
    private static final int HILLBLOCKS_COUNT = 6;
    private static final int FLAGBLOCKS_COUNT = 4;

    private static final int TILE_BLOCK_WIDTH = 16;
    private static final int TILE_BLOCK_HEIGHT = 16;

    private BufferedImageLoader loader;

    private BufferedImage blocksSet;

    public BufferedImage[] terrain, lucky, scoreboard, pipe, cloud, bush, hill, flag;

    public Texturer() {

        terrain = new BufferedImage[BRICKSCASTLEBLOCKS_COUNT];
        lucky = new BufferedImage[LUCKYBLOCKS_COUNT];
        scoreboard = new BufferedImage[SCOREBOARD_COUNT];
        pipe = new BufferedImage[PIPEBLOCKS_COUNT];
        cloud = new BufferedImage[CLOUDBLOCKS_COUNT];
        bush = new BufferedImage[BUSHBLOCKS_COUNT];
        hill = new BufferedImage[HILLBLOCKS_COUNT];
        flag = new BufferedImage[FLAGBLOCKS_COUNT];

        loader = new BufferedImageLoader();

        try {
            blocksSet = loader.loadImage("it/unibo/superpeach/tiles/blocks_tile.png");
            loadTerrain();
            loadLucky();
            loadScoreboard();
            loadPipe();
            loadCloud();
            loadBush();
            loadHill();
            loadFlag();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTerrain() {
        int x = 0;
        int y = 16;
        int index = 0;

        for (int i = 0; i < BRICKSCASTLEBLOCKS_COUNT / 8; i++) {
            for (int j = 0; j < BRICKSCASTLEBLOCKS_COUNT / 2; j++) {
                terrain[index] = blocksSet.getSubimage(x+j*(TILE_BLOCK_WIDTH+1), y+i*(TILE_BLOCK_HEIGHT+1),
                        TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
                index++;
            }
        }
    }

    private void loadLucky() {
        lucky[0] = blocksSet.getSubimage(298, 78, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        lucky[1] = blocksSet.getSubimage(349, 78, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
    }

    private void loadScoreboard(){
        scoreboard[0] = blocksSet.getSubimage(315, 78, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        scoreboard[1] = blocksSet.getSubimage(315, 95, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        scoreboard[2] = blocksSet.getSubimage(298, 95, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        scoreboard[3] = blocksSet.getSubimage(332, 95, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
    }

    private void loadPipe() {
        pipe[0] = blocksSet.getSubimage(119, 196, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        pipe[1] = blocksSet.getSubimage(136, 196, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        pipe[2] = blocksSet.getSubimage(119, 213, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        pipe[3] = blocksSet.getSubimage(136, 213, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
    }

    private void loadCloud() {
        int x = 298;
        int y = 16;
        int index = 0;

        for (int i = 0; i < CLOUDBLOCKS_COUNT / 3; i++) {
            for (int j = 0; j < CLOUDBLOCKS_COUNT / 2; j++) {
                cloud[index] = blocksSet.getSubimage(x+j*(TILE_BLOCK_WIDTH+1), y+i*(TILE_BLOCK_HEIGHT+1),
                        TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
                index++;
            }
        }
    }

    private void loadBush() {
        int x = 0;
        int y = 213;

        for (int i = 0; i < BUSHBLOCKS_COUNT; i++) {
            bush[i] = blocksSet.getSubimage(x+i*(TILE_BLOCK_WIDTH+1), y, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        }
    }

    private void loadHill() {
        hill[0] = blocksSet.getSubimage(34, 230, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);

        int x = 0;
        int y = 247;

        for (int i = 0; i < HILLBLOCKS_COUNT - 1; i++) {
            hill[i+1] = blocksSet.getSubimage(x+i*(TILE_BLOCK_WIDTH+1), y, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        }
    }

    private void loadFlag() {
        flag[0] = blocksSet.getSubimage(136, 230, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        flag[1] = blocksSet.getSubimage(136, 247, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        flag[2] = blocksSet.getSubimage(119, 230, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        flag[3] = blocksSet.getSubimage(119, 247, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
    }

    public BufferedImage[] getTerrain() {
        return terrain;
    }

    public BufferedImage[] getLucky() {
        return lucky;
    }

    public BufferedImage[] getScoreboard() {
        return scoreboard;
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

    public BufferedImage[] getFlag() {
        return flag;
    }
}
