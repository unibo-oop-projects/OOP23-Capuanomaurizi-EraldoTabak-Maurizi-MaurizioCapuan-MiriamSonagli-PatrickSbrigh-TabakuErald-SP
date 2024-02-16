package it.unibo.superpeach.player.graphics;

import java.awt.image.BufferedImage;

public class PlayerTexture {
    private static final int  NUM_SHEETS = 3;
    private static final String NORMAL_PATH = "it/unibo/superpeach/player/playerSprite.png";
    private static final String STAR_PATH = "it/unibo/superpeach/player/playerSpriteStar.png";
    private ImageLoader loader;
    private BufferedImage img;
    private BufferedImage[] playerImg;
    private int currentPost;

    public PlayerTexture(){
        currentPost = 0;
        playerImg = new BufferedImage[NUM_SHEETS*2];
        loader = new ImageLoader();
        load(NORMAL_PATH);
        load(STAR_PATH);
    }

    private void load(String path){
        try{
            img = loader.loadImage(path);
        } catch(Exception e){
            e.printStackTrace();
        }

        int x = 0;
        int y = 0;
        int width = 16;
        int height = 32;
        for(int i = 0; i< NUM_SHEETS; i++){
            playerImg[currentPost] = img.getSubimage(x+(i*(width+1)), y, width, height);
            currentPost++;
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

