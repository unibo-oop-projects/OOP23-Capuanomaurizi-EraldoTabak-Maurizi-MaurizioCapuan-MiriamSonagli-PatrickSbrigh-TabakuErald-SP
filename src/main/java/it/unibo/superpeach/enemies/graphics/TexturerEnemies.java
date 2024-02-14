package it.unibo.superpeach.enemies.graphics;

import java.awt.image.BufferedImage;

public class TexturerEnemies {

    private static final int GOOMBA_COUNT = 1;
    private static final int KOOPATROOPA_COUNT = 1;

    private static final int ENEMY_WIDTH = 16;
    private static final int GOOMBA_HEIGHT = 16;
    private static final int KOOPA_HEIGHT = 23;

    private BufferedImageLoaderEnemies loader;

    private BufferedImage enemySet;

    public BufferedImage[] goomba, koopaTroopa;

    public TexturerEnemies() {

        goomba = new BufferedImage[GOOMBA_COUNT];
        koopaTroopa = new BufferedImage[KOOPATROOPA_COUNT];

        loader = new BufferedImageLoaderEnemies();

        try {
            enemySet = loader.loadImage("it/unibo/superpeach/tiles/enemies.png");
            loadGoomba();
            loadKoopaTroopa();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGoomba() {
        int x = 0;
        int y = 4;

        for (int i = 0; i < GOOMBA_COUNT; i++) {
            goomba[i] = enemySet.getSubimage(x + i * ENEMY_WIDTH, y, ENEMY_WIDTH, GOOMBA_HEIGHT);
        }
    }

    private void loadKoopaTroopa() {
        int x = 180;
        int y = 0;

        for (int i = 0; i < KOOPATROOPA_COUNT; i++) {
            koopaTroopa[i] = enemySet.getSubimage(x + i * ENEMY_WIDTH, y, ENEMY_WIDTH, KOOPA_HEIGHT);
        }
    }

    public BufferedImage[] getGoombaImg() {
        return this.goomba;
    }

    public BufferedImage[] getKoopaImg() {
        return this.koopaTroopa;
    }

}
