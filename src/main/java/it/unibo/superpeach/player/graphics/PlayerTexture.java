package it.unibo.superpeach.player.graphics;

import java.awt.image.BufferedImage;

public class PlayerTexture {
    private static final int  NUM_SHEETS = 5;
    private static final String PATH = "it/unibo/superpeach/player/playerSprite.png";
    private ImageLoader loader;
    private BufferedImage img;
    private BufferedImage[] playerImg;

    public PlayerTexture(){
        playerImg = new BufferedImage[NUM_SHEETS];
        loader = new ImageLoader();
        load();
    }

    private void load(){
        try{
            img = loader.loadImage(PATH);
        } catch(Exception e){
            e.printStackTrace();
        }

        int x = 0;
        int y = 0;
        int width = 16;
        int height = 32;

        playerImg[0] = img.getSubimage(x, y, width, height);
    }

    public BufferedImage[] getPlayerImage(){
        return playerImg;
    }
}

