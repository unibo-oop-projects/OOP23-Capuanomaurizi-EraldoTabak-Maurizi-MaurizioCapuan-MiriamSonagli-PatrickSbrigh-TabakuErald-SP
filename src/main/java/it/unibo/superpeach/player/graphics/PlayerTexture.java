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
        for(int i = 0; i< NUM_SHEETS; i++){
            playerImg[i] = img.getSubimage(x+(i*(width+1)), y, width, height);
        }

        //0 = 0-16
        //1 = 17-33
        //2 = 34-50
        //3 = 51-67
        //4 = 68-84
    }

    public BufferedImage[] getPlayerImage(){
        return playerImg;
    }
}

