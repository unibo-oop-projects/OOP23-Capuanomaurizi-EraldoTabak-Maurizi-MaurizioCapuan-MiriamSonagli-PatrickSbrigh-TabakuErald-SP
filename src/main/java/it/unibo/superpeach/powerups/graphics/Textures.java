package it.unibo.superpeach.powerups.graphics;

import java.awt.image.BufferedImage;

public class Textures {
    
    private static final int POWERUPS_COUNT = 4;

    private static final int TILE_BLOCK_WIDTH = 16;
    private static final int TILE_BLOCK_HEIGHT = 16;

    private BufferedImageLoader loader;

    private BufferedImage blocksSet;

    public BufferedImage[] powerups;

    public Textures(){

        powerups = new BufferedImage[POWERUPS_COUNT];

        loader = new BufferedImageLoader();

        try {
            blocksSet = loader.loadImage("it/unibo/superpeach/tiles/powerups_tiles.png");
            loadPowerups();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPowerups(){

        powerups[0] = blocksSet.getSubimage(16, 32, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        powerups[1] = blocksSet.getSubimage(96, 96, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        powerups[2] = blocksSet.getSubimage(256, 32, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);
        powerups[3] = blocksSet.getSubimage(64, 16, TILE_BLOCK_WIDTH, TILE_BLOCK_HEIGHT);

    }

    public BufferedImage[] getPowerups() {
        return powerups;
    }

}

