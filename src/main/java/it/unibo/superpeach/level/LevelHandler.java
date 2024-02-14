package it.unibo.superpeach.level;

import java.awt.image.BufferedImage;

import it.unibo.superpeach.blocks.BlocksHandler;
import it.unibo.superpeach.blocks.MapBackgroundBlock;
import it.unibo.superpeach.blocks.MapFixedBlock;
import it.unibo.superpeach.blocks.Block.BlockType;
import it.unibo.superpeach.blocks.graphics.BufferedImageLoader;
import it.unibo.superpeach.enemies.EnemiesHandler;
import it.unibo.superpeach.enemies.Goomba;
import it.unibo.superpeach.enemies.KoopaTroopa;

public class LevelHandler {

    private BufferedImageLoader loader;
    private BufferedImage levelImage;
    private BlocksHandler blocksHandler;
    private int GAME_SCALE;

    private EnemiesHandler enemiesHandler;

    public LevelHandler(BlocksHandler blocksHandler, int scale, EnemiesHandler enemiesHandler) {
        this.blocksHandler = blocksHandler;
        this.enemiesHandler = enemiesHandler;
        GAME_SCALE = scale;
        loader = new BufferedImageLoader();

    }

    public void drawLevel() {
        parseLevel("it/unibo/superpeach/level/level_blocks.png");
    }

    private void parseLevel(String lvlImagePath) {
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
                        blocksHandler.addFixedBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.BRICK));
                    } else if (r == 100 && g == 100 && b == 100) {
                        blocksHandler.addFixedBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.TERRAIN));
                    } else if (r == 0 && g == 0 && b == 0) {
                        blocksHandler.addFixedBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.STONE));
                    } else if (r == 255 && g == 201 && b == 14) {
                        blocksHandler.addFixedBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.LUCKY));
                    } else if (r == 255 && g == 127 && b == 39) {
                        blocksHandler.addFixedBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.DEATH_BLOCK));
                    }
                    //FIXED BUT NON INTERACTABLE BLOCKS
                    else if (r == 100 && g == 100 && b == 50) {
                        blocksHandler.addBackgroundBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.TERRAIN));
                    } else if (r == 0 && g == 0 && b == 50) {
                        blocksHandler.addBackgroundBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.STONE));
                    }
                    //CLOUD PARSING
                    else if (r == 153 && g == 217 && b == 234) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.CLOUD_TOP_LEFT));
                    } else if (r == 153 && g == 217 && b == 235) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.CLOUD_TOP_MIDDLE));
                    } else if (r == 153 && g == 217 && b == 236) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.CLOUD_TOP_RIGHT));
                    } else if (r == 153 && g == 217 && b == 237) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.CLOUD_BOT_LEFT));
                    } else if (r == 153 && g == 217 && b == 238) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.CLOUD_BOT_MIDDLE));
                    } else if (r == 153 && g == 217 && b == 239) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.CLOUD_BOT_RIGHT));
                    }
                    // BUSH PARSING
                    else if (r == 181 && g == 230 && b == 29) {
                        blocksHandler.addBackgroundBlock( new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.BUSH_LEFT));
                    } else if (r == 181 && g == 231 && b == 29) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.BUSH_MIDDLE));
                    } else if (r == 181 && g == 232 && b == 29) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.BUSH_RIGHT));
                    }
                    // HILL PARSING
                    else if (r == 34 && g == 177 && b == 76) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.HILL_UP));
                    } else if (r == 34 && g == 178 && b == 76) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.HILL_BLANK));
                    } else if (r == 34 && g == 179 && b == 76) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.HILL_SPOTS1));
                    } else if (r == 34 && g == 180 && b == 76) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.HILL_SPOTS2));
                    } else if (r == 34 && g == 181 && b == 76) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.HILL_TOP));
                    } else if (r == 34 && g == 182 && b == 76) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.HILL_DOWN));
                    }
                    // PIPE PARSING
                    else if (r == 237 && g == 28 && b == 36) {
                        blocksHandler.addFixedBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.PIPE_TOP_LEFT));
                    } else if (r == 238 && g == 28 && b == 36) {
                        blocksHandler.addFixedBlock( new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.PIPE_TOP_RIGHT));
                    } else if (r == 239 && g == 28 && b == 36) {
                        blocksHandler.addFixedBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.PIPE_LEFT));
                    } else if (r == 240 && g == 28 && b == 36) {
                        blocksHandler.addFixedBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.PIPE_RIGHT));
                    }
                    // FLAG PARSING
                    else if (r == 200 && g == 191 && b == 231) {
                        blocksHandler.addFixedBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.FLAG_TIP));
                    } else if (r == 163 && g == 73 && b == 164) {
                        blocksHandler.addBackgroundBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.FLAG_POLE));
                    } else if (r == 163 && g == 73 && b == 165) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.FLAG_LEFT));
                    } else if (r == 163 && g == 73 && b == 166) {
                        blocksHandler.addFixedBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.FLAG_POLE));
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.FLAG_RIGHT));
                    }
                    // CASTLE PARSING
                    else if (r == 255 && g == 174 && b == 201) {
                        blocksHandler.addFixedBlock(new MapFixedBlock(i*16, (j - 1)*16, 16, 16, GAME_SCALE, BlockType.CASTLE_DOOR_TOP));
                        blocksHandler.addFixedBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.CASTLE_DOOR_BOT));
                    } else if (r == 255 && g == 174 && b == 202) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.CASTLE_BRICK));
                    } else if (r == 255 && g == 174 && b == 203) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.CASTLE_BALCONY1));
                    } else if (r == 255 && g == 174 && b == 204) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.CASTLE_BALCONY2));
                    } else if (r == 255 && g == 174 && b == 205) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.CASTLE_WINDOW_LEFT));
                    } else if (r == 255 && g == 174 && b == 206) {
                        blocksHandler.addBackgroundBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.CASTLE_WINDOW_RIGHT));
                    }
                    //ENEMIES PARSING
                    else if (r == 63 && g == 72 && b == 204) {
                        enemiesHandler.addEnemy(new Goomba(i*16, j*16, 16, 16, GAME_SCALE, blocksHandler));
                    } else if (r == 64 && g == 72 && b == 204) {
                        enemiesHandler.addEnemy(new KoopaTroopa(i*16, (j-1)*16, 16, 23, GAME_SCALE, blocksHandler));
                    }
                }

            }
        }
    }

}
