package it.unibo.superpeach.graphics;

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
    private static final int GOOMBA_COUNT = 1;
    private static final int KOOPATROOPA_COUNT = 2;
    private static final int FLYINGKOOPA_COUNT = 2;
    private static final int PEACH_COUNT = 9;
    private static final int POWERUPS_COUNT = 4;

    private static final int TILE_WIDTH = 16;
    private static final int TILE_HEIGHT = 16;
    private static final int KOOPA_HEIGHT = 23;

    private BufferedImageLoader loader;

    private BufferedImage tileSet;

    public BufferedImage[] terrain, lucky, scoreboard, pipe, cloud, bush, hill, flag;
    public BufferedImage[] goomba, koopaTroopa, flyingKoopa;
    public BufferedImage[] peach;
    public BufferedImage[] powerups;

    public Texturer() {

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

        loader = new BufferedImageLoader();

        try {
            tileSet = loader.loadImage("it/unibo/superpeach/tiles/blocks_tile.png");
            loadTerrain();
            loadLucky();
            loadScoreboard();
            loadPipe();
            loadCloud();
            loadBush();
            loadHill();
            loadFlag();
            tileSet = loader.loadImage("it/unibo/superpeach/tiles/enemies.png");
            loadGoomba();
            loadKoopaTroopa();
            loadFlyingKoopa();
            tileSet = loader.loadImage("it/unibo/superpeach/tiles/player_tile.png");
            loadPeach();
            tileSet = loader.loadImage("it/unibo/superpeach/tiles/powerups_tiles.png");
            loadPowerups();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPeach(){
        for(int i = 0; i < PEACH_COUNT; i++){
            peach[i] = tileSet.getSubimage((TILE_WIDTH+1)*i, 0, TILE_WIDTH, TILE_HEIGHT*2);
        }

    }

    private void loadTerrain() {
        int x = 0;
        int y = 16;
        int index = 0;

        for (int i = 0; i < BRICKSCASTLEBLOCKS_COUNT / 8; i++) {
            for (int j = 0; j < BRICKSCASTLEBLOCKS_COUNT / 2; j++) {
                terrain[index] = tileSet.getSubimage(x+j*(TILE_WIDTH+1), y+i*(TILE_HEIGHT+1),
                        TILE_WIDTH, TILE_HEIGHT);
                index++;
            }
        }
    }

    private void loadLucky() {
        lucky[0] = tileSet.getSubimage(298, 78, TILE_WIDTH, TILE_HEIGHT);
        lucky[1] = tileSet.getSubimage(349, 78, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadScoreboard(){
        scoreboard[0] = tileSet.getSubimage(315, 78, TILE_WIDTH, TILE_HEIGHT);
        scoreboard[1] = tileSet.getSubimage(315, 95, TILE_WIDTH, TILE_HEIGHT);
        scoreboard[2] = tileSet.getSubimage(298, 95, TILE_WIDTH, TILE_HEIGHT);
        scoreboard[3] = tileSet.getSubimage(332, 95, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadPipe() {
        pipe[0] = tileSet.getSubimage(119, 196, TILE_WIDTH, TILE_HEIGHT);
        pipe[1] = tileSet.getSubimage(136, 196, TILE_WIDTH, TILE_HEIGHT);
        pipe[2] = tileSet.getSubimage(119, 213, TILE_WIDTH, TILE_HEIGHT);
        pipe[3] = tileSet.getSubimage(136, 213, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadCloud() {
        int x = 298;
        int y = 16;
        int index = 0;

        for (int i = 0; i < CLOUDBLOCKS_COUNT / 3; i++) {
            for (int j = 0; j < CLOUDBLOCKS_COUNT / 2; j++) {
                cloud[index] = tileSet.getSubimage(x+j*(TILE_WIDTH+1), y+i*(TILE_HEIGHT+1),
                        TILE_WIDTH, TILE_HEIGHT);
                index++;
            }
        }
    }

    private void loadBush() {
        int x = 0;
        int y = 213;

        for (int i = 0; i < BUSHBLOCKS_COUNT; i++) {
            bush[i] = tileSet.getSubimage(x+i*(TILE_WIDTH+1), y, TILE_WIDTH, TILE_HEIGHT);
        }
    }

    private void loadHill() {
        hill[0] = tileSet.getSubimage(34, 230, TILE_WIDTH, TILE_HEIGHT);

        int x = 0;
        int y = 247;

        for (int i = 0; i < HILLBLOCKS_COUNT - 1; i++) {
            hill[i+1] = tileSet.getSubimage(x+i*(TILE_WIDTH+1), y, TILE_WIDTH, TILE_HEIGHT);
        }
    }

    private void loadFlag() {
        flag[0] = tileSet.getSubimage(136, 230, TILE_WIDTH, TILE_HEIGHT);
        flag[1] = tileSet.getSubimage(136, 247, TILE_WIDTH, TILE_HEIGHT);
        flag[2] = tileSet.getSubimage(119, 230, TILE_WIDTH, TILE_HEIGHT);
        flag[3] = tileSet.getSubimage(119, 247, TILE_WIDTH, TILE_HEIGHT);
    }

    private void loadGoomba() {
        goomba[0] = tileSet.getSubimage(0, 4, TILE_WIDTH, TILE_HEIGHT);
    }
    
    private void loadKoopaTroopa() {
        koopaTroopa[0] = tileSet.getSubimage(180, 0, TILE_WIDTH, KOOPA_HEIGHT);
        koopaTroopa[1] = tileSet.getSubimage(211, 0, TILE_WIDTH, KOOPA_HEIGHT);
    }
    
    private void loadFlyingKoopa() {
        flyingKoopa[0] = tileSet.getSubimage(120, 0, TILE_WIDTH, KOOPA_HEIGHT);
        flyingKoopa[1] = tileSet.getSubimage(271, 0, TILE_WIDTH, KOOPA_HEIGHT);
    }

    private void loadPowerups(){
        powerups[0] = tileSet.getSubimage(16, 32, TILE_WIDTH, TILE_HEIGHT);
        powerups[1] = tileSet.getSubimage(96, 96, TILE_WIDTH, TILE_HEIGHT);
        powerups[2] = tileSet.getSubimage(256, 32, TILE_WIDTH, TILE_HEIGHT);
        powerups[3] = tileSet.getSubimage(64, 16, TILE_WIDTH, TILE_HEIGHT);
    }


    public BufferedImage[] getPeach() {
        return peach;
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

    public BufferedImage[] getGoombaImg() {
        return this.goomba;
    }
    
    public BufferedImage[] getKoopaImg() {
         return this.koopaTroopa;
    }
    
    public BufferedImage[] getFlyingKoopa() {
        return this.flyingKoopa;
    }

    public BufferedImage[] getPowerups() {
        return powerups;
    }
}
