package it.unibo.superpeach.blocks.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

    private BufferedImage img;

    public BufferedImage loadImage(String imgPath){
        try {
            img = ImageIO.read(new URL("C:\\Users\\mauri\\Desktop\\SuperPeach\\res\\BlocksTile.png")/*getClass().getResource(imgPath)*/);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}
