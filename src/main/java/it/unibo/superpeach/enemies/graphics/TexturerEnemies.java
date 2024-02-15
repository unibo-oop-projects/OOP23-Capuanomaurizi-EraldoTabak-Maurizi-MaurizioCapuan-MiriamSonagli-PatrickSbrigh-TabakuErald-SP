package it.unibo.superpeach.enemies.graphics;

import java.awt.image.BufferedImage;

public class TexturerEnemies {

    private static final int GOOMBA_COUNT = 1;
    private static final int KOOPATROOPA_COUNT = 2;

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

        goomba[0] = enemySet.getSubimage(0, 4, ENEMY_WIDTH, GOOMBA_HEIGHT);

    }

    private void loadKoopaTroopa() {

        koopaTroopa[0] = enemySet.getSubimage(180, 0, ENEMY_WIDTH, KOOPA_HEIGHT);
        koopaTroopa[1] = enemySet.getSubimage(211, 0, ENEMY_WIDTH, KOOPA_HEIGHT);

    }

    public BufferedImage[] getGoombaImg() {
        return this.goomba;
    }

    public BufferedImage[] getKoopaImg() {
        return this.koopaTroopa;
    }

}
