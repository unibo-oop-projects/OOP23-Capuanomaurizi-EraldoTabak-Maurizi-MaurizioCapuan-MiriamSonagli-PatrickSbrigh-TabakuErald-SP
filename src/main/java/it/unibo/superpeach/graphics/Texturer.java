package it.unibo.superpeach.graphics;

import java.awt.image.BufferedImage;

/**
 * Sprites manager class, loads from resource files sprites to draw.
 * 
 * @author Maurizio Capuano
 */
public final class Texturer {

    private static final int BRICKSCASTLEBLOCKS_COUNT = 16;
    private static final int LUCKYBLOCKS_COUNT = 2;
    private static final int SCOREBOARD_COUNT = 4;
    private static final int PIPEBLOCKS_COUNT = 4;
    private static final int CLOUDBLOCKS_COUNT = 6;
    private static final int BUSHBLOCKS_COUNT = 3;
    private static final int HILLBLOCKS_COUNT = 6;
    private static final int FLAGBLOCKS_COUNT = 4;
    private static final int GOOMBA_COUNT = 1;
    private static final int KOOPATROOPA_COUNT = 2;
    private static final int FLYINGKOOPA_COUNT = 2;
    private static final int PEACH_COUNT = 9;
    private static final int POWERUPS_COUNT = 4;

    private static final int TILE_WIDTH = 16;
    private static final int TILE_HEIGHT = 16;
    private static final int KOOPA_HEIGHT = 23;

    private final BufferedImage blocksTileSet;
    private final BufferedImage enemiesTileSet;
    private final BufferedImage playerTileSet;
    private final BufferedImage powerupsTileSet;

    private BufferedImage[] terrain, lucky, scoreboard, pipe, cloud, bush, hill, flag;
    private BufferedImage[] goomba, koopaTroopa, flyingKoopa;
    private BufferedImage[] peach;
    private BufferedImage[] powerups;

    /**
     * Texturer construtctor which initializes images arrays and loads sprites
     * inside of them.
     */
    public Texturer() {

        final BufferedImageLoader loader = new BufferedImageLoader();

        terrain = new BufferedImage[BRICKSCASTLEBLOCKS_COUNT];
        lucky = new BufferedImage[LUCKYBLOCKS_COUNT];
        scoreboard = new BufferedImage[SCOREBOARD_COUNT];
        pipe = new BufferedImage[PIPEBLOCKS_COUNT];
        cloud = new BufferedImage[CLOUDBLOCKS_COUNT];
        bush = new BufferedImage[BUSHBLOCKS_COUNT];
        hill = new BufferedImage[HILLBLOCKS_COUNT];
        flag = new BufferedImage[FLAGBLOCKS_COUNT];

        goomba = new BufferedImage[GOOMBA_COUNT];
        koopaTroopa = new BufferedImage[KOOPATROOPA_COUNT];
        flyingKoopa = new BufferedImage[FLYINGKOOPA_COUNT];

        peach = new BufferedImage[PEACH_COUNT];

        powerups = new BufferedImage[POWERUPS_COUNT];

        blocksTileSet = loader.loadImage("it/unibo/superpeach/tiles/blocks_tile.png");
        enemiesTileSet = loader.loadImage("it/unibo/superpeach/tiles/enemies.png");
        playerTileSet = loader.loadImage("it/unibo/superpeach/tiles/player_tile.png");
        powerupsTileSet = loader.loadImage("it/unibo/superpeach/tiles/powerups_tiles.png");
        loadTerrain();
        loadLucky();
        loadScoreboard();
        loadPipe();
        loadCloud();
        loadBush();
        loadHill();
        loadFlag();
        loadGoomba();
        loadKoopaTroopa();
        loadFlyingKoopa();
        loadPeach();
        loadPowerups();

    }

    private void loadPeach() {
        for (int i = 0; i < PEACH_COUNT; i++) {
            peach[i] = playerTileSet.getSubimage((TILE_WIDTH + 1) * i, 0, TILE_WIDTH, TILE_HEIGHT * 2);
        }

    }

    private void loadTerrain() {
        final int x = 0;
        final int y = 16;
        int index = 0;

        for (int i = 0; i < BRICKSCASTLEBLOCKS_COUNT / 8; i++) {
            for (int j = 0; j < BRICKSCASTLEBLOCKS_COUNT / 2; j++) {
                terrain[index] = blocksTileSet.getSubimage(x + j * (TILE_WIDTH + 1), y + i * (TILE_HEIGHT + 1),
                        TILE_WIDTH, TILE_HEIGHT);
                index++;
            }
        }
    }

    private void loadLucky() {
        final int x1 = 298, x2 = 349, y = 78;
        lucky[0] = blocksTileSet.getSubimage(x1, y, TILE_WIDTH, TILE_HEIGHT);
        lucky[1] = blocksTileSet.getSubimage(x2, y, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadScoreboard() {
        final int x1 = 315, x2 = 298, x3 = 332, y1 = 78, y2 = 95;
        scoreboard[0] = blocksTileSet.getSubimage(x1, y1, TILE_WIDTH, TILE_HEIGHT);
        scoreboard[1] = blocksTileSet.getSubimage(x1, y2, TILE_WIDTH, TILE_HEIGHT);
        scoreboard[2] = blocksTileSet.getSubimage(x2, y2, TILE_WIDTH, TILE_HEIGHT);
        scoreboard[3] = blocksTileSet.getSubimage(x3, y2, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadPipe() {
        final int x1 = 119, x2 = 136, y1 = 196, y2 = 213;
        pipe[0] = blocksTileSet.getSubimage(x1, y1, TILE_WIDTH, TILE_HEIGHT);
        pipe[1] = blocksTileSet.getSubimage(x2, y1, TILE_WIDTH, TILE_HEIGHT);
        pipe[2] = blocksTileSet.getSubimage(x1, y2, TILE_WIDTH, TILE_HEIGHT);
        pipe[3] = blocksTileSet.getSubimage(x2, y2, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadCloud() {
        final int x = 298;
        final int y = 16;
        int index = 0;

        for (int i = 0; i < CLOUDBLOCKS_COUNT / 3; i++) {
            for (int j = 0; j < CLOUDBLOCKS_COUNT / 2; j++) {
                cloud[index] = blocksTileSet.getSubimage(x + j * (TILE_WIDTH + 1), y + i * (TILE_HEIGHT + 1),
                        TILE_WIDTH, TILE_HEIGHT);
                index++;
            }
        }
    }

    private void loadBush() {
        final int x = 0;
        final int y = 213;

        for (int i = 0; i < BUSHBLOCKS_COUNT; i++) {
            bush[i] = blocksTileSet.getSubimage(x + i * (TILE_WIDTH + 1), y, TILE_WIDTH, TILE_HEIGHT);
        }
    }

    private void loadHill() {
        final int x1 = 34, x2 = 0;
        final int y1 = 230, y2 = 247;

        hill[0] = blocksTileSet.getSubimage(x1, y1, TILE_WIDTH, TILE_HEIGHT);
        for (int i = 0; i < HILLBLOCKS_COUNT - 1; i++) {
            hill[i + 1] = blocksTileSet.getSubimage(x2 + i * (TILE_WIDTH + 1), y2, TILE_WIDTH, TILE_HEIGHT);
        }
    }

    private void loadFlag() {
        final int x1 = 136, x2 = 119, y1 = 230, y2 = 247;
        flag[0] = blocksTileSet.getSubimage(x1, y1, TILE_WIDTH, TILE_HEIGHT);
        flag[1] = blocksTileSet.getSubimage(x1, y2, TILE_WIDTH, TILE_HEIGHT);
        flag[2] = blocksTileSet.getSubimage(x2, y1, TILE_WIDTH, TILE_HEIGHT);
        flag[3] = blocksTileSet.getSubimage(x2, y2, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadGoomba() {
        goomba[0] = enemiesTileSet.getSubimage(0, 4, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadKoopaTroopa() {
        final int x1 = 180, x2 = 211;
        koopaTroopa[0] = enemiesTileSet.getSubimage(x1, 0, TILE_WIDTH, KOOPA_HEIGHT);
        koopaTroopa[1] = enemiesTileSet.getSubimage(x2, 0, TILE_WIDTH, KOOPA_HEIGHT);
    }

    private void loadFlyingKoopa() {
        final int x1 = 120, x2 = 271;
        flyingKoopa[0] = enemiesTileSet.getSubimage(x1, 0, TILE_WIDTH, KOOPA_HEIGHT);
        flyingKoopa[1] = enemiesTileSet.getSubimage(x2, 0, TILE_WIDTH, KOOPA_HEIGHT);
    }

    private void loadPowerups() {
        final int xy2 = 96, x3 = 256;
        powerups[0] = powerupsTileSet.getSubimage(16, 32, TILE_WIDTH, TILE_HEIGHT);
        powerups[1] = powerupsTileSet.getSubimage(xy2, xy2, TILE_WIDTH, TILE_HEIGHT);
        powerups[2] = powerupsTileSet.getSubimage(x3, 32, TILE_WIDTH, TILE_HEIGHT);
        powerups[3] = powerupsTileSet.getSubimage(64, 16, TILE_WIDTH, TILE_HEIGHT);
    }

    /**
     * @return peach sprites
     */
    public BufferedImage[] getPeach() {
        return peach.clone();
    }

    /**
     * @return bricks and ground sprites
     */
    public BufferedImage[] getTerrain() {
        return terrain.clone();
    }

    /**
     * @return lucky blocks sprites
     */
    public BufferedImage[] getLucky() {
        return lucky.clone();
    }

    /**
     * @return coins and hearts sprites
     */
    public BufferedImage[] getScoreboard() {
        return scoreboard.clone();
    }

    /**
     * @return pipes sprites
     */
    public BufferedImage[] getPipe() {
        return pipe.clone();
    }

    /**
     * @return cloud blocks sprites
     */
    public BufferedImage[] getCloud() {
        return cloud.clone();
    }

    /**
     * @return bush blocks sprites
     */
    public BufferedImage[] getBush() {
        return bush.clone();
    }

    /**
     * @return hills blocks sprites
     */
    public BufferedImage[] getHill() {
        return hill.clone();
    }

    /**
     * @return flag, flag pole and flag tip sprites
     */
    public BufferedImage[] getFlag() {
        return flag.clone();
    }

    /**
     * @return goomba (enemy mushroom) sprites
     */
    public BufferedImage[] getGoombaImg() {
        return goomba.clone();
    }

    /**
     * @return koopa (turtle) sprites
     */
    public BufferedImage[] getKoopaImg() {
        return koopaTroopa.clone();
    }

    /**
     * @return flying koopa (turtle) sprites
     */
    public BufferedImage[] getFlyingKoopa() {
        return flyingKoopa.clone();
    }

    /**
     * @return powerups sprites
     */
    public BufferedImage[] getPowerups() {
        return powerups.clone();
    }
}
