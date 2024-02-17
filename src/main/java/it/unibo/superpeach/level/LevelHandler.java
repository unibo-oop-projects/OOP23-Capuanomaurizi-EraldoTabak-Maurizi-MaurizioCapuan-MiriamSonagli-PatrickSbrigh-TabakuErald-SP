package it.unibo.superpeach.level;

import java.awt.image.BufferedImage;

import it.unibo.superpeach.gameobjects.blocks.BlockType;
import it.unibo.superpeach.gameobjects.blocks.BlocksHandler;
import it.unibo.superpeach.gameobjects.blocks.LuckyBlock;
import it.unibo.superpeach.gameobjects.blocks.MapBackgroundBlock;
import it.unibo.superpeach.gameobjects.blocks.MapFixedBlock;
import it.unibo.superpeach.gameobjects.enemies.EnemiesHandler;
import it.unibo.superpeach.gameobjects.enemies.FlyingKoopa;
import it.unibo.superpeach.gameobjects.enemies.Goomba;
import it.unibo.superpeach.gameobjects.enemies.KoopaTroopa;
import it.unibo.superpeach.gameobjects.powerups.PowerUp.PowerUpType;
import it.unibo.superpeach.graphics.BufferedImageLoader;

/**
 * Level generation class that matches each color of a pixel matrix with a particular map block or enemy.
 * @author Maurizio Capuano
 */
public final class LevelHandler {

    private BufferedImageLoader loader;
    private BufferedImage levelImage;
    private BlocksHandler blocksHandler;
    private int gameScale;

    private EnemiesHandler enemiesHandler;

    /**
     * Level handler constructor.
     * 
     * @param blocksHandler handler of game blocks which contains every block and can create them 
     * @param scale game scale
     * @param enemiesHandler handler of enemies entities which contains every enemy and can create them 
     */
    public LevelHandler(final BlocksHandler blocksHandler, final int scale, final EnemiesHandler enemiesHandler) {
        this.blocksHandler = blocksHandler;
        this.enemiesHandler = enemiesHandler;
        gameScale = scale;
        loader = new BufferedImageLoader();
    }

    /**
     * Methods that adds all blocks and enemies to their handlers which will then render them.
     */
    public void drawLevel() {
        parseLevel("it/unibo/superpeach/level/level_blocks.png");
    }

    private void parseLevel(final String lvlImagePath) {
        levelImage = loader.loadImage(lvlImagePath);
        int width = levelImage.getWidth();
        int height = levelImage.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                int pixel = levelImage.getRGB(i, j);
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = (pixel) & 0xff;

                if (!(r == 255 && g == 255 && b == 255)) {
                    if (r == 185 && g == 122 && b == 87) {
                        blocksHandler
                                .addFixedBlock(new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.BRICK));
                    } else if (r == 100 && g == 100 && b == 100) {
                        blocksHandler.addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.TERRAIN));
                    } else if (r == 0 && g == 0 && b == 0) {
                        blocksHandler
                                .addFixedBlock(new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.STONE));
                    } else if (r == 255 && g == 201 && b == 14) {
                        blocksHandler.addFixedBlock(
                                new LuckyBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.LUCKY, PowerUpType.COIN));
                    } else if (r == 255 && g == 202 && b == 14) {
                        blocksHandler.addFixedBlock(new LuckyBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.LUCKY,
                                PowerUpType.RED_MUSHROOM));
                    } else if (r == 255 && g == 203 && b == 14) {
                        blocksHandler.addFixedBlock(new LuckyBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.LUCKY,
                                PowerUpType.LIFE_MUSHROOM));
                    } else if (r == 255 && g == 204 && b == 14) {
                        blocksHandler.addFixedBlock(
                                new LuckyBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.LUCKY, PowerUpType.STAR));
                    } else if (r == 255 && g == 127 && b == 39) {
                        blocksHandler.addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.DEATH_BLOCK));
                    } else if (r == 127 && g == 127 && b == 127) {
                        blocksHandler.addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.ALT_BLOCK));
                    } else if (r == 100 && g == 100 && b == 50) { // FIXED BUT NON INTERACTABLE BLOCKS
                        blocksHandler.addBackgroundBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.TERRAIN));
                    } else if (r == 0 && g == 0 && b == 50) {
                        blocksHandler.addBackgroundBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.STONE));
                    } else if (r == 153 && g == 217 && b == 234) { // CLOUD PARSING
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CLOUD_TOP_LEFT));
                    } else if (r == 153 && g == 217 && b == 235) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CLOUD_TOP_MIDDLE));
                    } else if (r == 153 && g == 217 && b == 236) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CLOUD_TOP_RIGHT));
                    } else if (r == 153 && g == 217 && b == 237) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CLOUD_BOT_LEFT));
                    } else if (r == 153 && g == 217 && b == 238) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CLOUD_BOT_MIDDLE));
                    } else if (r == 153 && g == 217 && b == 239) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CLOUD_BOT_RIGHT));
                    } else if (r == 181 && g == 230 && b == 29) { // BUSH PARSING
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.BUSH_LEFT));
                    } else if (r == 181 && g == 231 && b == 29) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.BUSH_MIDDLE));
                    } else if (r == 181 && g == 232 && b == 29) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.BUSH_RIGHT));
                    } else if (r == 34 && g == 177 && b == 76) { // HILL PARSING
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.HILL_UP));
                    } else if (r == 34 && g == 178 && b == 76) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.HILL_BLANK));
                    } else if (r == 34 && g == 179 && b == 76) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.HILL_SPOTS1));
                    } else if (r == 34 && g == 180 && b == 76) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.HILL_SPOTS2));
                    } else if (r == 34 && g == 181 && b == 76) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.HILL_TOP));
                    } else if (r == 34 && g == 182 && b == 76) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.HILL_DOWN));
                    } else if (r == 237 && g == 28 && b == 36) { // PIPE PARSING
                        blocksHandler.addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.PIPE_TOP_LEFT));
                    } else if (r == 238 && g == 28 && b == 36) {
                        blocksHandler.addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.PIPE_TOP_RIGHT));
                    } else if (r == 239 && g == 28 && b == 36) {
                        blocksHandler.addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.PIPE_LEFT));
                    } else if (r == 240 && g == 28 && b == 36) {
                        blocksHandler.addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.PIPE_RIGHT));
                    } else if (r == 200 && g == 191 && b == 231) { // FLAG PARSING
                        blocksHandler.addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.FLAG_TIP));
                    } else if (r == 163 && g == 73 && b == 164) {
                        blocksHandler.addBackgroundBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.FLAG_POLE));
                    } else if (r == 163 && g == 73 && b == 165) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.FLAG_LEFT));
                    } else if (r == 163 && g == 73 && b == 166) {
                        blocksHandler.addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.FLAG_POLE));
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.FLAG_RIGHT));
                    } else if (r == 255 && g == 174 && b == 201) { // CASTLE PARSING
                        blocksHandler.addFixedBlock(
                                new MapFixedBlock(i * 16, (j - 1) * 16, 16, 16, gameScale, BlockType.CASTLE_DOOR_TOP));
                        blocksHandler.addFixedBlock(
                                new MapFixedBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CASTLE_DOOR_BOT));
                    } else if (r == 255 && g == 174 && b == 202) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CASTLE_BRICK));
                    } else if (r == 255 && g == 174 && b == 203) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CASTLE_BALCONY1));
                    } else if (r == 255 && g == 174 && b == 204) {
                        blocksHandler.addBackgroundBlock(
                                new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale, BlockType.CASTLE_BALCONY2));
                    } else if (r == 255 && g == 174 && b == 205) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale,
                                BlockType.CASTLE_WINDOW_LEFT));
                    } else if (r == 255 && g == 174 && b == 206) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i * 16, j * 16, 16, 16, gameScale,
                                BlockType.CASTLE_WINDOW_RIGHT));
                    } else if (r == 63 && g == 72 && b == 204) { // ENEMIES PARSING
                        enemiesHandler.addEnemy(new Goomba(i * 16, j * 16, 16, 16, gameScale, blocksHandler));
                    } else if (r == 64 && g == 72 && b == 204) {
                        enemiesHandler
                                .addEnemy(new KoopaTroopa(i * 16, (j - 1) * 16, 16, 23, gameScale, blocksHandler));
                    } else if (r == 65 && g == 72 && b == 204) {
                        enemiesHandler
                                .addEnemy(new FlyingKoopa(i * 16, (j - 1) * 16, 16, 23, gameScale, blocksHandler));
                    }
                }
            }
        }
    }
}
