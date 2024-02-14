package it.unibo.superpeach.enemies.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BufferedImageLoaderEnemies {

    private BufferedImage img;

    public BufferedImage loadImage(String imgPath) {
        try {
            img = ImageIO.read(ClassLoader.getSystemResource(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
