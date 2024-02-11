package it.unibo.superpeach.level;

import java.awt.image.BufferedImage;

import it.unibo.superpeach.blocks.BlocksHandler;
import it.unibo.superpeach.blocks.MapBackgroundBlock;
import it.unibo.superpeach.blocks.MapFixedBlock;
import it.unibo.superpeach.blocks.Block.BlockType;
import it.unibo.superpeach.blocks.graphics.BufferedImageLoader;

public class LevelHandler {

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    private BufferedImageLoader loader;
    private BufferedImage levelImage;
    private BlocksHandler blocksHandler;
    private int GAME_SCALE;

    public LevelHandler(BlocksHandler blocksHandler, int scale){
        this.blocksHandler = blocksHandler;
        GAME_SCALE = scale;
        loader = new BufferedImageLoader();
    }

    public void drawLevel(){
        setLevel("it"+FILE_SEPARATOR+"unibo"+FILE_SEPARATOR+"superpeach"+FILE_SEPARATOR+"level"+FILE_SEPARATOR+"level_blocks_image.png");
    }

    private void setLevel(String lvlImagePath) {
        levelImage = loader.loadImage(lvlImagePath);
        int width = levelImage.getWidth();
        int height = levelImage.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                int pixel = levelImage.getRGB(i, j);
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = (pixel) & 0xff;

                if(!(r == 255 && g == 255 && b == 255)){
                    if(r == 185 && g == 122 && b == 87){
                        blocksHandler.addBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.BRICK));
                    }
                    else if (r == 100 && g == 100 && b == 100){
                        blocksHandler.addBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.TERRAIN));
                    }
                    else if (r == 0 && g == 0 && b == 0){
                        blocksHandler.addBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.STONE));
                    }
                    else if (r == 255 && g == 201 && b == 14){
                        blocksHandler.addBlock(new MapFixedBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.LUCKY));
                    }
                    else if (r == 153 && g == 217 && b == 234){
                        blocksHandler.addBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.CLOUD));
                    }
                    else if (r == 181 && g == 230 && b == 29){
                        blocksHandler.addBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.BUSH));
                    }
                    else if (r == 34 && g == 177 && b == 76){
                        blocksHandler.addBlock(new MapBackgroundBlock(i*16, j*16, 16, 16, GAME_SCALE, BlockType.HILL));
                    }
                }

            }
        }
    }

}
